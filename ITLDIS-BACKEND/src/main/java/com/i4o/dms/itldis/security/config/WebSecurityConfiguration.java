package com.i4o.dms.itldis.security.config;

import com.i4o.dms.itldis.security.jwt.AuthenticationTokenFilter;
import com.i4o.dms.itldis.security.jwt.SecurityDeniedHandler;
import com.i4o.dms.itldis.security.jwt.SecurityUnauthorisedHandler;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityUnauthorisedHandler unauthorizedHandler;

    @Autowired
    private SecurityDeniedHandler securityDeniedHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(withDefaults())	//It uses the CORS configuration written in GlobalCORSConfig class
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(handling -> handling.authenticationEntryPoint(this.unauthorizedHandler)
                        .accessDeniedHandler(this.securityDeniedHandler))
                .sessionManagement(management -> management
                		.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(requests -> requests
                		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		                .antMatchers("/v2/api-docs", "/swagger-resources", "/swagger-resources/configuration/ui",
		                        "/swagger-resources/configuration/service").permitAll()
		                .antMatchers("/", "/favicon.ico", "/**/*.png", "/**/*.PNG", "/**/*.jfif", "/**/*.JFIF", "/**/*.gif",
		                        "/**/*.GIF", "/**/*.svg", "/**/*.SVG", "/**/*.jpg", "/**/*.JPG", "/**/*.jpeg", "/**/*.JPEG",
		                        "/**/*.mp4", "/**/*.MP4", "/**/*.ogg", "/**/*.wmv", "/**/*.mpeg", "/**/*.mp3", "/**/*.wav",
		                        "/**/*.html", "/**/*.css", "/**/*.js", "/**/*.ttf", "/**/*.pdf", "/**/*.PDF").permitAll()
		                .antMatchers("/api/kubotauser/login").permitAll()
		                .antMatchers("/api/kubotauser/forgotPassword").permitAll()
		                .antMatchers("/api/files").permitAll()
		                .antMatchers("/api/version/**").permitAll()
		                // Camunda BPM Webapps and REST API access
		                // Note: In production, these should be secured with proper authentication
		                .antMatchers("/camunda/**", "/app/**", "/api/engine/**", "/rest/**").permitAll()
		                .anyRequest().fullyAuthenticated())
                .headers(headers -> headers
                		.contentTypeOptions(withDefaults())
                		.xssProtection(withDefaults())
                		.cacheControl(withDefaults())
                		.httpStrictTransportSecurity(withDefaults())
                		.frameOptions(withDefaults()));
        
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}


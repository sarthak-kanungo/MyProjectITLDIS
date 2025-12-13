package com.i4o.dms.kubota.configurations;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author suraj.gaur
 * @implNote implemented on 04-10-2024 for setting response header for each request. Requested by client in VAPT points.
 */
@Configuration
public class GlobalCORSConfig {
	
	/**
	 * @author suraj.gaur
	 * @implNote configuring prevention of origins, headers and methods at the time of responding/serving
	 * the response to client.
	 * @return CorsConfigurationSource
	 */
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        configuration.setAllowedOrigins(Constants.ALLOWED_ORIGINS);
        configuration.setAllowCredentials(true);
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowedHeaders(Arrays.asList("Accept", "Origin", "Content-Type", "Depth", "User-Agent",
                "If-Modified-Since", "Cache-Control", "Authorization", "X-Req", "X-File-Size", "X-Requested-With",
                "X-File-Name", "X-CROSSORIGIN-AUTH-USR", "X-CROSSORIGIN-AUTH-PSW", "X-CROSSORIGIN-CNHI"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

	/**
	 * @author suraj.gaur
	 * @implNote configuring prevention of origins, headers and methods at the time of a client 
	 * requests an API.  
	 * @return CorsFilter
	 */
	@Bean
    CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }
	
}

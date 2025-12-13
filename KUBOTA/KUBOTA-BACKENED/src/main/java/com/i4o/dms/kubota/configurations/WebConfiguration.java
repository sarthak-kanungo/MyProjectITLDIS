package com.i4o.dms.kubota.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.i4o.dms.kubota.common.service.ImageCompressNLimiterInterceptor;

/**
 * @author suraj.gaur
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
	@Value("${spring.servlet.multipart.max-file-size}")
	String maxFileSize;
	@Value("${spring.servlet.multipart.max-request-size}")
	String maxRequestSize;

	/**
	 * @author suraj.gaur
	 * @return CommonsMultipartResolver
	 * @implNote This configuration is done for testing the file size upload limitation in the application.
	 *   It need a dependency(commons-fileupload) for successfully execute the required action.
	 *   This implementation is used in this API: "/api/spares/purchase/discrepancyClaim/testing"
	 */
//	@Bean
//	CommonsMultipartResolver commonsMultipartResolver() {
//		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//		commonsMultipartResolver.setMaxUploadSize(DataSize.parse(maxRequestSize).toBytes());
//		commonsMultipartResolver.setMaxUploadSizePerFile(DataSize.parse(maxFileSize).toBytes());
//		return commonsMultipartResolver;
//	}
	
	@Bean
	MultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}

    @Bean
    ImageCompressNLimiterInterceptor imageComprNLimiterInterceptor() {
        return new ImageCompressNLimiterInterceptor();
    }
	
	/**
	 * @implNote Intercepter configuration for compression images files
	 */
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(imageComprNLimiterInterceptor());
    }

}

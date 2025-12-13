package com.i4o.dms.kubota;

import com.i4o.dms.kubota.storage.StorageProperties;
import com.i4o.dms.kubota.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
@EnableSwagger2
public class KubotaApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(KubotaApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
       return application.sources(KubotaApplication.class);
    }
}


package com.i4o.dms.kubota.configurations;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JaksonConfiguration {

	@Autowired
    public void JacksonConfiguration(ObjectMapper objectMapper){
        objectMapper.setTimeZone(TimeZone.getDefault());
    }
}

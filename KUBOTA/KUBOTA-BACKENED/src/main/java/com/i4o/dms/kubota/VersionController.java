package com.i4o.dms.kubota;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/version")
public class VersionController {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @GetMapping
    public ResponseEntity<?> version() {
        Map<String,Object> map = new HashMap<>();
        map.put("dataSourceUrl",dataSourceUrl);
        return ResponseEntity.ok(map);
    }

    @Value("${message.default.welcome}")
    private String welcomeMessage;

    @RequestMapping("/welcome")
    public String welcome() {
        return welcomeMessage;
    }
}

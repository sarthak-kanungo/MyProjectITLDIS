package com.i4o.dms.kubota.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class SecurityDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("exce "+accessDeniedException);
        ApiResponse apiResponse = new ApiResponse();//(403, "Access Denied", false);
        apiResponse.setStatus(403);
        apiResponse.setMessage("Access Denied");
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, new ResponseEntity<>(apiResponse,HttpStatus.FORBIDDEN));
        outputStream.flush();
    }
}

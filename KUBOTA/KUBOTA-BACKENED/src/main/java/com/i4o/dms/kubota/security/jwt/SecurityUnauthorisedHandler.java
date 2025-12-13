package com.i4o.dms.kubota.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


@Component
public class SecurityUnauthorisedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
        ApiResponse apiResponse = new ApiResponse();//(401, "Unauthorized. Token is expired", false);
    	httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    	httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        apiResponse.setMessage("Unauthorized. Token is expired");
        OutputStream outputStream = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, apiResponse);
        outputStream.flush();
       // httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
       /* ObjectMapper objectMapper = new ObjectMapper();

        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put(
                "timestamp",
                Calendar.getInstance().getTime());
        data.put(
                "exception",
                e.getMessage());

        httpServletResponse.getOutputStream()
                .println(objectMapper.writeValueAsString(data));*/
    }


    @ExceptionHandler (value = {AccessDeniedException.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AccessDeniedException accessDeniedException) throws IOException {
        // 403
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization Failed AuthenticationEntryPoint: " + accessDeniedException.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         Exception exception) throws IOException {
        // 500
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error AuthenticationEntryPoint: " + exception.getMessage());
    }
}
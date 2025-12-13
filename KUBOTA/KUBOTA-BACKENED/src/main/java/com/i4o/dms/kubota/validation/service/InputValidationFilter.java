package com.i4o.dms.kubota.validation.service;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.i4o.dms.kubota.configurations.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author suraj.gaur
 */
@Component
public class InputValidationFilter implements Filter {
    // Regular expression to allow only alphanumeric characters, forward slash, dash, and space
	private static final Pattern SPECIAL_CHARACTERS = Pattern.compile("^[a-zA-Z0-9/\\\\\\- \\{\\}\\[\\]:,@\".\\s]*$");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
//        // Bypassing validation for multi-part requests (file uploads, etc.)
//        if (ServletFileUpload.isMultipartContent(httpRequest)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // Allowed specific URLs to not check their inputs for validation. 
//        StringBuffer reqUrl = httpRequest.getRequestURL();
//        for(String link: Constants.ALLOWED_URL) {
//        	if(reqUrl.toString().contains(link)) {
//        		chain.doFilter(request, response);
//        		return; // Preventing further processing
//        	}
//        }
//        
//        // Wrapping the RequestBody (InputStream)
//        CustomHttpRequestWrapper wrappedRequest = new CustomHttpRequestWrapper(httpRequest);
//        try {
//        	// Validating Request Parameters (RequestParam and QueryParam)
//            validateRequestParams(httpRequest);
//            
//            // Validating the RequestBody (InputStream)
//            String requestBody = wrappedRequest.getBody();
//            if (requestBody != null && !requestBody.isEmpty())
//                validateRequestBody(requestBody);
//            
//        } catch (Exception e) {
//            // Handling the custom validation exception
//            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//            
//            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            httpServletResponse.getWriter().write("Invalid request: " + e.getMessage());
//            
//            return; // Preventing further processing
//        }
//
//        chain.doFilter(wrappedRequest, response);
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}

    /**
     * @implNote Validation method for Request Parameters
     * @param request
     * @throws ServletException
     */
    private void validateRequestParams(HttpServletRequest request) throws ServletException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (String param : parameterMap.keySet()) {
            String[] values = parameterMap.get(param);
            for (String value : values) {
                if (!SPECIAL_CHARACTERS.matcher(value).matches()) {
                    throw new ServletException("Invalid characters found in input: " + param + " with the value: " + value);
                }
            }
        }
    }

    /**
     * @implNote Validation method for RequestBody content
     * @param body
     * @throws ServletException
     */
    private void validateRequestBody(String body) throws ServletException {
        if (!SPECIAL_CHARACTERS.matcher(body).matches()) {
            throw new ServletException("Invalid characters found in the provided input.");
        }
    }

    /**
     * @implNote Custom HttpServletRequestWrapper to allow reading of RequestBody multiple times
     */
    private static class CustomHttpRequestWrapper extends HttpServletRequestWrapper {
        private final String body;

        public CustomHttpRequestWrapper(HttpServletRequest request) throws IOException {
            super(request);
            InputStream inputStream = request.getInputStream();
            this.body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        }

        public String getBody() {
            return this.body;
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
            return new DelegatingServletInputStream(byteArrayInputStream);
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }
    }

    /**
     * @implNote Helper class to allow delegation of InputStream 
     */
    private static class DelegatingServletInputStream extends ServletInputStream {
        private final InputStream sourceStream;

        public DelegatingServletInputStream(InputStream sourceStream) {
            this.sourceStream = sourceStream;
        }

        @Override
        public int read() throws IOException {
            return sourceStream.read();
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {}
    }
}

package com.modernapp.auth.dto;

import java.util.List;

public class LoginResponse {
    private String token;
    private String type = "Bearer";
    private UserInfo user;
    private Long expiresIn;
    
    public static class UserInfo {
        private String id;
        private String username;
        private String email;
        private List<String> roles;
        
        public UserInfo(String id, String username, String email, List<String> roles) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.roles = roles;
        }
        
        // Getters and Setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public List<String> getRoles() { return roles; }
        public void setRoles(List<String> roles) { this.roles = roles; }
    }

    public LoginResponse() {}

    public LoginResponse(String token, Long id, String username, String email, List<String> roles, Long expiresIn) {
        this.token = token;
        this.user = new UserInfo(String.valueOf(id), username, email, roles);
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}


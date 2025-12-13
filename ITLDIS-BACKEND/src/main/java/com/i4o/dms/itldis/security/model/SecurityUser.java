package com.i4o.dms.itldis.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 *
 */
@Getter
@Setter
public class SecurityUser implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Long dealerEmployeeId;
    private Long dealerId;
    private Long kubotaEmployeeId;
    private Boolean managementAccess;
    private Long branchId;
    private Collection<? extends GrantedAuthority> authorities;
    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean credentialsNonExpired = true;
    private Boolean enabled = true;


    public SecurityUser() {
        super();
    }

    public SecurityUser(Long id, String password, String username, Long dealerEmployeeId, Long dealerId,
                        Long kubotaEmployeeId,Long branchId,Boolean managementAccess, Collection<? extends GrantedAuthority> authorities) {
        this.setId(id);
        this.setUsername(username);
        this.setEnabled(password.equals("Y"));
        this.setDealerEmployeeId(dealerEmployeeId);
        this.setDealerId(dealerId);
        this.setBranchId(branchId);
        this.setKubotaEmployeeId(kubotaEmployeeId);
        this.setManagementAccess(managementAccess);
        this.setAuthorities(authorities);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @JsonIgnore
    public Boolean getAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.getAccountNonExpired();
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @JsonIgnore
    public Boolean getAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getAccountNonLocked();
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @JsonIgnore
    public Boolean getCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.getCredentialsNonExpired();
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @JsonIgnore
    public Boolean getEnabled() {
        return this.enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.getEnabled();
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}

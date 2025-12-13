package com.i4o.dms.itldis.masters.usermanagement.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String username;
    private String password;
    private Boolean appLogin = false;
}

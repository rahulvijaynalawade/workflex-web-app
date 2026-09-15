package com.workflex.user.dto.response;

import com.workflex.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private Long userId;

    private String fullName;

    private String email;

    private Role role;

    private String token;

    private String message;

}

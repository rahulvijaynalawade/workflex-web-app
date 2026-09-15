package com.workflex.user.dto.request;

import com.workflex.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNumber;

    @NotNull
    private Role role;
}
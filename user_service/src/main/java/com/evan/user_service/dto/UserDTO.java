package com.evan.user_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "UserDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Schema(
            description = "User Email"
    )
    @Email
    @NotEmpty(message = "Email shou not be null or empty")
    private String email;

    @Schema(
            description = "User username"
    )
    @NotEmpty(message = "Username should not be null or empty")
    private String username;

    @Schema(
            description = "User Password"
    )
    @NotEmpty(message = "password should not null or empty")
    private String password;

    @Schema(
            description = "User Role"
    )
    @NotNull(message = "role should not be null")
    private int role;
}


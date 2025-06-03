package com.mateus.lima.health_genda.adapters.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "Full name is required and must not be blank.")
    @Length(min = 6,max = 150,message = "Full name must be between 6 and 150 characters.")
    private String fullName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotBlank(message = "Password is required")
    @Length(min = 6, max = 150, message = "Password must be between 6 and 150 characters")
    private String password;


}

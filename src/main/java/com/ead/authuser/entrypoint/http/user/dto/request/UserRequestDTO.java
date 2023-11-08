package com.ead.authuser.entrypoint.http.user.dto.request;

import com.ead.authuser.validation.UsernameConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDTO {

    @NotBlank
    @Size(min = 4, max = 50)
    @UsernameConstraint
    private String username;
    @NotBlank
    @Email
    @JsonView
    private String email;
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
    private String fullName;
    private String phoneNumber;
    @CPF
    private String cpf;
    @NotBlank
    private String imageUrl;
}

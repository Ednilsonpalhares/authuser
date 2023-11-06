package com.ead.authuser.entrypoint.http.user.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {
    
    private String username;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String cpf;
    private String imageUrl;
}

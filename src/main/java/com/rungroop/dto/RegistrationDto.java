package com.rungroop.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationDto {
    private Long id ;
    @NotEmpty
    private String username ;
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min = 6 , max = 20 , message = "password is min 6 max 20")
    private String password ;
}

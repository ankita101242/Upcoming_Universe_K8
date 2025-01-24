package com.example.backend.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String name;
    private String email;
    private String password;
    private String about;
    private Integer userId;

}
package ru.otus.spring.davlks.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long id;

    private String username;

    private String password;

    private long roleNameId;

}

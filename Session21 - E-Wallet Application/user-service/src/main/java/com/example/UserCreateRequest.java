package com.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private UserIdentifier userIdentifier;

    @NotBlank
    private String identifierValue;

    public User toUser(){
        return User.builder()
                .name(this.name).phone(this.phone)
                .password(this.password).email(this.email)
                .userIdentifier(this.userIdentifier)
                .identifierValue(this.identifierValue)
                .build();
    }
}

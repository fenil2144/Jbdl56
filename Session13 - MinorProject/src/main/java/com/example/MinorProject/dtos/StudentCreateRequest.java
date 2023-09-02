package com.example.MinorProject.dtos;

import com.example.MinorProject.enums.AccountStatus;
import com.example.MinorProject.models.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
public class StudentCreateRequest {

    @NotBlank
    private String name;
    private String email;

    @NotBlank
    private String contact;

    private String address;

    public Student toStudent() {
        return Student.builder()
                .address(address)
                .contact(contact)
                .email(email)
                .name(name)
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }


}

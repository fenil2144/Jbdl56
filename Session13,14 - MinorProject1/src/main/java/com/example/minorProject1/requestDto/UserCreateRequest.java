package com.example.minorProject1.requestDto;

import com.example.minorProject1.models.Admin;
import com.example.minorProject1.models.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequest {

    private String username;
    private String password;
    private String authority;
    private Student student;
    private Admin admin;
}

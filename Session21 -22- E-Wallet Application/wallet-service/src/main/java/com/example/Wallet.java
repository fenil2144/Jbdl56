package com.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Long userId;

    private String phone;

    private Double balance;

    private String identifierValue;

    @Enumerated(value = EnumType.STRING)
    private UserIdentifier userIdentifier;
}

package com.br.luggycar.api.requests;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResquest {

    private String status;
    private String name;
    private String lastName;
    private String cpf;
    private String email;
    private String gender;
    private Date date;
    private LocalDate registration;
}
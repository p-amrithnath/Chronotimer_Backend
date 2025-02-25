package com.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;	

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Roles are mandatory")
    private String roles;

    private String empDesg;

    @PositiveOrZero(message = "Salary must be positive or zero")
    private BigDecimal salary;

    @PastOrPresent(message = "Date of joining must be in the past or present")
    private LocalDate doj;

    private String departmentName;
    private String empType;

    @PositiveOrZero(message = "Years of experience must be positive or zero")
    private double yearsOfExperience;  
    
    @ElementCollection
    private List<String> skillset;  
}
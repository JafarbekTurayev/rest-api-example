package com.example.restapiexample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDTO {
    @NotNull(message = "nomini ber iltimos!")
    private String name;
    @NotBlank(message = "phone berish kerak!")
    private String phone;
    @NotEmpty(message = "qanisan!")
    private String home;
    private String street;
    private String city;
}

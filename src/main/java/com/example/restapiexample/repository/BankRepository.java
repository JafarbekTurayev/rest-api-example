package com.example.restapiexample.repository;


import com.example.restapiexample.entity.Address;
import com.example.restapiexample.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankRepository extends JpaRepository<Bank, Integer> {
    boolean existsByPhone(String phone);
}

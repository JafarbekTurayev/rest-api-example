package com.example.restapiexample.repository;


import com.example.restapiexample.entity.Address;
import com.example.restapiexample.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
}

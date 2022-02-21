package com.example.restapiexample.service;

import com.example.restapiexample.dto.ApiResponse;
import com.example.restapiexample.dto.BankDTO;
import com.example.restapiexample.entity.Address;
import com.example.restapiexample.entity.Bank;
import com.example.restapiexample.repository.AddressRepository;
import com.example.restapiexample.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    @Autowired
    BankRepository bankRepository;
    @Autowired
    AddressRepository addressRepository;

    public List<Bank> getAll() {
        return bankRepository.findAll();
    }

    public Optional<Bank> getOne(Integer id) {
        return bankRepository.findById(id);
    }

    public ApiResponse add(BankDTO dto) {
        Bank bank = new Bank();
        bank.setName(dto.getName());
        bank.setPhone(dto.getPhone());

        Address address = new Address();
        address.setCity(dto.getCity());
        address.setHome(dto.getHome());
        address.setStreet(dto.getStreet());
        Address save = addressRepository.save(address);
        bank.setAddress(save);
        bankRepository.save(bank);
        return new ApiResponse("Mana", true);
    }
}

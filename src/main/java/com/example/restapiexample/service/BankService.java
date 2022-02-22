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

    public ApiResponse edit(Integer id, BankDTO dto) {
        Optional<Bank> bankOptional = bankRepository.findById(id);
        Bank edit = bankOptional.get();
        if (bankRepository.existsByPhone(dto.getPhone())) return new ApiResponse("Bunday nomer yes!", false);

        Address editAddress = edit.getAddress();
        editAddress.setStreet(dto.getStreet());
        editAddress.setHome(dto.getHome());
        editAddress.setCity(dto.getCity());

        edit.setPhone(dto.getPhone());
        edit.setName(dto.getName());
        edit.setAddress(editAddress);
        bankRepository.save(edit);
        return new ApiResponse("Edited!", true);
    }

    public ApiResponse delete(Integer id) {
//       1-variant
//        if (!bankRepository.existsById(id)) return new ApiResponse("NOt found!",false);
//        bankRepository.deleteById(id);

        //2-variant
        Optional<Bank> optionalBank = bankRepository.findById(id);
        Bank bank = optionalBank.get();
        bankRepository.delete(bank);
        return new ApiResponse("Deleted!", true, bank);
    }
}

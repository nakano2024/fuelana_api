package com.example.fuleana.service;

import com.example.fuleana.entity.Purpose;
import com.example.fuleana.repository.PurposeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PurposeServiceImpl implements PurposeService{

    @Autowired
    PurposeRepository purposeRepository;

    @Override
    public Purpose getPurposeByName(String name) {
        String trimmedName = name.trim();
        Purpose purpose = purposeRepository.findByName(trimmedName)
                .orElseThrow(()->new EntityNotFoundException("Purpose Not Found with Name : " + trimmedName));
        return purpose;
    }
}

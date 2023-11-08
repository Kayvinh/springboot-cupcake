package com.kevthedev.springboot.cupcake.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevthedev.springboot.cupcake.model.Cupcake;
import com.kevthedev.springboot.cupcake.repository.CupcakeRepository;


@Service
public class CupcakeService {
    //private List<Object> db = new ArrayList<>();

    private final CupcakeRepository cupcakeRepository;

    @Autowired
    public CupcakeService(CupcakeRepository cupcakeRepository) {
        this.cupcakeRepository = cupcakeRepository;
    }

    // Create all methods for cupcakeService
    public List<Cupcake> getAllCupcakes() {
        return cupcakeRepository.findAll();
    }

    public Cupcake getCupcake(long id) {
        return cupcakeRepository.findById(id).orElse(null);
    }
    
}

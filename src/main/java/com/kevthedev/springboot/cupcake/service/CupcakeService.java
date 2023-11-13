package com.kevthedev.springboot.cupcake.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kevthedev.springboot.cupcake.model.Cupcake;
import com.kevthedev.springboot.cupcake.repository.CupcakeRepository;


@Service
public class CupcakeService {
    private final CupcakeRepository cupcakeRepository;

    @Autowired
    public CupcakeService(CupcakeRepository cupcakeRepository) {
        this.cupcakeRepository = cupcakeRepository;
    }

    // Create methods for controller:
    public List<Cupcake> getAllCupcakes() {
        return cupcakeRepository.findAll();
    }

    public Cupcake getCupcake(long id) {
        Cupcake cupcake =  cupcakeRepository.findById(id).orElse(null);

        if(cupcake == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


        return cupcake;
    }

    public Cupcake createCupcake(Cupcake newCupcake) {
        Cupcake cupcake = new Cupcake();

        cupcake.setFlavor(newCupcake.getFlavor());
        cupcake.setSize(newCupcake.getSize());
        cupcake.setRating(newCupcake.getRating());
        cupcake.setImage(newCupcake.getImage());

        return cupcakeRepository.save(cupcake);
    }

    public Cupcake updateCupcake(Long id, Cupcake updatedCupcake) {
        Cupcake cupcake = cupcakeRepository.findById(id).orElse(null);

        if(cupcake == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        cupcake.setFlavor(updatedCupcake.getFlavor());
        cupcake.setSize(updatedCupcake.getSize());
        cupcake.setRating(updatedCupcake.getRating());
        cupcake.setImage(updatedCupcake.getImage());

        return cupcakeRepository.save(cupcake);
    }

    public void deleteCupcake(long id) {
        try {
            cupcakeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cupcake not found", e);
        }
    }
    
}

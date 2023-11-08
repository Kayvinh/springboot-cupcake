package com.kevthedev.springboot.cupcake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kevthedev.springboot.cupcake.model.Cupcake;


public interface CupcakeRepository extends JpaRepository<Cupcake, Long> {
    // Methods like findAll, findById, save should be here
}

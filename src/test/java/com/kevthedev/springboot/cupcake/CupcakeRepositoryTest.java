package com.kevthedev.springboot.cupcake;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.kevthedev.springboot.cupcake.model.Cupcake;
import com.kevthedev.springboot.cupcake.repository.CupcakeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:postgresql://localhost:${DB_PORT}/${TEST_DATABASE_NAME}",
    "spring.datasource.username=${DB_USERNAME}",
    "spring.datasource.password=${DB_PASSWORD}"
})
public class CupcakeRepositoryTest {

    @Autowired
    private CupcakeRepository cupcakeRepository;

    @Test
    public void testGetAllCupcakes() {
        List<Cupcake> cupcakes = Arrays.asList(
            new Cupcake("Chocolate", "Large", 4, "https://example.com/chocolate.jpg"),
            new Cupcake("Vanilla", "Medium", 5, "https://example.com/vanilla.jpg")
        );

        // Mock repository response when findAll is called
        cupcakeRepository.saveAll(cupcakes);

        // Retrieve cupcakes from the repository
        List<Cupcake> response = cupcakeRepository.findAll();

        // Assertions
        assertEquals(cupcakes.size(), response.size());
        assertEquals("Chocolate", response.get(0).getFlavor());
    }

    @Test
    public void testSaveAndFindById() {
        // Save an entity
        Cupcake cupcake = new Cupcake("Strawberry", "Small", 3, "https://example.com/strawberry.jpg");
        cupcakeRepository.save(cupcake);

        // Find the saved entity by ID
        Optional<Cupcake> foundCupcake = cupcakeRepository.findById(cupcake.getId());

        assertTrue(foundCupcake.isPresent());
        assertEquals("Strawberry", foundCupcake.get().getFlavor());
        assertEquals("Small", foundCupcake.get().getSize());
        assertEquals(3, foundCupcake.get().getRating());
        assertEquals("https://example.com/strawberry.jpg", foundCupcake.get().getImage());
    }

    @Test
    public void testUpdateCupcake() {

        Cupcake cupcake = new Cupcake("ToUpdate", "Medium", 2, "https://example.com/to-update.jpg");
        cupcakeRepository.save(cupcake);

        Cupcake updatedCupcake = new Cupcake("UpdatedFlavor", "UpdatedSize", 4, "https://example.com/updated.jpg");

        Optional<Cupcake> foundCupcake = cupcakeRepository.findById(cupcake.getId());
        foundCupcake.ifPresent(existingCupcake -> {
            existingCupcake.setFlavor(updatedCupcake.getFlavor());
            existingCupcake.setSize(updatedCupcake.getSize());
            existingCupcake.setRating(updatedCupcake.getRating());
            existingCupcake.setImage(updatedCupcake.getImage());

            cupcakeRepository.save(existingCupcake);
        });

        Optional<Cupcake> updatedCupcakeOptional = cupcakeRepository.findById(cupcake.getId());
        assertTrue(updatedCupcakeOptional.isPresent());
        Cupcake result = updatedCupcakeOptional.get();

        assertEquals(updatedCupcake.getFlavor(), result.getFlavor());
        assertEquals(updatedCupcake.getSize(), result.getSize());
        assertEquals(updatedCupcake.getRating(), result.getRating());
        assertEquals(updatedCupcake.getImage(), result.getImage());

    }

    @Test
    public void testDeleteCupcake() {

        Cupcake cupcake = new Cupcake("DeleteMe", "Small", 3, "https://example.com/delete-me.jpg");
        cupcakeRepository.save(cupcake);

        cupcakeRepository.deleteById(cupcake.getId());

        Optional<Cupcake> foundCupcake = cupcakeRepository.findById(cupcake.getId());
        assertFalse(foundCupcake.isPresent());
    }
}

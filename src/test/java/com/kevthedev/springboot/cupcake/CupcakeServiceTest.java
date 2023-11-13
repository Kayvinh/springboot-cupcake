package com.kevthedev.springboot.cupcake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.server.ResponseStatusException;

import com.kevthedev.springboot.cupcake.model.Cupcake;
import com.kevthedev.springboot.cupcake.repository.CupcakeRepository;
import com.kevthedev.springboot.cupcake.service.CupcakeService;

public class CupcakeServiceTest {
    private CupcakeRepository cupcakeRepository;
    private CupcakeService cupcakeService;
    private static long cupcakeId;

    @BeforeEach
    public void setUp() {
        cupcakeRepository = mock(CupcakeRepository.class);
        cupcakeService = new CupcakeService(cupcakeRepository);
    }
    
    @BeforeAll
    public static void setId() {
        cupcakeId = 1;
    }

    @Test
    public void testGetAllCupcakes() {
        Cupcake cupcake1 = new Cupcake();
        Cupcake cupcake2 = new Cupcake();
        List<Cupcake> cupcakes = Arrays.asList(cupcake1, cupcake2);

        when(cupcakeRepository.findAll()).thenReturn(cupcakes);

        List<Cupcake> result = cupcakeService.getAllCupcakes();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetCupcake() {
        Cupcake cupcake = new Cupcake();
        when(cupcakeRepository.findById(cupcakeId)).thenReturn(Optional.of(cupcake));

        Cupcake result = cupcakeService.getCupcake(cupcakeId);

        assertNotNull(result);
    }

    @Test
    public void testGetCupcake_NotFound() {
        when(cupcakeRepository.findById(cupcakeId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> cupcakeService.getCupcake(cupcakeId));
    }

     @Test
    public void testCreateCupcake() {
        Cupcake newCupcake = new Cupcake();
        when(cupcakeRepository.save(any(Cupcake.class))).thenReturn(newCupcake);

        Cupcake result = cupcakeService.createCupcake(newCupcake);

        assertNotNull(result);
    }

    @Test
    public void testUpdateCupcake() {
        Cupcake existingCupcake = new Cupcake();
        Cupcake updatedCupcake = new Cupcake();
        when(cupcakeRepository.findById(cupcakeId)).thenReturn(Optional.of(existingCupcake));
        when(cupcakeRepository.save(any(Cupcake.class))).thenReturn(updatedCupcake);

        Cupcake result = cupcakeService.updateCupcake(cupcakeId, updatedCupcake);

        assertNotNull(result);
    }

    @Test
    public void testUpdateCupcake_NotFound() {
        when(cupcakeRepository.findById(cupcakeId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> cupcakeService.updateCupcake(cupcakeId, new Cupcake()));
    }

     @Test
    public void testDeleteCupcake() {
        cupcakeService.deleteCupcake(cupcakeId);

        verify(cupcakeRepository, times(1)).deleteById(cupcakeId);
    }

    @Test
    public void testDeleteCupcake_NotFound() {
        doThrow(new EmptyResultDataAccessException(1)).when(cupcakeRepository).deleteById(cupcakeId);

        assertThrows(ResponseStatusException.class, () -> cupcakeService.deleteCupcake(cupcakeId));
    }
}

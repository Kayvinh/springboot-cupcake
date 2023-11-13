package com.kevthedev.springboot.cupcake;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.kevthedev.springboot.cupcake.model.Cupcake;
import com.kevthedev.springboot.cupcake.service.CupcakeService;

@WebMvcTest(CupcakeController.class)
public class CupcakeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CupcakeService cupcakeService;

    @InjectMocks
    private CupcakeController cupcakeController;

    @Test
    public void testGetCupcakes() throws Exception {
        when(cupcakeService.getAllCupcakes()).thenReturn(Arrays.asList(new Cupcake(), new Cupcake()));

        mockMvc.perform(MockMvcRequestBuilders.get("/cupcakes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetCupcake() throws Exception {
        long cupcakeId = 1;
        when(cupcakeService.getCupcake(cupcakeId)).thenReturn(new Cupcake());

        mockMvc.perform(MockMvcRequestBuilders.get("/cupcakes/{id}", cupcakeId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreateCupcake() throws Exception {
        Cupcake newCupcake = new Cupcake();
        when(cupcakeService.createCupcake(newCupcake)).thenReturn(newCupcake);

        mockMvc.perform(MockMvcRequestBuilders.post("/cupcakes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateCupcake() throws Exception {
        long cupcakeId = 1;
        Cupcake updatedCupcake = new Cupcake();
        when(cupcakeService.updateCupcake(cupcakeId, updatedCupcake)).thenReturn(updatedCupcake);

        mockMvc.perform(MockMvcRequestBuilders.patch("/cupcakes/{id}", cupcakeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteCupcake() throws Exception {
        long cupcakeId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/cupcakes/{id}", cupcakeId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(cupcakeService, times(1)).deleteCupcake(cupcakeId);
    }
}

package com.kevthedev.springboot.cupcake;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kevthedev.springboot.cupcake.service.CupcakeService;
import com.kevthedev.springboot.cupcake.model.Cupcake;
import java.util.List;

@RestController
public class CupcakeController {

    private final CupcakeService cupcakeService;

    public CupcakeController(CupcakeService cupcakeService) {
        this.cupcakeService = cupcakeService;
    }

    /*
     * Route for all cupcakes
     * Returns {}
     */
    @GetMapping("/cupcakes")
    public List<Cupcake> getCupcakes() {
        return cupcakeService.getAllCupcakes();
    }

    /**
     * Route for single cupcake by id
     * @param id 
     * @return {Cupcake}
     */
    @GetMapping("/cupcakes/{id}")
    public Cupcake getCupcake(@PathVariable long id) {
        return cupcakeService.getCupcake(id);
    }
    
}

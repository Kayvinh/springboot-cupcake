package com.kevthedev.springboot.cupcake;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * Returns [{id, flavor, size, rating, image}]
     */
    @GetMapping("/cupcakes")
    public List<Cupcake> getCupcakes() {
        return cupcakeService.getAllCupcakes();
    }

    /**
     * Route for single cupcake by id
     * Returns {id, flavor, size, rating, image}
     */
    @GetMapping("/cupcakes/{id}")
    public Cupcake getCupcake(@PathVariable Long id) {
        return cupcakeService.getCupcake(id);
    }

    @PostMapping("/cupcakes")
    public Cupcake createCupcake(@RequestBody Cupcake newCupcake) {
        return cupcakeService.createCupcake(newCupcake);
    }

    @PatchMapping("/cupcakes/{id}")
    public ResponseEntity<Cupcake> updateCupcake(@PathVariable Long id, @RequestBody Cupcake updatedCupcake) {
        Cupcake cupcake = cupcakeService.updateCupcake(id, updatedCupcake);
        return ResponseEntity.ok(cupcake);
    }

    @DeleteMapping("/cupcakes/{id}")
    public void deleteCupcake(@PathVariable Long id) {
        cupcakeService.deleteCupcake(id);
    }
}

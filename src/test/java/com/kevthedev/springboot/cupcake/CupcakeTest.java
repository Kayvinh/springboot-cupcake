package com.kevthedev.springboot.cupcake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.kevthedev.springboot.cupcake.model.Cupcake;

class CupcakeTest {
    Cupcake cupcake = new Cupcake("chocolate", "small", 3, "image1");

    @Test
    void testConstructor() {
        assertEquals(cupcake.getFlavor(), "chocolate");
        assertEquals(cupcake.getSize(), "small");
        assertEquals(cupcake.getRating(), 3);
        assertEquals(cupcake.getImage(), "image1");
    }

    @Test
    public void testGettersAndSetters() {
        Cupcake cupcake = new Cupcake();
        cupcake.setFlavor("Vanilla");
        cupcake.setSize("Small");
        cupcake.setRating(4);
        cupcake.setImage("https://example.com/vanilla-cupcake.jpg");
    
        assertEquals("Vanilla", cupcake.getFlavor());
        assertEquals("Small", cupcake.getSize());
        assertEquals(4, cupcake.getRating());
        assertEquals("https://example.com/vanilla-cupcake.jpg", cupcake.getImage());
    }

    @Test
    public void testDefaultImage() {
        Cupcake cupcake2 = new Cupcake("Strawberry", "Large", 4, null);
        assertEquals("https://tinyurl.com/demo-cupcake", cupcake2.getImage());
    }
}

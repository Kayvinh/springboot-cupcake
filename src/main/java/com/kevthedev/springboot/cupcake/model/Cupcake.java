package com.kevthedev.springboot.cupcake.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cupcakes")
public class Cupcake {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private Long id;

    @Column(nullable = false)
    private String flavor;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private String image;

    // Default image URL
    private static final String DEFAULT_IMAGE_URL = "https://tinyurl.com/demo-cupcake";


    public Cupcake() {
        this.image = DEFAULT_IMAGE_URL;
    }

    public Cupcake(String flavor, String size, int rating, String image) {
        this.flavor = flavor;
        this.size = size;
        this.rating = rating;
        this.image = (image == null) ? DEFAULT_IMAGE_URL : image;
    }

    public Long getId() {
        return this.id;
    }

    public String getFlavor() {
        return this.flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
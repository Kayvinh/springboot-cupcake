package com.kevthedev.springboot.cupcake.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cupcakes")
public class Cupcake {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private long id;

    @Column(nullable = false)
    private String flavor;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private String image = "https://tinyurl.com/demo-cupcake";

    public Cupcake() {}

    public Cupcake(String flavor, String size, int rating, String image) {
        this.flavor = flavor;
        this.size = size;
        this.rating = rating;
        this.image = image;
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
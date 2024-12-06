package com.example.foodorderapplication.model;

import jakarta.persistence.*;

@Entity
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private double price;

    protected Food() {}

    public Food(String foodName, double price) {
        this.foodName = foodName;
        this.price = price;
    }

    //Getter
    public Long getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getPrice() {
        return price;
    }

    //Setter
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

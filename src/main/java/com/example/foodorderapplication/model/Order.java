package com.example.foodorderapplication.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @Column(nullable = false)
    private Integer count;

    protected Order() {}

    public Order(Long orderId, Food food, User user, Integer count) {
        this.orderId = orderId;
        this.user = user;
        this.food = food;
        this.count = count;
    }

    //GETTER
    public Long getId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public Food getFood() {
        return food;
    }

    public Integer getCount() {
        return count;
    }

    //SETTER
    public void setUser(User user) {
        this.user = user;
    }

    public void setFood(Food food){
        this.food = food;
    }

    public void setCount(Integer count){
        this.count = count;
    }

}

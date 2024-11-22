package com.example.foodorderapplication.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
    private List<Order> orders;

    protected User() {}

    public User(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    //Getter
    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    //Setter
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}


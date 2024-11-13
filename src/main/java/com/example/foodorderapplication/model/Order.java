package com.example.foodorderapplication.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
    private Integer count;

    public Order() {}

    public Order(Long id, String menuName, Integer count) {
        this.id = id;
        this.menuName = menuName;
        this.count = count;
    }

    //GETTER
    public Long getId() {
        return id;
    }
    public String getMenuName() {
        return menuName;
    }
    public Integer getCount() {
        return count;
    }

    //SETTER
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public void setCount(Integer count) {
        this.count = count;
    }

}

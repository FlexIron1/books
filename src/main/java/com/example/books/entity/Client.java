package com.example.books.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Client {
    public Client() {
    }
    public Client(Long id, int number, Set<Orders> orders) {
        this.id = id;
        this.number = number;
        this.orders = orders;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private int number;
    @NotFound(
            action = NotFoundAction.IGNORE)
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_orders")
    private Set<Orders> orders;

    public Long getId(Long clientId) {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }
}


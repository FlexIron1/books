package com.example.books.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Orders {
    public Orders() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    String name;
    @Column(name = "Order_Creation_Date")
    private Date Order_Creation_Date;
    @Column(name = "Order_Execution_Date")
    private Date Order_Execution_Date;
    @Column(name = "Order_Execution_Flag")
    private Boolean OrderExecutionFlag;

    {
        OrderExecutionFlag = false;
    }

    @OneToMany( mappedBy = "orders",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Client> clients;

    @OneToMany( mappedBy = "ordersList",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Books> books;


    public Orders(String name, Date order_Creation_Date, Date order_Execution_Date,
                  Boolean orderExecutionFlag, Set<Client> clients, Set<Books> books) {
        this.name = name;
        Order_Creation_Date = order_Creation_Date;
        Order_Execution_Date = order_Execution_Date;
        OrderExecutionFlag = orderExecutionFlag;
        this.clients = clients;
        this.books = books;
    }

    public Long getId() {
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
//
//    public int getOrder_Creation_Date() {
//        return Order_Creation_Date;
//    }

//    public void setOrder_Creation_Date(int order_Creation_Date) {
//        Order_Creation_Date = order_Creation_Date;
//    }

//    public int getOrder_Execution_Date() {
//        return Order_Execution_Date;
//    }
//
//    public void setOrder_Execution_Date(int order_Execution_Date) {
//        Order_Execution_Date = order_Execution_Date;
//    }

    public Boolean getOrderExecutionFlag() {
        return OrderExecutionFlag;
    }

    public void setOrderExecutionFlag(Boolean orderExecutionFlag) {
        OrderExecutionFlag = orderExecutionFlag;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Books> getBooks() {
        return books;
    }

    public void setBooks(Set<Books> books) {
        this.books = books;
    }

    public Date getOrder_Creation_Date() {
        return Order_Creation_Date;
    }

    public void setOrder_Creation_Date(Date order_Creation_Date) {
        Order_Creation_Date = order_Creation_Date;
    }

    public Date getOrder_Execution_Date() {
        return Order_Execution_Date;
    }

    public void setOrder_Execution_Date(Date order_Execution_Date) {
        Order_Execution_Date = order_Execution_Date;
    }
}


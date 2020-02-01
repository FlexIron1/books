package com.example.books.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "orders")
@ApiModel(description = "Заказы")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "Order_Creation_Date")
    private Date Order_Creation_Date;
    @Column(name = "Order_Execution_Date")
    private Date Order_Execution_Date;
    @Column(name = "Order_Execution_Flag")
    private Boolean OrderExecutionFlag;
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private  Client clients;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Books books;

    public Orders() {
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

    public Date getOrder_Creation_Date() {
        return Order_Creation_Date;
    }

    public void setOrder_Creation_Date(Date order_Creation_Date) {
        Order_Creation_Date = order_Creation_Date;
    }

    public Date getOrder_Execution_Date() {
        return Order_Execution_Date;
    }

    public Boolean getOrderExecutionFlag() {
        return OrderExecutionFlag;
    }

    public void setOrderExecutionFlag(Boolean orderExecutionFlag) {
        OrderExecutionFlag = orderExecutionFlag;
    }

    public Client getClients() {
        return clients;
    }

    public void setClients(Client clients) {
        this.clients = clients;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public void setOrder_Execution_Date(Date order_Execution_Date) {
        Order_Execution_Date = order_Execution_Date;
    }

    public void orderExecution(long id) {
        setId(id);
        setOrderExecutionFlag(true);

    }


}


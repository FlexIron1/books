package com.example.books.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
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

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Client> clients;

    @OneToMany(mappedBy = "ordersList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Books> books;


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

    public Boolean getOrderExecutionFlag() {
        return OrderExecutionFlag;
    }

    public void setOrderExecutionFlag(Boolean orderExecutionFlag)
    {
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

    public void orderExecution(long id) {
setId(id);
        setOrderExecutionFlag(true);

    }


}


package com.example.books.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    String name;
    @Column(name = "Order_Creation_Date")
    private int Order_Creation_Date;
    @Column(name = "Order_Execution_Date")
    private int Order_Execution_Date;
    @Column(name = "Order_Execution_Flag")
    private Boolean OrderExecutionFlag;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "client_id")
//    private Client client;
  @JsonIgnoreProperties("orders")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinTable(
            name = "orders_client",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "clients_id")
    )
    List<Client> clientList;

//    @JsonIgnoreProperties("books")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinTable(
            name = "orders_books",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "books_id")
    )
   // @ManyToMany(mappedBy = "ordersList",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    List<Books> booksList;


    public Long getId(long id) {
        return this.id;
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

    public int getOrder_Creation_Date() {
        return Order_Creation_Date;
    }

    public void setOrder_Creation_Date(int order_Creation_Date) {
        Order_Creation_Date = order_Creation_Date;
    }

    public int getOrder_Execution_Date() {
        return Order_Execution_Date;
    }

    public void setOrder_Execution_Date(int order_Execution_Date) {
        Order_Execution_Date = order_Execution_Date;
    }

    public Boolean getOrderExecutionFlag() {
        return OrderExecutionFlag;
    }

    public void setOrderExecutionFlag(Boolean orderExecutionFlag) {
        OrderExecutionFlag = orderExecutionFlag;
    }
//
//    public List<Client> getClientList() {
//        return clientList;
//    }
//
//    public void setClientList(List<Client> clientList) {
//        this.clientList = clientList;
//    }

//    public Client getClient() {
//        return client;
//    }
//
//    public void setClient(Client client) {
//        this.client = client;
//    }

    public List<Books> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Books> booksList) {
        this.booksList = booksList;
    }

    public Long getId() {
        return id;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
}


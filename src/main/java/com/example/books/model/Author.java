package com.example.books.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Author")

@ApiModel(description = "Все подробности об авторе")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "Имя")
    @ApiModelProperty("Имя автора")
    private String name;
    @Column(name= "Фамилия")
    @ApiModelProperty("Фамилия автора")
    private String firstName;
    @ApiModelProperty("Дата рождения")
    @Column
    private int years;

    @JsonIgnoreProperties("Authors")
    @ManyToMany( cascade = {CascadeType.ALL}
    )
    @JoinTable(name = "authors_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "books_id")
    )
    private Set<Books> booksList;

    public Author() {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public Set<Books> getBooksList() {
        return booksList;
    }

     void setBooksList(Set<Books> booksList) {
        this.booksList = booksList;
    }


}

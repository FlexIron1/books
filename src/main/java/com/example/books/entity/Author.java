package com.example.books.entity;


import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Author")

@ApiModel(description = "Все подробности об авторе")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Author implements Serializable {
    public Author() {
    }

    public Author(String name, String firstName, int years, List<Books> booksList) {
        this.name = name;
        this.firstName = firstName;
        this.years = years;
        this.booksList = booksList;
    }

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
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}
    )
    @JoinTable(name = "authors_books",
            joinColumns = @JoinColumn(name = "books_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )

    private List<Books> booksList;
//  @JsonBackReference


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

    public List<Books> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Books> booksList) {
        this.booksList = booksList;
    }

    public void addBooks(Books books) {
        if (booksList == null) {
            booksList = new ArrayList<>();
            booksList.add(books);
        }
    }

    public void removeBooks(Books books) {
        if (booksList != null) {
            getBooksList().remove(books);
            books.setAuthorList(null);
        }
    }


}

package com.example.books.entity;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@ApiModel(description = "Все подробности о книге")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id;
    @ApiModelProperty("Название книги")
    @Column(name = "name")
    private String name;
    @ApiModelProperty("Год издания")
    @Column(name = "years")
    private int years;

    @JsonIgnoreProperties("book")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "books_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
//    @JsonManagedReference
    List<Author> authorList;
    @JsonIgnoreProperties("books")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinTable(
            name = "orders_books",
            joinColumns = @JoinColumn(name = "books_id"),
            inverseJoinColumns = @JoinColumn(name = "orders_id")
    )
    List<Orders> ordersList;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public void addAuthors(Author author) {
        if (authorList == null) {
            authorList = new ArrayList<>();
            authorList.add(author);
        }
    }

    public void removeAuthors(Author author) {
        getAuthorList().remove(author);
        author.setBooksList(null);
    }
}

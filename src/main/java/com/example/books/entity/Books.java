package com.example.books.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
@ApiModel(description = "Все подробности о книге")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Books {
    public Books() {
    }

    public Books(String name, String annotation, Date publishedAt,
                 int years, List<Author> authorList, Orders ordersList) {
        this.name = name;
        this.annotation = annotation;
        this.publishedAt = publishedAt;
        this.years = years;
        this.authorList = authorList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id;
    @ApiModelProperty("Название книги")
    @Column(name = "name")
    private String name;
    @ApiModelProperty("Аннотации")
    @Column(name = "annotation")
    private String annotation;
    @Column(name = "published_at")
    private Date publishedAt;
    @ApiModelProperty("Год издания")
    @Column(name = "years")
    private int years;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "books_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authorList;

    public Long getId(Long bookId) {
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


    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Long getId() {
        return Id;
    }
}

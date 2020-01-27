package com.example.books.entity;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
        this.ordersList = ordersList;
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
    @NotFound(
            action = NotFoundAction.IGNORE)
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private Orders ordersList;

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

    public void addAuthors(Author author) {
        if (authorList == null) {
            authorList = new ArrayList() {
            };
            authorList.add(author);
        }
    }

    public void removeAuthors(Author author) {
        getAuthorList().remove(author);
        author.setBooksList(null);
    }

    public void setOrdersList(Orders ordersList) {
        this.ordersList = ordersList;
    }

    public Orders getOrdersList() {
        return ordersList;
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
}

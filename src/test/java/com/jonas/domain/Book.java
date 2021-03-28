package com.jonas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Jonas created 28/03/2021
 */
@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String authorName;
    private String text;

    @JsonIgnore
    @ManyToOne //Anotacao para associacao 
    @JoinColumn(name = "categoria_id")
    private Category categories;

    //COSNTRUCTOR
    public Book() {
    }

    //COSNTRUCTOR
    public Book(Integer id, String title, String authorName, String text, Category categories) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.text = text;
        this.categories = categories;
    }

    //EQUALS AND HASHCODE
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    //GET AND SET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

}

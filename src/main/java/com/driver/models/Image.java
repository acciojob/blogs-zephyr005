package com.driver.models;

import javax.persistence.*;

@Entity
@Table(name ="image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private String dimensions;

    public Image() {
    }

    public Image(String description,String dimensions) {
        this.description = description;
        this.dimensions = dimensions;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public int getId() {
        return id;
    }

    // image is child of blog with many to one relation
    @ManyToOne
    @JoinColumn
    private Blog blog;

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public void setId(int id) {
        this.id = id;
    }
}
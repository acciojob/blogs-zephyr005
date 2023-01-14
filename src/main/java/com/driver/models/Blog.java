package com.driver.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;


    private Date pubDate;

    public Blog(){

    }

    public Blog(int id ,String title,String content, Date date,List<Image> imageList){
        this.id = id;
        this.imageList = imageList;
        this.title = title;
        this.content = content;
        this.pubDate = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    //    as blog and user have parent child relation M to One
    @ManyToOne
    @JoinColumn
    private User user;

    // blog is parent of image with one to many relation
    @OneToMany(mappedBy = "blog",cascade = CascadeType.ALL)
    private List<Image> imageList;

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
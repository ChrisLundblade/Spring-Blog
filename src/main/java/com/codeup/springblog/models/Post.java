package com.codeup.springblog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="posts")
public class Post {
    public Post(){

    }

    public Post(String title, String body){
        this.title = title;
        this.body = body;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT NOT NULL", nullable = false)
    private String body;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostImage> images;

    public String getBody() {
        return body;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<PostImage> getImages() {
        return images;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImages(List<PostImage> images) {
        this.images = images;
    }

    public void setId(long id) {
        this.id = id;
    }
}
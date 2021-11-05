package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;

    public PostImage(){
    }

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    public PostImage(String url){
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Post getPost() {
        return post;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

package com.pozoriche.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    private Long id;

    @Column
    private String imageURL;

    @ManyToMany(mappedBy = "images")
    private List<Page> pages;

    public Image(){}

    public void setPage(Page page){
        this.pages.add(page);
    }

    public Image(String imageURL){
        this.imageURL = imageURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

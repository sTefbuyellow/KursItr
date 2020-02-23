package com.pozoriche.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pozoriche.service.Bonus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.awt.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class PageDto {

    private Long Id;
    private String name;
    private String tag;
    private String username;
    private List bonusList;
    private List categories;
    private String money;
    private String youTubeVideo;
    private List images;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endingDate;

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUsername() {
        return username;
    }

    public List getBonusList() {
        return bonusList;
    }

    public void setBonusList(List bonusList) {
        this.bonusList = bonusList;
    }

    public List getCategories() {
        return categories;
    }

    public void setCategories(List categories) {
        this.categories = categories;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getYouTubeVideo() {
        return youTubeVideo;
    }

    public void setYouTubeVideo(String youTubeVideo) {
        this.youTubeVideo = youTubeVideo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List getImages() {
        return images;
    }

    public void setImages(List images) {
        this.images = images;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDateTime endingDate) {
        this.endingDate = endingDate;
    }
}

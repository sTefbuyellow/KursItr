package com.pozoriche.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

public class PageDto {

    private Long Id;
    private String name;
    private String tag;
    private String username;
    private List<String> bonusList;
    private List<String> categories;
    private Long money;
    private Long needed;
    private String youTubeVideo;
    private List<String> images;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String endingDate;


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

    public List<String> getBonusList() {
        return bonusList;
    }

    public void setBonusList(List<String> bonusList) {
        this.bonusList = bonusList;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getNeeded() {
        return needed;
    }

    public void setNeeded(Long needed) {
        this.needed = needed;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }
}

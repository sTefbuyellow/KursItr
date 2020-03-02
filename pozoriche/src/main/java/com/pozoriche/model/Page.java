package com.pozoriche.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;


@Entity
@Table
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "page_id")
    private long id;


    @NotBlank
    @Column
    private String tag;

    @Column
    @NotEmpty
    private String name;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bonus_id")
    private java.util.List<Bonus> bonusList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "page_id")
    private java.util.List<Donator> donators;

    @ManyToMany
    private java.util.List<Category> categories;

    @Column
    private Long money;

    @Column
    private Long needed;

    @Column
    private String youTubeVideo;

    @ManyToMany
    private java.util.List<Image> images;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endingDate;

    public Page() {
    }

    public void moneySumm(Long summ){
        this.setMoney(this.getMoney()+summ);
    }

    public void getDonator(Donator donator){
        this.donators.add(donator);
    }

    public java.util.List<Bonus> getBonuses(){
        return this.bonusList;
    }

    public java.util.List<String> getBonusList() {
        java.util.List<String> bonusList = new ArrayList<String>();
        for (Bonus bonus : this.bonusList) {
            bonusList.add(bonus.getName());
        }
        return bonusList;
    }

    public void setDonator(Donator donator){
        this.donators.add(donator);
    }

    public void setImages(java.util.List<Image> images) {
        this.images = images;
    }

    public java.util.List<String> getCategories(){
        java.util.List<String> categoriesList = new ArrayList<String>();
        for (Category category : this.categories){
            categoriesList.add(category.getCategory());
        }
        return categoriesList;
    }

    public java.util.List<String> getImages(){
        java.util.List<String> categoriesList = new ArrayList<String>();
        for (Image image: this.images){
            categoriesList.add(image.getImageURL());
        }
        return categoriesList;
    }

    public void setCategories(java.util.List<Category> categories) {
        this.categories = categories;
    }

    public void setBonusList(java.util.List<Bonus> bonusList) {
        this.bonusList=bonusList;
    }

    public List getDonators() {
        List donators = new List();
        for (Donator donator : this.donators) {
            donators.add(donator.getDonator().getUserName());
        }
        return donators;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNeeded() {
        return needed;
    }

    public void setNeeded(Long needed) {
        this.needed = needed;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getYouTubeVideo() {
        return youTubeVideo;
    }

    public void setYouTubeVideo(String youTubeVideo) {
        this.youTubeVideo = youTubeVideo;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }
}

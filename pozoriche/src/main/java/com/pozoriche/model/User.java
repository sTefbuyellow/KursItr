package com.pozoriche.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usr")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Page> pages;

    @ManyToMany
    private java.util.List<Bonus> bonusList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Donator> donated;

    public User() {
    }

    public void setDonator(Donator donator){
        this.donated.add(donator);
    }

    public void setBonus(Bonus bonus){
        this.bonusList.add(bonus);
    }

    public List<String> getBonusList() {
        List<String> bonusList = new ArrayList<String>();
        for (Bonus bonus : this.bonusList) {
            bonusList.add(bonus.getName());
        }
        return bonusList;
    }

    public List<String> getDonatedList() {
        List<String> donatedList = new ArrayList<String>();
        for (Donator donator : this.donated) {
            donatedList.add(donator.getPage().getName());
        }
        return donatedList;
    }

    public List<String> getPagesList() {
        List<String> pagesList = new ArrayList<String>();
        for (Page page : this.pages) {
            pagesList.add(page.getName());
        }
        return pagesList;
    }

    public void initBonusList(){
        this.bonusList = null;
    }

    public void setBonuses(List<Bonus> bonuses){
        this.bonusList.addAll(bonuses);
    }

    public void setBonusList(java.awt.List bonusList) {
        for(int i=0; i < bonusList.getRows(); i++) {
            Bonus bonus = new Bonus(bonusList.getItem(i));
            this.bonusList.add(bonus);
        }
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPage(Page page) {
        this.pages.add(page);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }


}

package com.pozoriche.dto;

import com.pozoriche.model.UserRole;

import javax.persistence.Column;
import java.util.List;

public class UserDto {

    private Long id;
    private String userName;
    private String email;
    private UserRole role;
    private List<String> bonusList;
    private List<String> donated;
    private List<String> pages;

    public Long getId() {
        return id;
    }

    public List<String> getBonusList() {
        return bonusList;
    }

    public void setBonusList(List<String> bonusList) {
        this.bonusList = bonusList;
    }

    public List<String> getDonated() {
        return donated;
    }

    public void setDonated(List<String> donated) {
        this.donated = donated;
    }

    public List<String> getPages() {
        return pages;
    }

    public void setPages(List<String> pages) {
        this.pages = pages;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

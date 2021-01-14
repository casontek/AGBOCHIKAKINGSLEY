package com.chika.decagontest.models;

import java.util.Arrays;

public class User {
    private String id;
    private String avatar;
    private String fullName;
    private String createdAt;
    private String gender;
    private String[] colors;
    private String[] countries;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "UserActivity{" +
                "id='" + id + '\'' +
                ", avatar='" + avatar + '\'' +
                ", fullName='" + fullName + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", gender='" + gender + '\'' +
                ", colors=" + Arrays.toString(colors) +
                ", countries=" + Arrays.toString(countries) +
                '}';
    }
}

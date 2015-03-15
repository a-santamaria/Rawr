package com.example.david.rawr.models;

/**
 * Created by david on 11/02/2015.
 */
public class Prospect {

    private int Picture;
    private String name;
    private String age;
    private String greed;
    private boolean like;

    public Prospect(){

    }

    public Prospect(int picture, String name, String age, String greed, boolean like) {
        Picture = picture;
        this.name = name;
        this.age = age;
        this.greed = greed;
        this.like = like;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public int getPicture() {
        return Picture;
    }

    public void setPicture(int picture) {
        Picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGreed() {
        return greed;
    }

    public void setGreed(String greed) {
        this.greed = greed;
    }
}

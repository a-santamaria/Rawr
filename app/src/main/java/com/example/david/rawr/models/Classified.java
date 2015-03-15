package com.example.david.rawr.models;

/**
 * Created by Fabián Merchán on 11/02/2015.
 */
public class Classified {

    byte[] picture;
    String articleName;
    String petName;
    int price;

    public Classified(){}

    public Classified(byte[] picture, String articleName, String petName, int price) {
        this.picture = picture;
        this.articleName = articleName;
        this.petName = petName;
        this.price = price;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

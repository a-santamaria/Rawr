package com.example.david.rawr.models;

import java.util.ArrayList;

/**
 * Created by Fabián Merchán on 11/02/2015.
 */
public class Post {

    String petName;
    int petPicture;
    String description;
    ArrayList<String> comments;

    public Post(String petName, int petPicture, String description, ArrayList<String> comments) {
        this.petName = petName;
        this.petPicture = petPicture;
        this.description = description;
        this.comments = comments;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetPicture() {
        return petPicture;
    }

    public void setPetPicture(int petPicture) {
        this.petPicture = petPicture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }
}

package com.example.david.rawr.models;

import android.os.Parcelable;

import com.example.david.rawr.mainActivities.PetList;

/**
 * Created by Alejandro on 10/02/2015.
 */
public class Pet {

    private int picture;
    private String name;
    private String type;
    private PetList petFriends;

    public Pet(){

    }
    public Pet(int picture, String name, String type) {
        this.picture = picture;
        this.name = name;
        this.type = type;
    }

    public PetList getPetFriends() {
        return petFriends;
    }

    public void setPetFriends(Parcelable petFriends) {
        this.petFriends = (PetList)petFriends;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.example.david.rawr.models;

import android.os.Parcelable;

import com.example.david.rawr.mainActivities.PetList;


/**
 * Created by Alejandro on 10/02/2015.
 */
public class Owner {
    private int picture;
    private String name;
    private String lastname;
    private PetList pets;

    public Owner() {

    }

    public Owner(int picture, String name, String lastname, PetList pets) {
        this.picture = picture;
        this.name = name;
        this.lastname = lastname;
        this.pets = pets;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public PetList getPets() {
        return pets;
    }

    public void setPets(Parcelable parcelable) {
        this.pets= (PetList) parcelable;
    }
}

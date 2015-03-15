package com.example.david.rawr.mainActivities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.david.rawr.models.Pet;

import java.util.ArrayList;


/**
 * Created by Alejandro on 11/02/2015.
 */
public class PetList extends ArrayList<Pet> implements Parcelable {


    public PetList() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        int size=this.size();
        dest.writeInt(size);
        for (int i=0;i<size();i++){
            Pet pet =this.get(i);
            dest.writeInt(pet.getPicture());
            dest.writeString(pet.getName());
            dest.writeString(pet.getType());
            dest.writeParcelable(pet.getPetFriends(),flags);
        }
    }


    public PetList(Parcel in){
        readfromParcel(in);
    }

    private void readfromParcel(Parcel in){
        this.clear();
        int size=in.readInt();
        for (int i=0;i<size;i++){
            Pet pet = new Pet();
            pet.setPicture(in.readInt());
            pet.setName(in.readString());
            pet.setType(in.readString());
            pet.setPetFriends(in.readParcelable(PetList.class.getClassLoader()));
            this.add(pet);
        }
    }

    public static final Creator CREATOR = new Creator(){
        public PetList createFromParcel(Parcel in){
            return new PetList(in);
        }
        public Object[] newArray(int arg0){
            return null;
        }
    };


}

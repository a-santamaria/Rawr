package com.example.david.rawr.mainActivities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.david.rawr.models.Owner;

import java.util.ArrayList;


/**
 * Created by Alejandro on 11/02/2015.
 */
public class OwnerList extends ArrayList<Owner> implements Parcelable {

    public OwnerList(){

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
            Owner owner=this.get(i);
            dest.writeInt(owner.getPicture());
            dest.writeString(owner.getName());
            dest.writeString(owner.getLastname());
            dest.writeParcelable(owner.getPets(),flags);
        }

    }

    public OwnerList(Parcel in){
        readfromParcel(in);
    }

    private void readfromParcel(Parcel in){
        this.clear();
        int size=in.readInt();
        for (int i=0;i<size;i++){
            Owner owner=new Owner();
            owner.setPicture(in.readInt());
            owner.setName(in.readString());
            owner.setLastname(in.readString());
            owner.setPets(in.readParcelable(PetList.class.getClassLoader()));
            this.add(owner);
        }
    }

    public static final Creator CREATOR = new Creator(){
        public OwnerList createFromParcel(Parcel in){
            return new OwnerList(in);
        }
        public Object[] newArray(int arg0){
            return null;
        }
    };



}

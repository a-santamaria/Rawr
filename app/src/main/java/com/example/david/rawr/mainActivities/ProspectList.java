package com.example.david.rawr.mainActivities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.david.rawr.models.Prospect;

import java.util.ArrayList;

/**
 * Created by david on 11/02/2015.
 */
public class ProspectList extends ArrayList<Prospect> implements Parcelable {

    public ProspectList(){

    }
    public ProspectList(Parcel in){
        readfromParcel(in);
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
            Prospect prospect = this.get(i);
            dest.writeInt(prospect.getPicture());
            dest.writeString(prospect.getName());
            dest.writeString(prospect.getAge());
            dest.writeString(prospect.getGreed());
        }
    }

    private void readfromParcel(Parcel in){
        this.clear();
        int size = in.readInt();
        for (int i=0; i<size; i++){
            Prospect prospect = new Prospect();
            prospect.setPicture(in.readInt());
            prospect.setName(in.readString());
            prospect.setAge(in.readString());
            prospect.setGreed(in.readString());
            this.add(prospect);
        }
    }
}

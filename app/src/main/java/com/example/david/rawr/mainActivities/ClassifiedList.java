package com.example.david.rawr.mainActivities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.david.rawr.models.Classified;

import java.util.ArrayList;

/**
 * Created by Fabián Merchán on 11/02/2015.
 */
public class ClassifiedList extends ArrayList<Classified> implements Parcelable {

    ClassifiedList(){
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        this.clear();
        int size = this.size();
        dest.writeInt(size);
        for(int i=0; i<size; i++){
            Classified classified = this.get(i);
            dest.writeString(classified.getPetName());
            //dest.writeInt(classified.getPicture());
            dest.writeInt(classified.getPrice());
        }
    }

    public ClassifiedList(Parcel in){
        readfromParcel(in);
    }

    private void readfromParcel(Parcel in){
        this.clear();
        int size=in.readInt();
        for (int i=0;i<size;i++){
            Classified classified = new Classified();
            classified.setPetName(in.readString());
            //classified.setPicture(in.read());
            classified.setPrice(in.readInt());
            this.add(classified);
        }
    }

    public static final Creator CREATOR = new Creator(){
        public ClassifiedList createFromParcel(Parcel in){
            return new ClassifiedList(in);
        }
        public Object[] newArray(int arg0){
            return null;
        }
    };

}

package com.example.david.rawr.mainActivities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.david.rawr.models.Post;

import java.util.ArrayList;

/**
 * Created by Fabián Merchán on 12/02/2015.
 */
public class SocialList extends ArrayList<Post> implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        int size=this.size();
        dest.writeInt(size);
        for (int i=0;i<size();i++) {
            Post post = this.get(i);
            dest.writeString(post.getPetName());
            dest.writeInt(post.getPetPicture());
            dest.writeString(post.getDescription());
            dest.writeSerializable(post.getComments());
        }
    }

    public SocialList(Parcel in){
        readfromParcel(in);
    }

    public SocialList(){

    }
    private void readfromParcel(Parcel in){
        this.clear();
        int size=in.readInt();
        for (int i=0;i<size;i++){
            Post post = this.get(i);
            post.setPetName(in.readString());
            post.setPetPicture(in.readInt());
            post.setDescription(in.readString());
            post.setComments((ArrayList<String>) in.readSerializable());
            this.add(post);
        }
    }
}

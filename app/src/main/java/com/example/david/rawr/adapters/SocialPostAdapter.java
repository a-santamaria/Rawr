package com.example.david.rawr.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.rawr.R;
import com.example.david.rawr.mainActivities.SocialList;
import com.example.david.rawr.mainActivities.SocialPost;
import com.example.david.rawr.models.Post;

import java.util.List;

/**
 * Created by Fabián Merchán on 11/02/2015.
 * Modified by Stiven Avila on 14/2/2015
 */
public class SocialPostAdapter extends BaseAdapter{

    private Context c;
    private SocialList posts;

    public SocialPostAdapter(Context c, SocialList posts) {
        this.c = c;
        this.posts = posts;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if( convertView == null )
            convertView = LayoutInflater.from(c).inflate(R.layout.fragment_social_post,null);

        Post p = posts.get(position);

        ImageView profilePic = (ImageView)convertView.findViewById(R.id.imageView_fragment_social_post_profilePic);
        TextView name = (TextView)convertView.findViewById(R.id.textView_fragment_social_post_name);
        TextView post = (TextView)convertView.findViewById(R.id.textView_fragment_social_post_post);

        profilePic.setImageResource(p.getPetPicture());
        name.setText(p.getPetName());
        post.setText(p.getDescription());

        return convertView;
    }
}

package com.example.david.rawr.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.rawr.R;
import com.example.david.rawr.mainActivities.ProspectList;
import com.example.david.rawr.models.Prospect;
import com.example.david.rawr.otherClasses.RoundImage;

import java.util.List;

/**
 * Created by david on 11/02/2015.
 */
public class ProspectAdapter extends BaseAdapter {

    private Context c;
    private ProspectList prospects;

    public ProspectAdapter(Context c, ProspectList prospects) {
        this.c = c;
        this.prospects = prospects;
    }

    @Override
    public int getCount() {
        return prospects.size();
    }

    @Override
    public Object getItem(int position) {
        return prospects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(c).inflate(R.layout.prospect_item,null);
        }
        TextView name = (TextView)convertView.findViewById(R.id.prospect_name);
        TextView age = (TextView)convertView.findViewById(R.id.prospect_age);
        TextView greed = (TextView)convertView.findViewById(R.id.prospect_greed);
        ImageView prospect_picture = (ImageView)convertView.findViewById(R.id.prospect_picture);
        ImageView cupid = (ImageView)convertView.findViewById(R.id.cupid);

        Prospect p = prospects.get(position);
        name.setText(p.getName());
        name.setTextColor(Color.DKGRAY);
        age.setText(p.getAge());
        age.setTextColor(Color.DKGRAY);
        greed.setText(p.getGreed());
        greed.setTextColor(Color.DKGRAY);
        Bitmap bm = BitmapFactory.decodeResource(convertView.getResources(), p.getPicture());
        prospect_picture.setImageBitmap(RoundImage.getRoundedShape(bm));

        if(p.isLike()){
            cupid.setImageResource(R.drawable.face_icon);
            p.setLike(false);
        }else{
            cupid.setImageResource(R.drawable.logo_icon);
            p.setLike(true);
        }
        return convertView;
    }

    public Prospect getProspect(int position){
        return prospects.get(position);
    }

    public void removeProspect(int position){
        prospects.remove(position);
    }
}

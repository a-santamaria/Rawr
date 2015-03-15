package com.example.david.rawr.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.rawr.R;
import com.example.david.rawr.mainActivities.PetList;
import com.example.david.rawr.models.Pet;
import com.example.david.rawr.otherClasses.RoundImage;


/**
 * Created by Alejandro on 11/02/2015.
 */
public class FriendsAdapter extends BaseAdapter {

    private Context c;
    private PetList petList;

    public FriendsAdapter(Context c, PetList petList){
        this.c=c;
        this.petList=petList;
    }

    @Override
    public int getCount() {
        return petList.size();
    }

    @Override
    public Object getItem(int position) {
        return petList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(c).inflate(R.layout.petfriend_item,null);
        }

        ImageView imageView1;
        RoundImage roundedImage;

        imageView1 = (ImageView)convertView.findViewById(R.id.iv_animal_amigos_foto);
        TextView nombre=(TextView)convertView.findViewById(R.id.tv_item_animal_amigos_nombre);
        TextView animal=(TextView)convertView.findViewById(R.id.tv_item_animal_amigos_animal);


        Pet pet = petList.get(position);

        Bitmap bm = BitmapFactory.decodeResource(convertView.getResources(), pet.getPicture());
        imageView1.setImageBitmap(RoundImage.getRoundedShape(bm));
        nombre.setText(pet.getName());
        animal.setText(pet.getType());


        return convertView;
    }



}

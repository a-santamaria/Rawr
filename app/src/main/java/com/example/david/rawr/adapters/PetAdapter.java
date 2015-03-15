package com.example.david.rawr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.rawr.mainActivities.PetList;
import com.example.david.rawr.R;
import com.example.david.rawr.models.Pet;


/**
 * Created by Alejandro on 10/02/2015.
 */
public class PetAdapter extends BaseAdapter {

    private Context c;
    private PetList pets;

    public PetAdapter(Context c, PetList pets){
        this.c=c;
        this.pets=pets;
    }

    @Override
    public int getCount() {
        return pets.size();
    }

    @Override
    public Object getItem(int position) {
        return pets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView==null){
            convertView= LayoutInflater.from(c).inflate(R.layout.pet_item,null);

        }

        ImageView imageView=(ImageView)convertView.findViewById(R.id.iv_item_animal_foto);
        TextView name = (TextView)convertView.findViewById(R.id.tv_item_animal_nombre);
        TextView type =(TextView)convertView.findViewById(R.id.tv_item_animal_animal);

        Pet m=pets.get(position);

        imageView.setImageResource(m.getPicture());
        name.setText(m.getName());
        type.setText(m.getType());

        return convertView;
    }
}

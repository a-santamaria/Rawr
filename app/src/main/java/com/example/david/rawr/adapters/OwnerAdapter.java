package com.example.david.rawr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.rawr.R;
import com.example.david.rawr.models.Owner;

import java.util.List;


/**
 * Created by Alejandro on 10/02/2015.
 */
public class OwnerAdapter extends BaseAdapter {

    private Context c;
    private List<Owner> owners;

    public OwnerAdapter(Context c, List<Owner> owners){
        this.c=c;
        this.owners=owners;
    }


    @Override
    public int getCount() {
        return owners.size();
    }

    @Override
    public Object getItem(int position) {
        return owners.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(c).inflate(R.layout.owner_item,null);
        }

        ImageView imagen=(ImageView)convertView.findViewById(R.id.iv_item_persona_foto);
        TextView nombre=(TextView)convertView.findViewById(R.id.tv_item_persona_nombre);
        TextView apellido=(TextView)convertView.findViewById(R.id.tv_item_persona_apellido);

        Owner p=owners.get(position);

        imagen.setImageResource(p.getPicture());
        nombre.setText(p.getName());
        apellido.setText(p.getLastname());

        return convertView;
    }

}

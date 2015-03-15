package com.example.david.rawr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.rawr.R;
import com.example.david.rawr.mainActivities.ClassifiedList;
import com.example.david.rawr.models.Classified;

import java.util.List;

/**
 * Created by Fabián Merchán on 11/02/2015.
 */
public class ClassifiedAdapter extends BaseAdapter{

    Context c;
    ClassifiedList classifiedList;


    public ClassifiedAdapter(Context c, ClassifiedList classifiedList) {
        this.c = c;
        this.classifiedList = classifiedList;
    }

    @Override
    public int getCount() {
        return classifiedList.size();
    }

    @Override
    public Object getItem(int position) {
        return classifiedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if( convertView == null )
            convertView = LayoutInflater.from(c).inflate(R.layout.fragment_classified_post,null);

        ImageView picture = (ImageView)convertView.findViewById(R.id.imageView_fragment_classified_post_picture);
        TextView description = (TextView)convertView.findViewById(R.id.textView_fragment_classified_post_description);
        TextView pet = (TextView)convertView.findViewById(R.id.textView_fragment_classified_post_pet);
        TextView price = (TextView)convertView.findViewById(R.id.textView_fragment_classified_post_price);

        Classified clasificado = classifiedList.get(position);

        //picture.setImageResource(clasificado.getPicture());
        description.setText(clasificado.getArticleName());
        pet.setText(clasificado.getPetName().toString());
        price.setText(""+clasificado.getPrice());

        return convertView;
    }
}

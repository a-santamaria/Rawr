package com.example.david.rawr.mainActivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.david.rawr.R;
import com.example.david.rawr.adapters.ClassifiedAdapter;
import com.example.david.rawr.adapters.ProspectAdapter;
import com.example.david.rawr.adapters.SocialPostAdapter;
import com.example.david.rawr.db.DbMethods;
import com.example.david.rawr.models.Classified;
import com.example.david.rawr.models.Prospect;
import com.example.david.rawr.otherClasses.RoundImage;
import com.example.david.rawr.otherClasses.SwipeListViewTouchListener;

import java.util.Timer;
import java.util.TimerTask;


public class Newsfeed extends Activity implements View.OnClickListener{

    ListView principal_listView;
    ImageView cupidButton, classifiedButton, socialButton,profileButton;
    ProspectAdapter prospectAdapter;
    ClassifiedAdapter classifiedAdapter;
    SocialPostAdapter socialPostAdapter;
    SwipeListViewTouchListener touchListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);
        principal_listView = (ListView)findViewById(R.id.principal_listView);
        cupidButton = (ImageView)findViewById(R.id.cupidButton);
        classifiedButton = (ImageView)findViewById(R.id.classifiedButton);
        profileButton = (ImageView) findViewById(R.id.profileButton);
        socialButton = (ImageView) findViewById(R.id.socialButton);
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.perro2);
        profileButton.setImageBitmap(RoundImage.getRoundedShape(bm));

        profileButton.setOnClickListener(this);
        cupidButton.setOnClickListener(this);
        classifiedButton.setOnClickListener(this);
        socialButton.setOnClickListener(this);

        prospectAdapter = new ProspectAdapter(this, DbMethods.getProspectList());
        classifiedAdapter = new ClassifiedAdapter(this, getClassifedList());
        socialPostAdapter = new SocialPostAdapter(this, DbMethods.getSocialPostList());

        touchListener = new SwipeListViewTouchListener(principal_listView, new SwipeListViewTouchListener.OnSwipeCallback(){

            @Override
            public void onSwipeLeft(ListView listView, int[] reverseSortedPositions) {
                prospectAdapter.removeProspect(reverseSortedPositions[0]);
                prospectAdapter.notifyDataSetChanged();
            }

            @Override
            public void onSwipeRight(ListView listView, int[] reverseSortedPositions) {
                    Prospect prospect = (Prospect)listView.getItemAtPosition(reverseSortedPositions[0]);
                    if (prospect.isLike()){
                        prospect.setLike(false);
                    }else {
                        prospect.setLike(true);
                    }
                prospectAdapter.notifyDataSetChanged();
                }
        });

        principal_listView.setOnTouchListener(touchListener);
        principal_listView.setOnScrollListener(touchListener.makeScrollListener());
        principal_listView.setAdapter(socialPostAdapter);
    }

    public void goToProfile(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putParcelable("owner", DbMethods.getOwners());
                Intent intent = new Intent(Newsfeed.this, OwnerProfile.class);
                intent.putExtras(bundle);
                startActivity(intent);
                //finishscreen();
            }
        }, 1000);
    }

    private void finishscreen(){
        this.finish();
    }



    private ClassifiedList getClassifedList(){
        ClassifiedList classifiedList = new ClassifiedList();
        Classified c = new Classified(new byte[10],"Collar", "Lucas", 100);
        classifiedList.add(c);
        c = new Classified(new byte[10], "Pelota", "Lucas", 300);
        classifiedList.add(c);

        return classifiedList;
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.cupidButton:
                    principal_listView.setAdapter(prospectAdapter);
                break;
            case R.id.classifiedButton:
                    principal_listView.setAdapter(classifiedAdapter);
                break;
            case R.id.profileButton:
                    goToProfile();
                break;
            case R.id.socialButton:
                    principal_listView.setAdapter(socialPostAdapter);
                break;
        }
    }
}

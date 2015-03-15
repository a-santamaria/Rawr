package com.example.david.rawr.mainActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.david.rawr.R;
import com.example.david.rawr.adapters.OwnerAdapter;
import com.example.david.rawr.adapters.PetAdapter;
import com.example.david.rawr.models.Pet;


public class OwnerProfile extends Activity {

    private ListView ls_owners;
    private ListView ls_pets;
    private Bundle bundleRx, bundleTx;
    private OwnerList owner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile);

        if(bundleRx ==null){
            bundleRx =new Bundle();
        }

        bundleRx = getIntent().getExtras();
        owner =  bundleRx.getParcelable("owner");


        ls_owners=(ListView)findViewById(R.id.ownersListView);
        ls_pets=(ListView)findViewById(R.id.petsListView);

        OwnerAdapter ownerAdapter = new OwnerAdapter(this,owner);
        ls_owners.setAdapter(ownerAdapter);
        PetList pets = owner.get(0).getPets();
        PetAdapter petAdapter = new PetAdapter(this,pets);
        ls_pets.setAdapter(petAdapter);

        ls_pets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(OwnerProfile.this,PetProfile.class);

                if (bundleTx == null){
                    bundleTx = new Bundle();
                }

                Pet m=(Pet)ls_pets.getItemAtPosition(position);
                PetList ms=new PetList();
                ms.add(m);

                bundleTx.putParcelable("ownerKey", owner);
                bundleTx.putInt("positionKey", position);

                intent.putExtras(bundleTx);
                startActivity(intent);

            }
        });

    }


}

package com.example.david.rawr.mainActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.rawr.R;
import com.example.david.rawr.adapters.FriendsAdapter;
import com.example.david.rawr.adapters.PetAdapter;


public class PetProfile extends ActionBarActivity {

    private ListView ls_mascota;
    private GridView gs_amigos;

    private TextView tx_dueno;
    private TextView tx_mensaje;
    private Bundle bundlePerfilDueno;
    PetList petList;
    OwnerList owner;
    ImageButton boton;
    PetList petFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile);

        ls_mascota=(ListView)findViewById(R.id.ls_perfil_mascota_mascota);
        gs_amigos=(GridView)findViewById(R.id.gv_perfil_mascotas_amigos);
        tx_dueno=(TextView)findViewById(R.id.tv_perfil_mascota_dueno);
        tx_mensaje=(TextView)findViewById(R.id.tv_perfil_mascota_mensajeAmigos);
        boton=(ImageButton)findViewById(R.id.ib_activity_perfil_mascota_owner);

        if(bundlePerfilDueno == null){
            bundlePerfilDueno = new Bundle();
        }

        bundlePerfilDueno =getIntent().getExtras();

        owner =  bundlePerfilDueno.getParcelable("ownerKey");
        int petPos= bundlePerfilDueno.getInt("positionKey");

        tx_dueno.setText(owner.get(0).getName());
        boton.setImageResource(owner.get(0).getPicture());


        if (petList ==null){
            petList =new PetList();
        }
        petList.add(owner.get(0).getPets().get(petPos));


        PetAdapter petAdapter = new PetAdapter(this, petList);
        ls_mascota.setAdapter(petAdapter);

        petFriends = petList.get(0).getPetFriends();

        if(petFriends!=null){
            gs_amigos.setVisibility(View.VISIBLE);
            FriendsAdapter friendsAdapter = new FriendsAdapter(this,petFriends);
            gs_amigos.setAdapter(friendsAdapter);
            tx_mensaje.setVisibility(View.INVISIBLE);
        }

        else {
            gs_amigos.setVisibility(View.INVISIBLE);
            tx_mensaje.setText("You don't have friends");
            tx_mensaje.setVisibility(View.VISIBLE);

        }


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(PetProfile.this,OwnerProfile.class);
                bundlePerfilDueno=new Bundle();

                bundlePerfilDueno.putParcelable("owner", owner);
                it.putExtras(bundlePerfilDueno);
                startActivity(it);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent it=new Intent(PetProfile.this,OwnerProfile.class);
        bundlePerfilDueno=new Bundle();

        bundlePerfilDueno.putParcelable("owner", owner);
        it.putExtras(bundlePerfilDueno);
        startActivity(it);
        finish();

    }
}

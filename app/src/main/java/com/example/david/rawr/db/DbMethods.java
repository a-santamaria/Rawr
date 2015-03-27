package com.example.david.rawr.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.david.rawr.R;
import com.example.david.rawr.mainActivities.OwnerList;
import com.example.david.rawr.mainActivities.PetList;
import com.example.david.rawr.mainActivities.ProspectList;
import com.example.david.rawr.mainActivities.SocialList;
import com.example.david.rawr.mainActivities.SocialPost;
import com.example.david.rawr.mainActivities.downloading_window;
import com.example.david.rawr.models.Owner;
import com.example.david.rawr.models.Pet;
import com.example.david.rawr.models.Post;
import com.example.david.rawr.models.Prospect;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.NameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;





public class DbMethods {

    private SQLiteDatabase database;
    private Context context;
    private DbManager dbManager;

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private String username;
    private String password;


    private String url_create_user = "http://178.62.233.249/rawr/create_user.php";

    public DbMethods(Context context){
        this.context = context;
        dbManager = new DbManager(context);
    }

    public void openDB(){
        try{

            database = dbManager.getWritableDatabase();

        }catch(SQLException ex){

            Log.e(TestDB.ERROR_OPEN,ex.getMessage());

        }
    }

    public void closeDB(){

        try{

            dbManager.close();

        }catch(SQLException ex){

            Log.e(TestDB.ERROR_CLOSE,ex.getMessage());

        }

    }

        public void singUp(String username, String password){

        this.username = username;
        this.password = password;
        new CreateNewUser().execute();
            /*
        try{
            boolean exist = false;
            String [] columns = new String[]{DbManager.NOM_COLUM_USER, DbManager.NOM_COLUM_PASS};
            Cursor c = database.query(DbManager.NOM_TABLE_USERS,columns,null,null,null,null,null);
            while(c.moveToNext()){
                if (c.getString(DbManager.INDEX_USER).compareTo(username) == 0){
                    exist = true;
                    break;
                }
            }
            if (exist){
                Toast.makeText(context, "This username exist",Toast.LENGTH_LONG ).show();
            }else {
                ContentValues val = new ContentValues();
                val.put(DbManager.NOM_COLUM_USER, username);
                val.put(DbManager.NOM_COLUM_PASS, password);
                val.put(DbManager.NOM_COLUM_PICTURE, "NULL");
                val.put(DbManager.NOM_COLUM_NAME, "a");
                val.put(DbManager.NOM_COLUM_LASTNAME, "a");
                long ID = database.insert(DbManager.NOM_TABLE_USERS, null, val);
                Toast.makeText(context, "Registered",Toast.LENGTH_LONG ).show();
                Log.i(TestDB.OK_INSERTAR, "" + ID);
            }
        }catch (SQLException ex){
            Log.e(TestDB.ERROR_INSERTAR, ex.getMessage());
        }
        */

    }

    class CreateNewUser extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog(NewProductActivity.this);
            pDialog.setMessage("Creating Product..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            */
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));

            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url_create_user);

            try {
                post.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse response = client.execute(post);
                Log.i("response ",response.getStatusLine().getStatusCode()+"");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            //pDialog.dismiss();
        }

    }


    public void changePassword(String username,String password){

        try{

            ContentValues contentValues = new ContentValues();
            contentValues.put(DbManager.NOM_COLUM_PASS,password);

            database.update(DbManager.NOM_TABLE_USERS,contentValues,
                    DbManager.NOM_COLUM_USER+"="+username,null);

            Log.i(TestDB.OK_ACTUALIZAR,"Actualizacion Correcta");

        }catch (SQLException ex) {
            Log.e(TestDB.ERROR_ACTUALIZAR,ex.getMessage());
        }

    }

    public boolean login(String username, String password){

        boolean retorno = true, exist = false;
        try{
            String [] columns = new String[]{DbManager.NOM_COLUM_USER, DbManager.NOM_COLUM_PASS};
            Cursor c = database.query(DbManager.NOM_TABLE_USERS,columns,null,null,null,null,null);
            while(c.moveToNext()){
                if (c.getString(DbManager.INDEX_USER).compareTo(username) == 0){
                    exist = true;
                    if(c.getString(DbManager.INDEX_PASS).compareTo(password) == 0) {
                        retorno = true;
                    }else{
                        retorno = false;
                        Toast.makeText(context,"This password is wrong",Toast.LENGTH_LONG).show();
                    }
                }
            }
            if (exist == false) {
                retorno = false;
                Toast.makeText(context, "This username isn't registered", Toast.LENGTH_LONG).show();
            }
        }catch (SQLException ex) {
            Log.e(TestDB.ERROR_ACTUALIZAR,ex.getMessage());
        }
        return retorno;
    }

    public static OwnerList getOwners(){
        OwnerList listaPersonas=new OwnerList();

        PetList listaMascotas=new PetList();
        listaMascotas.add(new Pet(R.drawable.perro1,"ppp","Perro"));
        listaMascotas.add(new Pet(R.drawable.perro2,"fff","Perro"));
        listaMascotas.add(new Pet(R.drawable.perro3,"hhh","Perro"));
        listaMascotas.add(new Pet(R.drawable.gato9,"uuuuuu","Gato"));
        listaMascotas.add(new Pet(R.drawable.gato10,"sdsggd","Gato"));
        listaMascotas.add(new Pet(R.drawable.gato8,"jgfgfjg","Gato"));
        listaMascotas.add(new Pet(R.drawable.perro5,"jgsdsddddddg","Perro"));

        PetList listaMascotas1=new PetList();
        listaMascotas1.add(new Pet(R.drawable.gato11,"ppp","Pdddd"));
        listaMascotas1.add(new Pet(R.drawable.gato10,"ppp","Pdddd"));
        listaMascotas1.add(new Pet(R.drawable.gato7,"fffdddd","Pedsfsdfgo"));
        listaMascotas1.add(new Pet(R.drawable.perro2,"hhhdddd","Perro"));
        listaMascotas1.add(new Pet(R.drawable.perro2,"fffdddd","Pedsfsdfgo"));
        listaMascotas1.add(new Pet(R.drawable.perro3,"hhhdddd","Perro"));
        listaMascotas1.add(new Pet(R.drawable.perro6,"ppp","Pdddd"));
        listaMascotas1.add(new Pet(R.drawable.perro2,"fffdddd","Pedsfsdfgo"));
        listaMascotas1.add(new Pet(R.drawable.perro3,"hhhdddd","Perro"));
        listaMascotas1.add(new Pet(R.drawable.perro6,"ppp","Pdddd"));
        listaMascotas1.add(new Pet(R.drawable.perro6,"ppp","Pdddd"));
        listaMascotas1.add(new Pet(R.drawable.perro2,"fffdddd","Pedsfsdfgo"));
        listaMascotas1.add(new Pet(R.drawable.perro3,"hhhdddd","Perro"));
        listaMascotas1.add(new Pet(R.drawable.perro2,"fffdddd","Pedsfsdfgo"));
        listaMascotas1.add(new Pet(R.drawable.perro3,"hhhdddd","Perro"));
        listaMascotas1.add(new Pet(R.drawable.perro6,"ppp","Pdddd"));
        listaMascotas1.add(new Pet(R.drawable.perro2,"fffdddd","Pedsfsdfgo"));
        listaMascotas1.add(new Pet(R.drawable.perro3,"hhhdddd","Perro"));

        PetList listaMascotas2=new PetList();
        listaMascotas2.add(new Pet(R.drawable.gato7,"gass","gato"));
        listaMascotas2.add(new Pet(R.drawable.gato8,"ddfdfd","gatos"));

        PetList listaMascotas3=new PetList();
        listaMascotas3.add(new Pet(R.drawable.gato9,"gass","gato"));
        listaMascotas3.add(new Pet(R.drawable.perro5,"dfdgffdgfdfdfdgf","perro"));
        listaMascotas3.add(new Pet(R.drawable.perro3,"hhhdddd","Perro"));
        listaMascotas3.add(new Pet(R.drawable.perro6,"ppp","Pdddd"));
        listaMascotas3.add(new Pet(R.drawable.perro2,"fffdddd","Pedsfsdfgo"));


        listaMascotas.get(0).setPetFriends(listaMascotas1);
        listaMascotas.get(1).setPetFriends(listaMascotas2);
        listaMascotas.get(5).setPetFriends(listaMascotas3);

        Owner p=new Owner(R.drawable.persona1,"David","Avila",listaMascotas);
        listaPersonas.add(p);
        return listaPersonas;
    }

    public static ProspectList getProspectList(){
        ProspectList prospectList = new ProspectList();
        Prospect p = new Prospect(R.drawable.perro1, "Pepe", "10", "Pastor Aleman", false);
        prospectList.add(p);
        p = new Prospect(R.drawable.perro2, "Lucas", "6", "Frenchpuddle", false);
        prospectList.add(p);
        p = new Prospect(R.drawable.perro3, "Maira", "8", "asafasf", false);
        prospectList.add(p);
        p = new Prospect(R.drawable.gat12, "Daniela", "2", "Frenchpuddle", false);
        prospectList.add(p);
        p = new Prospect(R.drawable.perro5, "Felipe", "5", "Callejero", false);
        prospectList.add(p);
        p = new Prospect(R.drawable.perro6, "Fabian", "7", "Vagabundo", false);
        prospectList.add(p);
        return prospectList;
    }

    public static SocialList getSocialPostList(){
        SocialList socialList = new SocialList();
        ArrayList comments = new ArrayList();
        comments.add("ola que ase");
        comments.add("guau");
        Post p = new Post("Paco", R.drawable.perro2, "Hola soy paco", comments);
        socialList.add(p);
        comments = new ArrayList();
        comments.add("zZzzZ");
        comments.add("Duerme");
        p = new Post ("Lola" ,R.drawable.gat12, "Me siento soñolienta", comments);
        socialList.add(p);
        return socialList;
    }
}

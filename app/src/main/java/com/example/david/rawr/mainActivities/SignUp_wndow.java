package com.example.david.rawr.mainActivities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.rawr.R;
import com.example.david.rawr.db.DbMethods;


public class SignUp_wndow extends Activity implements View.OnClickListener{

    EditText userText, passText, firstName, lastName;
    Button SignUp;
    DbMethods tablaDbMethods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_window);
        userText = (EditText) findViewById(R.id.userText);
        passText = (EditText) findViewById(R.id.passText);
        firstName  = (EditText) findViewById(R.id.firstName);
        lastName  = (EditText) findViewById(R.id.lastName);
        SignUp = (Button) findViewById(R.id.SignUpButton);
        SignUp.setOnClickListener(this);
        tablaDbMethods = new DbMethods(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up_wndow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        String username = userText.getText().toString();
        String password = passText.getText().toString();
        Intent intent;
        switch(v.getId()){
            case (R.id.SignUpButton):
                tablaDbMethods.openDB();
                tablaDbMethods.register(username, password);
                tablaDbMethods.closeDB();
                Toast.makeText(this, "Registered", Toast.LENGTH_LONG).show();
                intent = new Intent(SignUp_wndow.this, downloading_window.class );
                startActivity(intent);
                finish_screen();
                break;
        }
    }

    private void finish_screen(){
        this.finish();
    }

}

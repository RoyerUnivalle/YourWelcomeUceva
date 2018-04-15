package com.example.yourwelcome;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class HomeApp extends AppCompatActivity implements  FragSPA.OnFragmentInteractionListener{

    FragSPA fr2;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("");
        myToolbar.hideOverflowMenu();

        setSupportActionBar(myToolbar);

    }

    public void mostrarDatabaseOption(){
        if(fr2 instanceof FragSPA){

        }else{
            fr2 = new FragSPA();
            FragmentTransaction transtion=getSupportFragmentManager().beginTransaction();
            transtion.add(R.id.layout_frag,fr2);
            transtion.commit();
        }
    }

    public void VolverFrag(View f){
        Toast.makeText(this,"hola",Toast.LENGTH_LONG).show();
        FragmentTransaction transtionHideen=getSupportFragmentManager().beginTransaction();
        if (fr2.isAdded()) {
            transtionHideen.hide(fr2);
            transtionHideen.commit();
            getSupportFragmentManager().popBackStack();
            fr2=null;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate activity menu items.
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back_gorund:
                // User chose the "Settings" item, show the app settings UI...
                item.getTitle();
                Toast.makeText(this,"hola "+item.getTitle(),Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_data_base:
                //Toast.makeText(this,"hola "+item.getTitle(),Toast.LENGTH_LONG).show();
                mostrarDatabaseOption();
                return true;

            case R.id.action_service:
                Toast.makeText(this,"hola "+item.getTitle(),Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_network:
                Toast.makeText(this,"hola "+item.getTitle(),Toast.LENGTH_LONG).show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

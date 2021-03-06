package com.example.yourwelcome;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yourwelcome.Conexion.Conexion;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;

public class HomeApp extends AppCompatActivity implements  FragSPA.OnFragmentInteractionListener, PersonasView.OnFragmentInteractionListener {

    FragSPA fr2;
    FragmentManager fragmentManager;
    HttpExample fr3;
    GoogleMapExample fr4;

    EditText edNombreEstudiante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Proyecto móviles");
        //myToolbar.hideOverflowMenu();
        setSupportActionBar(myToolbar);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        //MobileAds.initialize(this, "ca-app-pub-5880552622720589~9469420518");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener(){

        });
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

    public  void ejemplosHttp(){
        if(fr3 instanceof HttpExample){

        }else{
            fr3 = new HttpExample();
            FragmentTransaction transtion=getSupportFragmentManager().beginTransaction();
            transtion.add(R.id.layout_frag,fr3);
            transtion.commit();
        }
    }
    public  void ejemplosMaps(){
        if(fr4 instanceof GoogleMapExample){

        }else{
            fr4 = new GoogleMapExample();
            FragmentTransaction transtion=getSupportFragmentManager().beginTransaction();
            transtion.add(R.id.layout_frag,fr4);
            transtion.commit();
        }
    }


    public void VolverFrag(View f){
        //Toast.makeText(this,"hola",Toast.LENGTH_LONG).show();
        FragmentTransaction transtionHideen=getSupportFragmentManager().beginTransaction();
        if (fr2.isAdded()) {
            transtionHideen.hide(fr2);
            transtionHideen.commit();
            getSupportFragmentManager().popBackStack();
            fr2=null;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*try {
            con.BD_backup();
            Toast.makeText(this,"Base de datos copiada",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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
                //item.getTitle();
                //Toast.makeText(this,"hola "+item.getTitle(),Toast.LENGTH_LONG).show();
                ejemplosMaps();
                return true;

            case R.id.action_data_base:
                //Toast.makeText(this,"hola "+item.getTitle(),Toast.LENGTH_LONG).show();
                mostrarDatabaseOption();
                return true;

            case R.id.action_service:
                Toast.makeText(this,"hola "+item.getTitle(),Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_network:
                //Toast.makeText(this,"hola "+item.getTitle(),Toast.LENGTH_LONG).show();
                ejemplosHttp();
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
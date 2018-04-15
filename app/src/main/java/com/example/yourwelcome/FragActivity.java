package com.example.yourwelcome;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FragActivity extends AppCompatActivity implements  FragSPA.OnFragmentInteractionListener, View.OnClickListener {

    Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);

        btn1 = (Button) findViewById(R.id.button5);
        btn2 = (Button) findViewById(R.id.button6);
        btn3 = (Button) findViewById(R.id.volverFm);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this); // tiene que implementarse View.OnClickListener

        /*Footer fr3 = new Footer();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedor,fr3);*/
    }
    @Override
    public void onFragmentInteraction(Uri uri) {}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button6:
                /*FragMenu fr1 = new FragMenu();
                FragmentTransaction transtion1 = getSupportFragmentManager().beginTransaction();
                transtion1.replace(R.id.contenedor,fr1);
                transtion1.commit();*/
                break;
            case R.id.button5:
                FragSPA fr2 = new FragSPA();
                FragmentTransaction transtion2 = getSupportFragmentManager().beginTransaction();
                transtion2.replace(R.id.contenedor,fr2);
                transtion2.commit();
                break;
            case R.id.volverFm:
                Intent volver = new Intent(FragActivity.this,Home.class);
                volver.addFlags(volver.FLAG_ACTIVITY_CLEAR_TASK | volver.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(volver);
                break;
        }
    }
}

package com.example.yourwelcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class Home extends AppCompatActivity implements View.OnClickListener{

    Button b1,b2;
    int contador=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Enlace con GUI
        b1 = (Button) findViewById(R.id.inlineID);
        // Agregando escuchador
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Hola Inline",Toast.LENGTH_LONG).show();
            }
        });
        //Enlace con GUI
        b2 = (Button) findViewById(R.id.interfaceID);
        b2.setOnClickListener(this);

        //recibir datos
        Bundle recibo = getIntent().getExtras();
        if(recibo!=null){
            contador =recibo.getInt("contadorActual");
            Toast.makeText(getApplicationContext(),"Hola contador"+contador,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //recibir datos
        Bundle recibo = getIntent().getExtras();
        if(recibo!=null){
            contador =recibo.getInt("contadorActual");
            Toast.makeText(getApplicationContext(),"Hola2 contador"+contador,Toast.LENGTH_LONG).show();
        }
    }

    public void IrFrag(View k){
        Intent volver = new Intent(Home.this,FragActivity.class);
        volver.addFlags(volver.FLAG_ACTIVITY_CLEAR_TASK | volver.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(volver);
    }

    public void Volver(View i){
        Intent volver = new Intent(Home.this,MainActivity.class);
        volver.addFlags(volver.FLAG_ACTIVITY_CLEAR_TASK | volver.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(volver);
    }
    public void irPulso(View p){
        Intent volver = new Intent(Home.this,Contador.class);
        volver.addFlags(volver.FLAG_ACTIVITY_CLEAR_TASK | volver.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle entrega = new Bundle();
        entrega.putInt("contadorActual",contador);
        volver.putExtras(entrega);
        startActivity(volver);
    }
    public void irServicio(View p){
        Intent volver = new Intent(Home.this,Servicio.class);
        volver.addFlags(volver.FLAG_ACTIVITY_CLEAR_TASK | volver.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(volver);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.interfaceID:
                Toast.makeText(getApplicationContext(),"Hola interface",Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(getApplicationContext(),"Ninguna opcion",Toast.LENGTH_LONG).show();
        }
    }
}
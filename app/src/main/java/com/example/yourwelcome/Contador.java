package com.example.yourwelcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Contador extends AppCompatActivity {
    int contador = 0;
    int contador2 = 0;
    TextView campo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ///alternativa  onRestoreInstanceState
        if(savedInstanceState!=null){
            contador2= savedInstanceState.getInt("contadorActual");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);
        //enlace
        campo = (TextView)findViewById(R.id.campo);

        //recibir datos
        Bundle recibo = getIntent().getExtras();
        if(recibo!=null){
            contador =recibo.getInt("contadorActual");
            Toast.makeText(getApplicationContext(),"Hola contador"+contador,Toast.LENGTH_LONG).show();
        }
    }

    public void Pulso(View f){
        contador++;
        campo.setText(""+contador);
    }

    public void RegresarHome(View p){
        Intent volver = new Intent(Contador.this,Home.class);
        volver.addFlags(volver.FLAG_ACTIVITY_CLEAR_TASK | volver.FLAG_ACTIVITY_CLEAR_TOP);

        Bundle datos = new Bundle();
        datos.putInt("contadorActual",contador);
        volver.putExtras(datos);
        startActivity(volver);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("contadorActual",contador);
    }

    @Override
    protected void onRestoreInstanceState(Bundle datos) {
        super.onRestoreInstanceState(datos);
        contador=datos.getInt("contadorActual");
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed(); // comentando esto se deshabilitara el boton de ir atras.
        //Bundle bundle = new Bundle();
        //bundle.putInt("contadorActual", contador);
        //onSaveInstanceState(bundle);
        //Toast.makeText(getApplicationContext(),"onBackPressed"+contador,Toast.LENGTH_LONG).show();
    }
}
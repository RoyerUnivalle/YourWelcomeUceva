package com.example.yourwelcome;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yourwelcome.Servicios.Alerta;

public class Servicio extends AppCompatActivity {

    TextView pintar;
    int entero=0;

    Pintar obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio);
        pintar = (TextView) findViewById(R.id.colorear);

        //pintar.setBackgroundColor(Color.rgb(55,55,69));
    }
    public void VolverH(View i){
        Intent volver = new Intent(Servicio.this,Home.class);
        volver.addFlags(volver.FLAG_ACTIVITY_CLEAR_TASK | volver.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(volver);
    }
    public void IniciarServicio(View i){
        Intent volver = new Intent(Servicio.this,Alerta.class);
        //volver.addFlags(volver.FLAG_ACTIVITY_CLEAR_TASK | volver.FLAG_ACTIVITY_CLEAR_TOP);
        startService(volver);
    }
    public void Pintar1(View r){
        for (int i=0;i<30;i++){
            try {
                pintar.setBackgroundColor(Color.rgb(aleatorio(),aleatorio(),aleatorio()));
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void Pintar2(View r){
        obj = new Pintar();
        obj.execute();
    }

    //<Params, Progress, Result>
    public class Pintar extends AsyncTask<Void,Integer,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            for (int i=0;i<30;i++){
                try {
                    publishProgress(i);// LLAMA AL METODO onProgressUpdate, QUIEN ES EL QUE TIENEN ACEESO A LA GUI
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... datos) {
            super.onProgressUpdate(datos);
            pintar.setBackgroundColor(Color.rgb(aleatorio(),aleatorio(),aleatorio()));
            pintar.setText(""+datos[0]);
            Toast.makeText(getApplicationContext(),"Hola asyntask"+datos[0],Toast.LENGTH_LONG).show();
        }

    }

    public int aleatorio(){
        int numero = (int) (Math.random() * 255) + 1;
        return numero;
    }

}

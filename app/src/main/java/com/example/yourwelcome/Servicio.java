package com.example.yourwelcome;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yourwelcome.Servicios.Alerta;

public class Servicio extends AppCompatActivity {

    TextView pintar;
    int entero=0;
    int flag=0;

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
        //AlertDialog dialgo= new AlertDialog();
        AlertDialog alertDialog = new AlertDialog.Builder(Servicio.this).create();
        alertDialog.setTitle("Información");
        alertDialog.setMessage("Desea ejecutar el ejemplo en el mismo proceso");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i=0;i<30;i++){
                            try {
                                pintar.setBackgroundColor(Color.rgb(aleatorio(),aleatorio(),aleatorio()));
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
    public void Pintar2(View r){
        //
        if(flag==1){
            obj.cancel(true);
            obj = new Pintar();
            obj.execute();
            Toast.makeText(this,""+obj.getStatus().toString(),Toast.LENGTH_SHORT).show();
        }else{
            obj = new Pintar();
            obj.execute();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        obj.cancel(true);
    }

    //<Params, Progress, Result>
    public class Pintar extends AsyncTask<Void,Integer,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            flag=1;
        }
        @Override
        protected Void doInBackground(Void... params) {
            for (int i=0;i<=30;i++){
                if(obj.isCancelled()){
                   break;
                }else{
                    try {
                        publishProgress(i);// LLAMA AL METODO onProgressUpdate, QUIEN ES EL QUE TIENEN ACEESO A LA GUI
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... datos) {
            super.onProgressUpdate(datos);
            pintar.setBackgroundColor(Color.rgb(aleatorio(),aleatorio(),aleatorio()));
            pintar.setText(""+datos[0]);
            Toast.makeText(getApplicationContext(),"Hola asyntask"+datos[0],Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            //obj=null;
            flag=0;
        }
    }

    public int aleatorio(){
        int numero = (int) (Math.random() * 255) + 1;
        return numero;
    }

}

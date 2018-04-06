package com.example.yourwelcome.Servicios;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Alerta extends Service {

    private String fecha;
    private  SimpleDateFormat dateFormat;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public Alerta() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        dateFormat = new SimpleDateFormat("HH:mm:ss");
        fecha = dateFormat.format(new Date());
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        for (int i=0;i<3;i++){
            try {
                Thread.sleep(4000);
                Toast.makeText(getApplicationContext()," Hi, I am a Service "+" >:"+i,Toast.LENGTH_SHORT).show();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*
        for (int i=0;i<=100;i++){
            if(i==1 || i==10 || i==20 || i==30 || i==40 || i==50 || i==60 || i==70 || i==80 || i==90|| i==100){
                Toast.makeText(getApplicationContext(),i+" La fecha es: "+fecha,Toast.LENGTH_SHORT).show();
            }
        }*/
        stopSelf(startId);//detener el servicio una vez se termina con la opeación
        //https://developer.android.com/guide/components/processes-and-threads.html

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext()," Servicio destruido "+fecha,Toast.LENGTH_SHORT).show();
    }
}

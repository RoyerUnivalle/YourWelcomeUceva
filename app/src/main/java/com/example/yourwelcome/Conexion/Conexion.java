package com.example.yourwelcome.Conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Roger on 16/04/2018.
 */

public class Conexion extends SQLiteOpenHelper {

    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    static  String  DATABASE_NAME="ProyectoM";
    String query="create table personas " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT);";

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void BD_backup() throws IOException {
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());

        final String inFileName = "/data/data/com.example.yourwelcome/databases/"+DATABASE_NAME;
        File dbFile = new File(inFileName);
        FileInputStream fis = null;

        fis = new FileInputStream(dbFile);

        //String directorio = obtenerDirectorioCopias();
        String directorio = "/storage/sdcard0/Download/";
        //String directorio = "/storage/extSdCard/Download";
        File d = new File(directorio);
        if (!d.exists()) {
            d.mkdir();
        }
        String outFileName = directorio + "/"+DATABASE_NAME + "_"+timeStamp;

        OutputStream output = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }

        output.flush();
        output.close();
        fis.close();
    }
}

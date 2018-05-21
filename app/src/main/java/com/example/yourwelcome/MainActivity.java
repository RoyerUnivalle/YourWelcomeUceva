package com.example.yourwelcome;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    boolean isMobile,IsWifi=false;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient googleApiClient;

    AccountManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        manager = AccountManager.get(this);

        findViewById(R.id.sign_in).setOnClickListener(this);
    }

    public void iniciar(View g){
        //Nueva intención
        Intent ir = new Intent(MainActivity.this,Home.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TASK | ir.FLAG_ACTIVITY_CLEAR_TOP);
        //Pasar a otra actividad
        startActivity(ir);
    }
    public void iniciarFrag(){
        //Nueva intención
        Intent ir = new Intent(MainActivity.this,HomeApp.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TASK | ir.FLAG_ACTIVITY_CLEAR_TOP);
        //Pasar a otra actividad
        startActivity(ir);
    }
    public void iniciarFragV(View g){
        iniciarFrag();
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public  boolean checkConnection(){
        Toast.makeText(this,"hola1",Toast.LENGTH_SHORT).show();
        boolean isConnet=false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork!=null){
            isConnet = activeNetwork.isConnectedOrConnecting();
        }
        if(isConnet && activeNetwork!=null){
            isMobile= activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
            IsWifi = activeNetwork.getType()== ConnectivityManager.TYPE_WIFI;
            return  true;
        }else{
            return  false;
        }
    }

    private  void signIn(){
        if(checkConnection()){
            Toast.makeText(this,"hola2",Toast.LENGTH_SHORT).show();
            if(isMobile){
                Toast.makeText(this,"hola3",Toast.LENGTH_SHORT).show();
                AlertDialog dialogo = new AlertDialog.Builder(this).create();
                dialogo.setTitle("Validar red");
                dialogo.setMessage("Desea consumir datos");
                dialogo.setCancelable(false);
                dialogo.setButton(DialogInterface.BUTTON_POSITIVE,"Aceptar",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                        startActivityForResult(signIntent,RC_SIGN_IN);
                    }
                });
                dialogo.setButton(DialogInterface.BUTTON_NEGATIVE, "La proxima", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogo.show();
            }else{
                Toast.makeText(this,"hola4",Toast.LENGTH_SHORT).show();
                Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signIntent,RC_SIGN_IN);
            }
        }else{
            Toast.makeText(this,"55",Toast.LENGTH_SHORT).show();
            AlertDialog dialogo = new AlertDialog.Builder(this).create();
            dialogo.setTitle("Sin conexion");
            dialogo.setMessage("No puede ingresar sin estar conectado");
            dialogo.setButton(DialogInterface.BUTTON_NEUTRAL, "Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialogo.show();
        }
    }

    public void alertaPersonalizada(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_signin, null));
        builder.create().show();
    }

    private void handleSignInResult(GoogleSignInResult result ) {
        if(result.isSuccess()){
            //alertaPersonalizada();
            iniciarFrag();
        }else{
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (RC_SIGN_IN==requestCode){
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            GoogleSignInResult resul = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(resul);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in:
                signIn();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this,"Hola Ondestroy",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(this,"Hola onStart",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(this,"Hola onStop",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(this,"Hola onResume",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(this,"Hola onPause",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Toast.makeText(this,"Hola onRestart",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

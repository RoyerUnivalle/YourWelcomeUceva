package com.example.yourwelcome;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yourwelcome.Conexion.Conexion;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragSPA.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragSPA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragSPA extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText edName;
    Button btnViewOptions,btnInsert;

    Conexion con;
    SQLiteDatabase db;

    private OnFragmentInteractionListener mListener;

    public FragSPA() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragSPA.
     */
    // TODO: Rename and change types and number of parameters
    public static FragSPA newInstance(String param1, String param2) {
        FragSPA fragment = new FragSPA();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);//parametro recibido desde la actividad que invocar
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //se recibe parametrso recarga las variables
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //para usar menus
        setHasOptionsMenu(true);
        DatabaseCreate();
    }

    public void DatabaseCreate(){
        con = new Conexion(getContext(),"ProyectoM",null,1);
        db = con.getWritableDatabase();
        if(con!=null){
            Toast.makeText(getContext(),"Base de datos creada",Toast.LENGTH_LONG).show();
        }
    }

    public void ingresarEstudiante(String personName){
        //String prueba=f.getResources().getResourceEntryName(R.id.btnExecuteBD);
        String estudiante ="insert into personas (nombre) values ('"+personName+"');";
        db.execSQL(estudiante);
        //¿La transacción fue exitosa?
        edName.setText("");
        //Toast.makeText(getContext(),"Estudiante creado ",Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate( R.layout.fragment_frag_s, container, false);

        edName = (EditText) view.findViewById(R.id.edName);
        btnInsert = (Button) view.findViewById(R.id.bntInsertar);
        btnViewOptions = (Button) view.findViewById(R.id.btnViewOptions);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String personName= edName.getText().toString().trim();
                ingresarEstudiante(personName);
            }
        });
        btnViewOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edName.setVisibility(View.VISIBLE);
                btnInsert.setVisibility(View.VISIBLE);
            }
        });

        // TODO Adjust layout params of inflated view here

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.frag_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean showMessage = false;
        String message="";
        // handle item selection
        switch (item.getItemId()) {
            case R.id.frag_item_one:
                message="Hola fragment item";
                showMessage=true;
                //return true;
        }
        if(showMessage) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setMessage(message);
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.pari.docente;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import com.pari.docente.Personas.Add.UseraddViewModel;
import com.pari.docente.R;

public class useradd extends Fragment implements
        Response.Listener<JSONObject>, Response.ErrorListener {
    private UseraddViewModel useraddViewModel;
    EditText camCodi, camNomb, camApellIdo, camCorreo;
    Button btnguardar;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    private OnFragmentInteractionListener mListener;
    public useradd() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.useradd_fragment, container, false);
        camCodi = vista.findViewById(R.id.campoCod);
        camNomb = vista.findViewById(R.id.campoNom);
        camApellIdo = vista.findViewById(R.id.campoApe);
        camCorreo = vista.findViewById(R.id.campoCorreo);
        btnguardar = vista.findViewById(R.id.btnGuardar);
        request = Volley.newRequestQueue(getContext());
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });

        return vista;
    }

    public void cargarWebService() {
        progreso = new ProgressDialog(getContext());
        progreso.setMessage("cargando ...");
        progreso.show();
        String url="http://192.168.0.25:80/bddocente/registrarAlumno.php?codigo="+camCodi.getText().toString()
                +"&nombre="+camNomb.getText().toString()+"&apellido="+camApellIdo.getText().toString()+"&correo="+camApellIdo.getText().toString();
        url=url.replace(" ","%20");
                jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
                request.add(jsonObjectRequest);
    }
    public void onButtonPressed(Uri uri){
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
    }
}
    @Override
        public void onErrorResponse(VolleyError error) {
    progreso.hide();
    Toast.makeText(getContext(), "No se pudo registrar"+ error.toString(), Toast.LENGTH_SHORT).show();
    Log.i("Error", error.toString());
}
    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "se ha registrado exitosamente", Toast.LENGTH_SHORT).show();
        progreso.hide();
        camCorreo.setText("");
        camCodi.setText("");
        camNomb.setText("");
        camApellIdo.setText("");

    }

public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
     void onFragmentInteraction(Uri uri);
}}
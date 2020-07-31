package com.pari.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class useradd2 extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    EditText camCodi, camNomb, camApellIdo, camCorreo;
    Button btnguardar;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    private useradd.OnFragmentInteractionListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useradd2);
        camCodi = findViewById(R.id.campoCod);
        camNomb = findViewById(R.id.campoNom);
        camApellIdo = findViewById(R.id.campoApe);
        camCorreo = findViewById(R.id.campoCorreo);
        btnguardar = findViewById(R.id.btnGuardar);
        request = Volley.newRequestQueue(this);
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });

    }

    private void cargarWebService() {
        progreso = new ProgressDialog(this);
        progreso.setMessage("cargando ...");
        progreso.show();
        String url="http://192.168.0.25:80/bddocente/registrarAlumno.php?codigo="+camCodi.getText().toString()
                +"&nombre="+camNomb.getText().toString()+"&apellido="+camApellIdo.getText().toString()+"&correo="+camCorreo.getText().toString();
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
        Toast.makeText(this, "No se pudo registrar"+ error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "se ha registrado exitosamente", Toast.LENGTH_SHORT).show();
        progreso.hide();
        camCorreo.setText("");
        camCodi.setText("");
        camNomb.setText("");
        camApellIdo.setText("");
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
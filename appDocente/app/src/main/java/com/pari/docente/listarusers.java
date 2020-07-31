package com.pari.docente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pari.docente.adapter.UsuariosAdapter;
import com.pari.docente.entidades.Alumnos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class listarusers extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject>, View.OnClickListener {
    private consultuser.OnFragmentInteractionListener mListener;
    RecyclerView recyclerUsuarios;
    ArrayList<Alumnos> listaUsuarios;
    Button btnAgregar, btnConsultar;


    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarusers);
        listaUsuarios = new ArrayList<Alumnos>();
        recyclerUsuarios = (RecyclerView)findViewById(R.id.idRecycler);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this));
        recyclerUsuarios.setHasFixedSize(true);
        request = Volley.newRequestQueue(this);
        cargarWebService();
        btnAgregar=findViewById(R.id.btnAgregarAlumno);
        btnConsultar=findViewById(R.id.btnConsutar);
        btnAgregar.setOnClickListener(this);
        btnConsultar.setOnClickListener(this);
    }

    private void cargarWebService() {
        progress = new ProgressDialog(this);
        progress.setMessage("Listando");
        progress.show();
        String url = "http://192.168.0.25:80/bddocente/listarAlumnos.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se pudo conectar "+ error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error",error.toString());
        progress.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        Alumnos usuario = null;
        JSONArray json = response.optJSONArray("alumnos");
        try {
            for(int i=0;i<json.length();i++){
                usuario = new Alumnos();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                usuario.setCodigo(jsonObject.optInt("codigo"));
                usuario.setNombre(jsonObject.optString("nombre"));
                usuario.setApellido(jsonObject.optString("apellido"));
                usuario.setCorreo(jsonObject.optString("correo"));
                listaUsuarios.add(usuario);
            }
            progress.hide();
            UsuariosAdapter adapter = new UsuariosAdapter(listaUsuarios);
            recyclerUsuarios.setAdapter(adapter);
        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(this,"No hay conexión con el servidor", Toast.LENGTH_SHORT).show();
            progress.hide();
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnAgregarAlumno:
                Intent j = new Intent(this, useradd2.class);
                startActivity(j);

            case R.id.btnConsutar:
                Intent i = new Intent(this, consultuser.class);
                startActivity(i);
                break;
        }
    }
}
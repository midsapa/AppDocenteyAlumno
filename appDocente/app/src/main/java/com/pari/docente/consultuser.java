package com.pari.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pari.docente.entidades.Alumnos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class consultuser extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    EditText camDocu;
    TextView txtNom, txtApe, txtCorreo;
    Button btnBuscar;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    ImageView campoImagen;
    private useradd.OnFragmentInteractionListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultuser);
        camDocu=findViewById(R.id.camCod);
        txtNom=findViewById(R.id.txtNombre);
        txtApe=findViewById(R.id.txtApellido);
        txtCorreo=findViewById(R.id.txtCorreo);
        btnBuscar=findViewById(R.id.btnConsutar);
        request= Volley.newRequestQueue(this);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargaWebService();
            }
        });
    }

    private void cargaWebService() {
        progreso = new ProgressDialog(this);
        progreso.setMessage("consultando...");
        progreso.show();
        String url = "http://192.168.0.25:80/bddocente/consultarAlumno.php?codigo="+camDocu.getText().toString();
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null,this,this);
        url=url.replace(" ", "%20");

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
        Toast.makeText(this, "Resultado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this, "No se pudo consultar "+error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Toast.makeText(this, "Mensaje " + response, Toast.LENGTH_SHORT).show();
        Alumnos usuario = new Alumnos();
        JSONArray json = response.optJSONArray("alumnos");
        JSONObject jsonObject = null;
        try {
            jsonObject = json.getJSONObject(0);
            usuario.setNombre(jsonObject.optString("nombre"));
            usuario.setApellido(jsonObject.optString("apellido"));
            usuario.setCorreo(jsonObject.optString("correo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        txtNom.setText(usuario.getNombre());
        txtApe.setText(usuario.getApellido());
        txtCorreo.setText(usuario.getCorreo());


    }

    public class OnFragmentInteractionListener {
    }
}
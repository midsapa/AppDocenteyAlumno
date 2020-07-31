package com.pari.docente;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class GrupoClases extends Fragment {
    RequestQueue rq;
    JsonRequest jrq;
    EditText nombreclase,seccion,asunto;
    Button guardargrupos,atarsgrupos;
    String usr_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_grupo_clases, container, false);
        nombreclase=view.findViewById(R.id.edtnombreClase_cg);
        seccion=view.findViewById(R.id.edtseccion_cg);
        asunto=view.findViewById(R.id.edtasunto_cg);
        guardargrupos=view.findViewById(R.id.btnGuardarGruposnew);

        SharedPreferences preferences = getActivity().getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);

        usr_name = preferences.getString("usr_nombre","");

        guardargrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicio("http://farmaciafarmax.net/clases/services/docente/lessons.php");
            }
        });
        atarsgrupos=view.findViewById(R.id.btnAtrasgruposnew);
        atarsgrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction frage= getFragmentManager().beginTransaction();
                frage.replace(R.id.contenedor, new Gruposdeclases());
                frage.commit();
                //servicio("http://192.168.0.25:80/bddocente/Grupos_Clases.php");
            }
        });
        rq=Volley.newRequestQueue(getContext());

        return view;
    }
    public void servicio(String URL){
       StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               Toast.makeText(getActivity().getApplicationContext(), "todo genial !!", Toast.LENGTH_SHORT).show();
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
           }
       }){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String>parametros=new HashMap<String,String>();
               parametros.put("lesson_name",nombreclase.getText().toString());
               //parametros.put("Seccion",seccion.getText().toString());
               //parametros.put("Asunto",asunto.getText().toString());
               parametros.put("username",usr_name);

               return parametros;
           }
       };
        RequestQueue requestQueue= Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);
    }
}


package com.pari.docente;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.print.PrinterId;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Gruposdeclases extends Fragment  implements  Response.Listener<JSONObject>,Response.ErrorListener{
    private static final String url_curs="http://farmaciafarmax.net/clases/services/lessons.php";
    //private static final String user="root";
    //private static final String pass="";
    List<modelocursos>modelocursosList;
    //RecyclerView recyclerView;
    ProgressDialog progress;
    RequestQueue requestQueue;
    JSONObject jsonObject;
    Button btnentrar,btnunirse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_gruposdeclases, container, false);
        //recyclerView=(RecyclerView) view.findViewById(R.id.regrupo);
        modelocursosList =new ArrayList<>();
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        //requestQueue=Volley.newRequestQueue(getContext());
        btnunirse = view.findViewById(R.id.btnunirse);
        btnunirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Cursos.class));
            }
        });

        btnentrar = view.findViewById(R.id.btncreargrupo);
        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction frag = getFragmentManager().beginTransaction();
                frag.replace(R.id.contenedor, new GrupoClases());
                frag.commit();
            }
        });
        //cargardatos();
        return view;
    }

    private void cargardatos() {
    }

    /*StringRequest stringRequest= new StringRequest(Request.Method.GET, url_curs, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject modelocursos = array.getJSONObject(i);
                            modelocursosList.add(new modelocursos(
                                    modelocursos.getString("id"),
                                    modelocursos.getString("Nombre_Curso"),
                                    modelocursos.getString("Seccion"),
                                    modelocursos.getString("Asunto")
                            ));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

        }

});*/

    @Override
    public void onErrorResponse(VolleyError error) {
        
    }

    @Override
    public void onResponse(JSONObject response) {

    }
}

package com.pari.docente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class fragments extends AppCompatActivity {
    ArrayList<String> dato2,dato3,dato4,dato5;
    String id,nombre_grupo,seccion,asunto;
    RequestQueue queue2;
    RecyclerView recyclerView;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contenedor, new Gruposdeclases());
        fragmentTransaction.commit();
        queue2 = Volley.newRequestQueue(this);
        recicler();
    }

    private void recicler()
    {
        recyclerView = findViewById(R.id.re);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //dato1 = new ArrayList<String>();
        dato2 = new ArrayList<String>();
        dato3 = new ArrayList<String>();
        dato4 = new ArrayList<String>();
        dato5 = new ArrayList<String>();

        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);

        String usr_name = preferences.getString("usr_nombre","");

        String url = "http://192.168.0.25:80/bddocente/see_grupos.php?usr_id=321";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("valores");

                    for (int i = 0 ; i < array.length() ; i++) {
                        JSONObject a = array.getJSONObject(i);
                        //ID = a.getString("id");
                        id = a.getString("id");
                        nombre_grupo = a.getString("Nombre_Clase");
                        seccion = a.getString("Seccion");
                        asunto = a.getString("Asunto");

                        dato2.add(id);
                        dato3.add(nombre_grupo);
                        dato4.add(seccion);
                        dato5.add(asunto);
                    }
                    Adapter_items adapter_items = new Adapter_items(/*dato1, */dato2, dato3, dato4, dato5,new Adapter_items.MyAdapterListener() {
                        @Override
                        public void eliminar(View v, int position) {
                            //String a = dato1.get(position);
                            String id = dato2.get(position);
                            String c = dato4.get(position);

                        }
                    })    ;
                    recyclerView.setAdapter(adapter_items);

                } catch (JSONException e) {
                    Toast.makeText(fragments.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(fragments.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue2.add(request);

    }

}
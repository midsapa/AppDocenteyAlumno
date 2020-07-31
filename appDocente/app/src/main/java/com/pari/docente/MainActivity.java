package com.pari.docente;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtcorreo,txtcontraseña;
    Button btnentrar, crear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.titulomodificado);
        txtcorreo=findViewById(R.id.correologin);
        txtcontraseña=findViewById(R.id.contraseñalogin);
        crear=findViewById(R.id.crearcuentalogin);

        btnentrar= findViewById(R.id.entrarlogin);


        crear.setOnClickListener(this);
        btnentrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.entrarlogin:
                //Intent j = new Intent(this, fragments.class);
                //startActivity(j);
                if (txtcontraseña.getText().toString().length()<1 || txtcorreo.getText().toString().length()<1){
                Toast.makeText(MainActivity.this, "Campo contraseña o correo vacio", Toast.LENGTH_SHORT).show();
                }else{
                validarUsuario("http://farmaciafarmax.net/clases/services/login2.php");
                }

            break;

            case R.id.crearcuentalogin: {
                Intent i = new Intent(this, Alumno.class);
                startActivity(i);
            }break;


        }
    }
    private void validarUsuario(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()){

                    String string = response;
                    String[] parts = string.split("//");
                    String part1 = parts[0];
                    String part2 = parts[1];
                    String part3 = parts[2];

                    if (part1.length()>2){
                        guardarPreferencias(part3);
                        Toast.makeText(MainActivity.this,"Bienvenido: "+part3,Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),fragments.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(MainActivity.this,"Credenciales inválidas2!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Credenciales inválidas!",Toast.LENGTH_SHORT).show();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String>getParams()throws AuthFailureError{
                Map<String,String>parametro=new HashMap<String,String>();
                parametro.put("usr",txtcorreo.getText().toString());
                parametro.put("pwd",txtcontraseña.getText().toString());
                return parametro;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void guardarPreferencias(String usr_nombre){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username",usr_nombre);
        editor.commit();
        Toast.makeText(MainActivity.this,"Credenciales guardadas!",Toast.LENGTH_SHORT).show();
    }
}

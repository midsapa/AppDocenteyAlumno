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
                validarUsuario("http://192.168.0.25:80/bddocente/validar_usuario.php");
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
                if (response.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrecta ", Toast.LENGTH_SHORT).show();
                } else {
                    guardarPreferencias(txtcorreo.getText().toString());
                    Toast.makeText(MainActivity.this, "bienvenido :) ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), fragments.class);

                    startActivity(intent);
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
                parametro.put("password",txtcontraseña.getText().toString());
                parametro.put("correo",txtcorreo.getText().toString());
                return parametro;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void guardarPreferencias(String usr_nombre){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usr_nombre",usr_nombre);
        editor.commit();
        Toast.makeText(MainActivity.this,"Credenciales guardadas!",Toast.LENGTH_SHORT).show();
    }
}

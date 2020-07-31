package com.pari.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
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
import java.util.regex.Pattern;

public class Alumno extends AppCompatActivity {

    EditText txtNombre,txtUsuario,txtcorreo,txtcelular,txtpassword;

    Button btnregistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);

        txtNombre=(EditText) findViewById(R.id.nombre);
        txtUsuario=(EditText)findViewById(R.id.usuariore);
        txtcorreo=(EditText) findViewById(R.id.correo);
        txtcelular=(EditText) findViewById(R.id.celular);
        txtpassword=(EditText) findViewById(R.id.password);

        btnregistrar=(Button) findViewById(R.id.registrar);

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validarEmail(txtcorreo.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Correo inválido.",Toast.LENGTH_SHORT).show();
                }else{
                    ejecutar("http://farmaciafarmax.net/clases/services/proses-api.php");
                }
            }
        });
    }

    private void ejecutar(String URL){
        StringRequest StringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Usuario registrado!?",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }else{
                    Toast.makeText(getApplicationContext(),"Error al registrar!",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("fullname",txtNombre.getText().toString());
                parametros.put("username",txtUsuario.getText().toString());
                parametros.put("email",txtcorreo.getText().toString());
                parametros.put("teléfono",txtcelular.getText().toString());
                parametros.put("contraseña",txtpassword.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(StringRequest);
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

}

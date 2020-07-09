package com.pari.docente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class crearactividad extends AppCompatActivity implements View.OnClickListener {

    Button btncreartarea,btncreartema;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_actividad);
        btncreartarea=findViewById(R.id.btnnuevatarea);
        btncreartema=findViewById(R.id.btnnuevotema23);

       btncreartarea.setOnClickListener(this);
       btncreartema.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
           case R.id.btnnuevatarea:
               Intent i = new Intent(this,fragmentNew.class);
               startActivity(i);
               break;
            case R.id.btnnuevotema23:
                Intent j = new Intent(this,fragnentcontTema.class);
                startActivity(j);
        }

    }
}

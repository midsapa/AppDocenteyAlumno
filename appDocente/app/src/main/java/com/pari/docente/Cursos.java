package com.pari.docente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabItem;

public class Cursos extends AppCompatActivity implements View.OnClickListener {

    Button btnnuevoact;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);
        btnnuevoact = findViewById(R.id.btniractividad);

        btnnuevoact.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btniractividad:
                Intent j = new Intent(this, crearactividad.class);
                startActivity(j);
                break;

        }
    }

}

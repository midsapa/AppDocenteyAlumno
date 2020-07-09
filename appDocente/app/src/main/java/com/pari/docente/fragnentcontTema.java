package com.pari.docente;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class fragnentcontTema extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentcontema);
        FragmentTransaction fragmentTransaction2=getSupportFragmentManager().beginTransaction();
        fragmentTransaction2.add(R.id.contenedortema, new Tema());
        fragmentTransaction2.commit();

    }
}

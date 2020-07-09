package com.pari.docente;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class fragment_agregartarea extends Fragment {
 EditText edttitulo,edtintrucciones,edtpuntos, edtfechatarea;
 private Spinner tema;
 //private AsyncTask;
 Button btncrearclase,btncancelar,btnfecha;
 Calendar c=Calendar.getInstance();
 int yy=c.get(Calendar.YEAR);
 int mm=c.get(Calendar.MONTH);
 int dd=c.get(Calendar.DAY_OF_MONTH);
 int hor=c.get(Calendar.HOUR_OF_DAY);
 int min=c.get(Calendar.MINUTE);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_agregartarea, container, false);
        edttitulo=view.findViewById(R.id.edtTitulotarea);
        edtintrucciones=view.findViewById(R.id.edtInstrucciones);
        edtpuntos=view.findViewById(R.id.edtpuntos);
        edtfechatarea=view.findViewById(R.id.edtfechatarea);
        btncrearclase=view.findViewById(R.id.btnguarfarTarea);
        btnfecha=view.findViewById(R.id.btnfechatarea);
        tema=view.findViewById(R.id.spiTema);
        edtfechatarea.setEnabled(false);
        btnfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tenerfecha();
            }
        });
       btncrearclase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicio("http://192.168.0.25:80/bddocente/crear_tema.php");
            }
       });
       return view;
    }
    public void tenerfecha(){
        DatePickerDialog obtenerfecha=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int mesdehoy=month +1;
                String dianuevo=(dayOfMonth <10)?"0" + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesnuevo=(mesdehoy <10)?"0" + String.valueOf(mesdehoy):String.valueOf(mesdehoy);
                edtfechatarea.setText(dianuevo + "/" + mesnuevo + "/" + year);
            }

        },yy,mm,dd);
        obtenerfecha.show();
    }
    /*public void tenerHora(){
        DatePickerDialog obtenerhora=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int hour, int minute) {
                int horadehoy=hour +1;
                String horanuevo=(hour <10)?"0" + String.valueOf(hourOfDay):String.valueOf(dayOfMonth);
                String minutonuevo=(minute <10)?"0" + String.valueOf(mesdehoy):String.valueOf(mesdehoy);
                edtfechatarea.setText(horanuevo + "/" + minutonuevo + "/" );
            }

        },yy,mm,dd);
        obtenerhora.show();
    }*/
    private void llenarSpiner(){
        String URL="http://192.168.0.25:80/bddocente/crear_tema.php";

    }
    public void servicio(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity().getApplicationContext(), "todo genial", Toast.LENGTH_SHORT).show();
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
                parametros.put("titulo",edttitulo.getText().toString());
                parametros.put("instruciones",edtintrucciones.getText().toString());
                parametros.put("puntos",edtpuntos.getText().toString());
                parametros.put("fecha_entrega",edtfechatarea.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);
    }
}



package com.example.subirimagenesaplicacion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    EditText et;
    Button btnBuscar, btnSubir;

    Bitmap bitmap;
    int PICK_IMAGE_REQUEST = 1;
    String URL_UPLOAD = "http://192.168.1.34:80/prueba/upload.php";

    String KEY_IMAGE = "foto";
    String KEY_NOMBRE = "nombre";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.imageView);
        et = findViewById(R.id.editText);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnSubir = findViewById(R.id.btnSubir);

        //ACCION AL BOTON BUSCAR
        btnBuscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showFileChooser();
            }
        });
        //ACCION BOTON SUBIR
        btnSubir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                uploadImagen();
            }
        });
    }
    //CONVIERTE LA IMAGEN EN UN STRING
    public String getStringImage(Bitmap bmp)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100,baos);
        byte[] imagebytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imagebytes, Base64.DEFAULT);

        return encodedImage;
    }

    // SUBIR IMAGEN
    public void uploadImagen() {
        final ProgressDialog cargando = ProgressDialog.show(this,"subiendo ...","Espere por favor");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPLOAD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                cargando.dismiss();
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    cargando.dismiss();
                    Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                String imagen = getStringImage(bitmap);
                String nombre = et.getText().toString().trim();

                Map<String, String> params = new Hashtable<String,String>();
                params.put(KEY_IMAGE, imagen);
                params.put(KEY_NOMBRE, nombre);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    //PARA SELECCIONAR UNA IMAGEN
    private void showFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null){
            Uri filePath = data.getData();
            try{
                    //COMO OBTENER EL MAPA DE BITS DE LA GALERIA
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                    //CONFIGURACIÓN DEL MAPA DE BITS DE LA GALERIA
                iv.setImageBitmap(bitmap);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
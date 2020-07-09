package com.pari.docente;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_items extends RecyclerView.Adapter<Adapter_items.ViewHolderDatos> {
    private ArrayList<String> /*datos1,*/datos2,datos3,datos4,datos5;

    Adapter_items(/*ArrayList<String> adatos1,*/ArrayList<String> adatos2,ArrayList<String> adatos3,ArrayList<String> adatos4,ArrayList<String> adatos5,MyAdapterListener listener){
        //this.datos1 = adatos1;
        this.datos2 = adatos2;
        this.datos3 = adatos3;
        this.datos4 = adatos4;
        this.datos5 = adatos5;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_recycler,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_items.ViewHolderDatos holder, int position) {
        holder.ponerDatos(/*datos1.get(position),*/datos2.get(position),datos3.get(position), datos4.get(position),datos5.get(position));
    }

    @Override
    public int getItemCount() {
        return datos2.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView txt2,txt3,txt4,txt5;
        Button eliminar;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            //txt1 = itemView.findViewById(R.id.item1);
            txt2 = itemView.findViewById(R.id.txtid);
            txt3 = itemView.findViewById(R.id.txtnombre);
            txt4 = itemView.findViewById(R.id.txtseccion);
            txt5 = itemView.findViewById(R.id.txtasunto);
            eliminar =itemView.findViewById(R.id.eliminar);

            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.eliminar(v,getAdapterPosition());
                }
            });
        }
        public void ponerDatos(/*String a1,*/String a2,String a3 , String a4,String a5){
            //txt1.setText(a1);
            txt2.setText(a2);
            txt3.setText(a3);
            txt4.setText(a4);
            txt5.setText(a5);
        }
    }
    public MyAdapterListener onClickListener;
    public interface MyAdapterListener {

        void eliminar(View v, int position);
    }
}
package com.pari.docente.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pari.docente.entidades.Alumnos;

import java.util.List;

import com.pari.docente.R;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.UsuariosHolder> {
    List<Alumnos> listaUsuarios;
    public UsuariosAdapter(List<Alumnos> listaUsuarios){
        this.listaUsuarios = listaUsuarios;
    }
    @NonNull
    @Override
    public UsuariosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuarios_list,parent,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosAdapter.UsuariosHolder holder, int position) {
        holder.txtCodigo.setText(String.valueOf(listaUsuarios.get(position).getCodigo()));
        holder.txtNombre.setText(String.valueOf(listaUsuarios.get(position).getNombre()));
        holder.txtApellido.setText(String.valueOf(listaUsuarios.get(position).getApellido()));
        holder.txtCorreo.setText(String.valueOf(listaUsuarios.get(position).getCorreo()));
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuariosHolder extends RecyclerView.ViewHolder {
        TextView txtCodigo, txtNombre, txtApellido, txtCorreo;
        public UsuariosHolder(@NonNull View itemView){
            super(itemView);
            txtCodigo=itemView.findViewById(R.id.ultxtCódigo);
            txtNombre = itemView.findViewById(R.id.ultxtNombre);
            txtApellido = itemView.findViewById(R.id.ultxtApellido);
            txtCorreo = itemView.findViewById(R.id.ultxtCorreo);
        }
    }
}

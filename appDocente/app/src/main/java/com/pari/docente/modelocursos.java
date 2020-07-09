package com.pari.docente;

public class modelocursos {
    public String idgrupo;
    public String nombre_grupo;
    public String Seccion;
    public String Asunto;

    public modelocursos(String idgrupo,String nombre_grupo,String seccion,String asunto){
        this.idgrupo = idgrupo;
        this.nombre_grupo = nombre_grupo;
        this.Seccion= seccion;
        this.Asunto = asunto;
    }

    public String getIdgrupo() {
        return idgrupo;
    }

    public String getNombre_grupo() {
        return nombre_grupo;
    }

    public String getSeccion() {
        return Seccion;
    }

    public String getAsunto() {
        return Asunto;
    }
}

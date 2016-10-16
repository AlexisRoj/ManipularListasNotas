package com.nextuniversity.notas.modelo;

import java.util.Calendar;

public class Nota {

    private Integer identificador;

    private String titulo;

    private String descripcion;

    private Calendar fechaModificacion;

    public Nota() {
        this.fechaModificacion = Calendar.getInstance();
    }

    public Nota(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaModificacion = Calendar.getInstance();
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
        this.fechaModificacion = Calendar.getInstance();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        this.fechaModificacion = Calendar.getInstance();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        this.fechaModificacion = Calendar.getInstance();
    }

    public Calendar getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Calendar fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}

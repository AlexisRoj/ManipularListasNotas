package com.nextuniversity.notas.servicio;

import com.nextuniversity.notas.modelo.Nota;

import java.util.ArrayList;
import java.util.List;

public class ServicioNota {

    private List<Nota> notas;

    private ServicioNota() {
        this.notas = new ArrayList<>();

        this.agregar(new Nota("Primera nota", "Descripción de la primera nota"));
        this.agregar(new Nota("Segunda nota", "Descripción de la segunda nota"));
    }

    private static final ServicioNota INSTANCE = new ServicioNota();

    public static ServicioNota getInstance() {
        return INSTANCE;
    }

    public Nota agregar(Nota nota) {
        nota.setIdentificador(notas.size() + 1);
        notas.add(nota);
        return nota;
    }

    public Nota modificar(Nota nota) {
        if (nota.getIdentificador() == null)
            return null;

        int posicion = nota.getIdentificador() - 1;
        notas.set(posicion, nota);
        return nota;
    }

    public Nota eliminar(Integer identificacion) {
        if (identificacion == null)
            return null;

        return notas.remove(identificacion - 1);
    }

    public List<Nota> listar() {
        return notas;
    }

    public Nota obtenerNota(Integer posicion) {
        if (posicion != null)
            return notas.get(posicion);
        return null;
    }

}

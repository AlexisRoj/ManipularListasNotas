package com.nextuniversity.notas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListaNotasFragment.INotaSeleccionada {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_lista_nota) == null)
            getSupportFragmentManager().beginTransaction()
            .add(R.id.contenedor, new ListaNotasFragment())
            .commit();
    }

    @Override
    public void notaSeleccionada(int posicion) {
        NotaDetalleFragment fragment = (NotaDetalleFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_nota_detalle);

        if (fragment != null) {
            fragment.actualizarVista(posicion);
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, NotaDetalleFragment.getInstance(posicion))
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void clicAgregar() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor, new NotaDetalleFragment())
                .addToBackStack(null)
                .commit();
    }

}

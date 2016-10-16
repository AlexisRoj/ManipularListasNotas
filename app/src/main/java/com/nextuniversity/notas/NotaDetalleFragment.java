package com.nextuniversity.notas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nextuniversity.notas.modelo.Nota;
import com.nextuniversity.notas.servicio.ServicioNota;

import java.text.DateFormat;

public class NotaDetalleFragment extends Fragment {

    EditText editTitulo;
    EditText editDescripcion;

    TextView textId;
    TextView textFechaModificacion;

    Button btnGuardar;
    Button btnEliminar;

    private int posicionActual = -1;

    private static final String LLAVE_POSICION = "posicion";

    public static NotaDetalleFragment getInstance(int posicion) {
        NotaDetalleFragment fragment = new NotaDetalleFragment();

        Bundle argumentos = new Bundle();
        argumentos.putInt(LLAVE_POSICION, posicion);

        fragment.setArguments(argumentos);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null)
            this.posicionActual = savedInstanceState.getInt(LLAVE_POSICION);

        return inflater.inflate(R.layout.fragment_nota_detalle, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(LLAVE_POSICION, posicionActual);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTitulo = (EditText) view.findViewById(R.id.edit_titulo);
        editDescripcion = (EditText) view.findViewById(R.id.edit_descripcion);

        textId = (TextView) view.findViewById(R.id.text_id);
        textFechaModificacion = (TextView) view.findViewById(R.id.text_modificacion);

        btnGuardar = (Button) view.findViewById(R.id.btn_guardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicGuardar();
            }
        });

        btnEliminar = (Button) view.findViewById(R.id.btn_eliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicEliminar();
            }
        });
    }

    private void clicEliminar() {
        if (posicionActual != -1) {
            ServicioNota.getInstance().eliminar(posicionActual + 1);

            getActivity().onBackPressed();
        }
    }

    private void clicGuardar() {
        ServicioNota servicioNota = ServicioNota.getInstance();

        String titulo = editTitulo.getText().toString();
        String descripcion = editDescripcion.getText().toString();

        if (posicionActual != -1) {
            Nota nota = servicioNota.obtenerNota(posicionActual);
            nota.setTitulo(titulo);
            nota.setDescripcion(descripcion);
            servicioNota.modificar(nota);
        } else {
            Nota nuevaNota = new Nota(titulo, descripcion);
            servicioNota.agregar(nuevaNota);
        }

        getActivity().onBackPressed();
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle argumentos = getArguments();
        if (argumentos != null) {
            actualizarVista(argumentos.getInt(LLAVE_POSICION));
        } else if (posicionActual != -1) {
            actualizarVista(posicionActual);
        }
    }

    public void actualizarVista(int posicion) {
        if (posicion != -1) {
            Nota nota = ServicioNota.getInstance().obtenerNota(posicion);

            editTitulo.setText(nota.getTitulo());
            editDescripcion.setText(nota.getDescripcion());

            textId.setText("Identificador: " + nota.getIdentificador());

            DateFormat dateFormat = DateFormat.getDateTimeInstance();
            String modificacion = dateFormat.format(nota.getFechaModificacion().getTime());

            textFechaModificacion.setText(modificacion);
        }
        posicionActual = posicion;
    }
}

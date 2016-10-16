package com.nextuniversity.notas;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.nextuniversity.notas.adaptador.ListNotaAdapter;
import com.nextuniversity.notas.servicio.ServicioNota;

public class ListaNotasFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listNotas;

    Button btnAgregar;

    private INotaSeleccionada implementacion;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        implementacion.notaSeleccionada(position);
    }

    public interface INotaSeleccionada {
        void notaSeleccionada(int posicion);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            implementacion = (INotaSeleccionada) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException(context.toString()
                    + " debe implementar la interfaz INotaSeleccionada");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_notas, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listNotas = (ListView) view.findViewById(R.id.list_notas);
        actualizarAdapter();
        listNotas.setOnItemClickListener(this);

        this.btnAgregar = (Button) view.findViewById(R.id.btn_agregar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final MainActivity activity = (MainActivity) getActivity();
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.clicAgregar();
            }
        });
    }

    public void actualizarAdapter() {
        ListNotaAdapter adapter =
                new ListNotaAdapter(getContext(), ServicioNota.getInstance().listar());
        this.listNotas.setAdapter(adapter);
    }
}


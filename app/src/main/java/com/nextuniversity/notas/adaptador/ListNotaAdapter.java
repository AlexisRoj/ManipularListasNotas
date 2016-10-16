package com.nextuniversity.notas.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nextuniversity.notas.R;
import com.nextuniversity.notas.modelo.Nota;

import java.text.DateFormat;
import java.util.List;

public class ListNotaAdapter extends BaseAdapter {

    private Context context;

    private List<Nota> notas;

    public ListNotaAdapter(Context context, List<Nota> notas) {
        this.context = context;
        this.notas = notas;
    }

    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Object getItem(int position) {
        return notas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notas.get(position).getIdentificador();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_nota, null);
        }

        Nota nota = notas.get(position);

        ((TextView) view.findViewById(R.id.text_titulo)).setText(nota.getTitulo());
        ((TextView) view.findViewById(R.id.text_descripcion)).setText(nota.getDescripcion());

        DateFormat dateFormat = DateFormat.getDateInstance();
        String modificacion = dateFormat.format(nota.getFechaModificacion().getTime());

        ((TextView) view.findViewById(R.id.text_modificacion)).setText(modificacion);

        return view;
    }
}

package com.example.jokenpo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<ItemLista> itens;

    public MyAdapter(Context context, ArrayList<ItemLista> itens) {
        this.itens = itens;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ItemLista item = itens.get(position);
        view = inflater.inflate(R.layout.item_lista, null);

        TextView nome = view.findViewById(R.id.nome);
        TextView vitoria = view.findViewById(R.id.vitoria);
        TextView empate = view.findViewById(R.id.empate);
        TextView derrota = view.findViewById(R.id.derrota);

        nome.setText(item.getNome());
        vitoria.setText(String.valueOf(item.getVitorias()));
        empate.setText(String.valueOf(item.getEmpates()));
        derrota.setText(String.valueOf(item.getDerrotas()));

        return view;
    }
}

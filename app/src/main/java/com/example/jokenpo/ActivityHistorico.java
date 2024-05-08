package com.example.jokenpo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityHistorico extends AppCompatActivity {
    private ListView listViewHistorico;
    private MyAdapter myAdapter;
    private Controller controller = new Controller();
    private ArrayList<ItemLista> lista = new ArrayList<ItemLista>();
    private Button buttonVoltar;
    private static final String SHARED_PREFS = "jokenpo_preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        listViewHistorico = findViewById(R.id.listViewHistorico);
        buttonVoltar = findViewById(R.id.buttonVoltar);

        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        ArrayList<ItemLista> historico = controller.recuperarTodos(sp);

        myAdapter = new MyAdapter(this, historico);

        listViewHistorico.setAdapter(myAdapter);

        buttonVoltar.setOnClickListener(v -> finish());
    }
}

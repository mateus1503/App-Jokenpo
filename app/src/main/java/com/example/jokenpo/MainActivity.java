package com.example.jokenpo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome;
    private Button buttonIniciarJogo;
    private Button buttonHistorico;
    private static final String SHARED_PREFS = "jokenpo_preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        buttonIniciarJogo = findViewById(R.id.buttonIniciarJogo);
        buttonHistorico = findViewById(R.id.buttonHistorico);

        buttonIniciarJogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeJogador = editTextNome.getText().toString().trim();
                Toast.makeText(MainActivity.this, "Nome Salvo", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, JogarActivity.class);
                intent.putExtra("nomeJogador", nomeJogador); // Passando o nome do jogador para a JogarActivity
                startActivity(intent);
            }
        });

        buttonHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeJogador = editTextNome.getText().toString().trim();

                SharedPreferences sp = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

                Intent intent = new Intent(MainActivity.this, ActivityHistorico.class);
                intent.putExtra("nomeJogador", nomeJogador); // Passando o nome do jogador para a JogarActivity
                startActivity(intent);
            }
        });
    }
}


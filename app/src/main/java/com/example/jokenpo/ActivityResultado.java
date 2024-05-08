package com.example.jokenpo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityResultado extends AppCompatActivity {
    private Controller controller = new Controller();
    private ImageView imageViewResultado;
    private TextView textViewEscolha;
    private TextView textViewResultadoJogo;
    private Button buttonJogarNovamente;
    private int escolha;
    private static final String SHARED_PREFS = "jokenpo_preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        imageViewResultado = findViewById(R.id.imageViewResultado);
        textViewResultadoJogo = findViewById(R.id.textViewResultadoJogo);
        textViewEscolha = findViewById(R.id.textViewEscolha);
        buttonJogarNovamente = findViewById(R.id.buttonJogarNovamente);
        escolha = getIntent().getIntExtra("escolha", 0);
        String nomeJogador = getIntent().getStringExtra("nomeJogador");

        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        controller.sortear(imageViewResultado, textViewEscolha);
        controller.jogar(escolha, textViewResultadoJogo);
        controller.salvar(sp, nomeJogador);

        buttonJogarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityResultado.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}

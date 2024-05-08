package com.example.jokenpo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class JogarActivity extends AppCompatActivity {
     private int escolha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogar);
    }

    public void clicked(View view) {
        Intent intent = null;
        String nomeJogador = getIntent().getStringExtra("nomeJogador");

        if (R.id.imageViewPedra == view.getId()) {
            escolha = 0;
        } if (R.id.imageViewPapel == view.getId()) {
            escolha = 1;
        } if (R.id.imageViewTesoura == view.getId()) {
            escolha = 2;
        }
        intent = new Intent(this, ActivityResultado.class);
        intent.putExtra("escolha", escolha);
        intent.putExtra("nomeJogador", nomeJogador);
        startActivity(intent);
    }
}

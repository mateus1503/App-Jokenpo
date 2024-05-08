package com.example.jokenpo;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Controller {
    private Random random = new Random();
    private int sorteio = random.nextInt(3);
    private String resultadoPartida;
    private static final String SHARED_PREFS = "jokenpo_preferences";
    private static final String GAME_KEY = "partidas";
    private static final String VITORIA_KEY = "vitorias";
    private static final String EMPATE_KEY = "empates";
    private static final String DERROTA_KEY = "derrotas";

    public void sortear(ImageView imageViewResultado, TextView textViewEscolha) {
        int image = 0;
        int escolha = 0;
        if (sorteio == 0) {
            image = R.drawable.pedra;
            escolha = R.string.pedra;
        } else if (sorteio == 1) {
            image = R.drawable.papel;
            escolha = R.string.papel;
        } else if (sorteio == 2) {
            image = R.drawable.tesoura;
            escolha = R.string.tesoura;
        }

        textViewEscolha.setText(escolha);
        imageViewResultado.setImageResource(image);
    }

    public void jogar(int escolha, TextView textViewResultadoJogo) {
        int resultado = (sorteio - escolha + 3) % 3;

        int fraseResultado = 0;
        if (resultado == 0) {
            resultadoPartida = "Empatou";
            fraseResultado = R.string.empate;
        } else if (resultado == 1) {
            resultadoPartida = "Perdeu";
            fraseResultado = R.string.voc_perdeu;
        } else {
            resultadoPartida = "Vitória";
            fraseResultado = R.string.voc_venceu;
        }

        textViewResultadoJogo.setText(fraseResultado);
    }

    public void jogar1(int escolha, TextView textViewResultadoJogo, SharedPreferences sp, String nomeJogador) {
        int vitoria = sp.getInt(VITORIA_KEY + nomeJogador, 0);
        int empate = sp.getInt(EMPATE_KEY + nomeJogador, 0);
        int derrota = sp.getInt(DERROTA_KEY + nomeJogador, 0);
        SharedPreferences.Editor editor = sp.edit();

        int resultado = (sorteio - escolha + 3) % 3;

        int fraseResultado = 0;
        if (resultado == 0) {
            empate++;
            resultadoPartida = "Empatou";
            fraseResultado = R.string.empate;
        } else if (resultado == 1) {
            derrota++;
            resultadoPartida = "Perdeu";
            fraseResultado = R.string.voc_perdeu;
        } else {
            vitoria++;
            resultadoPartida = "Vitória";
            fraseResultado = R.string.voc_venceu;
        }

        textViewResultadoJogo.setText(fraseResultado);
        editor.putInt(VITORIA_KEY + nomeJogador, vitoria);
        editor.putInt(EMPATE_KEY + nomeJogador, empate);
        editor.putInt(DERROTA_KEY + nomeJogador, derrota);
    }

    public void salvar(SharedPreferences sp, String nomeJogador) {
        int jogadas = sp.getInt(GAME_KEY + nomeJogador, 0);
        jogadas++;

        ArrayList<ItemLista> listaJogadas = recuperar(sp, nomeJogador);
        listaJogadas.add(new ItemLista(nomeJogador, jogadas, resultadoPartida));

        SharedPreferences.Editor editor = sp.edit();
        Set<String> setJogadas = new HashSet<>();

        // Convertendo a lista de objetos ItemLista para uma lista de strings antes de salvar
        for (ItemLista item : listaJogadas) {
            setJogadas.add(item.getNome() + " " + item.getJogada() +
                    " " + item.getResultado());
        }

        editor.putStringSet("listajogadas_" + nomeJogador, setJogadas);
        editor.putInt(GAME_KEY + nomeJogador, jogadas);
        editor.apply();
    }

    public static ArrayList<ItemLista> recuperar(SharedPreferences sp, String nomeJogador) {
        Set<String> setJogadas = sp.getStringSet("listajogadas_" + nomeJogador, new HashSet<>());
        ArrayList<ItemLista> listaJogadas = new ArrayList<>();

        // Convertendo a lista de strings de volta para uma lista de objetos ItemLista
        for (String jogada : setJogadas) {
            String[] parts = jogada.split(" ");
            String jogador = parts[0];
            int jogadas = Integer.parseInt(parts[1]);
            String resultado = parts[2];

            listaJogadas.add(new ItemLista(jogador, jogadas, resultado));
        }

        return listaJogadas;
    }


    public static ArrayList<ItemLista> recuperarTodos(SharedPreferences sp) {
        ArrayList<ItemLista> todasJogadas = new ArrayList<>();
        Map<String, ?> allEntries = sp.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().startsWith("listajogadas_")) {
                Set<String> setJogadas = sp.getStringSet(entry.getKey(), new HashSet<>());

                // Convertendo a lista de strings de volta para uma lista de objetos ItemLista
                for (String jogada : setJogadas) {
                    String[] parts = jogada.split(" ");
                    String jogador = parts[0];
                    int jogadas = Integer.parseInt(parts[1]);
                    String resultado = parts[2];

                    todasJogadas.add(new ItemLista(jogador, jogadas, resultado));
                }
            }
        }

        return todasJogadas;
    }
}

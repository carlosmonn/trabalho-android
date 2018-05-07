package br.edu.unidavi.trabalhoandroid.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.controller.JogadorController;
import br.edu.unidavi.trabalhoandroid.model.Jogador;

public class JogadorActivity extends AppCompatActivity {

    private RecyclerView recyclerViewJogador;
    private JogadorAdapter adapterJogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogador);

        Intent intent = getIntent();
        int clubeId = intent.getIntExtra("clubeId", 0);

        JogadorController jogadorController = new JogadorController(getBaseContext());
        List<Jogador> listaJogadores = jogadorController.listarJogadoresClube(clubeId);

        recyclerViewJogador = findViewById(R.id.recycler_jogadores);
        recyclerViewJogador.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewJogador.setHasFixedSize(true);

        adapterJogador = new JogadorAdapter(new ArrayList<Jogador>(),this);
        adapterJogador.setJogadorList(listaJogadores);
        adapterJogador.notifyDataSetChanged();
        recyclerViewJogador.setAdapter(adapterJogador);
    }
}

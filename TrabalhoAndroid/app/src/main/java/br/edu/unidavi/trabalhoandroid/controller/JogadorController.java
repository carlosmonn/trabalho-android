package br.edu.unidavi.trabalhoandroid.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

import br.edu.unidavi.trabalhoandroid.datamodel.JogadorDataModel;
import br.edu.unidavi.trabalhoandroid.datasource.DataSource;
import br.edu.unidavi.trabalhoandroid.model.Jogador;

public class JogadorController extends DataSource {

    ContentValues dados;

    public JogadorController(Context context) {
        super(context);
    }

    public boolean salvar(Jogador jogador) {

        boolean sucesso = true;

        dados = new ContentValues();
        dados.put(JogadorDataModel.getId(), jogador.getId());
        dados.put(JogadorDataModel.getNome(), jogador.getNome());
        dados.put(JogadorDataModel.getPosicao(), jogador.getPosicao());
        dados.put(JogadorDataModel.getFoto(), jogador.getFoto());
        dados.put(JogadorDataModel.getClubeId(), jogador.getClubeId());

        sucesso = insert(JogadorDataModel.getTABELA(), dados);

        return sucesso;
    }

    public boolean deletarTodos() {

        boolean sucesso = true;

        sucesso = deletarTodos(JogadorDataModel.getTABELA());

        return sucesso;
    }

    public List<Jogador> listarJogadoresClube(int clubeId) {
        return getListarJogadoresClube(clubeId);
    }
}

package br.edu.unidavi.trabalhoandroid.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

import br.edu.unidavi.trabalhoandroid.datamodel.ClubeDataModel;
import br.edu.unidavi.trabalhoandroid.datasource.DataSource;
import br.edu.unidavi.trabalhoandroid.model.Clube;

public class ClubeController extends DataSource {

    ContentValues dados;

    public ClubeController(Context context) {
        super(context);
    }

    public boolean salvar(Clube clube) {

        boolean sucesso = true;

        dados = new ContentValues();
        dados.put(ClubeDataModel.getId(), clube.getId());
        dados.put(ClubeDataModel.getNome(), clube.getNome());
        dados.put(ClubeDataModel.getAbreviacao(), clube.getAbreviacao());
        dados.put(ClubeDataModel.getEscudo(), clube.getEscudo());

        sucesso = insert(ClubeDataModel.getTABELA(), dados);

        return sucesso;
    }

    public boolean deletarTodos() {

        boolean sucesso = true;

        sucesso = deletarTodos(ClubeDataModel.getTABELA());

        return sucesso;
    }

    public List<Clube> listar() {
        return getListarClubes();
    }
}

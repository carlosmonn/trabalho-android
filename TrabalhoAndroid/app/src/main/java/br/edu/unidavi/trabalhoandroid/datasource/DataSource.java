package br.edu.unidavi.trabalhoandroid.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.edu.unidavi.trabalhoandroid.datamodel.ClubeDataModel;
import br.edu.unidavi.trabalhoandroid.datamodel.JogadorDataModel;
import br.edu.unidavi.trabalhoandroid.model.Clube;
import br.edu.unidavi.trabalhoandroid.model.Jogador;

public class DataSource extends SQLiteOpenHelper {

    private static final String DB_NAME = "trabalho.sqlite";
    private static final int DB_VERSION = 1;

    Cursor cursor;

    SQLiteDatabase db;

    public DataSource(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(ClubeDataModel.criarTabela());
            db.execSQL(JogadorDataModel.criarTabela());
        } catch (Exception e) {
            Log.e("DB", "Erro: "+e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(String tabela, ContentValues dados) {

        boolean sucesso = true;

        try {
            sucesso = db.insert(tabela, null, dados) > 0;
        }catch (Exception e) {
            sucesso = false;
        }

        return sucesso;
    }

    public boolean deletarTodos(String tabela) {

        boolean sucesso = true;

        sucesso = db.delete(tabela, null, null) > 0;

        return sucesso;
    }

    public List<Clube> getListarClubes() {
        Clube clube;

        List<Clube> listaClubes = new ArrayList<>();
        String sql = "SELECT * FROM " + ClubeDataModel.getTABELA() + " ORDER BY NOME";

        cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                clube = new Clube();

                clube.setId(cursor.getInt(cursor.getColumnIndex(ClubeDataModel.getId())));
                clube.setNome(cursor.getString(cursor.getColumnIndex(ClubeDataModel.getNome())));
                clube.setAbreviacao(cursor.getString(cursor.getColumnIndex(ClubeDataModel.getAbreviacao())));
                clube.setEscudo(cursor.getString(cursor.getColumnIndex(ClubeDataModel.getEscudo())));

                listaClubes.add(clube);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return listaClubes;
    }

    public List<Jogador> getListarJogadoresClube(int clubeId) {
        Jogador jogador;

        List<Jogador> listaJogadores = new ArrayList<>();
        String sql = "SELECT * FROM " + JogadorDataModel.getTABELA() + " WHERE CLUBE_ID = " + clubeId + " ORDER BY NOME";

        cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                jogador = new Jogador();

                jogador.setId(cursor.getInt(cursor.getColumnIndex(JogadorDataModel.getId())));
                jogador.setNome(cursor.getString(cursor.getColumnIndex(JogadorDataModel.getNome())));
                jogador.setPosicao(cursor.getString(cursor.getColumnIndex(JogadorDataModel.getPosicao())));
                jogador.setFoto(cursor.getString(cursor.getColumnIndex(JogadorDataModel.getFoto())));
                jogador.setClubeId(cursor.getInt(cursor.getColumnIndex(JogadorDataModel.getClubeId())));

                listaJogadores.add(jogador);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return listaJogadores;
    }

}

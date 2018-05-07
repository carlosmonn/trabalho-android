package br.edu.unidavi.trabalhoandroid.datamodel;

public class JogadorDataModel {
    private final static String TABELA = "jogador";
    private final static String id = "id";
    private final static String nome = "nome";
    private final static String posicao = "posicao";
    private final static String foto = "foto";
    private final static String clubeId = "clube_id";

    private static String queryCriarTabela = "";

    public static String criarTabela() {

        queryCriarTabela = "CREATE TABLE "+TABELA;
        queryCriarTabela += " (";
        queryCriarTabela += id + " INTEGER PRIMARY KEY, ";
        queryCriarTabela += nome + " TEXT, ";
        queryCriarTabela += posicao + " TEXT, ";
        queryCriarTabela += foto + " TEXT, ";
        queryCriarTabela += clubeId + " INTEGER ";
        queryCriarTabela += " )";
        return queryCriarTabela;
    }

    public static String getTABELA() {
        return TABELA;
    }

    public static String getId() {
        return id;
    }

    public static String getNome() {
        return nome;
    }

    public static String getPosicao() {
        return posicao;
    }

    public static String getFoto() {
        return foto;
    }

    public static String getClubeId() {
        return clubeId;
    }

    public static String getQueryCriarTabela() {
        return queryCriarTabela;
    }

    public static void setQueryCriarTabela(String queryCriarTabela) {
        JogadorDataModel.queryCriarTabela = queryCriarTabela;
    }
}

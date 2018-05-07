package br.edu.unidavi.trabalhoandroid.datamodel;

public class ClubeDataModel {
    private final static String TABELA = "clube";
    private final static String id = "id";
    private final static String nome = "nome";
    private final static String abreviacao = "abreviacao";
    private final static String escudo = "escudo";

    private static String queryCriarTabela = "";

    public static String criarTabela() {

        queryCriarTabela = "CREATE TABLE "+TABELA;
        queryCriarTabela += " (";
        queryCriarTabela += id + " INTEGER PRIMARY KEY, ";
        queryCriarTabela += nome + " TEXT, ";
        queryCriarTabela += abreviacao + " TEXT, ";
        queryCriarTabela += escudo + " TEXT ";
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

    public static String getAbreviacao() {
        return abreviacao;
    }

    public static String getEscudo() {
        return escudo;
    }

    public static String getQueryCriarTabela() {
        return queryCriarTabela;
    }

    public static void setQueryCriarTabela(String queryCriarTabela) {
        ClubeDataModel.queryCriarTabela = queryCriarTabela;
    }
}

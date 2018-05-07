package br.edu.unidavi.trabalhoandroid.web;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.controller.ClubeController;
import br.edu.unidavi.trabalhoandroid.model.Clube;

public class WebTaskClube extends WebTaskBase {

    private static final String SERVICE_NAME = "clubes";

    public WebTaskClube(Context context) {
        super(context, SERVICE_NAME);
    }

    @Override
    public String getRequestBody() {
        Map<String,String> myMap = new HashMap<>();

        JSONObject requestJson = new JSONObject(myMap);

        return requestJson.toString();
    }

    @Override
    public void handleResponse(String response) {

        try {
            ClubeController clubeController = new ClubeController(getContext());
            clubeController.deletarTodos();

            List<Clube> clubeList = new ArrayList<>();

            JSONArray jsonArray = new JSONArray(response);

            for(int index= 0; index < jsonArray.length(); index++){
                JSONObject clubeJSON = (JSONObject) jsonArray.get(index);
                Clube clube = new Clube();

                clube.setId(clubeJSON.getInt("id"));
                clube.setNome(clubeJSON.getString("nome"));
                clube.setAbreviacao(clubeJSON.getString("abreviacao"));
                clube.setEscudo(clubeJSON.getString("escudo"));

                clubeController.salvar(clube);
                clubeList.add(clube);
            }

            ordenaPorNome(clubeList);
            EventBus.getDefault().post(clubeList);
        } catch (JSONException e) {
            EventBus.getDefault().post(new Error(getContext().getString(
                    R.string.error_request)));
        }
    }

    private static void ordenaPorNome(List<Clube> lista) {
        Collections.sort(lista, new Comparator<Clube>() {
            @Override
            public int compare(Clube c1, Clube c2) {
                return c1.getNome().compareTo(c2.getNome());
            }
        });
    }
}

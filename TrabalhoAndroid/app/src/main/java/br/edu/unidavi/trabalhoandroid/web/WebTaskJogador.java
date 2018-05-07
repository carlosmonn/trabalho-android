package br.edu.unidavi.trabalhoandroid.web;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.controller.JogadorController;
import br.edu.unidavi.trabalhoandroid.model.Jogador;

public class WebTaskJogador extends WebTaskBase {

    private static final String SERVICE_NAME = "jogadores";

    public WebTaskJogador(Context context) {
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
            JogadorController jogadorController = new JogadorController(getContext());
            jogadorController.deletarTodos();

            JSONArray jsonArray = new JSONArray(response);

            for(int index= 0; index < jsonArray.length(); index++){
                JSONObject jogadorJSON = (JSONObject) jsonArray.get(index);
                Jogador jogador = new Jogador();

                jogador.setId(jogadorJSON.getInt("id"));
                jogador.setNome(jogadorJSON.getString("nome"));
                jogador.setPosicao(jogadorJSON.getString("posicao"));
                jogador.setFoto(jogadorJSON.getString("foto"));
                jogador.setClubeId(jogadorJSON.getInt("clube_id"));

                jogadorController.salvar(jogador);

            }

            EventBus.getDefault().post(R.id.edtEmail);
        } catch (JSONException e) {
            EventBus.getDefault().post(new Error(getContext().getString(
                    R.string.error_request)));
        }
    }
}

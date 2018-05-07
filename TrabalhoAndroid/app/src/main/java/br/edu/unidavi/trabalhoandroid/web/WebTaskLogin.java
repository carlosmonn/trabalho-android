package br.edu.unidavi.trabalhoandroid.web;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.model.User;

public class WebTaskLogin extends WebTaskBase{

    private static final String SERVICE_NAME = "login";

    private String email;
    private String senha;

    public WebTaskLogin(Context context, String email, String senha) {
        super(context, SERVICE_NAME);
        this.email = email;
        this.senha = senha;
    }

    @Override
    public String getRequestBody() {
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("email", email);
        requestMap.put("password", senha);

        JSONObject json = new JSONObject(requestMap);
        String jsonString = json.toString();

        return  jsonString;
    }

    @Override
    public void handleResponse(String response) {
        User user = new User();
        try {
            JSONObject responseAsJSON = new JSONObject(response);
            String email = responseAsJSON.getString("email");
            user.setEmail(email);
            String senha = responseAsJSON.getString("senha");
            user.setSenha(senha);

            EventBus.getDefault().post(user);

        } catch (JSONException e) {
            EventBus.getDefault().post(new Error(getContext().getString(
                    R.string.label_error_invalid_response)));
        }
    }
}

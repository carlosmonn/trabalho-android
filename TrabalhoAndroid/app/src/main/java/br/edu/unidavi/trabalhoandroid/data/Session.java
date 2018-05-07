package br.edu.unidavi.trabalhoandroid.data;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    private final String FIELD_USERNAME = "email";
    private final String CATEGORY_SESSION = "session";

    private SharedPreferences sharedPreferences;

    public Session(Context context){
        sharedPreferences = context.getSharedPreferences(CATEGORY_SESSION, Context.MODE_PRIVATE);
    }

    public void saveEmailInSession(String email){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FIELD_USERNAME, email);
        editor.commit();
    }

    public String getEmailInSession(){
        return sharedPreferences.getString(FIELD_USERNAME,"");
    }
}

package br.edu.unidavi.trabalhoandroid.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.data.Session;
import br.edu.unidavi.trabalhoandroid.model.User;
import br.edu.unidavi.trabalhoandroid.web.WebTaskClube;
import br.edu.unidavi.trabalhoandroid.web.WebTaskJogador;
import br.edu.unidavi.trabalhoandroid.web.WebTaskLogin;

public class LoginActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Session session = new Session(this);
        EditText edtEmail = findViewById(R.id.edtEmail);
        edtEmail.setText(session.getEmailInSession());

        Button btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryLogin();
            }
        });

        ImageButton btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void tryLogin(){
        EditText edtEmail = findViewById(R.id.edtEmail);
        String email = edtEmail.getText().toString();

        EditText edtSenha = findViewById(R.id.edtSenha);
        String senha = edtSenha.getText().toString();

        showDialog();

        WebTaskLogin taskLogin = new WebTaskLogin(this, email, senha);
        taskLogin.execute();

        WebTaskClube taskClube = new WebTaskClube(this);
        taskClube.execute();

        WebTaskJogador taskJogador = new WebTaskJogador(this);
        taskJogador.execute();
    }

    @Subscribe
    public void onEvent(User user){

        hideDialog();

        Session session = new Session(this);
        session.saveEmailInSession(user.getEmail());
        goToHome();
    }

    @Subscribe
    public void onEvent(Error error){

        hideDialog();

        Snackbar.make(findViewById(R.id.container),
                error.getMessage(), Snackbar.LENGTH_LONG)
                .show();
    }

    private void goToHome(){
        Intent intent = new Intent(this,
                MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    public void showDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.aguarde));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    public void hideDialog(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.cancel();
        }
    }
}

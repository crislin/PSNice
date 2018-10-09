package br.com.livroandroid.psnice.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import br.com.livroandroid.psnice.R;

public class LoginActivity extends AppCompatActivity {

    private LinearLayout btLogin;
    private LinearLayout layoutPai;
    private EditText etSenha;
    private EditText etLogin;

    boolean loginHabilitado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = findViewById(R.id.btLogin);
        etLogin = findViewById(R.id.etLogin);
        etSenha = findViewById(R.id.etSenha);
        layoutPai = findViewById(R.id.layoutPai);

        etLogin.addTextChangedListener(habilitaBotao());
        etSenha.addTextChangedListener(habilitaBotao());
        layoutPai.setOnClickListener(onClickLayoutPai());
        btLogin.setOnClickListener(onClickLogin());
    }

    private View.OnClickListener onClickLayoutPai() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager =(InputMethodManager) getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        };
    }

    private View.OnClickListener onClickLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginHabilitado){
                    if (etLogin.getText().toString().equalsIgnoreCase("gazinice") && etSenha.getText().toString().equalsIgnoreCase("nice22")){
                        Intent it = new Intent(LoginActivity.this, HomeActivity.class);
                        it.putExtra("logado", true);
                        startActivity(it);
                    }
                }
            }
        };
    }

    private TextWatcher habilitaBotao() {
        return new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etLogin.getText().toString().equals("") && !etSenha.getText().toString().equals("")){
                    btLogin.setBackgroundResource(R.drawable.botao_habilitado);
                    loginHabilitado = true;
                } else {
                    btLogin.setBackgroundResource(R.drawable.botao_desabilitado);
                    loginHabilitado = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}

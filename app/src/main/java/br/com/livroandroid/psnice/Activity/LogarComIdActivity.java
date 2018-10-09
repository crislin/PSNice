package br.com.livroandroid.psnice.Activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.livroandroid.psnice.R;

public class LogarComIdActivity extends AppCompatActivity {

    private LinearLayout btUpdatePsnId;
    private LinearLayout btLogIn;
    private LinearLayout layoutPai;
    private EditText etPsnId;
    private TextView btCadastro;

    boolean updateHabilitado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logar_com_id);

        btUpdatePsnId = findViewById(R.id.btUpdatePsnId);
        btLogIn = findViewById(R.id.btLogIn);
        etPsnId = findViewById(R.id.etPsnId);
        btCadastro = findViewById(R.id.btCadastro);
        layoutPai = findViewById(R.id.layoutPai);

        etPsnId.addTextChangedListener(habilitaBotao());
        btLogIn.setOnClickListener(vaiProLogin());
        layoutPai.setOnClickListener(onClickLayoutPai());
        btCadastro.setOnClickListener(vaiProCadastro());
        btUpdatePsnId.setOnClickListener(vaiPraHome());
    }

    private View.OnClickListener vaiPraHome() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateHabilitado){
                    if (etPsnId.getText().toString().equalsIgnoreCase("gazinice")){
                        Intent it = new Intent(LogarComIdActivity.this, MainActivity.class);
                        it.putExtra("logado", false);
                        startActivity(it);
                    }
                }
            }
        };
    }

    private View.OnClickListener vaiProCadastro() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LogarComIdActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        };
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

    private View.OnClickListener vaiProLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LogarComIdActivity.this, LoginActivity.class);
                startActivity(it);
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
                if (!etPsnId.getText().toString().equals("")) {
                    btUpdatePsnId.setBackgroundResource(R.drawable.botao_habilitado);
                    updateHabilitado = true;
                } else {
                    btUpdatePsnId.setBackgroundResource(R.drawable.botao_desabilitado);
                    updateHabilitado = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}

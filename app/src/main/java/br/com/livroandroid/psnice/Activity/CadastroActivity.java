package br.com.livroandroid.psnice.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.com.livroandroid.psnice.R;

public class CadastroActivity extends AppCompatActivity {

    private LinearLayout btCadastrar;
    private LinearLayout layoutPai;
    private EditText psnId;
    private EditText senha;
    private EditText confirmarSenha;

    private boolean cadastroHabilitado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btCadastrar = findViewById(R.id.btCadastrar);
        psnId = findViewById(R.id.etPsnId);
        senha = findViewById(R.id.etSenha);
        confirmarSenha = findViewById(R.id.etConfirmarSenha);
        layoutPai = findViewById(R.id.layoutPai);

        btCadastrar.setOnClickListener(enviarCadastro());
        psnId.addTextChangedListener(habilitarBotao());
        senha.addTextChangedListener(habilitarBotao());
        confirmarSenha.addTextChangedListener(habilitarBotao());
        layoutPai.setOnClickListener(onClickLayoutPai());
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

    private TextWatcher habilitarBotao() {
        return new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!psnId.getText().toString().equals("") && !senha.getText().toString().equals("") && !confirmarSenha.getText().toString().equals("")) {
                    btCadastrar.setBackgroundResource(R.drawable.botao_habilitado);
                    cadastroHabilitado = true;
                } else {
                    btCadastrar.setBackgroundResource(R.drawable.botao_desabilitado);
                    cadastroHabilitado = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }


    private View.OnClickListener enviarCadastro() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean idValido = validaId();
                if (cadastroHabilitado){
                    if (idValido){
                        if (senha.getText().toString().equalsIgnoreCase(confirmarSenha.getText().toString())){
                            cadastrar();
                        } else {
                            Toast.makeText(CadastroActivity.this, "Campo confirmar senha deve conter a mesma senha", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CadastroActivity.this, "PSN-ID não válido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
    }

    private void cadastrar(){
        psnId.setText("");
        senha.setText("");
        confirmarSenha.setText("");

        Toast.makeText(CadastroActivity.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
    }

    private boolean validaId(){
        boolean valido = false;
        if (psnId.getText().toString().equalsIgnoreCase("gazinice")){
            valido = true;
        }
        return valido;
    }
}

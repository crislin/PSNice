package br.com.livroandroid.psnice.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import br.com.livroandroid.psnice.R;

public class CadastroActivity extends AppCompatActivity {

    private LinearLayout btCadastrar;
    private LinearLayout layoutPai;
    private EditText etPsnId;
    private EditText etSenha;
    private EditText etConfirmarSenha;

    private boolean cadastroHabilitado = false;
    private FirebaseFirestore firebaseStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btCadastrar = findViewById(R.id.btCadastrar);
        etPsnId = findViewById(R.id.etPsnId);
        etSenha = findViewById(R.id.etSenha);
        etConfirmarSenha = findViewById(R.id.etConfirmarSenha);
        layoutPai = findViewById(R.id.layoutPai);

        btCadastrar.setOnClickListener(enviarCadastro());
        etPsnId.addTextChangedListener(habilitarBotao());
        etSenha.addTextChangedListener(habilitarBotao());
        etConfirmarSenha.addTextChangedListener(habilitarBotao());
        layoutPai.setOnClickListener(onClickLayoutPai());
        firebaseStore = FirebaseFirestore.getInstance();
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
                if (!etPsnId.getText().toString().equals("") && !etSenha.getText().toString().equals("") && !etConfirmarSenha.getText().toString().equals("")) {
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
                        if (etSenha.getText().toString().equalsIgnoreCase(etConfirmarSenha.getText().toString())){
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
        String psnId = etPsnId.getText().toString();
        String senha = etSenha.getText().toString();

        Map<String, String> usermap = new HashMap<>();

        usermap.put("psn_id", psnId);
        usermap.put("senha", senha);

        firebaseStore.collection("users").add(usermap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(CadastroActivity.this, "Cadastro feito com sucesso!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CadastroActivity.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
            }
        });

        etPsnId.setText("");
        etSenha.setText("");
        etConfirmarSenha.setText("");
    }

    private boolean validaId(){
        boolean valido = false;
        if (etPsnId.getText().toString().equalsIgnoreCase("gazinice")){
            valido = true;
        }
        return valido;
    }
}

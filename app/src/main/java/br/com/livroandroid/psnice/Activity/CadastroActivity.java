package br.com.livroandroid.psnice.Activity;

import android.content.Context;
import android.content.Intent;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.livroandroid.psnice.Cadastro;
import br.com.livroandroid.psnice.R;

import static br.com.livroandroid.psnice.Activity.LogarComIdActivity.idsValidasFirebase;

public class CadastroActivity extends AppCompatActivity {

    private LinearLayout btCadastrar;
    private LinearLayout layoutPai;
    private EditText etPsnId;
    private EditText etSenha;
    private EditText etConfirmarSenha;
    private List<Cadastro> listaCadastrados = new ArrayList<>();

    private boolean cadastroHabilitado = false;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    private DatabaseReference mCadastroReference = mRootReference.child("cadastros");

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
        mCadastroReference.addChildEventListener(childEventListener);
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
                if (cadastroHabilitado){
                    if (verificaId(etPsnId.getText().toString())){
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
        if (!verificaSeCadastrado(etPsnId.getText().toString())) {
            Cadastro cadastro = new Cadastro(etPsnId.getText().toString(), etSenha.getText().toString());
            mCadastroReference.push().setValue(cadastro);
            Toast.makeText(CadastroActivity.this, "Cadastro feito com sucesso", Toast.LENGTH_SHORT).show();
            etPsnId.setText("");
            etSenha.setText("");
            etConfirmarSenha.setText("");
            Intent it = new Intent(CadastroActivity.this, LoginActivity.class);
            startActivity(it);
        } else {
            Toast.makeText(CadastroActivity.this, "Id ja cadastrado", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean verificaId(String id){
        for (int i = 0; i < idsValidasFirebase.size(); i++){
            if (id.equalsIgnoreCase(idsValidasFirebase.get(i))){
                return true;
            }
        }
        return false;
    }

    public boolean verificaSeCadastrado(String psnId){
        for (int i = 0; i < listaCadastrados.size(); i++){
            if (psnId.equalsIgnoreCase(listaCadastrados.get(i).getPsnId())){
                return true;
            }
        }
        return false;
    }

    ChildEventListener childEventListener = new ChildEventListener(){

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Cadastro cadastro = dataSnapshot.getValue(Cadastro.class);
            listaCadastrados.add(cadastro);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}

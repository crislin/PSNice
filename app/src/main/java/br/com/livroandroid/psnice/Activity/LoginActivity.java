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
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.Cadastro;
import br.com.livroandroid.psnice.R;

public class LoginActivity extends AppCompatActivity {

    private LinearLayout btLogin;
    private LinearLayout layoutPai;
    private EditText etSenha;
    private EditText etLogin;
    private List<Cadastro> listaCadastrados = new ArrayList<>();

    boolean loginHabilitado = false;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    private DatabaseReference mCadastroReference = mRootReference.child("cadastros");

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

    private View.OnClickListener onClickLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginHabilitado){
                    if (verificaSeCadastrado()){
                        Intent it = new Intent(LoginActivity.this, MainActivity.class);
                        it.putExtra("logado", true);
                        it.putExtra("psnId", etLogin.getText().toString());
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

    public boolean verificaSeCadastrado(){
        for (int i = 0; i < listaCadastrados.size(); i++){
            if (etLogin.getText().toString().equalsIgnoreCase(listaCadastrados.get(i).getPsnId()) && etSenha.getText().toString().equalsIgnoreCase(listaCadastrados.get(i).getSenha())){
                return true;
            }
            if (etLogin.getText().toString().equalsIgnoreCase(listaCadastrados.get(i).getPsnId()) && !etSenha.getText().toString().equalsIgnoreCase(listaCadastrados.get(i).getSenha())){
                Toast.makeText(this, "Senha errada.", Toast.LENGTH_SHORT).show();
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

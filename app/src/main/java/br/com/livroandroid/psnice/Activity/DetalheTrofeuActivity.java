package br.com.livroandroid.psnice.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.livroandroid.psnice.Adapter.ComentarioAdapter;
import br.com.livroandroid.psnice.Adapter.TrofeusAdapter;
import br.com.livroandroid.psnice.Comentario;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.Service.PSNiceService;
import br.com.livroandroid.psnice.Usuario;

public class DetalheTrofeuActivity extends AppCompatActivity {

    private ImageView imagemTrofeu;
    private TextView nomeTrofeu;
    private TextView descricaoTrofeu;
    private FloatingActionButton fabComentario;
    private LinearLayout campoComentario;
    private LinearLayout btEnviarComentario;
    private LinearLayout layoutPai;
    private EditText etComentario;
    private RecyclerView mRecyclerView;
    private TextView tvTotalComentarios;

    private int imagem;
    private String nome;
    private String nomeJogo;
    private String descricao;
    private boolean earned;
    private boolean comentarioAberto;
    private boolean btComentarioHabilitado = false;
    private FirebaseFirestore firebaseStore;

    public static List<Comentario> listaComent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_trofeu);

        imagemTrofeu = findViewById(R.id.imagemTrofeu);
        nomeTrofeu = findViewById(R.id.nomeTrofeu);
        descricaoTrofeu = findViewById(R.id.descricaoTrofeu);
        fabComentario = findViewById(R.id.fabComentario);
        campoComentario = findViewById(R.id.campoComentario);
        btEnviarComentario = findViewById(R.id.btEnviarComentario);
        etComentario = findViewById(R.id.etComentario);
        mRecyclerView = findViewById(R.id.recycler_view_layour_recycler_comentarios);
        tvTotalComentarios = findViewById(R.id.tvTotalComentarios);
        layoutPai = findViewById(R.id.layoutPai);

        fabComentario.setOnClickListener(abrirCampoComentario());
        btEnviarComentario.setOnClickListener(enviarComentario());
        etComentario.addTextChangedListener(habilitaBotao());
        imagemTrofeu.setOnClickListener(limparLista());

        Intent i = this.getIntent();
        imagem = i.getExtras().getInt("imagem");
        nome = i.getExtras().getString("nomeTrofeu");
        descricao = i.getExtras().getString("descricao");
        earned = i.getExtras().getBoolean("earned");
        nomeJogo = i.getExtras().getString("nomeJogo");
        firebaseStore = FirebaseFirestore.getInstance();
    }

    private View.OnClickListener limparLista() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaComent.clear();
                atualizaComentarios();
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        imagemTrofeu.setImageResource(imagem);
        nomeTrofeu.setText(nome);
        descricaoTrofeu.setText(descricao);
        if (earned){
            imagemTrofeu.setPadding(3,3,3,3);
            imagemTrofeu.setBackgroundColor(this.getResources().getColor(R.color.trophie_earned));
        }
        if (!earned){
            imagemTrofeu.setAlpha((float) 0.5);
        }
        comentarioAberto = false;
        atualizaComentarios();
    }

    private View.OnClickListener abrirCampoComentario() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(layoutPai);
                if (!comentarioAberto){
                    comentarioAberto = true;
                    campoComentario.setVisibility(View.VISIBLE);
                    fabComentario.setImageDrawable(getResources().getDrawable(R.drawable.ic_close));
                } else {
                    comentarioAberto = false;
                    campoComentario.setVisibility(View.GONE);
                    fabComentario.setImageDrawable(getResources().getDrawable(R.drawable.ic_comentario));
                    InputMethodManager inputMethodManager =(InputMethodManager) getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        };
    }

    private View.OnClickListener enviarComentario() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = null;
                try {
                    usuario = PSNiceService.getUsuario(DetalheTrofeuActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (usuario != null){
                    Comentario comentario = new Comentario(usuario.getPsnId(), etComentario.getText().toString(), "24-12-2018", "0");
                    listaComent.add(comentario);
                    atualizaComentarios();
                    etComentario.setText("");

                    enviarProFirebase(comentario);

                    TransitionManager.beginDelayedTransition(layoutPai);
                    comentarioAberto = false;
                    campoComentario.setVisibility(View.GONE);
                    fabComentario.setImageDrawable(getResources().getDrawable(R.drawable.ic_comentario));
                    InputMethodManager inputMethodManager =(InputMethodManager) getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
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
                if (!etComentario.getText().toString().equals("")) {
                    btEnviarComentario.setBackgroundResource(R.drawable.botao_habilitado);
                    btComentarioHabilitado = true;
                } else {
                    btEnviarComentario.setBackgroundResource(R.drawable.botao_desabilitado);
                    btComentarioHabilitado = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    public void atualizaComentarios(){
        tvTotalComentarios.setText(String.valueOf(listaComent.size()));

        ComentarioAdapter listAdapter = new ComentarioAdapter();
        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    public void enviarProFirebase(Comentario comentario){
        Map<String, String> usermap = new HashMap<>();

        usermap.put("psn_id", comentario.getIdComentario());
        usermap.put("comentario", comentario.getComentario());

        firebaseStore.collection("comentarios").document(nomeJogo).collection(nome).add(usermap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(DetalheTrofeuActivity.this, "foi pro firebase", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DetalheTrofeuActivity.this, "não foi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

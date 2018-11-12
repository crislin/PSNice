package br.com.livroandroid.psnice.Activity;

import android.content.Context;
import android.content.Intent;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.Adapter.ComentarioAdapter;
import br.com.livroandroid.psnice.Comentario;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.Service.PSNiceService;
import br.com.livroandroid.psnice.Usuario;

public class DetalheTrofeuActivity extends AppCompatActivity implements ValueEventListener {

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
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    private DatabaseReference mComentarioReference = mRootReference.child("comentarios");
    private DatabaseReference mTrofeuReference;

    public static List<Comentario> listaComentarios = new ArrayList<>();

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

        Intent i = this.getIntent();
        imagem = i.getExtras().getInt("imagem");
        nome = i.getExtras().getString("nomeTrofeu");
        descricao = i.getExtras().getString("descricao");
        earned = i.getExtras().getBoolean("earned");
        nomeJogo = i.getExtras().getString("nomeJogo");
        firebaseStore = FirebaseFirestore.getInstance();

        mTrofeuReference = mComentarioReference.child(nomeJogo).child(nome);
        mTrofeuReference.addChildEventListener(childEventListener);
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
        listaComentarios.clear();
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
                    Comentario comentario = new Comentario(usuario.getPsnId(), etComentario.getText().toString(), "24-12-2018", 0);
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
        tvTotalComentarios.setText(String.valueOf(listaComentarios.size()));

        ComentarioAdapter listAdapter = new ComentarioAdapter(listaComentarios, nomeJogo, nome);
        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    public void enviarProFirebase(Comentario comentario){
        DatabaseReference mComentReference = mComentarioReference.child(nomeJogo).child(nome);
//        mComentReference.setValue(comentario);

        mComentReference.push().setValue(comentario);

//        DatabaseReference mComentReference = mComentarioReference.child(nomeJogo).child(nome);

//        Map<String, Comentario> usermap = new HashMap<>();
//
//        usermap.put("coment", comentario);
//
//        firebaseStore.collection("comentarios").document(nomeJogo).collection(nome).add(usermap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(DetalheTrofeuActivity.this, "foi pro firebase", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(DetalheTrofeuActivity.this, "não foi", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        pegaListaFirebase();
    }

    public void pegaListaFirebase(){
//        final List<Comentario> comentarios = new ArrayList<>();
//
//        CollectionReference collection = firebaseStore.collection("comentarios").document(nomeJogo).collection(nome);
//        collection.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshot) {
//                        for (DocumentSnapshot documentos : queryDocumentSnapshot){
//                            comentarios.add(documentos.toObject(Comentario.class));
//                        }
//                        Toast.makeText(DetalheTrofeuActivity.this, "pegou ", Toast.LENGTH_SHORT).show();
//                    }
//                });

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
//    DocumentReference collection = firebaseStore.collection("comentarios").document(nomeJogo);
//        collection.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//        @Override
//        public void onSuccess(QuerySnapshot documentSnapshots) {
//            for (DocumentSnapshot documentos : documentSnapshots){
//                comentarios.add(documentos.toObject(Comentario.class));
//            }
//
//        }
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot chidSnap : dataSnapshot.getChildren()) {
//                    Log.v("tmz",""+ chidSnap.getKey()); //displays the key for the node
//                    Log.v("tmz",""+ chidSnap.child("market_name").getValue());   //gives the value for given keyname
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    ChildEventListener childEventListener = new ChildEventListener(){

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Comentario comentario = dataSnapshot.getValue(Comentario.class);
            comentario.setKey(dataSnapshot.getKey());
            listaComentarios.add(comentario);
            atualizaComentarios();
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


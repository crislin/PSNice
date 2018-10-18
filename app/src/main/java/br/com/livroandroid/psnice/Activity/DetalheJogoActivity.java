package br.com.livroandroid.psnice.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.Adapter.TrofeusAdapter;

public class DetalheJogoActivity extends AppCompatActivity {

    private ImageView imagemDetalhe;
    private TextView nomeDetalhe;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_jogo);

        mRecyclerView = findViewById(R.id.recycler_view_layour_recycler_trofeus);

        imagemDetalhe = findViewById(R.id.imagemDetalhe);
        nomeDetalhe = findViewById(R.id.nomeDetalhe);

        Intent i = this.getIntent();
        int imagem = i.getExtras().getInt("imagem");
        String nome = i.getExtras().getString("nome");

        imagemDetalhe.setImageResource(imagem);
        nomeDetalhe.setText(nome);

        TrofeusAdapter listAdapter = new TrofeusAdapter(this, nome);
        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }
}

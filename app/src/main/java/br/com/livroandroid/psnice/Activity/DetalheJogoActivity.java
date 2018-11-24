package br.com.livroandroid.psnice.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.Adapter.TrofeusAdapter;
import br.com.livroandroid.psnice.Service.PSNiceService;
import br.com.livroandroid.psnice.Trofeu;

public class DetalheJogoActivity extends AppCompatActivity {

    private ImageView imagemDetalhe;
    private TextView nomeDetalhe;
    private RecyclerView mRecyclerView;
    private List<Trofeu> listaTrofeus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_jogo);

        mRecyclerView = findViewById(R.id.recycler_view_layour_recycler_trofeus);

        imagemDetalhe = findViewById(R.id.imagemDetalhe);
        nomeDetalhe = findViewById(R.id.nomeDetalhe);

        Intent i = this.getIntent();
        String imagem = i.getExtras().getString("imagem");
        String nome = i.getExtras().getString("nome");
        String psnId = i.getExtras().getString("psnId");
        boolean logado = i.getExtras().getBoolean("logado");

        Glide.with(this).load(imagem).into(imagemDetalhe);
        nomeDetalhe.setText(nome);

        try {
            listaTrofeus = PSNiceService.getTrofeus(this, nome);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TrofeusAdapter listAdapter = new TrofeusAdapter(this, listaTrofeus, nome, psnId, logado);
        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }
}

package br.com.livroandroid.psnice.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
    private TextView nomeJogo;
    private TextView tvTotalTrofeus;
    private TextView tvDesenvolvedora;
    private TextView tvGenero;
    private TextView tvPorcentagem;
    private ProgressBar pbProgresso;
    private RecyclerView mRecyclerView;
    private List<Trofeu> listaTrofeus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_jogo);

        mRecyclerView = findViewById(R.id.recycler_view_layour_recycler_trofeus);

        imagemDetalhe = findViewById(R.id.imagemDetalhe);
        nomeJogo = findViewById(R.id.nomeJogo);
        tvTotalTrofeus = findViewById(R.id.tvTotalTrofeus);
        tvDesenvolvedora = findViewById(R.id.tvDesenvolvedora);
        tvGenero = findViewById(R.id.tvGenero);
        tvPorcentagem = findViewById(R.id.tvPorcentagem);
        pbProgresso = findViewById(R.id.pbProgresso);

        Intent i = this.getIntent();
        String imagem = i.getExtras().getString("imagem");
        String nome = i.getExtras().getString("nome");
        String psnIdLogado = null;
        if (i.getExtras().getString("psnIdLogado") != null){
            psnIdLogado = i.getExtras().getString("psnIdLogado");
        }
        String psnId = i.getExtras().getString("psnId");
        String totalTrofeus = i.getExtras().getString("totalTrofeus");
        String desenvolvedora = i.getExtras().getString("desenvolvedora");
        String genero = i.getExtras().getString("genero");
        String porcentagem = i.getExtras().getString("porcentagem");
        boolean logado = i.getExtras().getBoolean("logado");
        boolean usuarioTemEsseJogo = i.getExtras().getBoolean("usuarioTemEsseJogo");

        Glide.with(this).load(imagem).into(imagemDetalhe);
        nomeJogo.setText(nome);
        tvTotalTrofeus.setText(totalTrofeus);
        tvDesenvolvedora.setText(desenvolvedora);
        tvGenero.setText(genero);
        tvPorcentagem.setText(porcentagem);
        pbProgresso.setProgress(Integer.valueOf(porcentagem));

        try {
            if (usuarioTemEsseJogo) {
                listaTrofeus = PSNiceService.getTrofeus(this, nome, psnId);
            } else {
                listaTrofeus = PSNiceService.getTrofeus(this, nome);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TrofeusAdapter listAdapter;
        if (psnIdLogado == null){
            listAdapter = new TrofeusAdapter(this, listaTrofeus, nome, psnId, logado);
        } else {
            listAdapter = new TrofeusAdapter(this, listaTrofeus, nome, psnId, logado, psnIdLogado);
        }

        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }
}

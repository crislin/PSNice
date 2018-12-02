package br.com.livroandroid.psnice.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.Adapter.ComparacaoJogosAdapter;
import br.com.livroandroid.psnice.Jogo;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.Service.PSNiceService;

public class ComparacaoJogoActivity extends AppCompatActivity {

    private String psnIdLogado;
    private String psnId;
    private List<Jogo> listaDeJogosLogado;
    private List<Jogo> listaDeJogosComparado;
    private List<Jogo> listaDeJogosEmComum;
    private List<Integer> listaPorcentagemComparado = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ImageView ivImagemJogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparacao_jogo);

        mRecyclerView = findViewById(R.id.recycler_view_layour_recycler_comparacao);
        Intent i = this.getIntent();
        psnId = i.getExtras().getString("psnId");
        psnIdLogado = i.getExtras().getString("psnIdLogado");
        boolean logado = i.getExtras().getBoolean("logado");

        listaDeJogosLogado = retornaListaDeJogos(psnIdLogado);
        listaDeJogosComparado = retornaListaDeJogos(psnId);
        listaDeJogosEmComum = comparaListas(listaDeJogosLogado, listaDeJogosComparado);

        ComparacaoJogosAdapter listAdapter = new ComparacaoJogosAdapter(this, listaPorcentagemComparado, listaDeJogosEmComum, psnIdLogado, psnId, logado);
        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);


    }

    public List<Jogo> retornaListaDeJogos(String psnId){
        List<Jogo> listaDosJogos = null;
        try {
            listaDosJogos = PSNiceService.getJogos(this, psnId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaDosJogos;
    }

    public List<Jogo> comparaListas(List<Jogo> listaLogado, List<Jogo> listaComparado){
        List<Jogo> listaDosJogos = new ArrayList<>();
        for (int i = 0; i < listaLogado.size(); i++){
            for (int t = 0; t < listaComparado.size(); t++){
                if (listaLogado.get(i).getIdJogo().equalsIgnoreCase(listaComparado.get(t).getIdJogo())){
                    listaDosJogos.add(listaLogado.get(i));
                    listaPorcentagemComparado.add(listaComparado.get(t).getGameProgress());
                }
            }
        }
        return listaDosJogos;
    }
}

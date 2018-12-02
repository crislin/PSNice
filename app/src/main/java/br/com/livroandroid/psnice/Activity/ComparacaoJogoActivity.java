package br.com.livroandroid.psnice.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.Adapter.ComparacaoJogosAdapter;
import br.com.livroandroid.psnice.Jogo;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.Service.PSNiceService;
import br.com.livroandroid.psnice.Usuario;

public class ComparacaoJogoActivity extends AppCompatActivity {

    private String psnIdLogado;
    private String psnId;
    private List<Jogo> listaDeJogosLogado;
    private List<Jogo> listaDeJogosComparado;
    private List<Jogo> listaDeJogosEmComum;
    private List<Integer> listaPorcentagemComparado = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ImageView ivAvatarLogado;
    private ImageView ivAvatarComparado;

    private Usuario usuarioLogado;
    private Usuario usuarioComparado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparacao_jogo);

        mRecyclerView = findViewById(R.id.recycler_view_layour_recycler_comparacao);
        ivAvatarLogado = findViewById(R.id.ivAvatarLogado);
        ivAvatarComparado = findViewById(R.id.ivAvatarComparado);
        Intent i = this.getIntent();
        psnId = i.getExtras().getString("psnId");
        psnIdLogado = i.getExtras().getString("psnIdLogado");
        boolean logado = i.getExtras().getBoolean("logado");

        try {
            usuarioLogado = PSNiceService.getUsuario(this, psnIdLogado);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            usuarioComparado = PSNiceService.getUsuario(this, psnId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listaDeJogosLogado = retornaListaDeJogos(psnIdLogado);
        listaDeJogosComparado = retornaListaDeJogos(psnId);
        listaDeJogosEmComum = comparaListas(listaDeJogosLogado, listaDeJogosComparado);
        Glide.with(this).load(usuarioLogado.getAvatar()).into(ivAvatarLogado);
        Glide.with(this).load(usuarioComparado.getAvatar()).into(ivAvatarComparado);

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

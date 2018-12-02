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

import br.com.livroandroid.psnice.Adapter.ComparacaoTrofeusAdapter;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.Service.PSNiceService;
import br.com.livroandroid.psnice.Trofeu;
import br.com.livroandroid.psnice.Usuario;

public class ComparacaoTrofeuActivity extends AppCompatActivity {

    private TextView tvNomeJogo;
    private RecyclerView mRecyclerView;
    private ImageView ivAvatarLogado;
    private ImageView ivAvatarComparado;

    private String psnIdLogado;
    private String psnIdComparado;
    private String nomeJogo;
    private List<Trofeu> listaTrofeuLogado;
    private List<Trofeu> listaTrofeuComparado;

    private Usuario usuarioLogado;
    private Usuario usuarioComparado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparacao_trofeu);
        tvNomeJogo = findViewById(R.id.tvNomeJogo);
        mRecyclerView = findViewById(R.id.recycler_view_layour_recycler_comparacao_trofeu);
        ivAvatarLogado = findViewById(R.id.ivAvatarLogado);
        ivAvatarComparado = findViewById(R.id.ivAvatarComparado);

        Intent i = this.getIntent();
        psnIdLogado = i.getExtras().getString("psnIdLogado");
        psnIdComparado = i.getExtras().getString("psnIdComparado");
        nomeJogo = i.getExtras().getString("nomeJogo");
        String idJogo = i.getExtras().getString("idJogo");
        boolean logado = i.getExtras().getBoolean("logado");

        listaTrofeuLogado = retornaListaTrofeu(psnIdLogado);
        listaTrofeuComparado = retornaListaTrofeu(psnIdComparado);

        try {
            usuarioLogado = PSNiceService.getUsuario(this, psnIdLogado);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            usuarioComparado = PSNiceService.getUsuario(this, psnIdComparado);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Glide.with(this).load(usuarioLogado.getAvatar()).into(ivAvatarLogado);
        Glide.with(this).load(usuarioComparado.getAvatar()).into(ivAvatarComparado);
        tvNomeJogo.setText(nomeJogo);

        ComparacaoTrofeusAdapter listAdapter = new ComparacaoTrofeusAdapter(this, listaTrofeuLogado, listaTrofeuComparado, psnIdLogado, psnIdComparado, idJogo, logado);
        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    public List<Trofeu> retornaListaTrofeu(String psnId){
        List<Trofeu> lista = new ArrayList<>();
        try {
            lista = PSNiceService.getTrofeus(this, nomeJogo, psnId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
}

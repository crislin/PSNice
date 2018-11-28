package br.com.livroandroid.psnice.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;

import br.com.livroandroid.psnice.Adapter.AbasPerfilAdapter;
import br.com.livroandroid.psnice.Fragment.EstatisticasFragment;
import br.com.livroandroid.psnice.Fragment.ListaAmigosFragment;
import br.com.livroandroid.psnice.Fragment.ListaJogosFragment;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.Service.PSNiceService;
import br.com.livroandroid.psnice.Usuario;

public class DetalheUsuarioActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView lbPsnId;
    private TextView lbTotal;
    private TextView lbPlatinum;
    private TextView lbGold;
    private TextView lbSilver;
    private TextView lbBronze;
    private TextView lbLevel;
    private ProgressBar pbLevel;
    private ImageView ivAvatar;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_usuario);
        viewPager = findViewById(R.id.abas_view_pager);
        tabLayout = findViewById(R.id.abas);
        lbPsnId = findViewById(R.id.lbPsnId);
        lbTotal = findViewById(R.id.lbTotal);
        lbPlatinum = findViewById(R.id.lbPlatinum);
        lbGold = findViewById(R.id.lbGold);
        lbSilver = findViewById(R.id.lbSilver);
        lbBronze = findViewById(R.id.lbBronze);
        lbLevel = findViewById(R.id.lbLevel);
        pbLevel = findViewById(R.id.pbLevel);
        ivAvatar = findViewById(R.id.ivAvatar);

        Intent i = this.getIntent();
        String psnId = i.getExtras().getString("psnId");

        try {
            usuario = PSNiceService.getUsuario(this, psnId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (usuario != null){
            carregaUsuario();
        }

        AbasPerfilAdapter adapter = new AbasPerfilAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putString("psnId", psnId);
        bundle.putInt("totalTrofeus", usuario.getTotal());
        bundle.putInt("rankMundial", usuario.getRankMundial());
        bundle.putInt("rankPais", usuario.getRankPais());
        bundle.putFloat("eficiencia", usuario.getEficiencia());
        bundle.putInt("totalTrofeusPossiveis", usuario.getTotalTrofeuPossivel());
//        bundle.putInt("ps3Games", usuario.getPs3Games());
//        bundle.putInt("ps4Games", usuario.getPs4Games());
//        bundle.putInt("psVitaGames", usuario.getPsVitaGames());
        ListaJogosFragment listaJogosFragment = new ListaJogosFragment();
        listaJogosFragment.setArguments(bundle);
        EstatisticasFragment estatisticasFragment = new EstatisticasFragment();
        estatisticasFragment.setArguments(bundle);

        adapter.adicionar( listaJogosFragment , "Jogos");
        adapter.adicionar( estatisticasFragment, "Estatisticas");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void carregaUsuario(){
        lbPsnId.setText(usuario.getPsnId());
        lbTotal.setText(String.valueOf(usuario.getTotal()));
        lbPlatinum.setText(String.valueOf(usuario.getPlatinum()));
        lbGold.setText(String.valueOf(usuario.getGold()));
        lbSilver.setText(String.valueOf(usuario.getSilver()));
        lbBronze.setText(String.valueOf(usuario.getBronze()));
        pbLevel.setProgress(usuario.getProgress());
        lbLevel.setText(String.valueOf(usuario.getLevel()));
        Glide.with(this).load(usuario.getAvatar()).into(ivAvatar);
    }
}

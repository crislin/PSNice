package br.com.livroandroid.psnice.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;

import br.com.livroandroid.psnice.Adapter.AbasMainAdapter;
import br.com.livroandroid.psnice.Adapter.AbasPerfilAdapter;
import br.com.livroandroid.psnice.Fragment.EstatisticasFragment;
import br.com.livroandroid.psnice.Fragment.HomeFragment;
import br.com.livroandroid.psnice.Fragment.ListaJogosFragment;
import br.com.livroandroid.psnice.Fragment.LogoutFragment;
import br.com.livroandroid.psnice.Fragment.PesquisaFragment;
import br.com.livroandroid.psnice.Fragment.PesquisaUsuarioFragment;
import br.com.livroandroid.psnice.R;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String psnId;
    private boolean logado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.abasViewPagerMain);
        tabLayout = findViewById(R.id.abasMain);

        Intent i = this.getIntent();
        psnId = i.getExtras().getString("psnId");
        logado = i.getExtras().getBoolean("logado");

        AbasMainAdapter adapterMain = new AbasMainAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putString("psnId", psnId);
        bundle.putBoolean("logado", logado);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        PesquisaUsuarioFragment pesquisaUsuarioFragment = new PesquisaUsuarioFragment();
        pesquisaUsuarioFragment.setArguments(bundle);
        PesquisaFragment pesquisaFragment = new PesquisaFragment();
        pesquisaFragment.setArguments(bundle);

        adapterMain.adicionar( homeFragment , "");
        adapterMain.adicionar( pesquisaFragment, "");
        adapterMain.adicionar( pesquisaUsuarioFragment, "");
        adapterMain.adicionar( new LogoutFragment(), "");

        viewPager.setAdapter(adapterMain);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_games);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_search);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_logout);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}

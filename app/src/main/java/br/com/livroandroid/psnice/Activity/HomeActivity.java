package br.com.livroandroid.psnice.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.livroandroid.psnice.Adapter.AbasPerfilAdapter;
import br.com.livroandroid.psnice.Fragment.EstatisticasFragment;
import br.com.livroandroid.psnice.Fragment.ListaAmigosFragment;
import br.com.livroandroid.psnice.Fragment.ListaJogosFragment;
import br.com.livroandroid.psnice.R;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView lbPsnId;

    private boolean logado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = findViewById(R.id.abas_view_pager);
        tabLayout = findViewById(R.id.abas);
        AbasPerfilAdapter adapter = new AbasPerfilAdapter(getSupportFragmentManager());

        lbPsnId = findViewById(R.id.lbPsnId);

        Intent i = this.getIntent();
        logado = i.getExtras().getBoolean("logado", false);

        adapter.adicionar( new ListaJogosFragment() , "Jogos");
        adapter.adicionar( new EstatisticasFragment(), "Estatisticas");
        if (logado){
            adapter.adicionar( new ListaAmigosFragment(), "Amigos");
        }

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

}












































package br.com.livroandroid.psnice.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.Fragment.EstatisticasFragment;
import br.com.livroandroid.psnice.Usuario;

/**
 * Created by livetouch on 30/08/18.
 */

public class AbasPerfilAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titulos = new ArrayList<>();

    public AbasPerfilAdapter(FragmentManager fm) {
        super(fm);
    }

    public void adicionar(Fragment fragment, String tituloAba){
        this.fragments.add(fragment);
        this.titulos.add(tituloAba);
    }

    public void mudaTexto(Bundle bundle){
        this.fragments.get(1).setArguments(bundle);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titulos.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return this.titulos.get(position);
    }
}

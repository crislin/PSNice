package br.com.livroandroid.psnice.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by livetouch on 11/09/18.
 */

public class AbasMainAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titulos = new ArrayList<>();

    public AbasMainAdapter(FragmentManager fm) {
        super(fm);
    }

    public void adicionar(Fragment fragment, String tituloAba){
        this.fragments.add(fragment);
        this.titulos.add(tituloAba);
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

package br.com.livroandroid.psnice.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;

import br.com.livroandroid.psnice.Adapter.AbasPerfilAdapter;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.Service.PSNiceService;
import br.com.livroandroid.psnice.Usuario;

public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView lbPsnId;
    private TextView lbTotal;
    private TextView lbPlatinum;
    private TextView lbGold;
    private TextView lbSilver;
    private TextView lbBronze;
    private TextView lbLevel;
    private SeekBar sbLevel;
    private ImageView ivAvatar;
    private View view;

    Usuario usuario;

    private boolean logado = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.abas_view_pager);
        tabLayout = view.findViewById(R.id.abas);
        lbPsnId = view.findViewById(R.id.lbPsnId);
        lbTotal = view.findViewById(R.id.lbTotal);
        lbPlatinum = view.findViewById(R.id.lbPlatinum);
        lbGold = view.findViewById(R.id.lbGold);
        lbSilver = view.findViewById(R.id.lbSilver);
        lbBronze = view.findViewById(R.id.lbBronze);
        lbLevel = view.findViewById(R.id.lbLevel);
        sbLevel = view.findViewById(R.id.sbLevel);
        ivAvatar = view.findViewById(R.id.ivAvatar);
        sbLevel.getThumb().mutate().setAlpha(0);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            usuario = PSNiceService.getUsuario(view.getContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (usuario != null){
            carregaUsuario();
        }

        AbasPerfilAdapter adapter = new AbasPerfilAdapter(getChildFragmentManager());

        lbPsnId = view.findViewById(R.id.lbPsnId);

//        Intent i = getActivity().getIntent();
//        logado = i.getExtras().getBoolean("logado", false);

        adapter.adicionar( new ListaJogosFragment() , "Jogos");
        adapter.adicionar( new EstatisticasFragment(), "Estatisticas");
        if (logado){
            adapter.adicionar( new ListaAmigosFragment(), "Amigos");
        }

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void carregaUsuario(){
        lbPsnId.setText(usuario.getPsnId());
        lbTotal.setText(String.valueOf(usuario.getTotal()));
        lbPlatinum.setText(String.valueOf(usuario.getPlatinum()));
        lbGold.setText(String.valueOf(usuario.getGold()));
        lbSilver.setText(String.valueOf(usuario.getSilver()));
        lbBronze.setText(String.valueOf(usuario.getBronze()));
        sbLevel.setProgress(usuario.getProgress());
        lbLevel.setText(String.valueOf(usuario.getLevel()));
        Glide.with(view).load(usuario.getAvatar()).into(ivAvatar);
    }

    public Bundle carregaDadosParaEstatisticas(Usuario usuario){
//        FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        EstatisticasFragment fragment = new EstatisticasFragment();
//        Bundle bundle = new Bundle();
//        Usuario usu = usuario;
//        bundle.putSerializable("usuarioF", usu);
//        fragment.setArguments(bundle);
//        ft.replace(android.R.id.content, fragment);
//        ft.addToBackStack(null);
//        ft.commit();


        EstatisticasFragment fragment = new EstatisticasFragment();
        Bundle args = new Bundle();
        args.putSerializable("usuario", usuario);
        fragment.setArguments(args);
        return args;
    }
}

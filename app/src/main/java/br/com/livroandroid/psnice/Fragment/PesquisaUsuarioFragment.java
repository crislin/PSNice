package br.com.livroandroid.psnice.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.Adapter.UsuariosAdapter;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.Service.PSNiceService;
import br.com.livroandroid.psnice.Usuario;

public class PesquisaUsuarioFragment extends Fragment {

    View view;
    RecyclerView mRecyclerView;
    private List<Usuario> listaUsuarios = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pesquisa_usuario, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view_layour_recycler);

        try {
            listaUsuarios = PSNiceService.getListaUsuarios(view.getContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        UsuariosAdapter listAdapter = new UsuariosAdapter(getActivity(), listaUsuarios);
        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}

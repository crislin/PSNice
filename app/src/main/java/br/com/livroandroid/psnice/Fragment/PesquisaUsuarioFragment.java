package br.com.livroandroid.psnice.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.Adapter.UsuariosAdapter;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.Service.PSNiceService;
import br.com.livroandroid.psnice.Usuario;

public class PesquisaUsuarioFragment extends Fragment {

    View view;
    private RecyclerView mRecyclerView;
    private EditText etPesquisaUsuario;
    private List<Usuario> listaUsuarios = new ArrayList<>();
    private List<Usuario> listaUsuariosParaRecycler = new ArrayList<>();
    private String psnId;
    private LinearLayout textoResultado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pesquisa_usuario, container, false);
        etPesquisaUsuario = view.findViewById(R.id.etPesquisaUsuario);
        textoResultado = view.findViewById(R.id.textoResultado);
        mRecyclerView = view.findViewById(R.id.recycler_view_layour_recycler);
        psnId = getArguments().getString("psnId");

        try {
            listaUsuarios = PSNiceService.getListaUsuarios(view.getContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listaUsuarios.size(); i++){
            if (psnId.equalsIgnoreCase(listaUsuarios.get(i).getPsnId())){
                listaUsuarios.remove(i);
            }
        }
        listaUsuariosParaRecycler.addAll(listaUsuarios);
        mostraLista(listaUsuariosParaRecycler);
        etPesquisaUsuario.addTextChangedListener(fazPesquisa());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private TextWatcher fazPesquisa() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!etPesquisaUsuario.getText().toString().equalsIgnoreCase("")){
                    listaUsuariosParaRecycler.clear();
                    textoResultado.setVisibility(View.VISIBLE);
                    for (int t = 0; t < listaUsuarios.size(); t++){
                        if (listaUsuarios.get(t).getPsnId().toLowerCase().contains(etPesquisaUsuario.getText().toString().toLowerCase())){
                            listaUsuariosParaRecycler.add(listaUsuarios.get(t));
                        }
                    }
                    mostraLista(listaUsuariosParaRecycler);
                } else {
                    textoResultado.setVisibility(View.GONE);
                    listaUsuariosParaRecycler.clear();
                    listaUsuariosParaRecycler.addAll(listaUsuarios);
                    mostraLista(listaUsuariosParaRecycler);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    public void mostraLista(List<Usuario> lista){
        UsuariosAdapter listAdapter = new UsuariosAdapter(getActivity(), lista);
        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
    }
}

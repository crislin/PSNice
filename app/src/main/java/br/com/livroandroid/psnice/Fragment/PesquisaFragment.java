package br.com.livroandroid.psnice.Fragment;


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

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.Adapter.JogosAdapter;
import br.com.livroandroid.psnice.Jogo;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.Service.PSNiceService;

/**
 * A simple {@link Fragment} subclass.
 */
public class PesquisaFragment extends Fragment {

    private EditText etPesquisa;
    private View view;
    private RecyclerView mRecyclerView;
    private List<Jogo> listaDosJogos = new ArrayList<>();
    private List<Jogo> listaDosJogosPraRecycle = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_pesquisa, container, false);
        etPesquisa = view.findViewById(R.id.etPesquisa);
        mRecyclerView = view.findViewById(R.id.recycler_view_layour_recycler);
        try {
            listaDosJogos = PSNiceService.getJogos(view.getContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listaDosJogosPraRecycle.addAll(listaDosJogos);

        mostraLista(listaDosJogosPraRecycle);

        etPesquisa.addTextChangedListener(fazPesquisa());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private TextWatcher fazPesquisa(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!etPesquisa.getText().toString().equalsIgnoreCase("")){
                    listaDosJogosPraRecycle.clear();
                    for (int t = 0; t < listaDosJogos.size(); t++){
                        if (listaDosJogos.get(t).getNome().toLowerCase().contains(etPesquisa.getText().toString().toLowerCase())){
                            listaDosJogosPraRecycle.add(listaDosJogos.get(t));
                        }
                    }
                    mostraLista(listaDosJogosPraRecycle);
                } else {
                    listaDosJogosPraRecycle.clear();
                    listaDosJogosPraRecycle.addAll(listaDosJogos);
                    mostraLista(listaDosJogosPraRecycle);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    public void mostraLista(List<Jogo> lista){
        JogosAdapter listAdapter = new JogosAdapter(getActivity(), lista);
        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
    }
}

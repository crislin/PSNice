package br.com.livroandroid.psnice.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.livroandroid.psnice.Adapter.JogosAdapter;
import br.com.livroandroid.psnice.R;

public class ListaJogosFragment extends Fragment {

    View view;
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista_jogos, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view_layour_recycler);

        JogosAdapter listAdapter = new JogosAdapter(getActivity());
        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("destruiu");
    }
}



































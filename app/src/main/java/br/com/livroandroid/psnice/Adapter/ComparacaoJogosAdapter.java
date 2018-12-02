package br.com.livroandroid.psnice.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.livroandroid.psnice.Activity.ComparacaoTrofeuActivity;
import br.com.livroandroid.psnice.Jogo;
import br.com.livroandroid.psnice.R;

/**
 * Created by gazip on 02/12/2018.
 */

public class ComparacaoJogosAdapter extends RecyclerView.Adapter<ComparacaoJogosListViewHolder> {

    private List<Integer> listaComparado;
    private List<Jogo> listaEmComum;
    private Context context;
    private String psnIdLogado;
    private String psnIdComparado;
    private boolean logado;

    public ComparacaoJogosAdapter(Context context, List<Integer> listaComparado, List<Jogo> listaEmComum, String psnIdLogado, String psnIdComparado, boolean logado){
        this.context = context;
        this.listaComparado = listaComparado;
        this.listaEmComum = listaEmComum;
        this.psnIdLogado = psnIdLogado;
        this.psnIdComparado = psnIdComparado;
        this.logado = logado;
    }
    @NonNull
    @Override
    public ComparacaoJogosListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_comparacao_jogos, parent, false);
        return new ComparacaoJogosListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComparacaoJogosListViewHolder holder, final int position) {
        holder.tvNomeJogo.setText(listaEmComum.get(position).getNome());
        holder.tvPorcentagemLogado.setText(String.valueOf(listaEmComum.get(position).getGameProgress()));
        holder.tvPorcentagemComparacao.setText(String.valueOf(listaComparado.get(position)));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaiPraComparacaoTrofeu(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaEmComum.size();
    }

    public void vaiPraComparacaoTrofeu(int position){
        Intent i = new Intent(context, ComparacaoTrofeuActivity.class);
        i.putExtra("psnIdLogado", psnIdLogado);
        i.putExtra("psnIdComparado", psnIdComparado);
        i.putExtra("nomeJogo", listaEmComum.get(position).getNome());
        i.putExtra("idJogo", listaEmComum.get(position).getIdJogo());
        i.putExtra("logado", logado);
        context.startActivity(i);
    }
}
class ComparacaoJogosListViewHolder extends RecyclerView.ViewHolder{
    public TextView tvNomeJogo;
    public TextView tvPorcentagemLogado;
    public TextView tvPorcentagemComparacao;

    public ComparacaoJogosListViewHolder(View itemView){
        super(itemView);
        tvNomeJogo = itemView.findViewById(R.id.tvNomeJogo);
        tvPorcentagemLogado = itemView.findViewById(R.id.tvPorcentagemLogado);
        tvPorcentagemComparacao = itemView.findViewById(R.id.tvPorcentagemComparacao);
    }
}
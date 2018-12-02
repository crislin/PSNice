package br.com.livroandroid.psnice.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.livroandroid.psnice.Activity.DetalheTrofeuActivity;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.Trofeu;

/**
 * Created by gazip on 02/12/2018.
 */

public class ComparacaoTrofeusAdapter extends RecyclerView.Adapter<ComparacaoTrofeusListViewHolder> {

    public Context context;
    public List<Trofeu> listaLogado;
    public List<Trofeu> listaComparado;
    public String psnIdLogado;
    public String psnIdComparado;
    public String idJogo;
    public boolean logado;

    public ComparacaoTrofeusAdapter(Context context, List<Trofeu> listaLogado, List<Trofeu> listaComparado, String psnIdLogado, String psnIdComparado, String idJogo, boolean logado){
        this.context = context;
        this.listaLogado = listaLogado;
        this.listaComparado = listaComparado;
        this.psnIdLogado = psnIdLogado;
        this.psnIdComparado = psnIdComparado;
        this.idJogo = idJogo;
        this.logado = logado;
    }

    @NonNull
    @Override
    public ComparacaoTrofeusListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_comparacao_trofeu, parent, false);
        return new ComparacaoTrofeusListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComparacaoTrofeusListViewHolder holder, final int position) {
        holder.tvNomeJogo.setText(listaLogado.get(position).getNome());
        if (!listaLogado.get(position).getEarned()){
            holder.ivTrofeuLogado.setAlpha((float) 0.5);
        }
        if (!listaComparado.get(position).getEarned()){
            holder.ivTrofeuComparado.setAlpha((float) 0.5);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailActivity(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaLogado.size();
    }

    private void openDetailActivity(int posicion) {
        Intent i = new Intent(context, DetalheTrofeuActivity.class);
        i.putExtra("imagem", listaLogado.get(posicion).getImagemTrofeu());
        i.putExtra("nomeTrofeu", listaLogado.get(posicion).getNome());
        i.putExtra("descricao", listaLogado.get(posicion).getDescricao());
        i.putExtra("earned", listaLogado.get(posicion).getEarned());
        i.putExtra("tipo", listaLogado.get(posicion).getTipo());
        i.putExtra("idJogo", idJogo);
        i.putExtra("psnId", psnIdComparado);
        i.putExtra("logado", logado);
        i.putExtra("psnIdLogado", psnIdLogado);
        i.putExtra("dataConquistada", listaLogado.get(posicion).getDayEarned() + "  " + listaLogado.get(posicion).getHourEarned());
        context.startActivity(i);
    }
}
class ComparacaoTrofeusListViewHolder extends RecyclerView.ViewHolder{
    public TextView tvNomeJogo;
    public ImageView ivTrofeuLogado;
    public ImageView ivTrofeuComparado;

    public ComparacaoTrofeusListViewHolder(View itemView){
        super(itemView);
        tvNomeJogo = itemView.findViewById(R.id.tvNomeJogo);
        ivTrofeuLogado = itemView.findViewById(R.id.ivTrofeuLogado);
        ivTrofeuComparado = itemView.findViewById(R.id.ivTrofeuComparado);
    }
}
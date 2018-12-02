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

import com.bumptech.glide.Glide;

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
        holder.tvDescricao.setText(listaLogado.get(position).getDescricao());
        Glide.with(context).load(listaLogado.get(position).getImagemTrofeu()).into(holder.ivImagemTrofeu);
        if (!listaLogado.get(position).getEarned()){
            holder.ivTipoLogado.setAlpha((float) 0.5);
        }
        if (!listaComparado.get(position).getEarned()){
            holder.ivTipoComparado.setAlpha((float) 0.5);
        }
        String tipo = listaLogado.get(position).getTipo();
        if (tipo.equalsIgnoreCase("platinum")){
            holder.ivTipoLogado.setImageResource(R.drawable.trofeu_platina);
            holder.ivTipoComparado.setImageResource(R.drawable.trofeu_platina);
        }
        if (tipo.equalsIgnoreCase("gold")){
            holder.ivTipoLogado.setImageResource(R.drawable.trofeu_ouro);
            holder.ivTipoComparado.setImageResource(R.drawable.trofeu_ouro);
        }
        if (tipo.equalsIgnoreCase("silver")){
            holder.ivTipoLogado.setImageResource(R.drawable.trofeu_prata);
            holder.ivTipoComparado.setImageResource(R.drawable.trofeu_prata);
        }
        if (tipo.equalsIgnoreCase("bronze")){
            holder.ivTipoLogado.setImageResource(R.drawable.trofeu_bronze);
            holder.ivTipoComparado.setImageResource(R.drawable.trofeu_bronze);
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
    public TextView tvDescricao;
    public ImageView ivTipoLogado;
    public ImageView ivTipoComparado;
    public ImageView ivImagemTrofeu;

    public ComparacaoTrofeusListViewHolder(View itemView){
        super(itemView);
        tvNomeJogo = itemView.findViewById(R.id.tvNomeJogo);
        ivTipoLogado = itemView.findViewById(R.id.ivTipoLogado);
        ivTipoComparado = itemView.findViewById(R.id.ivTipoComparado);
        ivImagemTrofeu = itemView.findViewById(R.id.ivImagemTrofeu);
        tvDescricao = itemView.findViewById(R.id.tvDescricao);
    }
}
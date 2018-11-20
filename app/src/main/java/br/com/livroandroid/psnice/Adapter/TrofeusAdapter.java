package br.com.livroandroid.psnice.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.Activity.DetalheJogoActivity;
import br.com.livroandroid.psnice.Activity.DetalheTrofeuActivity;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.TesteJogos;
import br.com.livroandroid.psnice.TesteTrofeu;
import br.com.livroandroid.psnice.Trofeu;

/**
 * Created by livetouch on 10/09/18.
 */

public class TrofeusAdapter extends RecyclerView.Adapter<TrofeusListViewHolder> {

    private Context context;
    private String nomeJogo;
    private List<Trofeu> listaTrofeus = new ArrayList<>();

    public TrofeusAdapter(Context c, List<Trofeu> listaTrofeus, String nomeJogo){
        context = c;
        this.listaTrofeus = listaTrofeus;
        this.nomeJogo = nomeJogo;
    }

    @NonNull
    @Override
    public TrofeusListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_trofeu, parent, false);
        return new TrofeusListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrofeusListViewHolder holder, final int position) {
        holder.nomeTrofeu.setText(listaTrofeus.get(position).getNome());
        holder.descricaoTrofeu.setText(listaTrofeus.get(position).getDescricao());
        Glide.with(context).load(listaTrofeus.get(position).getImagemTrofeu()).into(holder.imagemTrofeu);
        if (listaTrofeus.get(position).getEarned()){
            holder.linearPai.setBackgroundColor(context.getResources().getColor(R.color.trophie_earned));
        }
        if (!listaTrofeus.get(position).getEarned()){
            holder.imagemTrofeu.setAlpha((float) 0.5);
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
        return listaTrofeus.size();
    }

    private void openDetailActivity(int posicion) {
        Intent i = new Intent(context, DetalheTrofeuActivity.class);
        i.putExtra("imagem", listaTrofeus.get(posicion).getImagemTrofeu());
        i.putExtra("nomeTrofeu", listaTrofeus.get(posicion).getNome());
        i.putExtra("descricao", listaTrofeus.get(posicion).getDescricao());
        i.putExtra("earned", listaTrofeus.get(posicion).getEarned());
        i.putExtra("nomeJogo", nomeJogo);
        context.startActivity(i);
    }

}

class TrofeusListViewHolder extends RecyclerView.ViewHolder{
    public ImageView imagemTrofeu;
    public TextView nomeTrofeu;
    public TextView descricaoTrofeu;
    public LinearLayout linearPai;

    public  TrofeusListViewHolder(View itemView){
        super(itemView);
        imagemTrofeu = itemView.findViewById(R.id.imagemTrofeu);
        nomeTrofeu = itemView.findViewById(R.id.nomeTrofeu);
        descricaoTrofeu = itemView.findViewById(R.id.descricaoTrofeu);
        linearPai = itemView.findViewById(R.id.linearPai);
    }
}


























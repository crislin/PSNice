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

import br.com.livroandroid.psnice.Activity.DetalheJogoActivity;
import br.com.livroandroid.psnice.Activity.DetalheTrofeuActivity;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.TesteJogos;
import br.com.livroandroid.psnice.TesteTrofeu;

/**
 * Created by livetouch on 10/09/18.
 */

public class TrofeusAdapter extends RecyclerView.Adapter<TrofeusListViewHolder> {

    private Context context;
    private String nomeJogo;

    public TrofeusAdapter(Context c, String nomeJogo){
        context = c;
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
        holder.imagemTrofeu.setImageResource(TesteTrofeu.imagemTrofeu[position]);
        holder.nomeTrofeu.setText(TesteTrofeu.nomeTrofeu[position]);
        holder.descricaoTrofeu.setText(TesteTrofeu.descricaoTrofeu[position]);
        if (TesteTrofeu.trofeusGanhos[position]){
            holder.linearPai.setBackgroundColor(context.getResources().getColor(R.color.trophie_earned));
        }
        if (!TesteTrofeu.trofeusGanhos[position]){
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
        return TesteTrofeu.nomeTrofeu.length;
    }

    private void openDetailActivity(int posicion) {
        Intent i = new Intent(context, DetalheTrofeuActivity.class);
        i.putExtra("imagem", TesteTrofeu.imagemTrofeu[posicion]);
        i.putExtra("nomeTrofeu", TesteTrofeu.nomeTrofeu[posicion]);
        i.putExtra("descricao", TesteTrofeu.descricaoTrofeu[posicion]);
        i.putExtra("earned", TesteTrofeu.trofeusGanhos[posicion]);
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


























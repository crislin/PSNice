package br.com.livroandroid.psnice.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import br.com.livroandroid.psnice.Activity.DetalheJogoActivity;
import br.com.livroandroid.psnice.Activity.HomeActivity;
import br.com.livroandroid.psnice.Activity.LoginActivity;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.TesteJogos;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by livetouch on 05/09/18.
 */

public class JogosAdapter extends RecyclerView.Adapter<JogosListViewHolder> {

    private Context context;

    public JogosAdapter(Context c){
        context = c;
    }

    @NonNull
    @Override
    public JogosListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_jogo, parent, false);
        return new JogosListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JogosListViewHolder holder, final int position) {
        holder.sbPorcentagem.getThumb().mutate().setAlpha(0);
        holder.nomeJogos.setText(TesteJogos.nomeJogos[position]);
        holder.imagemJogos.setImageResource(TesteJogos.imagemJogos[position]);
        holder.trofeusGanhos.setText(String.valueOf(TesteJogos.trofeusJogos[position]));
        holder.totalTrofeus.setText(String.valueOf(TesteJogos.totalTrofeusJogos[position]));
        holder.porcentagem.setText(String.valueOf(TesteJogos.porcentagemJogos[position]));
        holder.sbPorcentagem.setProgress(TesteJogos.porcentagemJogos[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailActivity(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return TesteJogos.nomeJogos.length;
    }

    private void openDetailActivity(int posicion) {
        Intent i = new Intent(context, DetalheJogoActivity.class);
        i.putExtra("nome", TesteJogos.nomeJogos[posicion]);
        i.putExtra("imagem", TesteJogos.imagemJogos[posicion]);
        context.startActivity(i);
    }

}
class JogosListViewHolder extends RecyclerView.ViewHolder{
    public TextView nomeJogos;
    public ImageView imagemJogos;
    public TextView trofeusGanhos;
    public TextView totalTrofeus;
    public TextView porcentagem;
    public SeekBar sbPorcentagem;

    public  JogosListViewHolder(View itemView){
        super(itemView);
        nomeJogos = itemView.findViewById(R.id.nomeJogo);
        imagemJogos = itemView.findViewById(R.id.imagemJogo);
        trofeusGanhos = itemView.findViewById(R.id.tcTrofeus);
        totalTrofeus = itemView.findViewById(R.id.tvTotalTrofeus);
        porcentagem = itemView.findViewById(R.id.porcentagem);
        sbPorcentagem = itemView.findViewById(R.id.sbPorcentagemJogo);
    }
}
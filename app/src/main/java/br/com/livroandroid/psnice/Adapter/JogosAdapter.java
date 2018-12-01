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
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

import br.com.livroandroid.psnice.Activity.DetalheJogoActivity;
import br.com.livroandroid.psnice.Activity.HomeActivity;
import br.com.livroandroid.psnice.Activity.LoginActivity;
import br.com.livroandroid.psnice.Jogo;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.TesteJogos;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by livetouch on 05/09/18.
 */

public class JogosAdapter extends RecyclerView.Adapter<JogosListViewHolder> {

    private String psnId;
    private String psnIdLogado;
    private Context context;
    private boolean logado;
    private List<Jogo> listaDosJogos = new ArrayList<>();

    public JogosAdapter(Context c, List<Jogo> listaDosJogos, String psnId, boolean logado){
        this.context = c;
        this.listaDosJogos = listaDosJogos;
        this.psnId = psnId;
        this.logado = logado;
    }

    public JogosAdapter(Context context,List<Jogo> listaDosJogos, String psnId, boolean logado, String psnIdLogado) {
        this.psnId = psnId;
        this.psnIdLogado = psnIdLogado;
        this.context = context;
        this.logado = logado;
        this.listaDosJogos = listaDosJogos;
    }

    public JogosAdapter(Context c, List<Jogo> listaDosJogos){
        this.context = c;
        this.listaDosJogos = listaDosJogos;
    }

    @NonNull
    @Override
    public JogosListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_jogo, parent, false);
        return new JogosListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JogosListViewHolder holder, final int position) {
        holder.nomeJogos.setText(listaDosJogos.get(position).getNome());
        Glide.with(context).load(listaDosJogos.get(position).getImagem()).into(holder.imagemJogos);
        holder.trofeusGanhos.setText(String.valueOf(listaDosJogos.get(position).getGameTotalEarned()));
        holder.totalTrofeus.setText(String.valueOf(listaDosJogos.get(position).getGameTotal()));
        holder.porcentagem.setText(String.valueOf(listaDosJogos.get(position).getGameProgress()));
        holder.pbPorcentagem.setProgress(listaDosJogos.get(position).getGameProgress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailActivity(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDosJogos.size();
    }

    private void openDetailActivity(int position) {
        Intent i = new Intent(context, DetalheJogoActivity.class);
        i.putExtra("nome", listaDosJogos.get(position).getNome());
        i.putExtra("imagem", listaDosJogos.get(position).getImagem());
        i.putExtra("psnId", psnId);
        i.putExtra("idJogo", listaDosJogos.get(position).getIdJogo());
        i.putExtra("logado", logado);
        i.putExtra("psnIdLogado", psnIdLogado);
        i.putExtra("usuarioTemEsseJogo", true);
        i.putExtra("totalTrofeus", String.valueOf(listaDosJogos.get(position).getGameTotal()));
        i.putExtra("desenvolvedora", listaDosJogos.get(position).getDesenvolvedora());
        i.putExtra("genero", listaDosJogos.get(position).getGenero());
        i.putExtra("porcentagem", String.valueOf(listaDosJogos.get(position).getGameProgress()));
        context.startActivity(i);
    }

}
class JogosListViewHolder extends RecyclerView.ViewHolder{
    public TextView nomeJogos;
    public ImageView imagemJogos;
    public TextView trofeusGanhos;
    public TextView totalTrofeus;
    public TextView porcentagem;
    public ProgressBar pbPorcentagem;

    public  JogosListViewHolder(View itemView){
        super(itemView);
        nomeJogos = itemView.findViewById(R.id.nomeJogo);
        imagemJogos = itemView.findViewById(R.id.imagemJogo);
        trofeusGanhos = itemView.findViewById(R.id.tcTrofeus);
        totalTrofeus = itemView.findViewById(R.id.tvTotalTrofeus);
        porcentagem = itemView.findViewById(R.id.porcentagem);
        pbPorcentagem = itemView.findViewById(R.id.pbPorcentagem);
    }
}
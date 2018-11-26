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

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.Activity.DetalheJogoActivity;
import br.com.livroandroid.psnice.Jogo;
import br.com.livroandroid.psnice.R;

/**
 * Created by gazip on 25/11/2018.
 */

public class JogosPesquisaAdapter extends RecyclerView.Adapter<JogosPesquisaListViewHolder> {

    Context context;
    private String psnId;
    private boolean logado;
    private List<Jogo> listaJogos = new ArrayList<>();
    private List<Jogo> listaJogosUsuario = new ArrayList<>();

    public JogosPesquisaAdapter(Context context, List<Jogo> listaUsuarios, String psnId, boolean logado, List<Jogo> listaJogosUsuario){
        this.context = context;
        this.listaJogos = listaUsuarios;
        this.listaJogosUsuario = listaJogosUsuario;
        this.psnId = psnId;
        this.logado = logado;
    }

    @NonNull
    @Override
    public JogosPesquisaListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pesquisa_jogo, parent, false);
        return new JogosPesquisaListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JogosPesquisaListViewHolder holder, final int position) {
        Glide.with(context).load(listaJogos.get(position).getImagem()).into(holder.imagemJogo);
        holder.nomeJogo.setText(listaJogos.get(position).getNome());
        holder.tvTotalTrofeus.setText(String.valueOf(listaJogos.get(position).getGameTotal()));
        holder.tvDesenvolvedora.setText(listaJogos.get(position).getDesenvolvedora());
        holder.tvGenero.setText(listaJogos.get(position).getGenero());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailActivity(position);
            }
        });
    }

    private void openDetailActivity(int position) {
        Intent i = new Intent(context, DetalheJogoActivity.class);
        i.putExtra("imagem", listaJogos.get(position).getImagem());
        i.putExtra("psnId", listaJogos.get(position).getNome());
        i.putExtra("logado", logado);
        i.putExtra("psnId", psnId);
        i.putExtra("nome", listaJogos.get(position).getNome());
        i.putExtra("usuarioTemEsseJogo", retornaSeTemOJogo(position));
        i.putExtra("totalTrofeus", String.valueOf(listaJogos.get(position).getGameTotal()));
        i.putExtra("desenvolvedora", listaJogos.get(position).getDesenvolvedora());
        i.putExtra("genero", listaJogos.get(position).getGenero());
        i.putExtra("porcentagem", String.valueOf(retornaPorcentagemJogoUsuario(position)));
        context.startActivity(i);
    }

    private boolean retornaSeTemOJogo(int position){
        for (int i = 0; i < listaJogosUsuario.size(); i++){
            if (listaJogos.get(position).getNome().equalsIgnoreCase(listaJogosUsuario.get(i).getNome())){
                return true;
            }
        }
        return false;
    }

    private int retornaPorcentagemJogoUsuario(int position){
        for (int i = 0; i < listaJogosUsuario.size(); i++){
            if (listaJogos.get(position).getNome().equalsIgnoreCase(listaJogosUsuario.get(i).getNome())){
                return listaJogosUsuario.get(i).getGameProgress();
            }
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return listaJogos.size();
    }
}

class JogosPesquisaListViewHolder extends RecyclerView.ViewHolder{
    public ImageView imagemJogo;
    public TextView nomeJogo;
    public TextView tvTotalTrofeus;
    public TextView tvDesenvolvedora;
    public TextView tvGenero;

    public JogosPesquisaListViewHolder(View itemView) {
        super(itemView);
        imagemJogo = itemView.findViewById(R.id.imagemJogo);
        nomeJogo = itemView.findViewById(R.id.nomeJogo);
        tvTotalTrofeus = itemView.findViewById(R.id.tvTotalTrofeus);
        tvDesenvolvedora = itemView.findViewById(R.id.tvDesenvolvedora);
        tvGenero = itemView.findViewById(R.id.tvGenero);
    }
}
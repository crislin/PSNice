package br.com.livroandroid.psnice.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.Activity.DetalheTrofeuActivity;
import br.com.livroandroid.psnice.Comentario;
import br.com.livroandroid.psnice.R;

/**
 * Created by livetouch on 01/10/18.
 */

public class ComentarioAdapter extends RecyclerView.Adapter<ComentarioListViewHolder> {

    private List<Comentario> comentarios = new ArrayList<>();
    private String nomeJogo;
    private String nomeTrofeu;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    private DatabaseReference mComentarioReference = mRootReference.child("comentarios");
    private DatabaseReference mTrofeuReference;

    public ComentarioAdapter(List<Comentario> comentarios, String nomeJogo, String nomeTrofeu){
        this.comentarios = comentarios;
        this.nomeJogo = nomeJogo;
        this.nomeTrofeu = nomeTrofeu;
    }

    @NonNull
    @Override
    public ComentarioListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_comentarios, parent, false);
        mTrofeuReference = mComentarioReference.child(nomeJogo).child(nomeTrofeu);
        return new ComentarioListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ComentarioListViewHolder holder, final int position) {
        holder.tvIdComentario.setText(comentarios.get(position).getIdComentario());
        holder.tvComentario.setText(comentarios.get(position).getComentario());
        holder.tvData.setText(comentarios.get(position).getData());
        holder.tvTotalUpDownVotes.setText(String.valueOf(comentarios.get(position).getTotalVotos()));


        holder.upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(holder.layoutPaiVotos);
                int votos = comentarios.get(position).getTotalVotos();
                votos++;
                comentarios.get(position).setTotalVotos(votos);
                mTrofeuReference.child(comentarios.get(position).getKey()).setValue(comentarios.get(position));
                holder.tvTotalUpDownVotes.setText(String.valueOf(comentarios.get(position).getTotalVotos()));
            }
        });

        holder.downvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int votos = comentarios.get(position).getTotalVotos();
                votos--;
                comentarios.get(position).setTotalVotos(votos);
                mTrofeuReference.child(comentarios.get(position).getKey()).setValue(comentarios.get(position));
                holder.tvTotalUpDownVotes.setText(String.valueOf(comentarios.get(position).getTotalVotos()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }
}

class ComentarioListViewHolder extends RecyclerView.ViewHolder{
    public TextView tvIdComentario;
    public TextView tvComentario;
    public TextView tvData;
    public TextView tvTotalUpDownVotes;
    public ImageView upvote;
    public ImageView downvote;
    public LinearLayout layoutPaiVotos;

    public ComentarioListViewHolder(View itemView) {
        super(itemView);
        tvIdComentario = itemView.findViewById(R.id.tvIdComentario);
        tvComentario = itemView.findViewById(R.id.tvComentario);
        tvData = itemView.findViewById(R.id.tvData);
        tvTotalUpDownVotes = itemView.findViewById(R.id.tvTotalUpDownVotes);
        upvote = itemView.findViewById(R.id.upvote);
        downvote = itemView.findViewById(R.id.downvote);
        layoutPaiVotos = itemView.findViewById(R.id.layoutPaiVotos);
    }
}

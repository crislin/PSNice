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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.Activity.DetalheTrofeuActivity;
import br.com.livroandroid.psnice.Comentario;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.UsuarioLikes;

/**
 * Created by livetouch on 01/10/18.
 */

public class ComentarioAdapter extends RecyclerView.Adapter<ComentarioListViewHolder> {

    private List<Comentario> comentarios = new ArrayList<>();
    private String idJogo;
    private String nomeTrofeu;
    private String psnId;
    private Context context;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    private DatabaseReference mComentarioReference = mRootReference.child("comentarios");
    private DatabaseReference mTrofeuReference;

    public ComentarioAdapter(List<Comentario> comentarios, String idJogo, String nomeTrofeu, Context context, String psnId){
        this.comentarios = comentarios;
        this.idJogo = idJogo;
        this.nomeTrofeu = nomeTrofeu;
        this.context = context;
        this.psnId = psnId;
    }

    @NonNull
    @Override
    public ComentarioListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_comentarios, parent, false);
        mTrofeuReference = mComentarioReference.child(idJogo).child(nomeTrofeu);
        return new ComentarioListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ComentarioListViewHolder holder, final int position) {
        holder.tvIdComentario.setText(comentarios.get(position).getIdComentario());
        holder.tvComentario.setText(comentarios.get(position).getComentario());
        holder.tvData.setText(comentarios.get(position).getData());
        holder.tvLikes.setText(String.valueOf(comentarios.get(position).getLikes()));
        holder.tvDeslikes.setText(String.valueOf(comentarios.get(position).getDeslikes()));
        Glide.with(context).load(comentarios.get(position).getAvatar()).into(holder.miniAvatar);
        if (comentarios.get(position).getLikes() > 0){
            holder.tvLikes.setTextColor(context.getResources().getColor(R.color.Green_Playstation));
        }
        if (comentarios.get(position).getDeslikes() > 0){
            holder.tvDeslikes.setTextColor(context.getResources().getColor(R.color.Red_Playstation));
        }
        if (jaVotou(position)){
            if ("like".equalsIgnoreCase(retornaTipoVoto(position))){
                holder.upvote.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_like_votado));
            } else {
                holder.downvote.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_deslike_votado));
            }
        }


        holder.upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!jaVotou(position)) {
                    int likes = comentarios.get(position).getLikes();
                    likes++;
                    comentarios.get(position).setLikes(likes);
                    List<UsuarioLikes> lista;
                    if (comentarios.get(position).getVotos() != null) {
                        lista = comentarios.get(position).getVotos();
                    } else {
                        lista = new ArrayList<>();
                    }
                    lista.add(new UsuarioLikes(psnId, "like"));
                    comentarios.get(position).setVotos(lista);
                    mTrofeuReference.child(comentarios.get(position).getKey()).setValue(comentarios.get(position));
                    holder.tvLikes.setText(String.valueOf(comentarios.get(position).getLikes()));
                    holder.upvote.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_like_votado));
                    holder.tvLikes.setTextColor(context.getResources().getColor(R.color.Green_Playstation));
                } else {
                    Toast.makeText(context, "Já votou", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.downvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!jaVotou(position)) {
                    int deslikes = comentarios.get(position).getDeslikes();
                    deslikes++;
                    comentarios.get(position).setDeslikes(deslikes);
                    List<UsuarioLikes> lista;
                    if (comentarios.get(position).getVotos() != null) {
                        lista = comentarios.get(position).getVotos();
                    } else {
                        lista = new ArrayList<>();
                    }
                    lista.add(new UsuarioLikes(psnId, "deslike"));
                    comentarios.get(position).setVotos(lista);
                    mTrofeuReference.child(comentarios.get(position).getKey()).setValue(comentarios.get(position));
                    holder.tvDeslikes.setText(String.valueOf(comentarios.get(position).getDeslikes()));
                    holder.downvote.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_deslike_votado));
                    holder.tvDeslikes.setTextColor(context.getResources().getColor(R.color.Red_Playstation));
                } else {
                    Toast.makeText(context, "Já votou", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean jaVotou(int position){
        if (comentarios.get(position).getVotos() != null) {
            for (int i = 0; i < comentarios.get(position).getVotos().size(); i++) {
                if (psnId.equalsIgnoreCase(comentarios.get(position).getVotos().get(i).getPsnId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public String retornaTipoVoto(int position){
        if (comentarios.get(position).getVotos() != null) {
            for (int i = 0; i < comentarios.get(position).getVotos().size(); i++) {
                if (psnId.equalsIgnoreCase(comentarios.get(position).getVotos().get(i).getPsnId())) {
                    return comentarios.get(position).getVotos().get(i).getTipoVoto();
                }
            }
        }
        return "";
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
    public TextView tvLikes;
    public TextView tvDeslikes;
    public ImageView upvote;
    public ImageView downvote;
    public ImageView miniAvatar;

    public ComentarioListViewHolder(View itemView) {
        super(itemView);
        tvIdComentario = itemView.findViewById(R.id.tvIdComentario);
        tvComentario = itemView.findViewById(R.id.tvComentario);
        tvData = itemView.findViewById(R.id.tvData);
        tvLikes = itemView.findViewById(R.id.tvLikes);
        tvDeslikes = itemView.findViewById(R.id.tvDeslikes);
        upvote = itemView.findViewById(R.id.upvote);
        downvote = itemView.findViewById(R.id.downvote);
        miniAvatar = itemView.findViewById(R.id.miniAvatar);
    }
}

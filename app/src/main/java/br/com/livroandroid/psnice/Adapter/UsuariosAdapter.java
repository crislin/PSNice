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

import br.com.livroandroid.psnice.Activity.DetalheUsuarioActivity;
import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.Usuario;

/**
 * Created by gazip on 21/11/2018.
 */

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosListViewHolder> {

    Context context;
    private List<Usuario> listaUsuarios = new ArrayList<>();

    public UsuariosAdapter(Context context, List<Usuario> listaUsuarios){
        this.context = context;
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuariosListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pesquisa_usuarios, parent, false);
        return new UsuariosListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosListViewHolder holder, final int position) {
        holder.psnId.setText(listaUsuarios.get(position).getPsnId());
        holder.level.setText(String.valueOf(listaUsuarios.get(position).getLevel()));
        Glide.with(context).load(listaUsuarios.get(position).getAvatar()).into(holder.avatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailActivity(position);
            }
        });
    }

    private void openDetailActivity(int position) {
        Intent i = new Intent(context, DetalheUsuarioActivity.class);
        i.putExtra("psnId", listaUsuarios.get(position).getPsnId());
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }
}

class UsuariosListViewHolder extends RecyclerView.ViewHolder{
    public ImageView avatar;
    public TextView psnId;
    public TextView level;

    public UsuariosListViewHolder(View itemView) {
        super(itemView);
        avatar = itemView.findViewById(R.id.ivAvatar);
        psnId = itemView.findViewById(R.id.tvPsnId);
        level = itemView.findViewById(R.id.tvLevel);
    }
}

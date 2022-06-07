package com.psi.anidroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList nomeAnime, epAnime, idAnime, imageAnime, studioAnime, ratingAnime, sinopseAnime, linksAnime;
    private final String user_id;

    MyAdapter(Context context, ArrayList nomeAnime, ArrayList epAnime, ArrayList idAnime, ArrayList imageAnime, ArrayList<String> studioAnime, ArrayList<String> ratingAnime, ArrayList<String> sinopseAnime, ArrayList<String> linksAnime , String user_id){
        this.context = context;
        this.nomeAnime = nomeAnime;
        this.epAnime = epAnime;
        this.idAnime = idAnime;
        this.imageAnime = imageAnime;
        this.studioAnime = studioAnime;
        this.ratingAnime = ratingAnime;
        this.sinopseAnime = sinopseAnime;
        this.linksAnime = linksAnime;
        this.user_id = user_id;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_line, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //Muda o nome dos textViews
        holder.nomeAnime.setText("Nome: " + nomeAnime.get(position));
        holder.epAnime.setText("Episódios: " + epAnime.get(position));
        holder.idAnime.setText(String.valueOf(idAnime.get(position)));
        //Usamos uma classe para ajudar a colocar a foto no ecrã a partir de um link da net
        Glide.with(context).load(imageAnime.get(position)).into(holder.fotoAnime);
        //se se clicar no anime
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Muda de classe e envia informação extra ao qual este vai estar preparado
                Intent intent = new Intent(context, DetailsAnime.class);
                intent.putExtra("id", String.valueOf(idAnime.get(position)));
                intent.putExtra("nome", String.valueOf(nomeAnime.get(position)));
                intent.putExtra("epis", String.valueOf(epAnime.get(position)));
                intent.putExtra("image", String.valueOf(imageAnime.get(position)));
                intent.putExtra("studio", String.valueOf(studioAnime.get(position)));
                intent.putExtra("rating", String.valueOf(ratingAnime.get(position)));
                intent.putExtra("sinopse", String.valueOf(sinopseAnime.get(position)));
                intent.putExtra("links", String.valueOf(linksAnime.get(position)));
                intent.putExtra("idUser",user_id);
                //Muda de classe
                context.startActivity(intent);
            }
        });
    }
    //Devolve o número atual de animes
    @Override
    public int getItemCount() {
        return idAnime.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nomeAnime, epAnime, idAnime;
        ImageView fotoAnime;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //Inicializar os TextViews de acordo com o Layout
            nomeAnime = itemView.findViewById(R.id.nomeAnimeTxt);
            epAnime = itemView.findViewById(R.id.qntEpis);
            idAnime = itemView.findViewById(R.id.idAnime);
            fotoAnime = itemView.findViewById(R.id.fotoAnime);
            mainLayout = itemView.findViewById(R.id.mainLayoutL);
        }
    }
}

package com.psi.anidroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList nomeAnime, epAnime, idAnime, imageAnime;

    MyAdapter(Context context, ArrayList nomeAnime, ArrayList epAnime, ArrayList idAnime, ArrayList imageAnime){
        this.context = context;
        this.nomeAnime = nomeAnime;
        this.epAnime = epAnime;
        this.idAnime = idAnime;
        this.imageAnime = imageAnime;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_line, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nomeAnime.setText(String.valueOf(nomeAnime.get(position)));
        holder.epAnime.setText(String.valueOf(epAnime.get(position)));
        holder.idAnime.setText(String.valueOf(idAnime.get(position)));

        /*InputStream is = null;
        try {
            is = (InputStream) new URL(String.valueOf(imageAnime.get(position))).getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(is, "src name");
        holder.fotoAnime.setImageDrawable(d);*/
    }

    @Override
    public int getItemCount() {
        return idAnime.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nomeAnime, epAnime, idAnime;
        ImageView fotoAnime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeAnime = itemView.findViewById(R.id.nomeAnimeTxt);
            epAnime = itemView.findViewById(R.id.qntEpis);
            idAnime = itemView.findViewById(R.id.idAnime);
            fotoAnime = itemView.findViewById(R.id.fotoAnime);
        }
    }
}

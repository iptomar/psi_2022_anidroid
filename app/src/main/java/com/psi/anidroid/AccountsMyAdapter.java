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

public class AccountsMyAdapter extends RecyclerView.Adapter<AccountsMyAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList idUser, nameUser, emailUser, roleUser;

    AccountsMyAdapter(Context context, ArrayList idUser, ArrayList nameUser, ArrayList emailUser, ArrayList roleUser){
        this.context = context;
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.roleUser = roleUser;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_accounts_anime, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameUser.setText("Nome: " + nameUser.get(position));
        holder.emailUser.setText("Email: " + emailUser.get(position));
        holder.roleUser.setText("Role: " + roleUser.get(position));
        holder.idUser.setText(String.valueOf(idUser.get(position)));

        holder.accountlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsAnime.class);
                intent.putExtra("id", String.valueOf(idUser.get(position)));
                intent.putExtra("nome", String.valueOf(nameUser.get(position)));
                intent.putExtra("email", String.valueOf(emailUser.get(position)));
                intent.putExtra("role", String.valueOf(roleUser.get(position)));

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return idUser.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameUser, emailUser, idUser, roleUser;
        ImageView fotoUser;
        LinearLayout accountlayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameUser = itemView.findViewById(R.id.username_Account);
            emailUser = itemView.findViewById(R.id.email_Account);
            roleUser = itemView.findViewById(R.id.roles_Account);
            accountlayout = itemView.findViewById(R.id.accountLayout);
        }
    }

    public void clear() {
        int size = nameUser.size();
        nameUser.clear();
        notifyItemRangeRemoved(0, size);
    }
}

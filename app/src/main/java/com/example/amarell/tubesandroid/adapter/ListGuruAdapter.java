package com.example.amarell.tubesandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.amarell.tubesandroid.CustomOnItemClickListener;
import com.example.amarell.tubesandroid.DetailGuru;
import com.example.amarell.tubesandroid.R;
import com.example.amarell.tubesandroid.model.DataGuru;

import java.util.List;

public class ListGuruAdapter extends RecyclerView.Adapter<ListGuruAdapter.MyViewHolder>{

    private Context mContext;
    private List<DataGuru> mData;
    RequestOptions option;

    public ListGuruAdapter(Context mContext, List<DataGuru> mData) {
        this.mContext = mContext;
        this.mData = mData;
        option=new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.loading);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view=inflater.inflate(R.layout.guru_row_item, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.containertransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailGuru.class );
                intent.putExtra("id", mData.get(myViewHolder.getAdapterPosition()).getId());
                intent.putExtra("namaguru", mData.get(myViewHolder.getAdapterPosition()).getNama());
                intent.putExtra("pendidikan", mData.get(myViewHolder.getAdapterPosition()).getPendidikan());
                intent.putExtra("mapel", mData.get(myViewHolder.getAdapterPosition()).getNama_mapel());
                intent.putExtra("deskripsi", mData.get(myViewHolder.getAdapterPosition()).getDeskripsi());
                intent.putExtra("pengalaman", mData.get(myViewHolder.getAdapterPosition()).getPengalaman());
                intent.putExtra("prestasi", mData.get(myViewHolder.getAdapterPosition()).getPrestasi());
                intent.putExtra("telepon", mData.get(myViewHolder.getAdapterPosition()).getTelpon());
                intent.putExtra("tarif", mData.get(myViewHolder.getAdapterPosition()).getTarif());
                intent.putExtra("foto_guru", "http://192.168.43.64/webtemanbelajar/dokumen/profil/"+mData.get(myViewHolder.getAdapterPosition()).getFoto_profil());
                mContext.startActivity(intent);
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        /*int pokeCount=3;
        String hello=String.format(mContext.getResources().getString(R.string.nama),"Andi",pokeCount,"Budi");*/
        holder.nama.setText(mContext.getResources().getString(R.string.nama)+ " " + mData.get(position).getNama());
        holder.mapel.setText(mContext.getResources().getString(R.string.mapel)+ " " +mData.get(position).getNama_mapel());
        holder.status.setText(mContext.getResources().getString(R.string.tarif)+ " " +mData.get(position).getTarif());
        Glide.with(mContext).load("http://192.168.43.64/webtemanbelajar/dokumen/profil/"+mData.get(position).getFoto_profil()).apply(option).into(holder.imageView);

        int panjang=mData.get(position).getTelpon().length();
        String apiwa="https://api.whatsapp.com/send?phone=";
        final String url = apiwa+"+62"+mData.get(position).getTelpon();
        final String telpon="+62"+mData.get(position).getTelpon().substring(1, panjang);
        holder.chat.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                mContext.startActivity(i);
            }
        }));
        holder.telpon.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telpon, null));
                mContext.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nama, mapel, status;
        ImageView imageView;
        Button chat, telpon;
        LinearLayout containertransaksi;
        public MyViewHolder(View itemView) {
            super(itemView);
            containertransaksi=itemView.findViewById(R.id.containertransaksi);
            nama=itemView.findViewById(R.id.rowname);
            mapel=itemView.findViewById(R.id.namamapel);
            status=itemView.findViewById(R.id.status_booking);
            imageView=itemView.findViewById(R.id.img_item);
            chat=itemView.findViewById(R.id.btn_set_chat);
            telpon=itemView.findViewById(R.id.btn_set_telpon);
        }
    }
}

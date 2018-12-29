package com.example.amarell.tubesandroid;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailGuru extends AppCompatActivity {
    TextView tv_name, tv_pendidikan, tv_mapel, tv_deskripsi, tv_pengalaman,tv_prestasi;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_guru);
        //getSupportActionBar().hide();

        String nama = getIntent().getExtras().getString("namaguru");
        String pendidikan = getIntent().getExtras().getString("pendidikan");
        String nama_mapel = getIntent().getExtras().getString("mapel");
        String deskripsi = getIntent().getExtras().getString("deskripsi");
        String pengalaman = getIntent().getExtras().getString("pengalaman");
        String prestasi = getIntent().getExtras().getString("prestasi");
        String foto_guru =  getIntent().getExtras().getString("foto_guru");
        String tarif = getIntent().getExtras().getString("tarif");
        String telpon = getIntent().getExtras().getString("telepon");



        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsetoolbar);
        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setTitle(nama);



        tv_name=findViewById(R.id.aa_rowname);
        tv_pendidikan= findViewById(R.id.aa_pendidikan);
        tv_mapel=findViewById(R.id.aa_namamapel);
        tv_deskripsi=findViewById(R.id.tentordeskripsi);
        tv_pengalaman= findViewById(R.id.aa_pengalaman);
        tv_prestasi=findViewById(R.id.aa_prestasi);
        img =findViewById(R.id.aa_thumbnail);

        tv_name.setText(getResources().getString(R.string.nama)+ " " +nama);
        tv_pendidikan.setText(getResources().getString(R.string.edu)+ " " +pendidikan);
        tv_mapel.setText(getResources().getString(R.string.mapel)+ " " +nama_mapel);
        tv_deskripsi.setText(getResources().getString(R.string.desk)+ " " +deskripsi);
        tv_pengalaman.setText(getResources().getString(R.string.exper)+ " " +pengalaman);
        tv_prestasi.setText(getResources().getString(R.string.prestasi)+ " " +prestasi);


        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading);

        Glide.with(this).load(foto_guru).apply(requestOptions).into(img);


    }
}

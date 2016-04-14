package com.nua.rentalalifgame;

/**
 * Created by g40-70 on 13/09/2015.
 */
public class KategoriHarga {

    public String id_kategori;
    public String nama_kategori;
    public String harga_kategori;

    public KategoriHarga(String id_kategori,String nama, String harga){
        this.id_kategori = id_kategori;
        this.nama_kategori = nama;
        this.harga_kategori = harga;
    }

    public String getId_kategori(){
        return this.id_kategori;
    }

    public String getNama_kategori(){
        return this.nama_kategori;
    }

    public  String getHarga_kategori(){
        return this.harga_kategori;
    }

}

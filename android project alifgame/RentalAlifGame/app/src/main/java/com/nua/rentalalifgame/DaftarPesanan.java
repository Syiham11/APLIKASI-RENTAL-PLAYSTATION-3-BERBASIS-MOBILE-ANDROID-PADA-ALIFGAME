package com.nua.rentalalifgame;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by g40-70 on 16/09/2015.
 */
public class DaftarPesanan {

    private String id_pemesanan;
    private String jenis_ps;
    private String kategori_ps;
    private String harga;
    private String tanggal;
    private String status_pesanan;

    public DaftarPesanan(String id_pemesanan, String jenis_ps, String kategori_ps, String harga, String tanggal, String status_pesanan){
        this.id_pemesanan = id_pemesanan;
        this.jenis_ps = "PS " + jenis_ps;
        this.kategori_ps = kategori_ps;
        this.harga = "Rp. " + harga;
        this.tanggal = tanggal;
        this.status_pesanan = status_pesanan;
    }

    public String getId_pemesanan(){
        return this.id_pemesanan;
    }

    public String getJenis_ps(){
        return this.jenis_ps;
    }

    public String getKategori_ps(){
        return this.kategori_ps;
    }

    public String getHarga(){
        return this.harga;
    }

    public String getTanggal(){
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date datetime = simpleDateFormat.parse(this.tanggal);
            String[] bulanArr = {"Jannuari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
            int bulan = datetime.getMonth();
            String namaBulan = null;
            for (int i = 0; i < bulanArr.length; i++) {
                if (bulan == i) {
                    namaBulan = bulanArr[i];
                    break;
                }
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            String hasil = datetime.getDate() + " " + namaBulan + " " + dateFormat.format(datetime);
            return hasil;
        } catch (ParseException e) {
            return this.tanggal;
        }
    }

    public String getStatus_pesanan(){
        if(this.status_pesanan.equals("lihat")){
            return "Menunggu Konfirmasi Alif Game";
        }else if(this.status_pesanan.equals("terima")){
            return "Pesanan Telah Dikonfirmasi";
        }else if(this.status_pesanan.equals("proses")){
            return "Pesanan Dalam Proses";
        }else if(this.status_pesanan.equals("tolak")){
            return "Pesanan Anda Ditolak";
        }else {
            return "Playstation Telah Anda Terima";
        }
    }
}

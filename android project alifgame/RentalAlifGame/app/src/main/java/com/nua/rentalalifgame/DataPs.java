package com.nua.rentalalifgame;

/**
 * Created by g40-70 on 10/09/2015.
 */
public class DataPs {
    public String id_ps;
    public String jenis_ps;
    public String kelengkapan_ps;
    public String keterangan_ps;


    public DataPs(){
    }

    public DataPs(String id_ps, String jenis_ps, String kelengkapan_ps, String keterangan_ps){
        this.id_ps = id_ps;
        this.jenis_ps = jenis_ps;
        this.kelengkapan_ps = kelengkapan_ps;
        this.keterangan_ps = keterangan_ps;
    }

    public void setId_ps(String idPs){
        this.id_ps = idPs;
    }

    public void setJenis_ps(String jenisPs){
        this.jenis_ps = jenisPs;
    }

    public void setKelengkapan_ps(String kelengkapanPs){
        this.kelengkapan_ps = kelengkapanPs;
    }

    public void setKeterangan_ps(String keteranganPs){
        this.keterangan_ps = keteranganPs;
    }

    public String getId_ps(){
        return this.id_ps;
    }

    public String getJenis_ps(){
        return this.jenis_ps;
    }

    public String getKelengkapan_ps(){
        return this.kelengkapan_ps;
    }

    public String getKeterangan_ps(){
        return this.keterangan_ps;
    }

}

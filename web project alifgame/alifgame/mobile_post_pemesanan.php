<?php
function generate($code, $event){
    $uniqid_event = uniqid($event);
    $sub_uniqid_event = substr($uniqid_event, -3);
    $uniqid_code = uniqid($code);
    $sub_uniqid_code = substr($uniqid_code, -3);
    $rand_start = uniqid($sub_uniqid_code,$sub_uniqid_event);
    $rand_finally = uniqid($rand_start);
    $rand_success = substr($rand_finally,-7);

    return $rand_success;
}
function gagal(){
    echo "{\"status_order\":null,\"kode_pemesanan\":null,\"error\":0}";
}
if(isset($_POST['nz']) and $_POST['nz'] == "mobile"){
    include "config/koneksi.php";
    $id_pengguna = isset($_POST['id_pengguna']) ? $_POST['id_pengguna'] : 0;
    $id_ps = isset($_POST['id_ps']) ? $_POST['id_ps'] : 0;
    $id_kategori = isset($_POST['id_kategori']) ? $_POST['id_kategori'] : 0;
    if(is_numeric($id_pengguna) and is_numeric($id_ps) and is_numeric($id_kategori) and $id_pengguna != 0 and $id_ps != 0 and $id_kategori != 0){
        $kode_pemesanan = generate('23213','32131');
        mysql_query("INSERT INTO `pemesanan` VALUES('','".$kode_pemesanan."','".$id_pengguna."','".$id_ps."','".$id_kategori."',now(),'lihat')") or die(gagal());
        echo "{\"status_order\":\"lihat\",\"kode_pemesanan\":\"".$kode_pemesanan."\",\"error\":0}";
    }else{
        gagal();
    }
}else{
    gagal();
}
?>
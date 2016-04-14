<?php
include "../config/koneksi.php";

$query = mysql_query("SELECT count(id_pemesanan) FROM `pemesanan` WHERE `status_pesanan`='proses' OR `status_pesanan`='terima'");// or die(mysql_error());
$row = mysql_num_rows($query);
if($row > 0){
    $data = mysql_fetch_row($query);
    $jumlah_data = $data[0] > 0 ? $data[0] : "0";
    echo $jumlah_data;
}else{
    echo "0";
}
?>
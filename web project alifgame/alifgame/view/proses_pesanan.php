<?php

$id_pemesanan = $_GET['id'];
$action = isset($_GET['act']) ? $_GET['act'] : "";
if($action != "" and is_numeric($id_pemesanan) and $id_pemesanan != 0){
    $cek_p = mysql_query("SELECT `id_pemesanan` FROM `pemesanan` WHERE `id_pemesanan`='".$id_pemesanan."'");// or die(mysql_error());
    $row = mysql_num_rows($cek_p);
    if($row > 0) {
        if ($action == "terima") {
            mysql_query("UPDATE `pemesanan` SET `status_pesanan`='terima' WHERE `id_pemesanan`='" . $id_pemesanan . "'") or die(mysql_error());
            header("Location:index.php?nav=pemesanan");
        } elseif ($action == "proses") {
            mysql_query("UPDATE `pemesanan` SET `status_pesanan`='proses' WHERE `id_pemesanan`='" . $id_pemesanan . "'");
            header("Location:index.php?nav=pemesanan");
        } elseif ($action == "sampai") {
            mysql_query("UPDATE `pemesanan` SET `status_pesanan`='sampai' WHERE `id_pemesanan`='" . $id_pemesanan . "'");
            header("Location:index.php?nav=pemesanan");
        } elseif ($action == "arsip") {
            mysql_query("UPDATE `pemesanan` SET `status_pesanan`='arsip' WHERE `id_pemesanan`='" . $id_pemesanan . "'");
            header("Location:index.php?nav=pemesanan");
        } else {
            header("Location:index.php?nav=pemesanan");
        }
    }else{
        header("Location:index.php?nav=pemesanan");
    }
}else{
    header("Location:index.php?nav=pemesanan");
}
?>
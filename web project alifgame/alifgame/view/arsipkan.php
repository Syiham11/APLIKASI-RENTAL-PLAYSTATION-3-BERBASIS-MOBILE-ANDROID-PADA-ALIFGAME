<?php

$ref = isset($_GET['ref']) ? $_GET['ref'] : "";
if($ref != ""){
    if($ref == "proses"){
        mysql_query("UPDATE `pemesanan` SET `status_pesanan`='arsip' WHERE `status_pesanan`='terima' OR `status_pesanan`='proses'");
        header("Location:index.php?nav=pemesanan");
    }elseif($ref == "sampai"){
        mysql_query("UPDATE `pemesanan` SET `status_pesanan`='arsip' WHERE `status_pesanan`='sampai'");
        header("Location:index.php?nav=pemesanan");
    }else{
        header("Location:index.php?nav=pemesanan");
    }
}else{
    header("Location:index.php?nav=pemesanan");
}
?>
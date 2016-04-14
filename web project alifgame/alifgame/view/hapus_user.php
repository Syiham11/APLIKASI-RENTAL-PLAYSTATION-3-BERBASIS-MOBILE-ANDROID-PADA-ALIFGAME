<?php

include "../config/koneksi.php";

$id_pengguna = isset($_GET['id']) ? $_GET['id'] : 0;
if(is_numeric($id_pengguna) and $id_pengguna != 0){
    $cek_q = "SELECT `A`.`id_pengguna`,`B`.`id_pengguna` FROM `pengguna` `A`,`user` `B` WHERE `A`.`id_pengguna`=`B`.`id_pengguna` AND `A`.`id_pengguna`='".$id_pengguna."'";
    $cek_r = mysql_query($cek_q);//or die(mysql_error());
    $row = mysql_num_rows($cek_r);
    if($row > 0){
        mysql_query("DELETE FROM `pengguna` WHERE `id_pengguna`='".$id_pengguna."'");
        mysql_query("DELETE FROM `user` WHERE `id_pengguna`='".$id_pengguna."'");
        header("Location:index.php?nav=pengguna");
    }else{
        header("Location:index.php?nav=pengguna");
    }
}
?>
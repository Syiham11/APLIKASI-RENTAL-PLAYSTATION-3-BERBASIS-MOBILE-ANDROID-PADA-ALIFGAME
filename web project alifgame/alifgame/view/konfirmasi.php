<?php

include "../config/koneksi.php";
$id_pengguna = isset($_GET['id']) ? $_GET['id'] : 0;
if(is_numeric($id_pengguna) and $id_pengguna != 0){
    $query = "SELECT `konfirmasi` FROM `user` WHERE `id_pengguna`='".$id_pengguna."'";
    $result = mysql_query($query);//or die(mysql_error());
    $row = mysql_num_rows($result);
    if($row > 0){
        $fetch = mysql_fetch_row($result);
        if($fetch[0] == 0){
            mysql_query("UPDATE `user` SET `konfirmasi`='1' WHERE `id_pengguna`='".$id_pengguna."'");
            header("Location:index.php?nav=user");
        }else{
            mysql_query("UPDATE `user` SET `konfirmasi`='0' WHERE `id_pengguna`='".$id_pengguna."'");
            header("Location:index.php?nav=user");
        }
    }else{
        header("Location:index.php?nav=user");
    }
}
?>
<?php

$id_ps = isset($_GET['id']) ? $_GET['id'] : 0;
if($params == "hapus_ps") {
    if (is_numeric($id_ps) and $id_ps != 0) {
        mysql_query("DELETE FROM `ps` WHERE `id_ps`='" . $id_ps . "'");
        header("Location:index.php?nav=ps");
    }
}elseif($params == "hapus_kategori"){
    if (is_numeric($id_ps) and $id_ps != 0) {
        mysql_query("DELETE FROM `kategori_harga` WHERE `id_kategori`='" . $id_ps . "'");
        header("Location:index.php?nav=ps");
    }
}else{
    header("Location:index.php?nav=ps");
}
?>
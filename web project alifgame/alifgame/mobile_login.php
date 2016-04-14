<?php

if(isset($_POST['nz']) and $_POST['nz'] == "mobile"){
    include "config/koneksi.php";

    $email = isset($_POST['email']) ? $_POST['email'] : "";
    $password = isset($_POST['password']) ? $_POST['password'] : "";

    if($email != "" and $password != ""){
        $query = "SELECT `id_pengguna`,`email`,`password` FROM `user` WHERE `email`='".$email."' AND `konfirmasi`='1'";
        $result = mysql_query($query);
        $row = mysql_num_rows($result);
        if($row > 0) {
            $data = mysql_fetch_row($result);
            $id_penguna = $data[0];
            $email = $data[1];
            $pass = $data[2];
            $password = md5($password);
            if ($pass == $password) {
                echo "{\"id_pengguna\":" . $id_penguna . ",\"email\":\"" . $email . "\",\"error\":0}";
            }else {
                echo "{\"id_pengguna\":" . $id_penguna . ",\"email\":\"" . $email . "\",\"error\":1}";
            }
        }else{
            echo "{\"id_pengguna\":0,\"email\":null,\"error\":0}";
        }
    }
}else{
    echo "{\"id_pengguna\":0,\"email\":null,\"error\":0}";
}
?>
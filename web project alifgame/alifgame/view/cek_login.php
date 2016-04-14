<?php

include "../config/koneksi.php";

session_start();

$email = isset($_POST['email']) ? $_POST['email'] : null;
$password = isset($_POST['password']) ? $_POST['password'] : null;
$pwd = md5($password);

if($email != null and $password != null){
	$query = "SELECT * FROM `user` WHERE `email`='". $email ."'";
	$execute = mysql_query($query) or die(mysql_error());
	$row = mysql_num_rows($execute);
	if($row > 0){
		$fetch = mysql_fetch_array($execute);
		$email_user = $fetch['email'];
		$password_user = $fetch['password'];
		$level = $fetch['level'];
		if($pwd == $password_user){
			$_SESSION['level'] = $level;
			header("Location:index.php");
		}else{
			echo "Maaf, username dan password tidak sesuai!";
		}
	}else{
		echo "Maaf, anda belum terdaftar";
	}
}else{
	echo "Maaf, username dan password harus di isi!";
}
?>
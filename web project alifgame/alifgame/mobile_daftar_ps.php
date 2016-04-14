<?php
if(isset($_POST['nz']) and $_POST['nz'] == "mobile"){
	include "config/koneksi.php";
	echo "{\"daftar_ps\":";
	$query = mysql_query("SELECT * FROM `ps`");
	$row = mysql_num_rows($query);
	if($row > 0){
		$daftar = array();
		while($hasil = mysql_fetch_assoc($query)){
			$daftar[] = $hasil;
		}
		echo json_encode($daftar);
	}else{
		echo "[]";
	}
	echo "}";
}else{
	echo "{\"daftar_ps\":[]}";
}
?>
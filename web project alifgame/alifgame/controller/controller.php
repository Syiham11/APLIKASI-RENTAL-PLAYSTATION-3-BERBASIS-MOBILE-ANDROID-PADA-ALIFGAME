<?php
$is_id = isset($_GET['id']);
if($params == "home"){
	include_once "home.php";
}else if($params == "logout"){
	include_once "logout.php";
}else if($params == "ps"){
	include_once "data_ps.php";
}else if($params == "form_ps"){
	include_once "form_ps.php";
}else if($params == "form_kategori"){
	include_once "form_kategori.php";
}else if($params == "pengguna"){
	include_once "data_pengguna.php";
}else if($params == "pemesanan"){
	include_once "data_pemesanan.php";
}else if($params == "user"){
	include_once "data_user.php";
}else if($params == "detail_pengguna" and $is_id ){
	include_once "detail_pengguna.php";
}else if($params == "hapus_user" and $is_id ){
	include_once "hapus_user.php";
}else if($params == "konfirmasi" and $is_id ){
	include_once "konfirmasi.php";
}else if($params == "setting"){
	include_once "setting.php";
}else if($params == "proses_pesanan" and $is_id){
	include_once "proses_pesanan.php";
}else if($params == "arsipkan"){
	include_once "arsipkan.php";
}else if($params == "detail_ps"){
	include_once "form_detail_ps.php";
}else if($params == "hapus_ps" and $is_id){
	include_once "hapus_ps.php";
}else if($params == "detail_kategori" and $is_id){
	include_once "form_detail_kategori.php";
}else if($params == "hapus_kategori" and $is_id){
	include_once "hapus_ps.php";
}else{
	include_once "home.php";
}
?>
<?php
$id_pengguna = isset($_GET['id']) ? $_GET['id'] : 0;
include "../config/koneksi.php";
if(is_numeric($id_pengguna) and $id_pengguna != 0){
    $query = "SELECT `A`.* ,`B`.`konfirmasi` FROM `pengguna` `A`, `user` `B` WHERE `A`.`id_pengguna`=`B`.`id_pengguna` AND `A`.`id_pengguna`='".$id_pengguna."'";
    $execute = mysql_query($query);// or die(mysql_error());
    $row = mysql_num_rows($execute);
    if($row > 0){
        $fetch = mysql_fetch_assoc($execute);
        $id_pengguna = $fetch['id_pengguna'];
        $nama_lengkap = $fetch['nama_lengkap'];
        $jkl = $fetch['jenis_kelamin'] == "L" ? "Laki-laki" : "Perempuan";
        $alamat = $fetch['alamat'];
        $tanggal_lahir = $fetch['tanggal_lahir'];
        $email = $fetch['email'];
        $no_hp = $fetch['no_hp'];
        $foto = $fetch['foto'];
        $confir = $fetch['konfirmasi'];

        $tanggal = explode("-",$tanggal_lahir);
        $array_bulan = array('Jannuari','Februari','Maret','April','Mei','Juni','Juli','Agustus','September','Oktober','November','Desember');
        $bulan = $tanggal[1];
        for($i = 0;$i < count($array_bulan);$i++){
            if($tanggal[1] == ($i+1)){
                $bulan = $array_bulan[$i];
            }
        }
        $tgl = $tanggal[2] . " " . $bulan . " " .$tanggal[0];
        $css_class_panel = $confir == 0 ? "warning" : "success";
        $text_confir = $confir == 0 ? "Tidak Dikonfirmasi" : "Dikonfimasi";
        ?>
        <div class="row" xmlns="http://www.w3.org/1999/html"><div class="col-lg-12"><div class="page-header"><h1>Data Pengguna</h1></div></div></div>
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-<?php echo $css_class_panel?>">
                    <div class="panel-heading">
                        Detail Pengguna - Status : <strong><?php echo $text_confir; ?></strong>
                        <div class="pull-right">
                            <div class="btn-group tooltip-demo" style="margin-top:-5px;">
                                <?php
                                if($confir == 0){
                                    ?>
                                    <a href="index.php?nav=konfirmasi&id=<?php echo $id_pengguna;?>" class="btn btn-default btn-circle" data-toggle="tooltip" data-placement="left" title="Konfirmasi"><i class="fa fa-check"></i></a>
                                    <?php
                                }else{
                                    ?>
                                    <a href="index.php?nav=konfirmasi&id=<?php echo $id_pengguna;?>" class="btn btn-default btn-circle" data-toggle="tooltip" data-placement="left" title="Batalkan Konfirmasi"><i class="glyphicon glyphicon-remove"></i></a>
                                    <?php
                                }
                                ?>
                                <a href="index.php?nav=hapus_user&id=<?php echo $id_pengguna;?>" class="btn btn-default btn-circle" data-toggle="tooltip" data-placement="left" title="Hapus Pengguna"><i class="glyphicon glyphicon-trash"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <?php
                        ?>
                        <table class="table table-bordered">
                            <tbody>
                            <tr>
                                <td>Nama Lengkap</td>
                                <td><?php echo $nama_lengkap; ?></td>
                            </tr>
                            <tr>
                                <td>Jenis Kelamani</td>
                                <td><?php echo $jkl; ?></td>
                            </tr>
                            <tr>
                                <td>Alamat</td>
                                <td><?php echo $alamat; ?></td>
                            </tr>
                            <tr>
                                <td>Tanggal Lahir</td>
                                <td><?php echo $tgl; ?></td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td><?php echo $email; ?></td>
                            </tr>
                            <tr>
                                <td>No. HP</td>
                                <td><?php echo $no_hp; ?></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <?php
    }else{
        header("Location:index.php?nav=pengguna");
    }
}else{
    header("Location:index.php?nav=pengguna");
}
?>
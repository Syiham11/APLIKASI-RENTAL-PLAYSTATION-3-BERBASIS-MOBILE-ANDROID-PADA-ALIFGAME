<?php

$id_ps = isset($_GET['id']) ? $_GET['id'] : 0;
$jenis = 1;
$kelengkapan = "";
$ket = "";
$unit_ps = "";
$gambar = 0;
if(is_numeric($id_ps) and $id_ps != 0) {
    ?>
    <div class="row">
        <div class="col-lg-12">
            <div class="page-header">
                <h1>Form Detail Data Playstation</h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Form Playstation

                    <div class="pull-right">
                        <div class="btn-group tooltip-demo" style="margin-top:-5px;">
                            <a href="index.php?nav=ps" class="btn btn-default btn-circle" data-toggle="tooltip"
                               data-placement="left" title="Lihat data Playstation"><i class="fa fa-list"></i></a>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <?php
                    if (isset($_POST['update'])) {
                        $jenis_ps = $_POST['jenis_ps'];
                        $kelengkapan_ps = mysql_real_escape_string($_POST['kelengkapan_ps']);
                        $keterangan_ps = mysql_real_escape_string($_POST['keterangan_ps']);
                        $unit = $_POST['unit_ps'] == "" ? 0 : $_POST['unit_ps'];
                        if (is_numeric($jenis_ps) and $jenis_ps > 0 and $jenis_ps < 5 and $kelengkapan_ps != "" and $keterangan_ps != "" and is_numeric($unit)) {
                            //mengambil auto increment id ps
                            //$query_cekId = mysql_query("SHOW TABLE STATUS LIKE 'ps'");
                            //$f_rows = mysql_fetch_row($query_cekId);
                            //$id_ps = $f_rows[10];

                            //mengambil auto increment id gambar
                            /*$query_cekIdG = mysql_query("SHOW TABLE STATUS LIKE 'gambar'");
                            $f_row = mysql_fetch_row($query_cekIdG);
                            $id_gambar = $f_row[10];*/

                            //melakukan penyimpanan data ps
                            $query = "UPDATE `ps` SET `jenis_ps`='" . $jenis_ps . "',`kelengkapan_ps`='" . $kelengkapan_ps . "',`keterangan`='" . $keterangan_ps . "' WHERE `id_ps`='".$id_ps."'";
                            $execute = mysql_query($query) or die(gagal());
                            ?>
                            <div class="alert alert-success alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert"
                                        aria-hidden="true">&times;</button>
                                Berhasil meperbarui data playstation.
                            </div>
                            <?php
                        } else {
                            ?>
                            <div class="alert alert-warning alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert"
                                        aria-hidden="true">&times;</button>
                                Pengisian form tidak sesuai ketentuan !
                            </div>
                            <?php
                        }
                    }

                    function gagal()
                    {
                        ?>
                        <div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert"
                                    aria-hidden="true">&times;</button>
                            Terjadi kesalahan ! Data tidak dapat disimpan.
                        </div>
                        <?php
                    }

                    $query1 = "SELECT * FROM `ps` WHERE `id_ps`='" . $id_ps . "'";
                    $result1 = mysql_query($query1);//or die(mysql_error());
                    $rows1 = mysql_num_rows($result1);
                    if ($rows1 > 0) {
                        $data = mysql_fetch_assoc($result1);
                        $jenis = $data['jenis_ps'];
                        $kelengkapan = $data['kelengkapan_ps'];
                        $ket = $data['keterangan'];
                        $unit_ps = $data['unit'] != 0 ? $data['unit'] : "";
                        $gambar = $data['id_gambar'];
                    } else {
                        header("Location:index.php?nav=ps");
                    }
                    ?>
                    <form role="form" method="post" action="index.php?nav=detail_ps&id=<?php echo $id_ps; ?>">
                        <div class="col-lg-6">
                            <h2>Data Playstation</h2>

                            <div class="form-group">
                                <label>Jenis Playstation</label>
                                <select class="form-control" name="jenis_ps">
                                    <?php
                                    for ($x = 1; $x < 5; $x++) {
                                        $selected = $jenis == $x ? "selected" : "";
                                        ?>
                                        <option value="<?php echo $x ?>" <?php echo $selected ?>>PS <?php echo $x ?></option>
                                        <?php
                                    }
                                    ?>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Kelengkapan Playstation</label>
                                <textarea name="kelengkapan_ps" placeholder="Kelengkapan" class="form-control"
                                          rows="3"><?php echo $kelengkapan; ?></textarea>
                            </div>
                            <div class="form-group">
                                <label>Keterangan</label>
                                <textarea name="keterangan_ps" placeholder="Keterangan" class="form-control"
                                          rows="3"><?php echo $ket; ?></textarea>
                            </div>
                            <!--<div class="form-group">
                                <label>Jumlah Unit</label>
                                <input type="text" class="form-control" name="unit_ps" placeholder="Unit"
                                       value="<php echo $unit_ps; ?>">
                            </div>-->
                        </div>
                        <!--<div class="col-lg-6">
                            <h2>Gambar Terkait Playstation</h2>
                            <div class="form-group">
                                <label>File Gambar 1</label>
                                <input type="file" name="gambar1">
                            </div>
                            <div class="form-group">
                                <label>File Gambar 2</label>
                                <input type="file" name="gambar2">
                            </div>
                            <div class="form-group">
                                <label>File Gambar 3</label>
                                <input type="file" name="gambar3">
                            </div>
                        </div>-->
                        <div class="col-lg-12">
                            <!--<div class="pull-right">-->
                            <button type="submit" name="update" class="btn btn-success">Perbarui Data</button>
                            <button type="reset" class="btn btn-default">Reset Form</button>
                            <!--</div>-->
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <?php
}else{
    header("Location:index.php?nav=ps");
}
?>
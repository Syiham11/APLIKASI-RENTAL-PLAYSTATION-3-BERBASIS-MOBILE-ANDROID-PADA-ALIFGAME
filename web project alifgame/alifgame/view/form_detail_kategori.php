<?php
$id_kategoti = isset($_GET['id']) ? $_GET['id'] : 0;
$nama_cat = "";
$harga_cat = "";
if(is_numeric($id_kategoti) and $id_kategoti != 0) {
    ?>
    <div class="row">
        <div class="col-lg-12">
            <div class="page-header">
                <h1>Form Detail Data Kategori Harga Rental</h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Form Kategori

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
                        $nama_kat = mysql_real_escape_string($_POST['nama_kategori']);
                        $harga = $_POST['harga'];
                        if ($nama_kat != "" and is_numeric($harga)) {
                            $query = "UPDATE `kategori_harga` SET `nama_kategori`='" . $nama_kat . "',`harga`='" . $harga . "' WHERE `id_kategori`='".$id_kategoti."'";
                            $execute = mysql_query($query) or die(gagal());
                            ?>
                            <div class="alert alert-success alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert"
                                        aria-hidden="true">&times;</button>
                                Berhasil memperbarui kategori
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
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            Terjadi kesalahan ! Data tidak dapat disimpan.
                        </div>
                        <?php
                    }

                    $query1 = "SELECT * FROM `kategori_harga` WHERE `id_kategori`='".$id_kategoti."'";
                    $result1 = mysql_query($query1);
                    $rows = mysql_num_rows($result1);
                    if($rows > 0) {
                        $data = mysql_fetch_assoc($result1);
                        $nama_cat = $data['nama_kategori'];
                        $harga_cat = $data['harga'];
                        ?>
                        <form role="form" method="post" action="index.php?nav=detail_kategori&id=<?php echo $id_kategoti; ?>">
                            <div class="col-lg-6">
                                <h2>Data Kategori</h2>

                                <div class="form-group">
                                    <label>Jenis / Nama Kategori</label>
                                    <input type="text" name="nama_kategori" class="form-control"
                                           placeholder="Nama kategori" value="<?php echo $nama_cat; ?>">
                                </div>
                                <div class="form-group">
                                    <label>Harga Rental</label>
                                    <input type="text" name="harga" class="form-control" placeholder="Harga" value="<?php echo $harga_cat; ?>">
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <button type="submit" name="update" class="btn btn-success">Simpan Data</button>
                                <button type="reset" class="btn btn-default">Reset Form</button>
                            </div>
                        </form>
                        <?php
                    }
                    ?>
                </div>
            </div>
        </div>
    </div>
    <?php
}
?>
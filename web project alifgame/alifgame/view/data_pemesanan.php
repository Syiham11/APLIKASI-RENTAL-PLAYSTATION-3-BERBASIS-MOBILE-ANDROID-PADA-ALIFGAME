<div class="row">
    <div class="col-lg-12">
        <div class="page-header">
            <h1>Pemesanan Rental Playstation</h1>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-12 col-md-12">
        <div class="panel panel-info">
            <div class="panel-heading">
                Data Pemesanan Baru

                <!--<div class="pull-right">
                    <div class="btn-group tooltip-demo" style="margin-top:-5px;">
                        <a href="index.php?nav=form_pelanggan" class="btn btn-default btn-circle" data-toggle="tooltip" data-placement="left" title="Tambah data Pelanggan"><i class="fa fa-plus"></i></a>
                    </div>
                </div>-->
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <thead>
                        <tr>
                            <th>No.</th>
                            <th>Waktu</th>
                            <th>Jam</th>
                            <th>Nama Lengkap</th>
                            <th>Jenis PS</th>
                            <th>Kategori</th>
                            <th>Harga</th>
                            <th>Tindakan</th>
                        </tr>
                        </thead>
                        <tbody>
                        <?php
                        $query_ps1 = "SELECT `A`.`id_pemesanan`,`B`.`nama_lengkap`,`C`.`jenis_ps`,`D`.`nama_kategori`,`D`.`harga`,`A`.`id_pengguna`,`A`.`tanggal` FROM `pemesanan` `A`, `pengguna` `B`, `ps` `C`,`kategori_harga` `D` WHERE `A`.`id_pengguna`=`B`.`id_pengguna` AND `A`.`id_ps`=`C`.`id_ps` AND `A`.`id_kategori`=`D`.`id_kategori` AND `A`.`status_pesanan`='lihat' ORDER BY  `A`.`id_pemesanan` DESC";
                        $result_ps1 = mysql_query($query_ps1);// or die(mysql_error());
                        $row_ps1 = mysql_num_rows($result_ps1);
                        if($row_ps1 > 0){
                            $no_ps1 = 1;
                            while($fetch_ps1 = mysql_fetch_row($result_ps1)){
                                $id_pemesanan1 = $fetch_ps1[0];
                                $nama_lengkap1 = $fetch_ps1[1];
                                $jenis_ps1 = "PS " . $fetch_ps1[2];
                                $kategori1 = $fetch_ps1[3];
                                $harga1 = $fetch_ps1[4];
                                $id_pengguna1 = $fetch_ps1[5];
                                $waktu_pesan1 = $fetch_ps1[6];
                                $tanggal_pesan1 = explode(" ",$waktu_pesan1);
                                $tanggal1 = explode("-",$tanggal_pesan1[0]);
                                $array_bulan1 = array('Jannuari','Februari','Maret','April','Mei','Juni','Juli','Agustus','September','Oktober','November','Desember');
                                $bulan1 = $tanggal1[1];
                                for($i1 = 0;$i1 < count($array_bulan1);$i1++){
                                    if($tanggal1[1] == ($i1+1)){
                                        $bulan1 = $array_bulan1[$i1];
                                    }
                                }
                                $tgl1 = $tanggal1[2] . " " . $bulan1 . " " .$tanggal1[0];
                                ?>
                                <tr class="odd gradeX">
                                    <td><?php echo $no_ps1; ?></td>
                                    <td><?php echo $tgl1; ?></td>
                                    <td><?php echo  $tanggal_pesan1[1]; ?></td>
                                    <td class="tooltip-demo"><a href="index.php?nav=detail_pengguna&id=<?php echo $id_pengguna1;?>" data-toggle="tooltip" data-placement="top" title="Lihat Detail Pengguna" ><?php echo $nama_lengkap1; ?></a></td>
                                    <td><?php echo $jenis_ps1; ?></td>
                                    <td><?php echo $kategori1; ?></td>
                                    <td><?php echo $harga1; ?></td>
                                    <td class="tooltip-demo">
                                        <a href="index.php?nav=proses_pesanan&act=terima&id=<?php echo $id_pemesanan1; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Terima Pesanan"><i class="glyphicon glyphicon-saved"></i></a>
                                        <a href="index.php?nav=proses_pesanan&act=tolak&id=<?php echo $id_pemesanan1; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Tolak Pesanan"><i class="glyphicon glyphicon-remove"></i></a>
                                    </td>
                                </tr>
                                <?php
                                $no_ps1++;
                            }
                        }else{
                            ?>
                            <tr><td colspan="8"><div class="alert alert-info">Belum ada pemesanan baru.</div></td></tr>
                            <?php
                        }
                        ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="page-header">
            <h1>Monitoring Pemesanan</h1>
        </div>
    </div>
</div>
<!-- Pemesanan Monitoring -->
<div class="row">
    <div class="col-md-6">
        <div class="panel panel-warning">
            <div class="panel-heading">
                Dalam Proses
                <div class="pull-right">
                    <div class="btn-group tooltip-demo" style="margin-top:-5px;">
                        <a href="index.php?nav=arsipkan&ref=proses" class="btn btn-default btn-circle" data-toggle="tooltip" data-placement="left" title="Arsipkan Semua"><i class="glyphicon glyphicon-floppy-disk"></i></a>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table class="table table-bordered table-hover" id="dataTables-example">
                        <thead>
                        <tr>
                            <th>No.</th>
                            <th>Nama Lengkap</th>
                            <th>Jenis PS</th>
                            <th>Kategori</th>
                            <th>Harga</th>
                            <th>Status</th>
                            <th>Tindakan</th>
                        </tr>
                        </thead>
                        <tbody>
                        <?php
                        $query_ps = "SELECT `A`.`id_pemesanan`,`B`.`nama_lengkap`,`C`.`jenis_ps`,`D`.`nama_kategori`,`D`.`harga`,`A`.`status_pesanan`,`A`.`id_pengguna` FROM `pemesanan` `A`, `pengguna` `B`, `ps` `C`,`kategori_harga` `D` WHERE `A`.`id_pengguna`=`B`.`id_pengguna` AND `A`.`id_ps`=`C`.`id_ps` AND `A`.`id_kategori`=`D`.`id_kategori` AND (`A`.`status_pesanan`='proses' OR `A`.`status_pesanan`='terima') ORDER BY  `A`.`id_pemesanan` DESC";
                        $result_ps = mysql_query($query_ps);// or die(mysql_error());
                        $row_ps = mysql_num_rows($result_ps);
                        if($row_ps > 0){
                            $no_ps = 1;
                            while($fetch_ps = mysql_fetch_row($result_ps)){
                                $id_pemesanan = $fetch_ps[0];
                                $nama_lengkap = $fetch_ps[1];
                                $jenis_ps = "PS " . $fetch_ps[2];
                                $kategori = $fetch_ps[3];
                                $harga = $fetch_ps[4];
                                $status_pesanan = $fetch_ps[5] == "terima" ? "Diproses" : "Dikirim";
                                $id_pengguna = $fetch_ps[6];
                                ?>
                                <tr>
                                    <td><?php echo $no_ps; ?></td>
                                    <td class="tooltip-demo"><a href="index.php?nav=detail_pengguna&id=<?php echo $id_pengguna;?>" data-toggle="tooltip" data-placement="top" title="Lihat Detail Pengguna" ><?php echo $nama_lengkap; ?></a></td>
                                    <td><?php echo $jenis_ps; ?></td>
                                    <td><?php echo $kategori; ?></td>
                                    <td><?php echo $harga; ?></td>
                                    <td><?php echo $status_pesanan; ?></td>
                                    <td class="tooltip-demo">
                                        <?php ?>
                                        <a href="index.php?nav=proses_pesanan&act=proses&id=<?php echo $id_pemesanan; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Kirim Pesanan"><i class="glyphicon glyphicon-transfer"></i></a>
                                        <a href="index.php?nav=proses_pesanan&act=sampai&id=<?php echo $id_pemesanan; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Diterima Pemesan"><i class="fa fa-child"></i></a>
                                        <a href="index.php?nav=proses_pesanan&act=arsip&id=<?php echo $id_pemesanan; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Arsipkan"><i class="glyphicon glyphicon-floppy-save"></i></a>
                                    </td>
                                </tr>
                                <?php
                                $no_ps++;
                            }
                        }else{
                            ?>
                            <tr><td colspan="7"><div class="alert alert-warning">Tidak ada pengriman saat ini.</div></td></tr>
                            <?php
                        }
                        ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="panel panel-success">
            <div class="panel-heading">
                Telah Diterima Pengguna
                <div class="pull-right">
                    <div class="btn-group tooltip-demo" style="margin-top:-5px;">
                        <a href="index.php?nav=arsipkan&ref=sampai" class="btn btn-default btn-circle" data-toggle="tooltip" data-placement="left" title="Arsipkan Semua"><i class="glyphicon glyphicon-floppy-disk"></i></a>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table class="table table-bordered table-hover" id="dataTables-example">
                        <thead>
                        <tr>
                            <th>No.</th>
                            <th>Nama Lengkap</th>
                            <th>Jenis PS</th>
                            <th>Kategori</th>
                            <th>Harga</th>
                            <th>Tindakan</th>
                        </tr>
                        </thead>
                        <tbody>
                        <?php
                        $query_ps = "SELECT `A`.`id_pemesanan`,`B`.`nama_lengkap`,`C`.`jenis_ps`,`D`.`nama_kategori`,`D`.`harga`,`A`.`id_pengguna` FROM `pemesanan` `A`, `pengguna` `B`, `ps` `C`,`kategori_harga` `D` WHERE `A`.`id_pengguna`=`B`.`id_pengguna` AND `A`.`id_ps`=`C`.`id_ps` AND `A`.`id_kategori`=`D`.`id_kategori` AND `A`.`status_pesanan`='sampai' ORDER BY  `A`.`id_pemesanan` DESC";
                        $result_ps = mysql_query($query_ps);// or die(mysql_error());
                        $row_ps = mysql_num_rows($result_ps);
                        if($row_ps > 0){
                            $no_ps = 1;
                            while($fetch_ps = mysql_fetch_row($result_ps)){
                                $id_pemesanan = $fetch_ps[0];
                                $nama_lengkap = $fetch_ps[1];
                                $jenis_ps = "PS " . $fetch_ps[2];
                                $kategori = $fetch_ps[3];
                                $harga = $fetch_ps[4];
                                $id_pengguna = $fetch_ps[5];
                                ?>
                                <tr>
                                    <td><?php echo $no_ps; ?></td>
                                    <td class="tooltip-demo"><a href="index.php?nav=detail_pengguna&id=<?php echo $id_pengguna;?>" data-toggle="tooltip" data-placement="top" title="Lihat Detail Pengguna" ><?php echo $nama_lengkap; ?></a></td>
                                    <td><?php echo $jenis_ps; ?></td>
                                    <td><?php echo $kategori; ?></td>
                                    <td><?php echo $harga; ?></td>
                                    <td class="tooltip-demo"><a href="index.php?nav=proses_pesanan&act=arsip&id=<?php echo $id_pemesanan; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Arsipkan"><i class="glyphicon glyphicon-floppy-save"></i></a></td>
                                </tr>
                                <?php
                                $no_ps++;
                            }
                        }else{
                            ?>
                            <tr><td colspan="6"><div class="alert alert-success">Tidak ada pengriman saat ini.</div></td></tr>
                            <?php
                        }
                        ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="page-header">
            <h1>Laporan Pemesanan</h1>
        </div>
    </div>
</div>
<!-- Arsip Pemesanan -->
<div class="row">
    <div class="col-sm-12 col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Arsip Pemesanan

                <!--<div class="pull-right">
                    <div class="btn-group tooltip-demo" style="margin-top:-5px;">
                        <a href="index.php?nav=form_pelanggan" class="btn btn-default btn-circle" data-toggle="tooltip" data-placement="left" title="Tambah data Pelanggan"><i class="fa fa-plus"></i></a>
                    </div>
                </div>-->
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <thead>
                        <tr>
                            <th>No.</th>
                            <th>Nama Lengkap</th>
                            <th>Jenis PS</th>
                            <th>Kategori</th>
                            <th>Harga</th>
                        </tr>
                        </thead>
                        <tbody>
                        <?php
                        $query_ps = "SELECT `A`.`id_pemesanan`,`B`.`nama_lengkap`,`C`.`jenis_ps`,`D`.`nama_kategori`,`D`.`harga` FROM `pemesanan` `A`, `pengguna` `B`, `ps` `C`,`kategori_harga` `D` WHERE `A`.`id_pengguna`=`B`.`id_pengguna` AND `A`.`id_ps`=`C`.`id_ps` AND `A`.`id_kategori`=`D`.`id_kategori` AND `A`.`status_pesanan`='arsip'ORDER BY  `A`.`id_pemesanan` DESC";
                        $result_ps = mysql_query($query_ps);// or die(mysql_error());
                        $row_ps = mysql_num_rows($result_ps);
                        if($row_ps > 0){
                            $no_ps = 1;
                            while($fetch_ps = mysql_fetch_row($result_ps)){
                                $id_pemesanan = $fetch_ps[0];
                                $nama_lengkap = $fetch_ps[1];
                                $jenis_ps = "PS " . $fetch_ps[2];
                                $kategori = $fetch_ps[3];
                                $harga = $fetch_ps[4];
                                ?>
                                <tr>
                                    <td><?php echo $no_ps; ?></td>
                                    <td><?php echo $nama_lengkap; ?></td>
                                    <td><?php echo $jenis_ps; ?></td>
                                    <td><?php echo $kategori; ?></td>
                                    <td><?php echo $harga; ?></td>
                                </tr>
                                <?php
                                $no_ps++;
                            }
                        }else{
                            ?>
                            <tr><td colspan="5"><div class="alert alert-danger">Maaf data pemesanan tidak tersedia.</div></td></tr>
                            <?php
                        }
                        ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Detail Kategori</h4>
            </div>
            <div class="modal-body">
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
</script>
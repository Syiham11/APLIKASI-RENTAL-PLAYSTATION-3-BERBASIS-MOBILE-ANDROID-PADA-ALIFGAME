<div class="row"><div class="col-lg-12"><div class="page-header"><h1>Pendaftar Baru</h1></div></div></div>
<div class="row">
    <div class="col-sm-10 col-md-10">
        <div class="panel panel-info">
            <div class="panel-heading">
                Data Akun User Belum Dikonfirmasi
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <thead>
                        <tr>
                            <th>No.</th>
                            <th>Nama Lengkap</th>
                            <th>Email</th>
                            <th>Konfirmasi</th>
                            <th>Tindakan</th>
                        </tr>
                        </thead>
                        <tbody>
                        <?php
                        $query_ps = "SELECT `A`.`id_pengguna`,`B`.`nama_lengkap`,`A`.`email`,`A`.`konfirmasi` FROM `user` `A`, `pengguna` `B` WHERE `A`.`id_pengguna`=`B`.`id_pengguna` AND `A`.`email`=`B`.`email` AND `A`.`level`='user' AND `A`.`konfirmasi`='0' ORDER BY  `A`.`id_pengguna` DESC";
                        $result_ps = mysql_query($query_ps) or die(mysql_error());
                        $row_ps = mysql_num_rows($result_ps);
                        if($row_ps > 0){
                            $no_ps = 1;
                            while($fetch_ps = mysql_fetch_row($result_ps)){
                                $id_pengguna = $fetch_ps[0];
                                $nama_lengkap = $fetch_ps[1];
                                $email = $fetch_ps[2];
                                $konfirmasi = $fetch_ps[3];
                                $s_konfirmasi = $konfirmasi == 1 ? "<span class=\"text-success\"><i class=\"fa fa-check\"></i> Dikonfirmasi</span>" : "<span class=\"text-warning\"><i class=\"fa fa-warning\"></i> Tidak</span>";
                                ?>
                                <tr">
                                    <td><?php echo $no_ps; ?></td>
                                    <td class="tooltip-demo"><a href="index.php?nav=detail_pengguna&id=<?php echo $id_pengguna;?>" data-toggle="tooltip" data-placement="top" title="Lihat Detail Pengguna" ><?php echo $nama_lengkap; ?></a></td>
                                    <td><?php echo $email; ?></td>
                                    <td><?php echo $s_konfirmasi; ?></td>
                                    <td class="tooltip-demo">
                                        <?php if($konfirmasi == 1){
                                            ?>
                                            <a href="index.php?nav=konfirmasi&id=<?php echo $id_pengguna; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Batalkan Konfirmasi"><i class="glyphicon glyphicon-remove"></i></a>
                                            <?php
                                        }else{
                                            ?>
                                            <a href="index.php?nav=konfirmasi&id=<?php echo $id_pengguna; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Konfirmasi"><i class="fa fa-check"></i></a>
                                            <?php
                                        }?>
                                        <a href="index.php?nav=detail_pengguna&id=<?php echo $id_pengguna; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Lihat Detail Pengguna"><i class="fa fa-users"></i></a>
                                        <a href="index.php?nav=hapus_user&id=<?php echo $id_pengguna; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Hapus Pengguna"><i class="glyphicon glyphicon-trash"></i></a>
                                    </td>
                                </tr>
                                <?php
                                $no_ps++;
                            }
                        }else{
                            ?>
                            <tr><td colspan="5"><div class="alert alert-info">Tidak ditemukan data pendaftar saat ini.</div></td></tr>
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
<div class="row"><div class="col-lg-12"><div class="page-header"><h1>Akun User Alif Game</h1></div></div></div>
<div class="row">
    <div class="col-sm-10 col-md-10">
        <div class="panel panel-default">
            <div class="panel-heading">
                Data Akun User

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
                            <th>Email</th>
                            <th>Konfirmasi</th>
                            <th>Tindakan</th>
                        </tr>
                        </thead>
                        <tbody>
                        <?php
                        $query_ps = "SELECT `A`.`id_pengguna`,`B`.`nama_lengkap`,`A`.`email`,`A`.`konfirmasi` FROM `user` `A`, `pengguna` `B` WHERE `A`.`id_pengguna`=`B`.`id_pengguna` AND `A`.`email`=`B`.`email` AND `A`.`level`='user' ORDER BY  `A`.`id_pengguna` DESC";
                        $result_ps = mysql_query($query_ps) or die(mysql_error());
                        $row_ps = mysql_num_rows($result_ps);
                        if($row_ps > 0){
                            $no_ps = 1;
                            while($fetch_ps = mysql_fetch_row($result_ps)){
                                $id_pengguna = $fetch_ps[0];
                                $nama_lengkap = $fetch_ps[1];
                                $email = $fetch_ps[2];
                                $konfirmasi = $fetch_ps[3];
                                $s_konfirmasi = $konfirmasi == 1 ? "<span class=\"text-success\"><i class=\"fa fa-check\"></i> Dikonfirmasi</span>" : "<span class=\"text-warning\"><i class=\"fa fa-warning\"></i> Tidak</span>";
                                ?>
                                <tr">
                                    <td><?php echo $no_ps; ?></td>
                                    <td class="tooltip-demo"><a href="index.php?nav=detail_pengguna&id=<?php echo $id_pengguna;?>" data-toggle="tooltip" data-placement="top" title="Lihat Detail Pengguna" ><?php echo $nama_lengkap; ?></a></td>
                                    <td><?php echo $email; ?></td>
                                    <td><?php echo $s_konfirmasi; ?></td>
                                    <td class="tooltip-demo">
                                        <?php if($konfirmasi == 1){
                                            ?>
                                            <a href="index.php?nav=konfirmasi&id=<?php echo $id_pengguna; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Batalkan Konfirmasi"><i class="glyphicon glyphicon-remove"></i></a>
                                            <?php
                                        }else{
                                            ?>
                                            <a href="index.php?nav=konfirmasi&id=<?php echo $id_pengguna; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Konfirmasi"><i class="fa fa-check"></i></a>
                                            <?php
                                        }?>
                                        <a href="index.php?nav=detail_pengguna&id=<?php echo $id_pengguna; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Lihat Detail Pengguna"><i class="fa fa-users"></i></a>
                                        <a href="index.php?nav=hapus_user&id=<?php echo $id_pengguna; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Hapus Pengguna"><i class="glyphicon glyphicon-trash"></i></a>
                                    </td>
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
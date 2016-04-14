<div class="row"><div class="col-lg-12"><div class="page-header"><h1>Pengguna</h1></div></div></div>
<div class="row">
    <div class="col-sm-10 col-md-10">
        <div class="panel panel-default">
            <div class="panel-heading">
                Daftar Pengguna

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
                            <th>JKL</th>
                            <th>Alamat</th>
                            <th>No. Hp</th>
                        </tr>
                        </thead>
                        <tbody>
                        <?php
                        $query_ps = "SELECT `id_pengguna`,`nama_lengkap`,`jenis_kelamin`,`alamat`,`no_hp` FROM `pengguna` ORDER BY `id_pengguna` DESC";
                        $result_ps = mysql_query($query_ps);
                        $row_ps = mysql_num_rows($result_ps);
                        if($row_ps > 0){
                            $no_ps = 1;
                            while($fetch_ps = mysql_fetch_row($result_ps)){
                                $id_pengguna = $fetch_ps[0];
                                $nama_lengkap = $fetch_ps[1];
                                $jkl = $fetch_ps[2];
                                $alamat = $fetch_ps[3];
                                $no_hp = $fetch_ps[4];
                                ?>
                                <tr class="odd gradeX">
                                    <td><?php echo $no_ps; ?></td>
                                    <td class="tooltip-demo"><a href="index.php?nav=detail_pengguna&id=<?php echo $id_pengguna;?>" data-toggle="tooltip" data-placement="top" title="Lihat Detail Pengguna" ><?php echo $nama_lengkap; ?></a></td>
                                    <td><?php echo $jkl; ?></td>
                                    <td><?php echo $alamat; ?></td>
                                    <td><?php echo $no_hp; ?></td>
                                </tr>
                                <?php
                                $no_ps++;
                            }
                        }else{
                            ?>
                            <tr><td colspan="5"><div class="alert alert-danger">Maaf data pengguna tidak tersedia.</div></td></tr>
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
<div class="row">
	<div class="col-lg-12">
		<div class="page-header">
			<h1>Play Station</h1>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-sm-7 col-md-7">
		<div class="panel panel-default">
			<div class="panel-heading">
				Data Play Station

				<div class="pull-right">
					<div class="btn-group tooltip-demo" style="margin-top:-5px;">
						<a href="index.php?nav=form_ps" class="btn btn-default btn-circle" data-toggle="tooltip" data-placement="left" title="Tambah data Playstation"><i class="fa fa-plus"></i></a>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<div class="dataTable_wrapper">
					<table class="table table-striped table-bordered table-hover" id="dataTables-example">
						<thead>
						<tr>
							<th>No.</th>
							<th>Jenis PS</th>
							<th>Kelengkapan PS</th>
							<th>Keterangan</th>
							<th>Tindakan</th>
						</tr>
						</thead>
						<tbody>
						<?php
						$query_ps = "SELECT `id_ps`,`jenis_ps`,`kelengkapan_ps`,`keterangan` FROM `ps` ORDER BY `id_ps` DESC";
						$result_ps = mysql_query($query_ps);
						$row_ps = mysql_num_rows($result_ps);
						if($row_ps > 0){
							$no_ps = 1;
							while($fetch_ps = mysql_fetch_row($result_ps)){
								$id_ps = $fetch_ps[0];
								$jenis_ps = "PS " .$fetch_ps[1];
								$kelengkapan_ps = $fetch_ps[2];
								$keterangan = $fetch_ps[3];
								?>
								<tr>
									<td><?php echo $no_ps; ?></td>
									<td class="tooltip-demo"><a href="index.php?nav=detail_ps&id=<?php echo $id_ps; ?>" data-toggle="tooltip" data-placement="top" title="Lihat Detail PS"><?php echo $jenis_ps; ?></a></td>
									<td><?php echo $kelengkapan_ps; ?></td>
									<td><?php echo $keterangan; ?></td>
									<td class="tooltip-demo">
										<a href="index.php?nav=detail_ps&id=<?php echo $id_ps; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Lihat Detail PS"><i class="fa fa-gamepad"></i></a>
										<a href="index.php?nav=hapus_ps&id=<?php echo $id_ps; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Hapus PS"><i class="glyphicon glyphicon-trash"></i></a>
									</td>
								</tr>
								<?php
								$no_ps++;
							}
						}else{
							?>
							<tr><td colspan="5"><div class="alert alert-danger">Maaf data playstation tidak tersedia.</div></td></tr>
							<?php
						}
						?>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-5 col-md-5">
		<div class="panel panel-default">
			<div class="panel-heading">
				Kategori Harga

				<div class="pull-right">
					<div class="btn-group tooltip-demo" style="margin-top:-5px;">
						<a href="index.php?nav=form_kategori" class="btn btn-default btn-circle" data-toggle="tooltip" data-placement="left" title="Tambah kategori harga">
							<i class="fa fa-plus"></i>
						</a>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<div class="dataTable_wrapper">
					<table class="table table-striped table-bordered table-hover" id="dataTables-example">
						<thead>
						<tr>
							<th>No.</th>
							<th>Jenis / Nama Kategori</th>
							<th>Harga</th>
							<th>Tindakan</th>
						</tr>
						</thead>
						<tbody>
						<?php
						$query = "SELECT * FROM `kategori_harga` ORDER BY `id_kategori` ASC";
						$result = mysql_query($query) or die(mysql_error());
						$row = mysql_num_rows($result);
						if($row > 0){
							$no = 1;
							while($fetch = mysql_fetch_row($result)){
								$id_kategori = $fetch[0];
								$nama_kat = $fetch[1];
								$harga_kat = $fetch[2];
								?>
								<tr>
									<td class="center"><?php echo $no; ?></td>
									<td class="tooltip-demo"><a href="index.php?nav=detail_kategori&id=<?php echo $id_kategori ?>" data-toggle="tooltip" data-placement="top" title="Lihat Detail Kategori"><?php echo $nama_kat; ?></a></td>
									<td><?php echo $harga_kat; ?></td>
									<td class="tooltip-demo">
										<a href="index.php?nav=detail_kategori&id=<?php echo $id_kategori; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Lihat Detail Kategori"><i class="glyphicon glyphicon-edit"></i></a>
										<a href="index.php?nav=hapus_kategori&id=<?php echo $id_kategori; ?>" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="left" title="Hapus Kategori"><i class="glyphicon glyphicon-trash"></i></a>
									</td>
								</tr>
								<?php
								$no++;
							}
						}else{
							?>
							<tr><td colspan="4"><div class="alert alert-danger">Tidak ditemukan data kategori harga.</div></td></tr>
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
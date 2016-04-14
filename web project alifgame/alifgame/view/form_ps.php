<div class="row">
	<div class="col-lg-12">
		<div class="page-header">
			<h1>Penginputan Data Playstation</h1>
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
						<a href="index.php?nav=ps" class="btn btn-default btn-circle" data-toggle="tooltip" data-placement="left" title="Lihat data Playstation"><i class="fa fa-list"></i></a>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<?php
					if(isset($_POST['simpan'])){
						$jenis_ps = $_POST['jenis_ps'];
						$kelengkapan_ps = mysql_real_escape_string($_POST['kelengkapan_ps']);
						$keterangan_ps = mysql_real_escape_string($_POST['keterangan_ps']);
						$unit = $_POST['unit_ps'] == "" ? 0 : $_POST['unit'];
						if(is_numeric($jenis_ps) and $jenis_ps > 0 and $jenis_ps < 5 and $kelengkapan_ps != "" and $keterangan_ps != "" and is_numeric($unit)){
							//mengambil auto increment id ps
							$query_cekId = mysql_query("SHOW TABLE STATUS LIKE 'ps'");
							$f_rows = mysql_fetch_row($query_cekId);
							$id_ps = $f_rows[10];
							
							//mengambil auto increment id gambar
							/*$query_cekIdG = mysql_query("SHOW TABLE STATUS LIKE 'gambar'");
							$f_row = mysql_fetch_row($query_cekIdG);
							$id_gambar = $f_row[10];*/
							
							//melakukan penyimpanan data ps
							$query = "INSERT INTO `ps` VALUES('".$id_ps."','".$jenis_ps."','".$kelengkapan_ps."','".$keterangan_ps."','".$unit."','')";
							$execute = mysql_query($query) or die(gagal());
							?>
							<div class="alert alert-success alert-dismissable">
								<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
								Berhasil menyimpan data playstation.
							</div>
							<?php
						}else{
							?>
							<div class="alert alert-warning alert-dismissable">
								<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
								Pengisian form tidak sesuai ketentuan !
							</div>
							<?php
						}
					}
					
					function gagal(){
						?>
						<div class="alert alert-danger alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
							Terjadi kesalahan ! Data tidak dapat disimpan.
						</div>
						<?php
					}
				?>
				<form role="form" method="post" action="index.php?nav=form_ps">
					<div class="col-lg-6">
						<h2>Data Playstation</h2>
						<div class="form-group">
                            <label>Jenis Playstation</label>
                            <select class="form-control" name="jenis_ps">
                                <option value="1">PS 1</option>
                                <option value="2">PS 2</option>
                                <option value="3">PS 3</option>
                                <option value="4">PS 4</option>
                            </select>
                        </div>
						<div class="form-group">
							<label>Kelengkapan Playstation</label>
                            <textarea name="kelengkapan_ps" placeholder="Kelengkapan" class="form-control" rows="3"></textarea>
						</div>
						<div class="form-group">
							<label>Keterangan</label>
                            <textarea name="keterangan_ps" placeholder="Keterangan" class="form-control" rows="3"></textarea>
						</div>
						<!--<div class="form-group">
							<label>Jumlah Unit</label>
                            <input type="text" name="unit_ps" placeholder="Unit" class="form-control">
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
							<button type="submit" name="simpan" class="btn btn-primary">Simpan Data</button>
							<button type="reset" class="btn btn-default">Reset Form</button>
						<!--</div>-->
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="page-header">
			<h1>Penginputan Kategori Harga Rental</h1>
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
						<a href="index.php?nav=ps" class="btn btn-default btn-circle" data-toggle="tooltip" data-placement="left" title="Lihat data Playstation"><i class="fa fa-list"></i></a>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<?php
					if(isset($_POST['simpan'])){
						$nama_kat = mysql_real_escape_string($_POST['nama_kategori']);
						$harga = $_POST['harga'];
						if($nama_kat != "" and is_numeric($harga)){
							$query = "INSERT INTO `kategori_harga` VALUES('','".$nama_kat."','".$harga."')";
							$execute = mysql_query($query) or die(gagal());
							?>
							<div class="alert alert-success alert-dismissable">
								<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
								Berhasil menyimpan kategori
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
				<form role="form" method="post" action="index.php?nav=form_kategori">
					<div class="col-lg-6">
						<h2>Data Kategori</h2>
						<div class="form-group">
                            <label>Jenis / Nama Kategori</label>
                            <input type="text" name="nama_kategori" class="form-control" placeholder="Nama kategori">
                        </div>
						<div class="form-group">
							<label>Harga Rental</label>
                            <input type="text" name="harga" class="form-control" placeholder="Harga">
						</div>
					</div>
					<div class="col-lg-12">
						<button type="submit" name="simpan" class="btn btn-primary">Simpan Data</button>
						<button type="reset" class="btn btn-default">Reset Form</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
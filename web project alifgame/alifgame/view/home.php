<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Dashboard</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <script>
        function cek_akun_baru(){
            $.ajax({
                url:"cek_akun_baru.php",
                success: function(data){
                    $("#akun_baru").html(data);
                }
            });
        }
        function cek_pesanan_baru(){
            $.ajax({
                url:"cek_pesanan_baru.php",
                success: function(data){
                    $("#pesanan_baru").html(data);
                }
            });
        }
        function cek_proses_kirim(){
            $.ajax({
                url:"cek_proses_pesanan.php",
                success: function(data){
                    $("#proses_kirim").html(data);
                }
            });
        }
        function cek(){
            cek_akun_baru();
            cek_pesanan_baru()
            cek_proses_kirim()
            var timeOut = setTimeout("cek()",10000);
        }
        window.onload = cek();
    </script>
    <div class="col-lg-3 col-md-6">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-3">
                        <i class="fa fa-users fa-5x"></i>
                    </div>
                    <div class="col-xs-9 text-right">
                        <div class="huge" id="akun_baru"></div>
                        <div>User Baru!</div>
                    </div>
                </div>
            </div>
            <a href="index.php?nav=user">
                <div class="panel-footer">
                    <span class="pull-left">Lihat Daftar User</span>
                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                    <div class="clearfix"></div>
                </div>
            </a>
        </div>
    </div>
    <div class="col-lg-3 col-md-6">
        <div class="panel panel-yellow">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-3">
                        <i class="fa fa-shopping-cart fa-5x"></i>
                    </div>
                    <div class="col-xs-9 text-right">
                        <div class="huge" id="pesanan_baru"></div>
                        <div>Pemesanan Baru!</div>
                    </div>
                </div>
            </div>
            <a href="index.php?nav=pemesanan">
                <div class="panel-footer">
                    <span class="pull-left">Lihat Daftar Pemesanan</span>
                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                    <div class="clearfix"></div>
                </div>
            </a>
        </div>
    </div>
    <div class="col-lg-3 col-md-6">
        <div class="panel panel-red">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-3">
                        <i class="fa fa-fighter-jet fa-5x"></i>
                    </div>
                    <div class="col-xs-9 text-right">
                        <div class="huge" id="proses_kirim"></div>
                        <div>Pemesanan Diproses!</div>
                    </div>
                </div>
            </div>
            <a href="index.php?nav=pemesanan">
                <div class="panel-footer">
                    <span class="pull-left">Lihat Monitoring Pemesanan</span>
                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                    <div class="clearfix"></div>
                </div>
            </a>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-12 col-md-12">
        <div class="page-header">
            <h1>Hi, Admin</h1>
        </div>
        <div class="alert alert-info">
            Pilih pada panel untuk mengelola data Alif Game
        </div>
    </div>
</div>
<!-- /.row -->
<!--
<div class="row">
	<div class="col-lg-8">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-bar-chart-o fa-fw"></i> Area Chart Example
			</div>-->
			<!-- /.panel-heading -->
			<!--<div class="panel-body">
				<div id="morris-area-chart"></div>
			</div>-->
			<!-- /.panel-body -->
		<!--</div>
</div>-->
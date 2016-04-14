<div class="row"><div class="col-lg-12"><div class="page-header"><h1>Pengaturan Admin</h1></div></div></div>
<div class="row">
    <div class="col-sm-12 col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">Administrator Setting</div>
            <div class="panel-body">
                <?php
                if(isset($_POST['update'])){
                    $username = isset($_POST['username']) ? mysql_real_escape_string($_POST['username']) : "";
                    $password = isset($_POST['password']) ? mysql_real_escape_string($_POST['password']) : "";
                    if($username != "" and $password != ""){
                        $password = md5($password);
                        $query = "UPDATE `user` SET `email`='".$username."',`password`='".$password."' WHERE `level`='admin'";
                        $execute = mysql_query($query) or die(gagal());
                        ?>
                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            Akun Administrator Berhasil Diubah
                        </div>
                        <?php
                    }else{
                        ?>
                        <div class="alert alert-warning alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            Akun Administrator Gagal Diubah !
                        </div>
                        <?php
                    }
                }

                function gagal(){
                    ?>
                    <div class="alert alert-danger alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        Terjadi kesalahan ! Data tidak dapat diubah.
                    </div>
                    <?php
                }
                ?>
                <form role="form" method="post" action="index.php?nav=setting">
                    <div class="col-lg-6">
                        <h2>Administrator Data</h2>
                        <div class="form-group">
                            <label>Username</label>
                            <input type="text" name="username" class="form-control" placeholder="Username">
                        </div>
                        <div class="form-group">
                            <label>Ubah Password</label>
                            <input type="password" name="password" class="form-control" placeholder="Password">
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <button type="submit" name="update" class="btn btn-primary">Ubah Data</button>
                        <button type="reset" class="btn btn-default">Reset Form</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
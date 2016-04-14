-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 06 Sep 2015 pada 20.56
-- Versi Server: 5.6.21
-- PHP Version: 5.5.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `alif_game`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `gambar_ps`
--

CREATE TABLE IF NOT EXISTS `gambar_ps` (
`id_gambar` int(10) unsigned NOT NULL,
  `id_ps` int(10) unsigned NOT NULL,
  `gambar1` tinytext,
  `gambar2` tinytext,
  `gambar3` tinytext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori_harga`
--

CREATE TABLE IF NOT EXISTS `kategori_harga` (
`id_kategori` int(11) unsigned NOT NULL,
  `nama_kategori` varchar(200) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kategori_harga`
--

INSERT INTO `kategori_harga` (`id_kategori`, `nama_kategori`, `harga`) VALUES
(1, '24 Jam', 70000),
(2, '20 Jam', 50000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pemesanan`
--

CREATE TABLE IF NOT EXISTS `pemesanan` (
`id_pemesanan` int(10) unsigned NOT NULL,
  `kode_pemesanan` varchar(35) NOT NULL,
  `id_pengguna` int(11) unsigned NOT NULL,
  `id_ps` int(10) unsigned NOT NULL,
  `id_kategori` int(10) unsigned NOT NULL,
  `tanggal` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status_pesanan` enum('lihat','terima','tolak','proses','sampai','arsip') NOT NULL DEFAULT 'lihat'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pemesanan`
--

INSERT INTO `pemesanan` (`id_pemesanan`, `kode_pemesanan`, `id_pengguna`, `id_ps`, `id_kategori`, `tanggal`, `status_pesanan`) VALUES
(1, '990dcbc', 2, 1, 1, '2015-09-07 01:53:13', 'arsip'),
(2, 'ec21f9e', 3, 1, 1, '2015-09-07 01:54:36', 'sampai'),
(3, '07c7475', 2, 1, 2, '2015-09-07 01:55:03', 'proses'),
(4, '0bc9740', 3, 1, 2, '2015-09-07 01:55:07', 'lihat');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengguna`
--

CREATE TABLE IF NOT EXISTS `pengguna` (
`id_pengguna` int(11) unsigned NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `alamat` tinytext NOT NULL,
  `tanggal_lahir` date NOT NULL DEFAULT '0000-00-00',
  `email` varchar(100) NOT NULL,
  `no_hp` varchar(35) NOT NULL,
  `foto` tinytext
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `nama_lengkap`, `jenis_kelamin`, `alamat`, `tanggal_lahir`, `email`, `no_hp`, `foto`) VALUES
(2, 'Rasyid', 'L', 'Jl. Perintis Kemerdekaan VII, Pondok Istiqomah, Tamalanrea.', '1992-06-02', 'rasyid@gmail.com', '+6285396679817', NULL),
(3, 'Sofyan', 'L', 'Jl. Perintis Kemerdekaan VII', '1993-03-04', 'sofyan@gmail.com', '+6285485458748', NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `ps`
--

CREATE TABLE IF NOT EXISTS `ps` (
`id_ps` int(11) unsigned NOT NULL,
  `jenis_ps` enum('1','2','3','4') NOT NULL DEFAULT '1',
  `kelengkapan_ps` tinytext NOT NULL,
  `keterangan` tinytext NOT NULL,
  `unit` int(11) unsigned DEFAULT '0',
  `id_gambar` int(10) unsigned DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `ps`
--

INSERT INTO `ps` (`id_ps`, `jenis_ps`, `kelengkapan_ps`, `keterangan`, `unit`, `id_gambar`) VALUES
(1, '3', 'Stick 2 unit, TV 24 inc, Dll', 'Terdapa banyak permainan pada playstationnya, mulai dari olah raga, action, dan advanture.', 0, 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) unsigned NOT NULL,
  `id_pengguna` int(11) unsigned NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `level` enum('admin','user') NOT NULL DEFAULT 'user',
  `konfirmasi` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `id_pengguna`, `email`, `password`, `level`, `konfirmasi`) VALUES
(1, 0, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', 1),
(3, 2, 'kedai564@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', 'user', 1),
(4, 3, 'sofyan@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', 'user', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `gambar_ps`
--
ALTER TABLE `gambar_ps`
 ADD PRIMARY KEY (`id_gambar`);

--
-- Indexes for table `kategori_harga`
--
ALTER TABLE `kategori_harga`
 ADD PRIMARY KEY (`id_kategori`);

--
-- Indexes for table `pemesanan`
--
ALTER TABLE `pemesanan`
 ADD PRIMARY KEY (`id_pemesanan`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
 ADD PRIMARY KEY (`id_pengguna`);

--
-- Indexes for table `ps`
--
ALTER TABLE `ps`
 ADD PRIMARY KEY (`id_ps`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `gambar_ps`
--
ALTER TABLE `gambar_ps`
MODIFY `id_gambar` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `kategori_harga`
--
ALTER TABLE `kategori_harga`
MODIFY `id_kategori` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `pemesanan`
--
ALTER TABLE `pemesanan`
MODIFY `id_pemesanan` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
MODIFY `id_pengguna` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `ps`
--
ALTER TABLE `ps`
MODIFY `id_ps` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

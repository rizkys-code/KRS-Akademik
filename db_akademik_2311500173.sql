-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 04, 2026 at 07:02 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_akademik_2311500173`
--

-- --------------------------------------------------------

--
-- Table structure for table `detil_krs`
--

CREATE TABLE `detil_krs` (
  `TA` varchar(9) NOT NULL,
  `Semester` varchar(8) NOT NULL,
  `NIM` char(10) NOT NULL,
  `KodeMTK` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detil_krs`
--

INSERT INTO `detil_krs` (`TA`, `Semester`, `NIM`, `KodeMTK`) VALUES
('2023/2024', 'Ganjil', '2023010001', 'KP001'),
('2023/2024', 'Ganjil', '2023010001', 'KP002');

-- --------------------------------------------------------

--
-- Table structure for table `krs`
--

CREATE TABLE `krs` (
  `TA` varchar(9) NOT NULL,
  `Semester` varchar(8) NOT NULL,
  `NIM` char(10) NOT NULL,
  `TglKRS` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `krs`
--

INSERT INTO `krs` (`TA`, `Semester`, `NIM`, `TglKRS`) VALUES
('2023/2024', 'Ganjil', '2023010001', '2023-09-01'),
('2023/2024', 'Ganjil', '2023010002', '2023-09-02');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `NIM` char(10) NOT NULL,
  `Nama` varchar(50) DEFAULT NULL,
  `Alamat` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`NIM`, `Nama`, `Alamat`) VALUES
('2023010001', 'Budi Santoso', 'Jl. Merdeka No. 10, Jakarta'),
('2023010002', 'Altaf', 'Jl. Petukangan No. 10, Jakarta'),
('2311500173', 'Rizky Saputra', 'Asal Dirumah'),
('2311500190', 'Muhammad Fauzan Akbar', 'Jl Cisauk raya'),
('2311500999', 'Aji Kalcer', 'Jl. Poncab');

-- --------------------------------------------------------

--
-- Table structure for table `matakuliah`
--

CREATE TABLE `matakuliah` (
  `KodeMTK` char(5) NOT NULL,
  `NamaMTK` varchar(50) DEFAULT NULL,
  `SKS` int(4) DEFAULT NULL,
  `KodePrasyarat` char(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `matakuliah`
--

INSERT INTO `matakuliah` (`KodeMTK`, `NamaMTK`, `SKS`, `KodePrasyarat`) VALUES
('KP001', 'Analisis Sentimen', 3, 'KP071'),
('KP002', 'Analisis Sentimen Lanjutan', 3, 'KP071'),
('KP900', 'IPBO Lanjutan', 3, 'KP899');

-- --------------------------------------------------------

--
-- Table structure for table `periode`
--

CREATE TABLE `periode` (
  `TA` varchar(9) NOT NULL,
  `Semester` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `periode`
--

INSERT INTO `periode` (`TA`, `Semester`) VALUES
('2023/2024', 'Ganjil'),
('2023/2024', 'Genap'),
('2025/2026', 'Gasal'),
('2029/2040', 'Gasal');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detil_krs`
--
ALTER TABLE `detil_krs`
  ADD PRIMARY KEY (`TA`,`Semester`,`NIM`,`KodeMTK`);

--
-- Indexes for table `krs`
--
ALTER TABLE `krs`
  ADD PRIMARY KEY (`TA`,`Semester`,`NIM`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`NIM`);

--
-- Indexes for table `matakuliah`
--
ALTER TABLE `matakuliah`
  ADD PRIMARY KEY (`KodeMTK`);

--
-- Indexes for table `periode`
--
ALTER TABLE `periode`
  ADD PRIMARY KEY (`TA`,`Semester`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

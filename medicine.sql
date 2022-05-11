-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 08, 2022 at 07:56 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `medicine`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `id` int(11) NOT NULL,
  `idShop` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `detailBill`
--

CREATE TABLE `detailBill` (
  `id` int(11) NOT NULL,
  `idBill` int(11) DEFAULT NULL,
  `idDrug` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `detailedWarehouse`
--

CREATE TABLE `detailedWarehouse` (
  `id` int(11) NOT NULL,
  `idDrug` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `DateAdded` int(11) DEFAULT NULL,
  `DateEnd` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `idWarehouse` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `detailPlan`
--

CREATE TABLE `detailPlan` (
  `id` int(11) NOT NULL,
  `idPlan` int(11) DEFAULT NULL,
  `idDrug` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `drug`
--

CREATE TABLE `drug` (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `price` double DEFAULT NULL,
  `term` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `plan`
--

CREATE TABLE `plan` (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `idCuaHang` int(11) DEFAULT NULL,
  `content` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `shop`
--

CREATE TABLE `shop` (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `idChuCH` int(11) DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `sex` tinyint(1) DEFAULT 0,
  `birthday` date DEFAULT NULL,
  `sdt` varchar(15) CHARACTER SET utf8 DEFAULT NULL,
  `isAdmin` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `warehouse`
--

CREATE TABLE `warehouse` (
  `id` int(11) NOT NULL,
  `idShop` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `bill_Users_id_fk` (`idUser`),
  ADD KEY `bill_shop_id_fk` (`idShop`);

--
-- Indexes for table `detailBill`
--
ALTER TABLE `detailBill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `detailBill_bill_id_fk` (`idBill`),
  ADD KEY `detailBill_drug_id_fk` (`idDrug`);

--
-- Indexes for table `detailedWarehouse`
--
ALTER TABLE `detailedWarehouse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `detailedWarehouse_drug_id_fk` (`idDrug`),
  ADD KEY `detailedWarehouse_warehouse_id_fk_2` (`idWarehouse`);

--
-- Indexes for table `detailPlan`
--
ALTER TABLE `detailPlan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `detailPlan_drug_id_fk` (`idDrug`),
  ADD KEY `detailPlan_plan_id_fk` (`idPlan`);

--
-- Indexes for table `drug`
--
ALTER TABLE `drug`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `plan`
--
ALTER TABLE `plan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `plan_shop_id_fk` (`idCuaHang`);

--
-- Indexes for table `shop`
--
ALTER TABLE `shop`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_Users_id_fk` (`idChuCH`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `warehouse`
--
ALTER TABLE `warehouse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `warehouse_shop_id_fk` (`idShop`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `detailBill`
--
ALTER TABLE `detailBill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `detailedWarehouse`
--
ALTER TABLE `detailedWarehouse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `detailPlan`
--
ALTER TABLE `detailPlan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `drug`
--
ALTER TABLE `drug`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `plan`
--
ALTER TABLE `plan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop`
--
ALTER TABLE `shop`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `warehouse`
--
ALTER TABLE `warehouse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_Users_id_fk` FOREIGN KEY (`idUser`) REFERENCES `Users` (`id`),
  ADD CONSTRAINT `bill_shop_id_fk` FOREIGN KEY (`idShop`) REFERENCES `shop` (`id`);

--
-- Constraints for table `detailBill`
--
ALTER TABLE `detailBill`
  ADD CONSTRAINT `detailBill_bill_id_fk` FOREIGN KEY (`idBill`) REFERENCES `bill` (`id`),
  ADD CONSTRAINT `detailBill_drug_id_fk` FOREIGN KEY (`idDrug`) REFERENCES `drug` (`id`);

--
-- Constraints for table `detailedWarehouse`
--
ALTER TABLE `detailedWarehouse`
  ADD CONSTRAINT `detailedWarehouse_drug_id_fk` FOREIGN KEY (`idDrug`) REFERENCES `drug` (`id`),
  ADD CONSTRAINT `detailedWarehouse_warehouse_id_fk_2` FOREIGN KEY (`idWarehouse`) REFERENCES `warehouse` (`id`);

--
-- Constraints for table `detailPlan`
--
ALTER TABLE `detailPlan`
  ADD CONSTRAINT `detailPlan_drug_id_fk` FOREIGN KEY (`idDrug`) REFERENCES `drug` (`id`),
  ADD CONSTRAINT `detailPlan_plan_id_fk` FOREIGN KEY (`idPlan`) REFERENCES `plan` (`id`);

--
-- Constraints for table `plan`
--
ALTER TABLE `plan`
  ADD CONSTRAINT `plan_shop_id_fk` FOREIGN KEY (`idCuaHang`) REFERENCES `shop` (`id`);

--
-- Constraints for table `shop`
--
ALTER TABLE `shop`
  ADD CONSTRAINT `shop_Users_id_fk` FOREIGN KEY (`idChuCH`) REFERENCES `Users` (`id`);

--
-- Constraints for table `warehouse`
--
ALTER TABLE `warehouse`
  ADD CONSTRAINT `warehouse_shop_id_fk` FOREIGN KEY (`idShop`) REFERENCES `shop` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2021 at 03:15 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `delivery`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `id` int(11) NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `pickupaddress` varchar(2000) DEFAULT NULL,
  `deliveryaddress` varchar(2000) DEFAULT NULL,
  `mass` float DEFAULT NULL,
  `receivername` varchar(100) DEFAULT NULL,
  `receiverphone` varchar(10) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `collectstate` bit(1) DEFAULT NULL,
  `collectmoney` float DEFAULT NULL,
  `postage` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `typeofservice` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `note` varchar(2000) DEFAULT NULL,
  `idpayment` int(11) DEFAULT NULL,
  `idshipment` int(11) DEFAULT NULL,
  `typeoforder` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE `contact` (
  `id` int(11) NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `title` varchar(2000) DEFAULT NULL,
  `content` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `idorder` int(11) DEFAULT NULL,
  `danhgiadichvu` float DEFAULT NULL,
  `ghichu` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `title` varchar(1000) DEFAULT NULL,
  `content` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `type` varchar(500) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `idwallet` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `policy`
--

CREATE TABLE `policy` (
  `id` int(11) NOT NULL,
  `name` varchar(2000) DEFAULT NULL,
  `description` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shipment`
--

CREATE TABLE `shipment` (
  `id` int(11) NOT NULL,
  `iddriver` int(11) DEFAULT NULL,
  `totalmass` float DEFAULT NULL,
  `totalorders` int(11) DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `gender` varchar(3) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `address` varchar(2000) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `idnumber` varchar(12) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `activationcode` varchar(6) DEFAULT NULL,
  `resetpasswordcode` varchar(6) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `driverlicensenumber` varchar(12) DEFAULT NULL,
  `typeofuser` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `gender`, `phone`, `address`, `email`, `idnumber`, `username`, `password`, `activationcode`, `resetpasswordcode`, `state`, `driverlicensenumber`, `typeofuser`) VALUES
(1, 'Đinh Tấn Tú', 'Nam', '123123123', '', NULL, NULL, 'tantudinh812', 'f5bbc8de146c67b44babbf4e6584cc0', NULL, '192093', NULL, NULL, 'ADMIN'),
(46, NULL, NULL, '123123123', NULL, NULL, NULL, 'vidiep123', 'bb2d91d0fbbebe8719509ed0f865c63f', '866328', '972030', 'consudung', NULL, 'CUSTOMER'),
(51, NULL, NULL, '327644657', NULL, NULL, NULL, 'dinhtantu812', 'bb2d91d0fbbebe8719509ed0f865c63f', '192093', '192093', 'consudung', NULL, 'CUSTOMER');

-- --------------------------------------------------------

--
-- Table structure for table `wallet`
--

CREATE TABLE `wallet` (
  `id` int(11) NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `type` varchar(500) DEFAULT NULL,
  `isactive` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idpayment` (`idpayment`),
  ADD KEY `iduser` (`iduser`),
  ADD KEY `idshipment` (`idshipment`);

--
-- Indexes for table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`),
  ADD KEY `iduser` (`iduser`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idorder` (`idorder`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idwallet` (`idwallet`);

--
-- Indexes for table `policy`
--
ALTER TABLE `policy`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `shipment`
--
ALTER TABLE `shipment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `iddriver` (`iddriver`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `wallet`
--
ALTER TABLE `wallet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `iduser` (`iduser`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contact`
--
ALTER TABLE `contact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `policy`
--
ALTER TABLE `policy`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shipment`
--
ALTER TABLE `shipment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `wallet`
--
ALTER TABLE `wallet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`idpayment`) REFERENCES `payment` (`id`),
  ADD CONSTRAINT `bill_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `bill_ibfk_3` FOREIGN KEY (`idshipment`) REFERENCES `shipment` (`id`);

--
-- Constraints for table `contact`
--
ALTER TABLE `contact`
  ADD CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`);

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`idorder`) REFERENCES `bill` (`id`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`idwallet`) REFERENCES `wallet` (`id`);

--
-- Constraints for table `shipment`
--
ALTER TABLE `shipment`
  ADD CONSTRAINT `shipment_ibfk_1` FOREIGN KEY (`iddriver`) REFERENCES `user` (`id`);

--
-- Constraints for table `wallet`
--
ALTER TABLE `wallet`
  ADD CONSTRAINT `wallet_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

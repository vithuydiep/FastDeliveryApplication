-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 06, 2021 lúc 09:54 PM
-- Phiên bản máy phục vụ: 10.4.19-MariaDB
-- Phiên bản PHP: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `delivery`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill`
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
  `postage` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `idpayment` int(11) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `iddriver` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `bill`
--

INSERT INTO `bill` (`id`, `iduser`, `pickupaddress`, `deliveryaddress`, `mass`, `receivername`, `receiverphone`, `description`, `postage`, `total`, `state`, `idpayment`, `startTime`, `endTime`, `iddriver`) VALUES
(1, 129, 'TPHCM', 'DongThap', 1.2, 'Vi Diep', '898018964', 'Hang mac tien', 150000, 25, 'DANGXULY', NULL, '2021-06-23 22:35:51', '0000-00-00 00:00:00', 145),
(2, 140, 'TPHCM', 'HaNoi', 2, 'Tu Dinh', '123132133', 'None', 550000, 10, 'DANGXULY', NULL, '2021-06-23 22:35:55', '0000-00-00 00:00:00', 145),
(3, 136, 'HaNoi', 'HaiPhong', 3.4, 'Khang Vo', '936639763', 'Hang de vo', 150000, 5, 'DANGXULY', NULL, '2021-06-23 22:35:58', NULL, 145),
(4, 136, 'TPHCM', 'VungTau', 1.5, 'Thao Pham', '123456789', 'None', 50000, 5, 'DANGXULY', NULL, '2021-06-09 22:36:00', '0000-00-00 00:00:00', 145),
(5, 56, 'TPHCM', 'TPHCM', 2, 'Toan Pham', '123456789', 'None', 50000, 7, 'DANGXULY', NULL, '2021-06-06 18:17:30', '2021-06-06 18:17:30', 145),
(6, 136, 'HaNoi', 'HaiPhong', 7, 'Tien Hoang', '456789123', 'None', 50000, 8, 'DANGXULY', NULL, '2021-06-06 18:18:02', '2021-06-06 18:18:02', 145),
(7, 136, 'CanTho', 'BacNinh', 12, 'Tram Pham', '123456789', 'None', 3050, 9, 'DANGXULY', NULL, '2021-06-06 18:18:48', '2021-06-06 18:18:48', 145),
(8, 136, 'DaNang', 'KienGiang', 7, 'Doanh Hoang', '56453278', 'None', 40000, 2, 'DANGXULY', NULL, '2021-06-06 18:18:48', '2021-06-06 18:18:48', 145);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `contact`
--

CREATE TABLE `contact` (
  `id` int(11) NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `title` varchar(2000) DEFAULT NULL,
  `content` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `contact`
--

INSERT INTO `contact` (`id`, `iduser`, `title`, `content`) VALUES
(1, 134, 'Hello', 'Can you tell me about your information');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `idorder` int(11) DEFAULT NULL,
  `danhgiadichvu` float DEFAULT NULL,
  `ghichu` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `feedback`
--

INSERT INTO `feedback` (`id`, `idorder`, `danhgiadichvu`, `ghichu`) VALUES
(1, 1, 4, 'Fast delivery, i wil use it next time');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `title` varchar(1000) DEFAULT NULL,
  `content` longtext DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payment`
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
-- Cấu trúc bảng cho bảng `policy`
--

CREATE TABLE `policy` (
  `id` int(11) NOT NULL,
  `name` varchar(2000) DEFAULT NULL,
  `description` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
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
  `typeofuser` varchar(20) DEFAULT NULL,
  `isReceiveNotification` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `name`, `gender`, `phone`, `address`, `email`, `idnumber`, `username`, `password`, `activationcode`, `resetpasswordcode`, `state`, `driverlicensenumber`, `typeofuser`, `isReceiveNotification`) VALUES
(52, 'Đinh Tấn Tú', '', '327644657', NULL, NULL, NULL, 'dinhtantu', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', '1', 'CUSTOMER', b'0'),
(53, 'Võ Hồ An Khang', NULL, '896521486', NULL, NULL, NULL, 'khangvo123', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', '1', 'CUSTOMER', b'0'),
(56, NULL, NULL, NULL, NULL, NULL, NULL, 'diepthuyvi', '25f9e794323b453885f5181f1b624db', '532082', '882700', 'consudung', NULL, 'ADMIN', b'0'),
(57, 'Diệp Thúy Vi', 'Nữ', '', 'Đồng Tháp', 'diepvi2810@gmail.com', NULL, 'vivivivi', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', NULL, 'DRIVER', b'0'),
(115, 'Duong Thanh Trang', NULL, '086555656', NULL, NULL, NULL, 'Trang127', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', '1', 'DRIVER', b'0'),
(129, 'Diep Thuy Vi', NULL, NULL, NULL, NULL, NULL, 'vi116', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', NULL, 'DRIVER', b'0'),
(134, 'Diep Vi', NULL, NULL, NULL, NULL, NULL, 'vi188', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', NULL, 'CUSTOMER', b'0'),
(136, 'Boi Tuyen', NULL, '898018964', NULL, NULL, NULL, 'tuyen191', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'block', NULL, 'CUSTOMER', b'0'),
(137, 'Cao Thi Mai Tram', NULL, '902441480', NULL, NULL, NULL, 'tram185', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', NULL, 'CUSTOMER', b'0'),
(140, 'Duong Boi Tuyen', NULL, '889562225', NULL, NULL, NULL, 'tuyen101', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', NULL, 'CUSTOMER', b'0'),
(142, 'Duong Gia Han', 'Fem', '896125368', 'TPHCM', 'giahan@gmail.com', '123123123', 'han88', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'block', '123123123', 'DRIVER', b'0'),
(144, 'Duong Thanh Trang', 'Fem', '863125964', 'Can Tho', 'tranglun@gmail.com', '123456789', 'trang41', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', '123123124', 'DRIVER', b'0'),
(145, 'Duong Thanh Trang', 'fem', '863125961', 'Can Tho', 'tranglun@gmail.com', '123456789', 'trang150', '25f9e794323b453885f5181f1b624db', NULL, '1', 'consudung', '123123124', 'DRIVER', b'0'),
(147, 'Diep Thuy Vi', 'Fem', '898018962', 'Dong Thap', 'diepvi2810@gmail.com', '123123121', 'vi129', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'block', '123123124', 'DRIVER', b'0'),
(148, 'Duong Gia Han', NULL, '898018960', NULL, NULL, NULL, 'han9', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', NULL, 'CUSTOMER', b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `wallet`
--

CREATE TABLE `wallet` (
  `id` int(11) NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `type` varchar(500) DEFAULT NULL,
  `isactive` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idpayment` (`idpayment`),
  ADD KEY `iduser` (`iduser`),
  ADD KEY `iddriver` (`iddriver`);

--
-- Chỉ mục cho bảng `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`),
  ADD KEY `iduser` (`iduser`);

--
-- Chỉ mục cho bảng `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idorder` (`idorder`);

--
-- Chỉ mục cho bảng `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `nofification_ibfk_1` (`iduser`);

--
-- Chỉ mục cho bảng `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idwallet` (`idwallet`);

--
-- Chỉ mục cho bảng `policy`
--
ALTER TABLE `policy`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `wallet`
--
ALTER TABLE `wallet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `iduser` (`iduser`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `bill`
--
ALTER TABLE `bill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `contact`
--
ALTER TABLE `contact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `policy`
--
ALTER TABLE `policy`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=149;

--
-- AUTO_INCREMENT cho bảng `wallet`
--
ALTER TABLE `wallet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`idpayment`) REFERENCES `payment` (`id`),
  ADD CONSTRAINT `bill_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `bill_ibfk_3` FOREIGN KEY (`iddriver`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `contact`
--
ALTER TABLE `contact`
  ADD CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`idorder`) REFERENCES `bill` (`id`);

--
-- Các ràng buộc cho bảng `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `nofification_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`idwallet`) REFERENCES `wallet` (`id`);

--
-- Các ràng buộc cho bảng `wallet`
--
ALTER TABLE `wallet`
  ADD CONSTRAINT `wallet_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 22, 2024 at 07:56 AM
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
-- Database: `tesdacomshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `msgs`
--

CREATE TABLE `msgs` (
  `msgID` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `message` text DEFAULT NULL,
  `Date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `msgs`
--

INSERT INTO `msgs` (`msgID`, `username`, `message`, `Date`) VALUES
(3, 'Test', 'Test date', '2024-07-22 05:08:29'),
(6, 'Test', 'Test reload message', '2024-07-22 05:17:31'),
(7, 'Test2', 'Test 2 reload', '2024-07-22 05:23:02');

-- --------------------------------------------------------

--
-- Table structure for table `pcs`
--

CREATE TABLE `pcs` (
  `pcNumber` varchar(20) NOT NULL,
  `status` enum('available','in_use') DEFAULT 'available',
  `isAdmin` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pcs`
--

INSERT INTO `pcs` (`pcNumber`, `status`, `isAdmin`) VALUES
('100', 'in_use', 1),
('101', 'available', 0),
('102', 'available', 0),
('103', 'available', 0),
('104', 'available', 0),
('105', 'available', 0),
('106', 'available', 0),
('107', 'available', 0),
('108', 'available', 0),
('109', 'available', 0),
('110', 'available', 0),
('111', 'available', 0),
('112', 'available', 0),
('113', 'available', 0),
('114', 'available', 0),
('115', 'available', 0),
('116', 'available', 0),
('117', 'available', 0),
('118', 'available', 0),
('119', 'available', 0),
('120', 'available', 0),
('121', 'available', 0),
('122', 'available', 0),
('123', 'available', 0),
('124', 'available', 0),
('125', 'available', 0),
('126', 'available', 0),
('127', 'available', 0),
('128', 'available', 0),
('129', 'available', 0),
('130', 'available', 0),
('131', 'available', 0),
('132', 'available', 0),
('133', 'available', 0),
('134', 'available', 0),
('135', 'available', 0),
('136', 'available', 0),
('137', 'available', 0),
('138', 'available', 0),
('139', 'available', 0),
('140', 'available', 0),
('141', 'available', 0),
('142', 'available', 0),
('143', 'available', 0),
('144', 'available', 0),
('145', 'available', 0),
('146', 'available', 0),
('147', 'available', 0),
('148', 'available', 0),
('149', 'available', 0),
('150', 'available', 0);

-- --------------------------------------------------------

--
-- Table structure for table `sessions`
--

CREATE TABLE `sessions` (
  `sessionID` int(11) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pcNumber` varchar(20) NOT NULL,
  `startTime` datetime NOT NULL DEFAULT current_timestamp(),
  `endTime` datetime DEFAULT NULL,
  `totalDuration` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sessions`
--

INSERT INTO `sessions` (`sessionID`, `username`, `pcNumber`, `startTime`, `endTime`, `totalDuration`, `price`) VALUES
(1, 'admin', '100', '2024-07-18 18:07:26', NULL, NULL, NULL),
(24, 'Test', '101', '2024-07-20 08:22:23', '2024-07-20 08:22:25', 2, 1.00),
(27, 'Test', '105', '2024-07-20 08:42:22', '2024-07-20 08:54:16', 714, 8.00),
(28, 'Test', '101', '2024-07-20 08:54:24', '2024-07-20 08:57:52', 208, 3.00),
(29, 'Test', '106', '2024-07-20 08:58:09', '2024-07-20 09:00:27', 138, 2.00),
(30, 'Test', '106', '2024-07-20 09:00:36', '2024-07-20 09:01:23', 47, 1.00),
(31, 'Test', '102', '2024-07-20 09:00:55', '2024-07-20 09:04:12', 197, 3.00),
(32, 'Test', '105', '2024-07-20 09:01:37', '2024-07-20 09:01:48', 11, 1.00),
(33, 'Test', '104', '2024-07-20 09:04:28', '2024-07-20 09:04:35', 7, 1.00),
(34, 'Test', '102', '2024-07-20 10:35:57', '2024-07-20 10:40:33', 276, 4.00),
(36, 'Test', '104', '2024-07-20 12:39:52', '2024-07-20 13:03:03', 1391, 16.00),
(37, 'Test2', '103', '2024-07-20 12:52:14', '2024-07-20 13:03:10', 656, 8.00),
(38, 'Test', '103', '2024-07-20 16:40:21', '2024-07-20 16:58:59', 1118, 13.00),
(39, 'Test2', '105', '2024-07-20 16:54:18', '2024-07-20 16:58:48', 270, 3.00),
(40, 'Test2', '101', '2024-07-20 17:47:05', '2024-07-20 17:47:29', 24, 1.00),
(41, 'Test', '103', '2024-07-20 17:55:49', '2024-07-20 17:56:39', 50, 1.00),
(42, 'Test', '101', '2024-07-20 17:56:52', '2024-07-20 17:56:54', 2, 1.00),
(43, 'Test', '103', '2024-07-20 18:08:36', '2024-07-20 18:08:50', 14, 1.00),
(44, 'Test', '107', '2024-07-20 19:02:13', '2024-07-20 19:02:41', 28, 1.00),
(45, 'Test2', '103', '2024-07-20 19:04:38', '2024-07-20 19:04:57', 19, 1.00),
(46, 'Test', '101', '2024-07-20 19:10:19', '2024-07-20 19:10:55', 36, 1.00),
(47, 'Test', '101', '2024-07-21 07:44:34', '2024-07-21 07:44:44', 10, 1.00),
(48, 'Test', '101', '2024-07-21 08:08:22', '2024-07-21 08:08:39', 17, 1.00),
(49, 'Test', '105', '2024-07-21 08:12:11', '2024-07-21 08:12:17', 6, 1.00),
(50, 'Test', '106', '2024-07-21 08:15:34', '2024-07-21 08:15:42', 8, 1.00),
(51, 'Test', '101', '2024-07-21 08:17:34', '2024-07-21 08:17:38', 4, 1.00),
(52, 'Test', '105', '2024-07-21 08:23:15', '2024-07-21 08:23:29', 14, 1.00),
(53, 'Test', '104', '2024-07-21 08:29:01', '2024-07-21 08:29:36', 35, 1.00),
(54, 'Test', '115', '2024-07-21 09:04:35', '2024-07-21 09:07:35', 180, 2.00),
(55, 'Test', '115', '2024-07-21 09:12:02', '2024-07-21 09:20:06', 484, 6.00),
(56, 'Test', '115', '2024-07-21 09:22:04', '2024-07-21 09:23:03', 59, 1.00),
(57, 'Test', '115', '2024-07-21 09:31:54', '2024-07-21 09:32:02', 8, 1.00),
(58, 'Test', '101', '2024-07-21 09:34:40', '2024-07-21 09:34:48', 8, 1.00),
(59, 'Test', '101', '2024-07-21 09:34:49', '2024-07-21 09:34:50', 1, 1.00),
(60, 'Test', '101', '2024-07-21 09:47:55', '2024-07-21 09:48:07', 12, 1.00),
(62, 'Test', '101', '2024-07-21 09:57:53', '2024-07-21 09:58:02', 9, 1.00),
(63, 'Test', '106', '2024-07-21 10:01:29', '2024-07-21 10:01:44', 15, 1.00),
(64, 'Test', '101', '2024-07-21 10:18:06', '2024-07-21 10:18:21', 15, 1.00),
(65, 'Test', '101', '2024-07-21 10:23:53', '2024-07-21 10:24:34', 41, 1.00),
(66, 'Test', '101', '2024-07-21 10:29:30', '2024-07-21 10:30:00', 30, 1.00),
(67, 'Test', '101', '2024-07-21 10:32:52', '2024-07-21 10:33:40', 48, 1.00),
(68, 'Test', '107', '2024-07-21 10:34:25', '2024-07-21 10:34:40', 15, 1.00),
(69, 'Test2', '106', '2024-07-21 10:36:57', '2024-07-21 10:37:49', 52, 1.00),
(71, 'Test', '105', '2024-07-22 08:28:02', '2024-07-22 08:29:09', 67, 1.00),
(72, 'Test', '109', '2024-07-22 08:33:02', '2024-07-22 08:34:04', 62, 1.00),
(73, 'Test', '101', '2024-07-22 08:54:23', '2024-07-22 08:55:16', 53, 1.00),
(74, 'Test2', '108', '2024-07-22 09:33:18', '2024-07-22 09:36:15', 177, 2.00),
(75, 'Test', '102', '2024-07-22 09:33:32', '2024-07-22 09:36:33', 181, 3.00),
(76, 'Test2', '102', '2024-07-22 09:48:09', '2024-07-22 09:50:40', 151, 2.00),
(77, 'Test', '107', '2024-07-22 09:48:43', '2024-07-22 09:49:16', 33, 1.00),
(78, 'Test', '103', '2024-07-22 10:04:25', '2024-07-22 10:04:31', 6, 1.00),
(79, 'Test', '103', '2024-07-22 11:03:41', '2024-07-22 11:04:50', 69, 1.00),
(80, 'Test2', '101', '2024-07-22 13:22:37', '2024-07-22 13:23:33', 56, NULL),
(81, 'Test2', '104', '2024-07-22 13:28:07', '2024-07-22 13:28:19', 12, NULL),
(82, 'Test2', '105', '2024-07-22 13:32:01', '2024-07-22 13:32:19', 18, NULL),
(83, 'Test2', '113', '2024-07-22 13:33:17', '2024-07-22 13:33:29', 12, NULL),
(84, 'Test', '105', '2024-07-22 13:34:51', '2024-07-22 13:35:03', 12, NULL),
(85, 'Test', '101', '2024-07-22 13:36:41', '2024-07-22 13:36:49', 8, NULL),
(86, 'Test', '101', '2024-07-22 13:37:15', '2024-07-22 13:37:18', 3, NULL),
(88, 'Test', '101', '2024-07-22 13:41:05', '2024-07-22 13:41:13', 8, NULL),
(89, NULL, '101', '2024-07-22 13:44:54', NULL, NULL, NULL),
(90, 'Test', '101', '2024-07-22 13:45:10', '2024-07-22 13:45:26', 16, NULL),
(91, 'Test', '101', '2024-07-22 13:46:28', '2024-07-22 13:46:49', 21, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` enum('active','offline','banned') DEFAULT 'offline',
  `isAdmin` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `status`, `isAdmin`) VALUES
('admin', 'Admin123', 'active', 1),
('Test', 'Test', 'active', 0),
('Test2', 'Test2', 'active', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `msgs`
--
ALTER TABLE `msgs`
  ADD PRIMARY KEY (`msgID`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `pcs`
--
ALTER TABLE `pcs`
  ADD PRIMARY KEY (`pcNumber`);

--
-- Indexes for table `sessions`
--
ALTER TABLE `sessions`
  ADD PRIMARY KEY (`sessionID`),
  ADD KEY `userID` (`username`),
  ADD KEY `pcNumber` (`pcNumber`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `msgs`
--
ALTER TABLE `msgs`
  MODIFY `msgID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `sessions`
--
ALTER TABLE `sessions`
  MODIFY `sessionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=92;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `msgs`
--
ALTER TABLE `msgs`
  ADD CONSTRAINT `msgs_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

--
-- Constraints for table `sessions`
--
ALTER TABLE `sessions`
  ADD CONSTRAINT `sessions_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `sessions_ibfk_2` FOREIGN KEY (`pcNumber`) REFERENCES `pcs` (`pcNumber`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

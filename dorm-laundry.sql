-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 31, 2020 at 09:15 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dorm-laundry`
--

-- --------------------------------------------------------

--
-- Table structure for table `cycle`
--

CREATE TABLE `cycle` (
  `id` int(32) NOT NULL,
  `type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `duration` int(32) DEFAULT NULL,
  `price` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cycle`
--

INSERT INTO `cycle` (`id`, `type`, `duration`, `price`) VALUES
(1, 'Quick', 17, 1),
(2, 'Wool', 40, 2),
(3, 'Cotton Bright', 160, 3),
(4, 'Gentle Hand Wash', 50, 2),
(5, 'Whites', 180, 3),
(6, 'Heavy Bedding', 60, 3),
(7, 'Dry', 120, 2);

-- --------------------------------------------------------

--
-- Table structure for table `cycleonmachine`
--

CREATE TABLE `cycleonmachine` (
  `id` int(32) NOT NULL,
  `description` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cycleFk` int(32) NOT NULL,
  `machineFk` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cycleonmachine`
--

INSERT INTO `cycleonmachine` (`id`, `description`, `cycleFk`, `machineFk`) VALUES
(1, 'Quick on Samsung', 1, 1),
(2, 'Wool on Samsung', 2, 1),
(3, 'Cotton Bright on Samsung', 3, 1),
(4, 'Wool on Quadro', 2, 2),
(5, 'Cotton Bright on Quadro', 3, 2),
(6, 'Gentle Hand Wash on Samsung', 4, 1),
(7, 'Gentle Hand Wash on Quadro', 4, 2),
(8, 'Whites on Samsung', 5, 1),
(9, 'Whites on Quadro', 5, 2),
(10, 'Heavy Bedding on Samsung', 6, 1),
(11, 'Dry on Bosch', 7, 3);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(32) NOT NULL,
  `firstname` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lastname` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `firstname`, `lastname`, `email`, `username`, `password`, `role`) VALUES
(1, NULL, NULL, NULL, 'admin', 'admin', 'admin'),
(2, 'Julija', 'Markić', 'jmarkic@laundry.ba', 'jmarkic', '1234', 'zaposlenik'),
(3, 'Ivanka', 'Pavić', 'ipavic@laundry.ba', 'ipavic', '1234', 'zaposlenik'),
(4, 'Marko', 'Jurić', 'mjuric@dom.ba', 'mjuric', '1234', 'zaposlenik');

-- --------------------------------------------------------

--
-- Table structure for table `machine`
--

CREATE TABLE `machine` (
  `id` int(32) NOT NULL,
  `model` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `machine`
--

INSERT INTO `machine` (`id`, `model`) VALUES
(1, 'Samsung'),
(2, 'Quadro'),
(3, 'Bosch');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(32) NOT NULL,
  `appointmentDateStart` datetime(6) DEFAULT NULL,
  `appointmentDateEnd` datetime(6) DEFAULT NULL,
  `studentFk` int(32) NOT NULL,
  `employeeFk` int(32) NOT NULL,
  `cycleonmachineFk` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `appointmentDateStart`, `appointmentDateEnd`, `studentFk`, `employeeFk`, `cycleonmachineFk`) VALUES
(1, '2020-06-02 07:00:00.000000', '2020-06-02 07:17:00.000000', 1, 3, 1),
(2, '2020-05-26 07:00:00.000000', '2020-05-26 07:17:00.000000', 1, 2, 1),
(3, '2020-06-10 07:00:00.000000', '2020-06-10 07:17:00.000000', 1, 2, 1),
(4, '2020-06-03 08:00:00.000000', '2020-06-03 08:17:00.000000', 1, 3, 1),
(5, '2020-06-08 08:00:00.000000', '2020-06-08 10:40:00.000000', 5, 2, 3),
(6, '2020-06-08 10:41:00.000000', '2020-06-08 12:41:00.000000', 5, 2, 11),
(7, '2020-06-03 09:00:00.000000', '2020-06-03 09:50:00.000000', 5, 2, 7),
(8, '2020-06-11 12:00:00.000000', '2020-06-11 13:00:00.000000', 5, 2, 10),
(9, '2020-05-30 12:00:00.000000', '2020-05-30 15:00:00.000000', 10, 2, 8),
(10, '2020-06-05 11:00:00.000000', '2020-06-05 14:00:00.000000', 7, 2, 9),
(11, '2020-05-06 14:00:00.000000', '2020-05-06 16:00:00.000000', 7, 2, 11),
(12, '2020-06-03 14:00:00.000000', '2020-06-03 16:40:00.000000', 6, 2, 5),
(13, '2020-06-03 17:00:00.000000', '2020-06-03 19:00:00.000000', 6, 2, 11),
(14, '2020-06-06 15:00:00.000000', '2020-06-06 17:00:00.000000', 6, 2, 11),
(15, '2020-06-01 12:00:00.000000', '2020-06-01 12:40:00.000000', 8, 2, 4),
(16, '2020-06-01 12:15:00.000000', '2020-06-01 12:55:00.000000', 7, 2, 2),
(17, '2020-05-06 11:00:00.000000', '2020-05-06 13:00:00.000000', 4, 2, 11);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(32) NOT NULL,
  `firstname` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lastname` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `telephone` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `firstname`, `lastname`, `telephone`) VALUES
(1, 'Ivana', 'Kraljević', '777444'),
(2, 'Hrvoje', 'Gabrić', '111999'),
(3, 'Mile', 'Kovačević', '333558'),
(4, 'Nina', 'Džeko', '777432'),
(5, 'Tina', 'Maze', '111555'),
(6, 'Marina', 'Nikolić', '063333'),
(7, 'Marko', 'Miletić', '43434'),
(8, 'Mihael', 'Marijanović', '77765'),
(9, 'Martina', 'Tomić', '3456'),
(10, 'Luka', 'Kovač', '963456'),
(11, 'Boris', 'Lalić', '303030');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cycle`
--
ALTER TABLE `cycle`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cycleonmachine`
--
ALTER TABLE `cycleonmachine`
  ADD PRIMARY KEY (`id`),
  ADD KEY `constraintmachine` (`machineFk`),
  ADD KEY `constraintcyclefk` (`cycleFk`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `machine`
--
ALTER TABLE `machine`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Reservation_Student_FK_1_studentFk` (`studentFk`),
  ADD KEY `Reservation_Employee_FK_2_employeeFk` (`employeeFk`),
  ADD KEY `Reservation_CycleOnMachine_FK_3_cycleonmachineFk` (`cycleonmachineFk`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cycle`
--
ALTER TABLE `cycle`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `cycleonmachine`
--
ALTER TABLE `cycleonmachine`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `machine`
--
ALTER TABLE `machine`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cycleonmachine`
--
ALTER TABLE `cycleonmachine`
  ADD CONSTRAINT `constraintcyclefk` FOREIGN KEY (`cycleFk`) REFERENCES `cycle` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `constraintmachine` FOREIGN KEY (`machineFk`) REFERENCES `machine` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `Reservation_CycleOnMachine_FK_3_cycleonmachineFk` FOREIGN KEY (`cycleonmachineFk`) REFERENCES `cycleonmachine` (`id`),
  ADD CONSTRAINT `Reservation_Employee_FK_2_employeeFk` FOREIGN KEY (`employeeFk`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `Reservation_Student_FK_1_studentFk` FOREIGN KEY (`studentFk`) REFERENCES `student` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

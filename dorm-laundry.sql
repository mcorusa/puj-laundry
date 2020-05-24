-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 24, 2020 at 10:52 PM
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
(1, 'Gentle or Hand Wash', 50, 2),
(2, 'Quick', 17, 1),
(3, 'Whites', 180, 4),
(4, 'Cotton Bright', 160, 3),
(5, 'Bedding', 50, 5),
(6, 'Wool', 40, 2),
(7, 'Drying', 100, 5);

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
(1, 'Gentle or Hand Wash on SAMSUNG WW80M6', 1, 1),
(2, 'Quick on SAMSUNG WW80M6', 2, 1),
(3, 'Whites on SAMSUNG WW80M6', 3, 1),
(4, 'Cotton Bright on SAMSUNG WW80M6', 4, 1),
(5, 'Bedding on SAMSUNG WW80M6', 5, 1),
(6, 'Wool on SAMSUNG WW80M6', 6, 1),
(7, 'Drying on SAMSUNG WW80M6', 7, 1),
(8, 'Gentle or Hand Wash on GORENJE WS168', 1, 2),
(9, 'Quick on GORENJE WS168', 2, 2),
(10, 'Whites on GORENJE WS168', 3, 2),
(11, 'Cotton Bright on GORENJE WS168', 4, 2),
(12, 'Gentle or Hand Wash on GORENJE WA844', 1, 3),
(13, 'Quick on GORENJE WA844', 2, 3),
(14, 'Whites on GORENJE WA844', 3, 3),
(15, 'Cotton Bright on GORENJE WA844', 4, 3),
(16, 'Bedding on GORENJE WA844', 5, 3),
(17, 'Wool on GORENJE WA844', 6, 3),
(18, 'Drying on GORENJE WA844', 7, 3);

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
(1, 'Julija', 'Markić', 'jmarkic@dom.ba', 'jmarkic', '1234', 'zaposlenik'),
(2, 'Ivanka', 'Pavić', 'ipavic@dom.ba', 'ipavic', '1234', 'zaposlenik'),
(3, 'Marko', 'Jurić', 'mjuric@dom.ba', 'mjuric', '1234', 'zaposlenik'),
(4, NULL, NULL, NULL, 'admin', 'admin', 'admin');

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
(1, 'SAMSUNG WW80M6'),
(2, 'GORENJE WS168'),
(3, 'GORENJE WA844');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(32) NOT NULL,
  `appointmentDate` timestamp(6) NULL DEFAULT NULL,
  `studentFk` int(32) NOT NULL,
  `employeeFk` int(32) NOT NULL,
  `cycleonmachineFk` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `appointmentDate`, `studentFk`, `employeeFk`, `cycleonmachineFk`) VALUES
(1, '2020-05-25 11:00:00.000000', 4, 2, 1),
(6, '2020-05-06 22:00:00.000000', 3, 3, 1),
(14, '2020-05-17 22:00:00.000000', 3, 3, 7),
(15, '2020-06-03 22:00:00.000000', 6, 3, 2),
(16, '2020-05-04 22:00:00.000000', 7, 1, 16),
(17, '2020-04-28 22:00:00.000000', 5, 3, 15),
(18, '2020-05-12 22:00:00.000000', 7, 1, 3);

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
(1, 'Hrvoje', 'Gabrić', '11111'),
(2, 'Ivana', 'Kraljević', '22222'),
(3, 'Mile', 'Kovačević', '33333'),
(4, 'Monika', 'Raguž', '44444'),
(5, 'Marija', 'Čule', '888111'),
(6, 'Marina', 'Nikolić', '010101'),
(7, 'Franjo', 'Topić', '000987'),
(8, 'Tina', 'Maze', '557893');

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
  ADD KEY `CycleOnMachine_Cycle_FK_1_cycleFk` (`cycleFk`),
  ADD KEY `CycleOnMachine_Machine_FK_2_machineFk` (`machineFk`);

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
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `cycleonmachine`
--
ALTER TABLE `cycleonmachine`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `machine`
--
ALTER TABLE `machine`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cycleonmachine`
--
ALTER TABLE `cycleonmachine`
  ADD CONSTRAINT `CycleOnMachine_Cycle_FK_1_cycleFk` FOREIGN KEY (`cycleFk`) REFERENCES `cycle` (`id`),
  ADD CONSTRAINT `CycleOnMachine_Machine_FK_2_machineFk` FOREIGN KEY (`machineFk`) REFERENCES `machine` (`id`);

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

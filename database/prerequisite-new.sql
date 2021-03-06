-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 14, 2019 at 02:44 PM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prerequisite`
--

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE `faculty` (
  `facultyId` varchar(10) NOT NULL DEFAULT 'id',
  `password` varchar(20) NOT NULL DEFAULT 'pass',
  `Name` varchar(20) NOT NULL DEFAULT 'name'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`facultyId`, `password`, `Name`) VALUES
('1234', 'pass', 'Faculty');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `subject` varchar(30) NOT NULL DEFAULT 'subjectName',
  `ans` varchar(1) NOT NULL DEFAULT '0',
  `option1` varchar(100) NOT NULL DEFAULT 'option1',
  `option2` varchar(100) NOT NULL DEFAULT 'option2',
  `option3` varchar(100) NOT NULL DEFAULT 'option3',
  `option4` varchar(100) NOT NULL DEFAULT 'option4',
  `data` varchar(200) NOT NULL DEFAULT 'question'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`subject`, `ans`, `option1`, `option2`, `option3`, `option4`, `data`) VALUES
('AM', '2', '4', '3', '1', '2', '1+1'),
('AM', '4', '4', '3', '2', '8', '2+2');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `username` varchar(8) NOT NULL,
  `password` varchar(10) NOT NULL DEFAULT '12345',
  `AM` varchar(3) DEFAULT '-1',
  `DLDA` varchar(3) NOT NULL DEFAULT '-1',
  `DS` varchar(3) DEFAULT '-1',
  `DM` varchar(3) DEFAULT '-1',
  `OOP` varchar(3) DEFAULT '-1',
  `ECCF` varchar(3) NOT NULL DEFAULT '-1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`username`, `password`, `AM`, `DLDA`, `DS`, `DM`, `OOP`, `ECCF`) VALUES
('user', 'pass', '66', '-1', '0', '-1', '-1', '-1'),
('17CE2025', '12345', '0', '-1', '-1', '-1', '-1', '-1'),
('16it2023', 'rait', '54', '45', '23', '53', '35', '39'),
('17ET2345', 'rait', '24', '35', '44', '55', '22', '24'),
('14Ce4664', 'rait', '63', '34', '62', '25', '26', '64'),
('18Ce1023', 'rait', '56', '54', '67', '69', '63', '86'),
('17CE1065', 'rait', '0', '0', '-1', '-1', '-1', '-1');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

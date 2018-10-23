-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 23, 2018 at 08:28 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Prerequisite`
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
('Applied Mathematics-3', '0', 'option1', 'option2', 'option3', 'option4', 'question'),
('Applied Mathematics-3', '1', '2', '0', '3', '4', '1+1=?'),
('Applied Mathematics-3', '3', '1', '2', '5', '4', '2+3=?'),
('Applied Mathematics-3', '4', '1', '2', '3', '4', '4*1=?'),
('Applied Mathematics-3', '3', '1', '2', '3', '4', '27/9=?'),
('Applied Mathematics-3', '1', '1', '2', '3', '4', '5/5=?'),
('Data Structures', '1', 'Graph', 'Array', 'linked List', 'Stack', 'Which is not a linear data structure?'),
('Discrete Mathematics', '1', 'Number of edges', 'Number of branches', 'Number of nodes', 'Number of atoms', 'Which is a condition for isomorphism?'),
('Object Oriented Programming', '2', 'All of these', 'Class', 'Datatype', 'None of these', 'Is String a class or datatype in java?'),
('DLDA', '3', 'None', 'A.B', 'A.(A+A\')', '0', 'A.1=?'),
('ECCF', '2', 'Yes.', 'None of these.', 'It\'s affect can be reduced with the help of many modulation techniques.', 'All of these.', 'Can noise be completely removed from a signal?'),
('Applied Mathematics-3', '2', '9/14', '54/5', '29/6', '1', '9/5+9=?'),
('Applied Mathematics-3', '4', '1', '24', '15', '16', '8+4*2=?');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `username` varchar(8) NOT NULL,
  `password` varchar(10) NOT NULL,
  `Applied Mathematics-3` varchar(3) NOT NULL DEFAULT '-1',
  `DLDA` varchar(3) NOT NULL DEFAULT '-1',
  `Data Structures` varchar(3) NOT NULL DEFAULT '-1',
  `Discrete Mathematics` varchar(3) NOT NULL DEFAULT '-1',
  `Object Oriented Programming` varchar(3) NOT NULL DEFAULT '-1',
  `ECCF` varchar(3) NOT NULL DEFAULT '-1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`username`, `password`, `Applied Mathematics-3`, `DLDA`, `Data Structures`, `Discrete Mathematics`, `Object Oriented Programming`, `ECCF`) VALUES
('user', 'pass', '66', '-1', '0', '-1', '-1', '-1'),
('17CE2025', '', '-1', '-1', '-1', '-1', '-1', '-1'),
('16it2023', 'rait', '54', '45', '23', '53', '35', '39'),
('17ET2345', 'rait', '24', '35', '44', '55', '22', '24'),
('14Ce4664', 'rait', '63', '34', '62', '25', '26', '64'),
('18Ce1023', 'rait', '56', '54', '67', '69', '63', '86'),
('17CE1065', 'rait', '0', '0', '-1', '-1', '-1', '-1');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

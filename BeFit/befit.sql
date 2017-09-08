-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 04 Sie 2017, 15:55
-- Wersja serwera: 10.1.21-MariaDB
-- Wersja PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `befit`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `cwiczenia_uzytkownikow`
--

CREATE TABLE `cwiczenia_uzytkownikow` (
  `id_cwiczenia` int(11) NOT NULL,
  `login` text COLLATE utf8_polish_ci NOT NULL,
  `exercise` text COLLATE utf8_polish_ci NOT NULL,
  `date` text COLLATE utf8_polish_ci NOT NULL,
  `photo` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `cwiczenia_uzytkownikow`
--

INSERT INTO `cwiczenia_uzytkownikow` (`id_cwiczenia`, `login`, `exercise`, `date`, `photo`) VALUES
(35, 'Test123', 'Chest', '2017-08-01', 'https://www.muscleandperformance.com/.image/t_share/MTQ1MzY2OTYxNjY4NTY0NzUz/bench-press.jpg'),
(36, 'Test123', 'Back', '2017-08-01', 'https://www.muscleandperformance.com/.image/t_share/MTQ1MzY2OTYxNjcxODQxMzM1/5-keys-for-a-superior-back.jpg'),
(37, 'Test123', 'Legs', '2017-08-01', 'https://m0.joe.co.uk/wp-content/uploads/2015/06/23130033/joel_corry_06-01-15_198-7.jpg'),
(38, 'Test123', 'Shoulders', '2017-08-01', 'https://www.muscleandperformance.com/.image/t_share/MTQ1MzY2OTYwODY5MDI1NTUz/image-placeholder-title.jpg'),
(39, 'Test123', 'Biceps', '2017-08-01', 'https://www.budujmase.pl/wp-content/uploads/Fotolia_129645924_Subscription_Monthly_M.jpg'),
(40, 'Test123', 'Triceps', '2017-08-01', 'https://www.muscleandperformance.com/.image/t_share/MTQ1MzY2OTYxNDAyNDg4NTkz/close-grip-bench-press.jpg'),
(41, 'Test123', 'Stomach', '2017-08-01', 'http://images02.military.com/media/military-fitness/achieving-washboard-abs-image.jpg'),
(42, 'Test123', 'Cardio', '2017-08-01', 'https://aaptiv.com/blog/wp-content/uploads/2017/05/guy-on-elliptical-losing-weight.jpg');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `produkty`
--

CREATE TABLE `produkty` (
  `id_produktu` int(11) NOT NULL,
  `name` text COLLATE utf8_polish_ci NOT NULL,
  `calories` float NOT NULL,
  `fat` float NOT NULL,
  `carbs` float NOT NULL,
  `protein` float NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `produkty`
--

INSERT INTO `produkty` (`id_produktu`, `name`, `calories`, `fat`, `carbs`, `protein`, `quantity`) VALUES
(1, 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100),
(2, 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100),
(3, 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100),
(4, 'Ziemniaki', 59.7, 0.1, 13.2, 1.5, 100),
(5, 'Ryz bialy', 339.5, 0.7, 76.6, 6.7, 100),
(6, 'Jablko', 46, 0.4, 10.2, 0.4, 100),
(7, 'Gruszka', 53.8, 0.2, 12.4, 0.6, 100),
(8, 'Kiwi', 55.7, 0.5, 11.9, 0.9, 100),
(9, 'Mleko 2%', 50.7, 1.9, 4.9, 3.5, 100);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `produkty_uzytkownikow`
--

CREATE TABLE `produkty_uzytkownikow` (
  `id_produktu` int(11) NOT NULL,
  `login` text COLLATE utf8_polish_ci NOT NULL,
  `name` text COLLATE utf8_polish_ci NOT NULL,
  `calories` float NOT NULL,
  `fat` float NOT NULL,
  `carbs` float NOT NULL,
  `protein` float NOT NULL,
  `quantity` int(11) NOT NULL,
  `type` text COLLATE utf8_polish_ci NOT NULL,
  `date` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `produkty_uzytkownikow`
--

INSERT INTO `produkty_uzytkownikow` (`id_produktu`, `login`, `name`, `calories`, `fat`, `carbs`, `protein`, `quantity`, `type`, `date`) VALUES
(3, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Supper', '2017-07-20'),
(4, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Dinner', '2017-07-20'),
(5, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Dinner', '2017-07-20'),
(6, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Dinner', '2017-07-20'),
(7, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Breakfast', '2017-07-21'),
(8, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Breakfast', '2017-07-21'),
(9, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Breakfast', '2017-07-21'),
(10, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Dinner', '2017-07-21'),
(11, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Dinner', '2017-07-21'),
(12, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Dinner', '2017-07-21'),
(13, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Breakfast', '2017-07-23'),
(14, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Breakfast', '2017-07-23'),
(15, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Lunch', '2017-07-23'),
(16, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Dinner', '2017-07-23'),
(17, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Dinner', '2017-07-23'),
(18, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Supper', '2017-07-24'),
(19, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Supper', '2017-07-24'),
(20, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Dinner', '2017-07-24'),
(21, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Lunch', '2017-07-24'),
(22, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Breakfast', '2017-07-25'),
(23, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Breakfast', '2017-07-25'),
(24, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Breakfast', '2017-07-25'),
(25, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Lunch', '2017-07-25'),
(26, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Lunch', '2017-07-25'),
(28, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Dinner', '2017-07-25'),
(29, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Dinner', '2017-07-25'),
(30, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Dinner', '2017-07-25'),
(31, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Supper', '2017-07-25'),
(32, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Supper', '2017-07-25'),
(33, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Supper', '2017-07-25'),
(34, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Dinner', '2017-07-26'),
(35, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Dinner', '2017-07-26'),
(36, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Dinner', '2017-07-26'),
(37, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Supper', '2017-07-26'),
(38, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Supper', '2017-07-26'),
(39, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Supper', '2017-07-26'),
(40, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Breakfast', '2017-07-27'),
(41, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Lunch', '2017-07-27'),
(42, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Lunch', '2017-07-27'),
(43, 'Test123', 'Mieso z piersi kurczaka', 117.612, 1.452, 0, 26.136, 100, 'Dinner', '2017-07-27'),
(44, 'Test123', 'Olej rzepakowy uniwersalny', 990, 110, 0, 0, 100, 'Breakfast', '2017-07-27'),
(45, 'Test123', 'Bulka pszenna', 182.622, 0.99, 38.082, 5.346, 100, 'Breakfast', '2017-07-27'),
(46, 'Test123', 'Olej rzepakowy uniwersalny', 1134, 126, 0, 0, 100, 'Breakfast', '2017-07-27'),
(47, 'Test123', 'Mieso z piersi kurczaka', 118.584, 1.464, 0, 26.352, 100, 'Breakfast', '2017-07-27'),
(48, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Supper', '2017-07-31'),
(49, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Dinner', '2017-07-31'),
(50, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Lunch', '2017-07-31'),
(51, 'Test123', 'Mieso z piersi kurczaka', 116.64, 1.44, 0, 25.92, 100, 'Breakfast', '2017-07-31'),
(52, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Breakfast', '2017-07-31'),
(53, 'Test123', 'Mieso z piersi kurczaka', 301.32, 3.72, 0, 66.96, 100, 'Breakfast', '2017-07-31'),
(60, 'Test123', 'Mleko 2%', 50.7, 1.9, 4.9, 3.5, 100, 'Breakfast', '2017-08-02'),
(61, 'Test123', 'Kiwi', 55.7, 0.5, 11.9, 0.9, 100, 'Breakfast', '2017-08-02'),
(62, 'Test123', 'Gruszka', 53.8, 0.2, 12.4, 0.6, 100, 'Breakfast', '2017-08-02'),
(63, 'Test123', 'Jablko', 46, 0.4, 10.2, 0.4, 100, 'Breakfast', '2017-08-02'),
(65, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Breakfast', '2017-08-02'),
(66, 'Test123', 'Jablko', 97.2, 1.2, 0, 21.6, 100, 'Breakfast', '2017-08-02'),
(67, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Lunch', '2017-08-02'),
(77, 'Test123', 'Mieso z piersi kurczaka', 135.108, 1.668, 0, 30.024, 139, 'Dinner', '2017-08-02'),
(78, 'Test123', 'Olej rzepakowy uniwersalny', 567, 63, 0, 0, 63, 'Dinner', '2017-08-02'),
(79, 'Test123', 'Jablko', 52.9, 0.46, 11.73, 0.46, 115, 'Supper', '2017-08-02'),
(80, 'Test123', 'Gruszka', 74.244, 0.276, 17.112, 0.828, 138, 'Supper', '2017-08-02'),
(81, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Breakfast', '2017-08-03'),
(82, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Breakfast', '2017-08-03'),
(83, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Breakfast', '2017-08-03'),
(84, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Breakfast', '2017-08-03'),
(85, 'Test123', 'Ziemniaki', 59.7, 0.1, 13.2, 1.5, 100, 'Breakfast', '2017-08-03'),
(86, 'Test123', 'Ryz bialy', 339.5, 0.7, 76.6, 6.7, 100, 'Breakfast', '2017-08-03'),
(87, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Breakfast', '2017-08-03'),
(88, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Breakfast', '2017-08-03'),
(89, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Breakfast', '2017-08-03'),
(90, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Breakfast', '2017-08-03'),
(91, 'Test123', 'Jablko', 46, 0.4, 10.2, 0.4, 100, 'Breakfast', '2017-08-03'),
(92, 'Test123', 'Ryz bialy', 339.5, 0.7, 76.6, 6.7, 100, 'Breakfast', '2017-08-03'),
(93, 'Test123', 'Ryz bialy', 339.5, 0.7, 76.6, 6.7, 100, 'Breakfast', '2017-08-03'),
(94, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Breakfast', '2017-08-03'),
(95, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Breakfast', '2017-08-03'),
(96, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Breakfast', '2017-08-03'),
(97, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Breakfast', '2017-08-03'),
(98, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Breakfast', '2017-08-03'),
(99, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Breakfast', '2017-08-03'),
(100, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Breakfast', '2017-08-03'),
(101, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Breakfast', '2017-08-03'),
(102, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Breakfast', '2017-08-03'),
(103, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Breakfast', '2017-08-03'),
(104, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Breakfast', '2017-08-03'),
(105, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Breakfast', '2017-08-03'),
(106, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Breakfast', '2017-08-03'),
(107, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Breakfast', '2017-08-03'),
(108, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Breakfast', '2017-08-03'),
(109, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Breakfast', '2017-08-03'),
(110, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Lunch', '2017-08-03'),
(111, 'Test123', 'Jablko', 46, 0.4, 10.2, 0.4, 100, 'Lunch', '2017-08-03'),
(112, 'Test123', 'Ziemniaki', 59.7, 0.1, 13.2, 1.5, 100, 'Lunch', '2017-08-03'),
(113, 'Test123', 'Gruszka', 53.8, 0.2, 12.4, 0.6, 100, 'Supper', '2017-08-03'),
(114, 'Test123', 'Jablko', 46, 0.4, 10.2, 0.4, 100, 'Supper', '2017-08-03'),
(115, 'Test123', 'Ryz bialy', 339.5, 0.7, 76.6, 6.7, 100, 'Supper', '2017-08-03'),
(116, 'Test123', 'Ziemniaki', 59.7, 0.1, 13.2, 1.5, 100, 'Supper', '2017-08-03'),
(117, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Supper', '2017-08-03'),
(118, 'Test123', 'Olej rzepakowy uniwersalny', 900, 100, 0, 0, 100, 'Supper', '2017-08-03'),
(119, 'Test123', 'Mieso z piersi kurczaka', 97.2, 1.2, 0, 21.6, 100, 'Supper', '2017-08-03'),
(122, 'Test123', 'Bulka pszenna', 276.7, 1.5, 57.7, 8.1, 100, 'Breakfast', '2017-08-04');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownicy`
--

CREATE TABLE `uzytkownicy` (
  `id` int(11) NOT NULL,
  `login` text COLLATE utf8_polish_ci NOT NULL,
  `password` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `uzytkownicy`
--

INSERT INTO `uzytkownicy` (`id`, `login`, `password`) VALUES
(1, 'Test', '1'),
(2, 'Test123', '12345');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `cwiczenia_uzytkownikow`
--
ALTER TABLE `cwiczenia_uzytkownikow`
  ADD PRIMARY KEY (`id_cwiczenia`);

--
-- Indexes for table `produkty`
--
ALTER TABLE `produkty`
  ADD PRIMARY KEY (`id_produktu`);

--
-- Indexes for table `produkty_uzytkownikow`
--
ALTER TABLE `produkty_uzytkownikow`
  ADD PRIMARY KEY (`id_produktu`);

--
-- Indexes for table `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `cwiczenia_uzytkownikow`
--
ALTER TABLE `cwiczenia_uzytkownikow`
  MODIFY `id_cwiczenia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;
--
-- AUTO_INCREMENT dla tabeli `produkty`
--
ALTER TABLE `produkty`
  MODIFY `id_produktu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT dla tabeli `produkty_uzytkownikow`
--
ALTER TABLE `produkty_uzytkownikow`
  MODIFY `id_produktu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=123;
--
-- AUTO_INCREMENT dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Czas generowania: 26 Sie 2017, 01:43
-- Wersja serwera: 5.5.55-cll
-- Wersja PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `apkawwsi_favourite_places`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `miejsca`
--

CREATE TABLE `miejsca` (
  `id` int(11) NOT NULL,
  `login` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `adres` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `city` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `x` double NOT NULL,
  `y` double NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `miejsca`
--

INSERT INTO `miejsca` (`id`, `login`, `adres`, `city`, `x`, `y`, `description`) VALUES
(40, 'Test', 'ul. Jana Pawla 2, 58-580 Jelenia Gora, Polska', 'Jelenia Gora', 50.3, 15.3, 'szkola'),
(38, 'Test', 'ul. Zachodnia 123, 58-580 Wroclaw, Polska', 'Wroclaw', 50, 15, 'lodziarnia'),
(39, 'Test', 'ul. Krzywa 57, 58-580 Swieradow, Polska', 'Swieradow', 50.2, 15.2, 'park linowy'),
(43, 'Test', 'ul. Bytla 1, 58-580 Cieplice, Polska', 'Cieplice', 50.4, 15.4, 'dom siostry'),
(67, 'Test', 'ul. Stroma 7, 58-580 Szklarska PorÄba, Polska', 'Szklarska PorÄba', 50.8238992, 15.5207434, 'rodzice'),
(46, 'Test', 'ul. Kamienna 8, 58-580 Szklarska PorÄba, Polska', 'Szklarska PorÄba', 50.8, 15.2, 'moj dom');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownicy`
--

CREATE TABLE `uzytkownicy` (
  `id_uzytkownicy` int(11) NOT NULL,
  `login` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `password` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `uzytkownicy`
--

INSERT INTO `uzytkownicy` (`id_uzytkownicy`, `login`, `password`) VALUES
(5, 'Test', '1234');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `miejsca`
--
ALTER TABLE `miejsca`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  ADD PRIMARY KEY (`id_uzytkownicy`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `miejsca`
--
ALTER TABLE `miejsca`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;
--
-- AUTO_INCREMENT dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  MODIFY `id_uzytkownicy` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Czas generowania: 14 Lip 2017, 21:14
-- Wersja serwera: 5.5.55-cll
-- Wersja PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `apkawwsi_logins`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `dane`
--

CREATE TABLE `dane` (
  `id_dane` int(11) NOT NULL,
  `login` text COLLATE utf8_polish_ci NOT NULL,
  `password` text COLLATE utf8_polish_ci NOT NULL,
  `imie` text COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` text COLLATE utf8_polish_ci NOT NULL,
  `numer_telefonu` text COLLATE utf8_polish_ci NOT NULL,
  `email` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `dane`
--

INSERT INTO `dane` (`id_dane`, `login`, `password`, `imie`, `nazwisko`, `numer_telefonu`, `email`) VALUES
(4, 'Jan', 'Kowalski', 'Janusz', 'Kowalski', '745896555', 'jkowalski@wp.pl'),
(5, 'student', 'test', 'Student', 'Student', '74589756', 'student@wp.pl'),
(6, 'Krzysiek', 'krzysiek', '', '', '', ''),
(7, 'krzysiek1', '1234', '', '', '', ''),
(8, 'krzysiek5', '1234', 'krzysiek', 'krzys1123', '745896555', 'krzys@wp.pl'),
(9, 'Krystian', 'test', '', '', '', ''),
(10, 'msteusz', 'test', 'Mateusz', 'Ros', '731020233', 'mmm@mm.pl'),
(11, 'm', 'mmmm', 'Magda', 'Ros', '668333222', 'n@n.pl'),
(12, 'alluch', 'epoka11', '', '', '', ''),
(13, 'mmm', 'kkkkk', '', '', '', ''),
(14, 'mr', '1234', 'mr', 'rost', '731020233', 'mm@mm@.pl'),
(15, 'krzysiek123', 'qwerty', '', '', '', '');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ogloszenia`
--

CREATE TABLE `ogloszenia` (
  `id_ogloszenia` int(11) NOT NULL,
  `login` text COLLATE utf8_polish_ci NOT NULL,
  `x` double NOT NULL,
  `y` double NOT NULL,
  `adres` text COLLATE utf8_polish_ci NOT NULL,
  `miasto` text COLLATE utf8_polish_ci NOT NULL,
  `ulica` text COLLATE utf8_polish_ci NOT NULL,
  `numer` text COLLATE utf8_polish_ci NOT NULL,
  `ilosc_pokoi` text COLLATE utf8_polish_ci NOT NULL,
  `metraz` text COLLATE utf8_polish_ci NOT NULL,
  `cena` text COLLATE utf8_polish_ci NOT NULL,
  `url` text COLLATE utf8_polish_ci NOT NULL,
  `data` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `ogloszenia`
--

INSERT INTO `ogloszenia` (`id_ogloszenia`, `login`, `x`, `y`, `adres`, `miasto`, `ulica`, `numer`, `ilosc_pokoi`, `metraz`, `cena`, `url`, `data`) VALUES
(54, 'Jan', 51.1275437, 17.0541829, 'mosbacha 8, wroclaw', 'wroclaw', 'mosbacha', '8', '1 pokĂłj', '47', '720', 'http://cdn21.urzadzamy.smcloud.net/t/photos/t/24545/bialy-salon_2793390.jpg', '2017-05-22'),
(55, 'Jan', 50.9160564, 15.7520502, 'jana pawla 16, jelenia gora', 'jelenia gora', 'jana pawla', '16', '1 pokĂłj', '24', '1900', 'http://cdn21.urzadzamy.smcloud.net/t/photos/t/24545/bialy-salon_2793390.jpg', '2017-05-20'),
(56, 'Jan', 52.2306095, 21.0158328, 'aleje jerozolimskie 22, warszawa', 'warszawa', 'aleje jerozolimskie', '22', '1 pokĂłj', '36', '780', 'http://cdn21.urzadzamy.smcloud.net/t/photos/t/24545/bialy-salon_2793390.jpg', '2017-05-20'),
(57, 'm', 51.066185, 17.0559311, 'konduktorska 2a, wroclaw', 'wroclaw', 'konduktorska', '2a', '2 pokoje', '30', '1200', 'http://cdn21.urzadzamy.smcloud.net/t/photos/t/24545/bialy-salon_2793390.jpg', '2017-05-25'),
(58, 'Jan', 51.1272267, 17.0545126, 'mosbacha 15, wroclaw', 'wroclaw', 'mosbacha', '15', '1 pokĂłj', '50', '200', '2017-05-27', '2017-05-27'),
(60, 'mr', 51.0665417, 17.0560353, 'konduktorska 2, WrocĹaw', 'WrocĹaw', 'konduktorska', '2', '1 pokĂłj', '25', '1000', 'http://aplikacja.apkawwsis.nstrefa.pl/php/images/594d3c654c006.png', '2017-06-23'),
(63, 'mr', 51.080064, 17.046725, 'krynicka 33, WrocĹaw', 'WrocĹaw', 'krynicka', '33', '1 pokĂłj', '45', '1000', 'http://aplikacja.apkawwsis.nstrefa.pl/php/images/594d501cc5093.png', '2017-06-23'),
(64, 'mr', 51.0707715, 17.0393549, 'pawie 1, WrocĹaw', 'WrocĹaw', 'pawie', '1', '1 pokĂłj', '111', '1111', 'http://aplikacja.apkawwsis.nstrefa.pl/php/images/594d50e67884b.png', '2017-06-23'),
(65, 'mr', 51.09437, 17.013336, 'krucza 11, WrocĹaw', 'WrocĹaw', 'krucza', '11', '1 pokĂłj', '45', '3333', 'http://aplikacja.apkawwsis.nstrefa.pl/php/images/594d588db54f6.png', '2017-06-23'),
(66, 'mr', 51.1282784, 16.9655801, 'bajana 14, WrocĹaw', 'WrocĹaw', 'bajana', '14', '1 pokĂłj', '33', '800', 'http://aplikacja.apkawwsis.nstrefa.pl/php/images/594e2133761f1.png', '2017-06-24');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `dane`
--
ALTER TABLE `dane`
  ADD PRIMARY KEY (`id_dane`);

--
-- Indexes for table `ogloszenia`
--
ALTER TABLE `ogloszenia`
  ADD PRIMARY KEY (`id_ogloszenia`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `dane`
--
ALTER TABLE `dane`
  MODIFY `id_dane` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT dla tabeli `ogloszenia`
--
ALTER TABLE `ogloszenia`
  MODIFY `id_ogloszenia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

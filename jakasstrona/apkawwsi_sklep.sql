-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Czas generowania: 29 Sie 2017, 20:17
-- Wersja serwera: 5.5.55-cll
-- Wersja PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `apkawwsi_sklep`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `nowe`
--

CREATE TABLE `nowe` (
  `id` int(11) NOT NULL,
  `login` text COLLATE utf8_polish_ci NOT NULL,
  `data` text COLLATE utf8_polish_ci NOT NULL,
  `dane` text COLLATE utf8_polish_ci NOT NULL,
  `ulica` text COLLATE utf8_polish_ci NOT NULL,
  `miasto` text COLLATE utf8_polish_ci NOT NULL,
  `telefon` text COLLATE utf8_polish_ci NOT NULL,
  `mail` text COLLATE utf8_polish_ci NOT NULL,
  `produkt_id` text COLLATE utf8_polish_ci NOT NULL,
  `produkt` text COLLATE utf8_polish_ci NOT NULL,
  `produkt1_id` text COLLATE utf8_polish_ci NOT NULL,
  `produkt1` text COLLATE utf8_polish_ci NOT NULL,
  `produkt2_id` text COLLATE utf8_polish_ci NOT NULL,
  `produkt2` text COLLATE utf8_polish_ci NOT NULL,
  `produkt3_id` text COLLATE utf8_polish_ci NOT NULL,
  `produkt3` text COLLATE utf8_polish_ci NOT NULL,
  `produkt4_id` text COLLATE utf8_polish_ci NOT NULL,
  `produkt4` text COLLATE utf8_polish_ci NOT NULL,
  `rozmiar` text COLLATE utf8_polish_ci NOT NULL,
  `rozmiar1` text COLLATE utf8_polish_ci NOT NULL,
  `rozmiar2` text COLLATE utf8_polish_ci NOT NULL,
  `rozmiar3` text COLLATE utf8_polish_ci NOT NULL,
  `rozmiar4` text COLLATE utf8_polish_ci NOT NULL,
  `razem` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `nowe`
--

INSERT INTO `nowe` (`id`, `login`, `data`, `dane`, `ulica`, `miasto`, `telefon`, `mail`, `produkt_id`, `produkt`, `produkt1_id`, `produkt1`, `produkt2_id`, `produkt2`, `produkt3_id`, `produkt3`, `produkt4_id`, `produkt4`, `rozmiar`, `rozmiar1`, `rozmiar2`, `rozmiar3`, `rozmiar4`, `razem`) VALUES
(1, 'Jan', '27 - 06 - 2017', 'Krzysztof Kurowski', 'Turystyczna 10D/11', '58-580 Szklarska PorÄba', '737490411', 'krzysztof_kurowski1990@wp.pl', '4', 'FIRMA MODEL', '28', 'FIRMA MODEL', '', '', '', '', '', '', 'S', 'S', '', '', '', '556');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `produkty`
--

CREATE TABLE `produkty` (
  `id` int(11) NOT NULL,
  `firma` text COLLATE utf8_polish_ci NOT NULL,
  `model` text COLLATE utf8_polish_ci NOT NULL,
  `cena` int(11) NOT NULL,
  `kategoria` text COLLATE utf8_polish_ci NOT NULL,
  `s` int(11) NOT NULL,
  `m` int(11) NOT NULL,
  `l` int(11) NOT NULL,
  `xl` int(11) NOT NULL,
  `40` int(11) NOT NULL,
  `41` int(11) NOT NULL,
  `42` int(11) NOT NULL,
  `uniwersalny` int(11) NOT NULL,
  `duzezdjecie` text COLLATE utf8_polish_ci NOT NULL,
  `opis` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `produkty`
--

INSERT INTO `produkty` (`id`, `firma`, `model`, `cena`, `kategoria`, `s`, `m`, `l`, `xl`, `40`, `41`, `42`, `uniwersalny`, `duzezdjecie`, `opis`) VALUES
(4, 'FIRMA', 'MODEL', 278, 'Meskie spodnie', 14, 51, 50, 54, 0, 0, 0, 0, 'm-spodnie-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(5, 'FIRMA', 'MODEL', 278, 'Meskie koszulki', 18, 51, 51, 54, 0, 0, 0, 0, 'm-koszulki-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(6, 'FIRMA', 'MODEL', 278, 'Meskie buty', 0, 0, 0, 0, 2, 7, 7, 0, 'm-buty-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(7, 'FIRMA', 'MODEL', 278, 'Damskie bluzy', 4, 0, 0, 0, 0, 0, 0, 0, 'm-bluza-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(8, 'FIRMA', 'MODEL', 278, 'Damskie spodnie', 17, 51, 51, 54, 0, 0, 0, 0, 'm-spodnie-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(9, 'FIRMA', 'MODEL', 278, 'Damskie koszulki', 18, 51, 51, 54, 0, 0, 0, 0, 'm-koszulki-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(10, 'FIRMA', 'MODEL', 278, 'Damskie buty', 0, 0, 0, 0, 6, 0, 0, 0, 'm-buty-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(11, 'FIRMA', 'MODEL', 278, 'Sportowe bluzy', 18, 51, 51, 54, 0, 0, 0, 0, 'm-bluza-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(12, 'FIRMA', 'MODEL', 278, 'Sportowe koszulki', 18, 51, 51, 54, 0, 0, 0, 0, 'm-koszulki-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(13, 'FIRMA', 'MODEL', 278, 'Sportowe spodnie', 18, 51, 51, 54, 0, 0, 0, 0, 'm-spodnie-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(28, 'FIRMA', 'MODEL', 278, 'Meskie spodnie', 14, 51, 51, 54, 0, 0, 0, 0, 'm-spodnie-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(29, 'FIRMA', 'MODEL', 278, 'Meskie spodnie', 18, 51, 51, 54, 0, 0, 0, 0, 'm-spodnie-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(30, 'FIRMA', 'MODEL', 278, 'Meskie spodnie', 18, 51, 51, 54, 0, 0, 0, 0, 'm-spodnie-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(31, 'FIRMA', 'MODEL', 278, 'Meskie spodnie', 18, 51, 51, 54, 0, 0, 0, 0, 'm-spodnie-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(35, 'FIRMA', 'MODEL', 99, 'Gadzety paski', 0, 0, 0, 0, 0, 0, 0, 36, 'g-paski-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(36, 'FIRMA', 'MODEL', 159, 'Gadzety sluchawki', 0, 0, 0, 0, 0, 0, 0, 36, 'g-sluchawki-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(37, 'FIRMA', 'MODEL', 209, 'Gadzety plecaki', 0, 0, 0, 0, 0, 0, 0, 36, 'g-plecaki-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(38, 'FIRMA', 'MODEL', 129, 'Gadzety portfele', 0, 0, 0, 0, 0, 0, 0, 36, 'g-portfele-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(40, 'FIRMA', 'MODEL', 389, 'Meskie bluzy', 2, 0, 0, 0, 0, 0, 0, 0, 'm-bluza-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(41, 'FIRMA', 'MODEL', 389, 'Meskie bluzy', 2, 0, 0, 0, 0, 0, 0, 0, 'm-bluza-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(43, 'FIRMA', 'MODEL', 389, 'Meskie bluzy', 2, 0, 0, 0, 0, 0, 0, 0, 'm-bluza-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(44, 'FIRMA', 'MODEL', 389, 'Meskie bluzy', 2, 0, 0, 0, 0, 0, 0, 0, 'm-bluza-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna'),
(47, 'FIRMA', 'MODEL', 199, 'Damskie bluzy', 0, 1, 1, 1, 0, 0, 0, 0, 'm-bluza-big.jpg', '- Duzy nadruk na przodzie bluzy <br /> - Kaptur z kontrastowa podszewka <br /> - Sciagacz u dolu oraz przy koncach rekawow <br /> - Wykonana z dbaloscia o szczegoly <br /> Material: 100% bawelna');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownicy`
--

CREATE TABLE `uzytkownicy` (
  `id` int(11) NOT NULL,
  `login` text COLLATE utf8_polish_ci NOT NULL,
  `haslo` text COLLATE utf8_polish_ci NOT NULL,
  `imie` text COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` text COLLATE utf8_polish_ci NOT NULL,
  `kod` text COLLATE utf8_polish_ci NOT NULL,
  `miasto` text COLLATE utf8_polish_ci NOT NULL,
  `ulica` text COLLATE utf8_polish_ci NOT NULL,
  `numer_domu` text COLLATE utf8_polish_ci NOT NULL,
  `numer_telefonu` text COLLATE utf8_polish_ci NOT NULL,
  `email` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `uzytkownicy`
--

INSERT INTO `uzytkownicy` (`id`, `login`, `haslo`, `imie`, `nazwisko`, `kod`, `miasto`, `ulica`, `numer_domu`, `numer_telefonu`, `email`) VALUES
(2, 'Jan', 'Kowalski', '', '', '', '', '', '', '', ''),
(3, 'admin', 'admin', '', '', '', '', '', '', '', '');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienia`
--

CREATE TABLE `zamowienia` (
  `id` int(11) NOT NULL,
  `login` text COLLATE utf8_polish_ci NOT NULL,
  `data` text COLLATE utf8_polish_ci NOT NULL,
  `dane` text COLLATE utf8_polish_ci NOT NULL,
  `ulica` text COLLATE utf8_polish_ci NOT NULL,
  `miasto` text COLLATE utf8_polish_ci NOT NULL,
  `telefon` text COLLATE utf8_polish_ci NOT NULL,
  `mail` text COLLATE utf8_polish_ci NOT NULL,
  `produkt_id` text COLLATE utf8_polish_ci NOT NULL,
  `produkt` text COLLATE utf8_polish_ci NOT NULL,
  `produkt1_id` text COLLATE utf8_polish_ci NOT NULL,
  `produkt1` text COLLATE utf8_polish_ci NOT NULL,
  `produkt2_id` text COLLATE utf8_polish_ci NOT NULL,
  `produkt2` text COLLATE utf8_polish_ci NOT NULL,
  `produkt3_id` text COLLATE utf8_polish_ci NOT NULL,
  `produkt3` text COLLATE utf8_polish_ci NOT NULL,
  `produkt4_id` text COLLATE utf8_polish_ci NOT NULL,
  `produkt4` text COLLATE utf8_polish_ci NOT NULL,
  `rozmiar` text COLLATE utf8_polish_ci NOT NULL,
  `rozmiar1` text COLLATE utf8_polish_ci NOT NULL,
  `rozmiar2` text COLLATE utf8_polish_ci NOT NULL,
  `rozmiar3` text COLLATE utf8_polish_ci NOT NULL,
  `rozmiar4` text COLLATE utf8_polish_ci NOT NULL,
  `razem` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `zamowienia`
--

INSERT INTO `zamowienia` (`id`, `login`, `data`, `dane`, `ulica`, `miasto`, `telefon`, `mail`, `produkt_id`, `produkt`, `produkt1_id`, `produkt1`, `produkt2_id`, `produkt2`, `produkt3_id`, `produkt3`, `produkt4_id`, `produkt4`, `rozmiar`, `rozmiar1`, `rozmiar2`, `rozmiar3`, `rozmiar4`, `razem`) VALUES
(1, 'Jan', '30 - 06 - 2017', 'Krzysztof Kurowski', 'Turystyczna 10D 11', '58-580 Szklarska PorÄba', '737-490-411', 'krzysztof_kurowski1990@wp.pl', '4', 'FIRMA MODEL', '47', 'FIRMA MODEL', '', '', '', '', '', '', 'L', 'S', '', '', '', '477');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `nowe`
--
ALTER TABLE `nowe`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produkty`
--
ALTER TABLE `produkty`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `zamowienia`
--
ALTER TABLE `zamowienia`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `nowe`
--
ALTER TABLE `nowe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT dla tabeli `produkty`
--
ALTER TABLE `produkty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;
--
-- AUTO_INCREMENT dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `zamowienia`
--
ALTER TABLE `zamowienia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

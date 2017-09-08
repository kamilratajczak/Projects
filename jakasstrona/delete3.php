<?php

session_start();

unset($_SESSION['id3']);
unset($_SESSION['rozmiar3']);

header('Location: koszyk.php');

?>
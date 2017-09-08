<?php

session_start();

unset($_SESSION['id2']);
unset($_SESSION['rozmiar2']);

header('Location: koszyk.php');

?>
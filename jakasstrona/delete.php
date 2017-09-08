<?php

session_start();

unset($_SESSION['id']);
unset($_SESSION['rozmiar']);

header('Location: koszyk.php');

?>
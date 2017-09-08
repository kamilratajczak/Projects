<?php

session_start();

unset($_SESSION['id4']);
unset($_SESSION['rozmiar4']);

header('Location: koszyk.php');

?>
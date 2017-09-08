<?php

session_start();

unset($_SESSION['id1']);
unset($_SESSION['rozmiar1']);

header('Location: koszyk.php');

?>
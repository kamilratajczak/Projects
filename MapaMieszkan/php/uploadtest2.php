<?php

$photo = 'userImage';
$sourcePath = $_FILES[$photo]['tmp_name'];
$targetPath = "images/".$_FILES[$photo]['name'];
$sciezka = "http://aplikacja.apkawwsis.nstrefa.pl/php/".$targetPath;
move_uploaded_file($sourcePath,$targetPath);
?>

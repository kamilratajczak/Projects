<?php
$servername = "localhost"; //replace it with your database server name
$username = "apkawwsi_test";  //replace it with your database username
$password = "test12345";  //replace it with your database password
$dbname = "apkawwsi_sklep";
// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);

// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
?>
<?php
$servername = "localhost"; //replace it with your database server name
$username = "apkawwsi_admin";  //replace it with your database username
$password = "test123";  //replace it with your database password
$dbname = "apkawwsi_logins";
// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
?>
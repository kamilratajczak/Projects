<?PHP
include_once("connection.php");
if(isset($_POST['txtUsername'])){
	$username = $_POST['txtUsername'];
	
	$query = "SELECT login FROM dane WHERE login = '$username'";
	
	$result = mysqli_query($conn, $query);
	
	if($result->num_rows > 0){
		echo "Failed";
	}
	else{
		if(isset($_POST['txtPassword'])){
	$password = $_POST['txtPassword'];
	
	$query = "INSERT INTO `dane` (`id_dane`, `login`, `password`) VALUES (NULL, '$username', '$password');";
	
	$result = mysqli_query($conn, $query);
	
	if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){
	echo "success";
	exit;
	}
	}
}
}
?>

<html>
<body>
<form action="<?PHP $_PHP_SELF ?>" method="post">
	Username <input type="text" name="txtUsername" /> <br />
	Password <input type="text" name="txtPassword" /> <br />
	<input type="submit" name="btnSubmit" value="Login" />
</form>
</body>
</html>

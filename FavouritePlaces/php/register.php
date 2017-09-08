<?PHP
include_once("connection.php");
if(isset($_POST['txtUsername'])){
	
	$username = mysqli_real_escape_string($conn, $_POST['txtUsername']);
	
	$query = "SELECT login FROM uzytkownicy WHERE login = '$username'";
	
	$result = mysqli_query($conn, $query);
	
	if($result->num_rows > 0){
		if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){
    	echo "failed";
	    exit;
		}
	}
	else{
		if(isset($_POST['txtPassword'])){

    $password = mysqli_real_escape_string($conn, $_POST['txtPassword']);
		
	$query = "INSERT INTO `uzytkownicy` VALUES (NULL, '$username', '$password');";
	
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

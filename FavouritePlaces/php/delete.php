<?PHP
include_once("connection.php");

	$login = $_POST['login'];
	$adres = $_POST['adres'];
	
		$query = "DELETE FROM `miejsca` WHERE `miejsca`.`adres` = '$adres' AND `miejsca`.`login` = '$login'";
		
		$result = mysqli_query($conn, $query);
				
	if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){ 
	echo "success";
	exit;
	}
?>

<html>
<body>
<form action="<?PHP $_PHP_SELF ?>" method="post">
	Login <input type="text" name="login" /> <br />
	Adres <input type="text" name="adres" /> <br />
	<input type="submit" name="btnSubmit" value="Login" />
</form>
</body>
</html>
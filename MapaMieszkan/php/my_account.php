<?PHP
include_once("connection.php");
if( isset($_POST['txtLogin']) && isset($_POST['txtImie']) && isset($_POST['txtNazwisko']) && isset($_POST['txtTel']) && isset($_POST['txtEmail'])){ 

	$login = $_POST['txtLogin'];
	
	$imie = $_POST['txtImie'];
	
	$nazwisko = $_POST['txtNazwisko'];
	
	$nr_tel = $_POST['txtTel'];
	
	$email = $_POST['txtEmail'];
		
	if($imie!=null){
		$query = "UPDATE `dane` SET `imie` = '$imie' WHERE `dane`.`login` = '$login';";
			$result = mysqli_query($conn, $query);

	}
	if($nazwisko!=null){
		$query = "UPDATE `dane` SET `nazwisko` = '$nazwisko' WHERE `dane`.`login` = '$login';";
			$result = mysqli_query($conn, $query);

	}
	if($nr_tel!=null){
		$query = "UPDATE `dane` SET `numer_telefonu` = '$nr_tel' WHERE `dane`.`login` = '$login';";
			$result = mysqli_query($conn, $query);

	}
	if($email!=null){
		$query = "UPDATE `dane` SET `email` = '$email' WHERE `dane`.`login` = '$login';";
			$result = mysqli_query($conn, $query);

	}
	
	if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){
	echo "success";
	exit;
	}
}
?>

<html>
<body>
<form action="<?PHP $_PHP_SELF ?>" method="post">
	Login <input type="text" name="txtLogin" /> <br />
	Imie <input type="text" name="txtImie" /> <br />
	Nazwisko <input type="text" name="txtNazwisko" /> <br />
	Nr tel <input type="text" name="txtTel" /> <br />
	E-Mail <input type="text" name="txtEmail" /> <br />
	<input type="submit" name="btnSubmit" value="Login" />
</form>
</body>
</html>
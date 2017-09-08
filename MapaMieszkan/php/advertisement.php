<?PHP
include_once("connection.php");
if( isset($_POST['txtLogin'])){ 

	$login = $_POST['txtLogin'];
	
		$query = "SELECT id_ogloszenia, x, y, adres, ilosc_pokoi, cena, url, data FROM `ogloszenia` WHERE login = '$login'";
		
			$result = mysqli_query($conn, $query);
				
if ($result->num_rows > 0) {
while($row = $result->fetch_assoc()) {	
		$data[] = $row;
		}
		if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){ 
	echo json_encode($data);
	exit;
	}
	} 
	else{
		if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){ 
	echo "failed";
	exit;
	}
	}
}
?>

<html>
<body>
<form action="<?PHP $_PHP_SELF ?>" method="post">
	Login <input type="text" name="txtLogin" /> <br />
	<input type="submit" name="btnSubmit" value="Login" />
</form>
</body>
</html>
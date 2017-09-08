<?PHP
include_once("connection.php");
if( isset($_POST['txtID'])){ 

	$id = $_POST['txtID'];
	
		$query = "SELECT adres, miasto, ulica, numer, ilosc_pokoi, metraz, cena, url, data FROM ogloszenia WHERE id_ogloszenia = '$id'";
		
			$result = mysqli_query($conn, $query);
				
if ($result->num_rows > 0) {
while($row = $result->fetch_assoc()) {	
		$data[] = $row;
		if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){ 
	echo json_encode($data);
	exit;
	}
	}
	} 
}
?>

<html>
<body>
<form action="<?PHP $_PHP_SELF ?>" method="post">
	ID <input type="text" name="txtID" /> <br />
	<input type="submit" name="btnSubmit" value="Login" />
</form>
</body>
</html>
<?PHP
include_once("connection.php");
if( isset($_POST['txtID']) && isset($_POST['txtX']) && isset($_POST['txtY']) && isset($_POST['txtAddress']) && isset($_POST['txtMiasto']) && isset($_POST['txtUlica']) && isset($_POST['txtNumer']) && isset($_POST['txtType']) && isset($_POST['txtSize']) && isset($_POST['txtPrice']) && isset($_POST['txtDate'])){ 

	$id = $_POST['txtID'];
	$x = $_POST['txtX'];
	$y = $_POST['txtY'];
	$address = $_POST['txtAddress'];
	$miasto = $_POST['txtMiasto'];
	$ulica = $_POST['txtUlica'];
	$numer = $_POST['txtNumer'];
	$type = $_POST['txtType'];
	$size = $_POST['txtSize'];
	$price = $_POST['txtPrice'];
	$photo = $_POST['txtPhoto'];
	$date = $_POST['txtDate'];

	if($x!=null){
		$query = "UPDATE `ogloszenia` SET `x` = '$x' WHERE `ogloszenia`.`id_ogloszenia` = '$id';";
			$result = mysqli_query($conn, $query);

	}
	if($y!=null){
		$query = "UPDATE `ogloszenia` SET `y` = '$y' WHERE `ogloszenia`.`id_ogloszenia` = '$id';";
			$result = mysqli_query($conn, $query);

	}
	if($address!=null){
		$query = "UPDATE `ogloszenia` SET `adres` = '$address' WHERE `ogloszenia`.`id_ogloszenia` = '$id';";
			$result = mysqli_query($conn, $query);

	}
	if($miasto!=null){
		$query = "UPDATE `ogloszenia` SET `miasto` = '$miasto' WHERE `ogloszenia`.`id_ogloszenia` = '$id';";
			$result = mysqli_query($conn, $query);
	}
	if($ulica!=null){
		$query = "UPDATE `ogloszenia` SET `ulica` = '$ulica' WHERE `ogloszenia`.`id_ogloszenia` = '$id';";
			$result = mysqli_query($conn, $query);
	}
	if($numer!=null){
		$query = "UPDATE `ogloszenia` SET `numer` = '$numer' WHERE `ogloszenia`.`id_ogloszenia` = '$id';";
			$result = mysqli_query($conn, $query);
	}
	if($type!=null){
		$query = "UPDATE `ogloszenia` SET `ilosc_pokoi` = '$type' WHERE `ogloszenia`.`id_ogloszenia` = '$id';";
			$result = mysqli_query($conn, $query);
	}
	if($size!=null){
		$query = "UPDATE `ogloszenia` SET `metraz` = $size' WHERE `ogloszenia`.`id_ogloszenia` = '$id';";
			$result = mysqli_query($conn, $query);
	}
	if($price!=null){
		$query = "UPDATE `ogloszenia` SET `cena` = '$price' WHERE `ogloszenia`.`id_ogloszenia` = '$id';";
			$result = mysqli_query($conn, $query);
	}
	if($photo!=null){
		$query = "UPDATE `ogloszenia` SET `url` = '$photo' WHERE `ogloszenia`.`id_ogloszenia` = '$id';";
			$result = mysqli_query($conn, $query);
	}
	if($date!=null){
		$query = "UPDATE `ogloszenia` SET `data` = '$date' WHERE `ogloszenia`.`id_ogloszenia` = '$id';";
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
	ID <input type="text" name="txtID" /> <br />
	X <input type="text" name="txtX" /> <br />
	Y <input type="text" name="txtY" /> <br />
	Address <input type="text" name="txtAddress" /> <br />
	Miasto <input type="text" name="txtMiasto" /> <br />
	Ulica <input type="text" name="txtUlica" /> <br />
	Numer <input type="text" name="txtNumer" /> <br />
	Type <input type="text" name="txtType" /> <br />
	Size <input type="text" name="txtSize" /> <br />
	Price <input type="text" name="txtPrice" /> <br />
	Photo <input type="text" name="txtPhoto" /> <br />
	Date <input type="text" name="txtDate" /> <br />
	<input type="submit" name="btnSubmit" value="Login" />
</form>
</body>
</html>
<?PHP
include_once("connection.php");
if( isset($_POST['txtId'])){ 

	$id = $_POST['txtId'];
	
		$query = "DELETE FROM `ogloszenia` WHERE `ogloszenia`.`id_ogloszenia` = '$id'";
		
			$result = mysqli_query($conn, $query);
				
		if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){ 
	echo "success";
	exit;
	}
}
?>

<html>
<body>
<form action="<?PHP $_PHP_SELF ?>" method="post">
	Id <input type="text" name="txtId" /> <br />
	<input type="submit" name="btnSubmit" value="Login" />
</form>
</body>
</html>
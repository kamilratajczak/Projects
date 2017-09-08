<?PHP
include_once("connection.php");

$id = $_GET['id'];

if($login !== "admin")
	header('Location: index.php');
	
	$query = "DELETE FROM `produkty` WHERE `produkty`.`id` = $id";
	
	$result = mysqli_query($conn, $query);
	
	header('Location: edycja-zmienione.php');

?>
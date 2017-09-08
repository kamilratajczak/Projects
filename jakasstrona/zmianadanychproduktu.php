<?PHP

session_start();

include_once("connection.php");

if($login !== "admin")
	header('Location: index.php');

	$id = $_SESSION['idzmiany'];
	
	$firma = $_POST['firma'];
	
	$model = $_POST['model'];
	
	$cena = $_POST['cena'];
	
	$s = $_POST['s'];
	
	$m = $_POST['m'];
	
	$l = $_POST['l'];
	
	$xl = $_POST['xl'];
	
	$r40 = $_POST['40'];
	
	$r41 = $_SESSION['41'];
	
	$r42 = $_SESSION['42'];
	
	$ru = $_SESSION['uniwersalny'];

		$query = "UPDATE `produkty` SET `firma` = '$firma', `model` = '$model', `cena` = '$cena', `s` = '$s',`m` = '$m', `l` = '$l', `xl` = '$xl', `40` = '$r40', `41` = '$r41', `42` = '$r42', `uniwersalny` = '$ru' WHERE `produkty`.`id` = '$id'";
		
		$result = mysqli_query($conn, $query);
		
		unset($_SESSION['idzmiany']);
					
		header('Location: edycja-zmienione2.php');
?>
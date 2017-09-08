<?PHP
include_once("connection.php");

$login = mysqli_real_escape_string($conn, $_POST['login']);
$haslo = mysqli_real_escape_string($conn, $_POST['haslo']);
$haslo2 = $_POST['haslo2'];

if($login !== ""){
	
	$query = "SELECT login FROM uzytkownicy WHERE login = '$login'";
	
	$result = mysqli_query($conn, $query);
	
	if($result->num_rows > 0){
		header('Location: loginzajety.php');
	}
	else{
		if(strlen($haslo)>4){
				
	if($haslo == $haslo2){
	
	$query = "INSERT INTO `uzytkownicy` VALUES (NULL, '$login', '$haslo', '', '', '', '', '', '', '', '')";
	
	$result = mysqli_query($conn, $query);
	
	$cookie_login = $login;
		
	setcookie("Login", $cookie_login, time()+(86400*30));
	
	header('Location: utworzone.php');
	}
	else
		header('Location: blednehasla.php');
			}
	else
		header('Location: dlugosc.php');
}
}
else
	header('Location: uzupelnij.php');
?>
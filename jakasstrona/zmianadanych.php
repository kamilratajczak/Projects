<?PHP

session_start();

include_once("connection.php");

if(isset($_SESSION['login'])){
if( isset($_POST['imie']) && isset($_POST['nazwisko']) && isset($_POST['kod']) && isset($_POST['miasto']) && isset($_POST['ulica']) && isset($_POST['numer']) && isset($_POST['telefon']) && isset($_POST['mail'])){ 

	$imie = mysqli_real_escape_string($conn, $_POST['imie']);
	
	$nazwisko = mysqli_real_escape_string($conn, $_POST['nazwisko']);
	
	$kod = mysqli_real_escape_string($conn, $_POST['kod']);
	
	$miasto = mysqli_real_escape_string($conn, $_POST['miasto']);
	
	$ulica = mysqli_real_escape_string($conn, $_POST['ulica']);
	
	$numer = mysqli_real_escape_string($conn, $_POST['numer']);
	
	$telefon = mysqli_real_escape_string($conn, $_POST['telefon']);
	
	$mail = mysqli_real_escape_string($conn, $_POST['mail']);
	
	$login = $_SESSION['login'];
		
		$query = "UPDATE `uzytkownicy` SET `imie` = '$imie', `nazwisko` = '$nazwisko', `nazwisko` = '$nazwisko', `kod` = '$kod',`miasto` = '$miasto', `ulica` = '$ulica', `numer_domu` = '$numer', `numer_telefonu` = '$telefon', `email` = '$mail' WHERE `uzytkownicy`.`login` = '$login'";
		
		$result = mysqli_query($conn, $query);
					
		header('Location: account-dane.php');
}
}
else
	header('Location: index.php');
?>
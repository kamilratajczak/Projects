<?PHP

session_start();

if(empty($_SESSION['login']))
	header('Location: zalogujsie.php');

include_once("connection.php");
	
	
			if(empty($_POST['imie']) || empty($_POST['nazwisko']) || empty($_POST['kod']) || empty($_POST['miasto']) || empty($_POST['ulica']) || empty($_POST['numer']) || empty($_POST['telefon']) || empty($_POST['mail'])){	
			
			header('Location: zamowienie-dane.php');
			}
			
			else{
	
	$today = date("j - m - Y");

	$login = mysqli_real_escape_string($conn, $_SESSION['login']);
	$imie = mysqli_real_escape_string($conn, $_POST['imie']);
	$nazwisko = mysqli_real_escape_string($conn, $_POST['nazwisko']);
	$kod = mysqli_real_escape_string($conn, $_POST['kod']);
	$miasto = mysqli_real_escape_string($conn, $_POST['miasto']);
	$ulica = mysqli_real_escape_string($conn, $_POST['ulica']);
	$numer = mysqli_real_escape_string($conn, $_POST['numer']);
	$telefon = mysqli_real_escape_string($conn, $_POST['telefon']);
	$mail = mysqli_real_escape_string($conn, $_POST['mail']);
	if(isset($_SESSION['id']))
	$id = $_SESSION['id'];
	else
	$id = "";
	if(isset($_SESSION['id1']))
	$id1 = $_SESSION['id1'];
	else
	$id1 = "";
	if(isset($_SESSION['id2']))
	$id2 = $_SESSION['id2'];
	else
	$id2 = "";
	if(isset($_SESSION['id3']))
	$id3 = $_SESSION['id3'];
	else
	$id3 = "";
	if(isset($_SESSION['id4']))
	$id4 = $_SESSION['id4'];
	else
	$id4 = "";
	if(isset($_SESSION['rozmiar']))
	$r = $_SESSION['rozmiar'];
	else
	$r = "";
	if(isset($_SESSION['rozmiar1']))
	$r1 = $_SESSION['rozmiar1'];
	else
	$r1 = "";
	if(isset($_SESSION['rozmiar2']))
	$r2 = $_SESSION['rozmiar2'];
	else
	$r2 = "";
	if(isset($_SESSION['rozmiar3']))
	$r3 = $_SESSION['rozmiar3'];
	else
	$r3 = "";
	if(isset($_SESSION['rozmiar4']))
	$r4 = $_SESSION['rozmiar4'];
	else
	$r4 = "";
	$razem = $_SESSION['razem'];
	if(isset($_SESSION['produkt']))
	$p = $_SESSION['produkt'];
	else
	$p = "";
	if(isset($_SESSION['produkt1']))
	$p1 = $_SESSION['produkt1'];
	else
	$p1 = "";
	if(isset($_SESSION['produkt2']))
	$p2 = $_SESSION['produkt2'];
	else
	$p2 = "";
	if(isset($_SESSION['produkt3']))
	$p3 = $_SESSION['produkt3'];
	else
	$p3 = "";
	if(isset($_SESSION['produkt4']))
	$p4 = $_SESSION['produkt4'];
	else
	$p4 = "";
	
	$dane = $imie . " " . $nazwisko;
	$ulicaK = $ulica . " " . $numer;
	$miastoK = $kod . " " . $miasto; 
	
	$query = "INSERT INTO `zamowienia` VALUES (NULL, '$login', '$today', '$dane', '$ulicaK', '$miastoK', '$telefon', '$mail', '$id', '$p', '$id1', '$p1', '$id2', '$p2', '$id3', '$p3', '$id4', '$p4', '$r', '$r1', '$r2', '$r3', '$r4', '$razem')";
		
	$result = mysqli_query($conn, $query);
	
	
	if(isset($_SESSION['rozmiar'])){
		if($_SESSION['rozmiar']){
			$query2 = "UPDATE `produkty` SET `$r` = `$r`-1 WHERE `produkty`.`id` = $id";
		$result2 = mysqli_query($conn, $query2);
	}
	}
	
	if(isset($_SESSION['rozmiar1'])){
		if($_SESSION['rozmiar1']){
			$query2 = "UPDATE `produkty` SET `$r1` = `$r1`-1 WHERE `produkty`.`id` = $id1";
		$result2 = mysqli_query($conn, $query2);
		}
	}
	
	if(isset($_SESSION['rozmiar2'])){
		if($_SESSION['rozmiar2']){
			$query2 = "UPDATE `produkty` SET `$r2` = `$r2`-1 WHERE `produkty`.`id` = $id2";
		$result2 = mysqli_query($conn, $query2);
	}
	}
	
	if(isset($_SESSION['rozmiar3'])){
		if($_SESSION['rozmiar3']){
			$query2 = "UPDATE `produkty` SET `$r3` = `$r3`-1 WHERE `produkty`.`id` = $id3";
		$result2 = mysqli_query($conn, $query2);
	}
	}
	
	if(isset($_SESSION['rozmiar4'])){
		if($_SESSION['rozmiar4']){
			$query2 = "UPDATE `produkty` SET `$r4` = `$r4`-1 WHERE `produkty`.`id` = $id4";
		$result2 = mysqli_query($conn, $query2);
	}
	}
		
	unset($_SESSION['id']);
	unset($_SESSION['id1']);
	unset($_SESSION['id2']);
	unset($_SESSION['id3']);
	unset($_SESSION['id4']);
	unset($_SESSION['rozmiar']);
	unset($_SESSION['rozmiar1']);
	unset($_SESSION['rozmiar2']);
	unset($_SESSION['rozmiar3']);
	unset($_SESSION['rozmiar4']);
	unset($_SESSION['razem']);
	unset($_SESSION['produkt']);
	unset($_SESSION['produkt1']);
	unset($_SESSION['produkt2']);
	unset($_SESSION['produkt3']);
	unset($_SESSION['produkt4']);
	
	header('Location: index-zlozone.php');
			}
?>
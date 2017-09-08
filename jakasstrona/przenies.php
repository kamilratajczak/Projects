<?PHP
include_once("connection.php");

session_start();

$login = $_SESSION['login'];

if($login !== "admin")
	header('Location: index.php');

$id = $_GET['id'];

$query = "SELECT * FROM `zamowienia` where id = '$id'";
		$result = mysqli_query($conn, $query);
		
		if ($result->num_rows > 0) {
		for($i=0; $i<$result->num_rows; $i++){
		while($row = $result->fetch_assoc()) {
		
		$login1 = $row['login'];
		$data = $row['data'];
		$dane = $row['dane'];
		$ulica = $row['ulica'];
		$miasto = $row['miasto'];
		$telefon = $row['telefon'];
		$mail = $row['mail'];
		$id0 = $row['produkt_id'];
		$id1 = $row['produkt1_id'];
		$id2 = $row['produkt2_id'];
		$id3 = $row['produkt3_id'];
		$id4 = $row['produkt4_id'];
		$p = $row['produkt'];
		$p1 = $row['produkt1'];
		$p2 = $row['produkt2'];
		$p3 = $row['produkt3'];
		$p4 = $row['produkt4'];
		$r = $row['rozmiar'];
		$r1 = $row['rozmiar1'];
		$r2 = $row['rozmiar2'];
		$r3 = $row['rozmiar3'];
		$r4 = $row['rozmiar4'];
		$razem = $row['razem'];
				
		$query1 = "INSERT INTO `nowe` VALUES (NULL, '$login1', '$data', '$dane', '$ulica', '$miasto',
		'$telefon', '$mail', '$id0', '$p', '$id1', '$p1', '$id2', '$p2', '$id3', '$p3', '$id4', '$p4', '$r', '$r1', '$r2', '$r3', '$r4', '$razem')";
						   
		$result1 = mysqli_query($conn, $query1);
		
		$query2 = "DELETE FROM `zamowienia` WHERE `id` = '$id'";
		
		$result2 = mysqli_query($conn, $query2);
		
		header('Location: przeniesione.php');
		
		}
		}
		}
?>
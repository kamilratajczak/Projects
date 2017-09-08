<?PHP
include_once("connection.php");
if( isset($_POST['txtX']) && isset($_POST['txtY']) && isset($_POST['txtZasieg']) && isset($_POST['txtIloscPokoi']) && isset($_POST['txtMetraz']) && isset($_POST['txtCena']) ) {
	$x = $_POST['txtX'];
	$y = $_POST['txtY'];
	$zasieg = $_POST['txtZasieg'];
	$ilosc_pokoi = $_POST['txtIloscPokoi'];
	$metraz = $_POST['txtMetraz'];
	$cena = $_POST['txtCena'];
	$oblX = 0;
	$oblY = 0;
	$cena1 = 0;
	$cena2 = 0;
	$metraz1 = 0;
	$metraz2 = 0;
	
if($zasieg == "do 5km"){
	$oblX = 0.045;
	$oblY = 0.07;
}
elseif($zasieg == "do 10km"){
	$oblX = 0.09;
	$oblY = 0.14;
}
elseif($zasieg == "do 20km"){
	$oblX = 0.18;
	$oblY = 0.28;
}

if($metraz == "poniżej 20m"){
	$metraz1 = 20;
	$metraz2 = 0;
}
elseif($metraz == "20-30m"){
	$metraz1 = 30;
	$metraz2 = 20;
}
elseif($metraz == "30-50m"){
	$metraz1 = 50;
	$metraz2 = 30;
}
elseif($metraz == "50-80m"){
	$metraz1 = 80;
	$metraz2 = 50;
}
elseif($metraz == "80m i więcej"){
	$metraz1 = 999999;
	$metraz2 = 80;
}


if($cena == "poniżej 500zł"){
	$cena1 = 500;
	$cena2 = 0;
}
elseif($cena == "500-800zł"){
	$cena1 = 800;
	$cena2 = 500;
}
elseif($cena == "800-1200zł"){
	$cena1 = 1200;
	$cena2 = 800;
}
elseif($cena == "1200-1600zł"){
	$cena1 = 1600;
	$cena2 = 1200;
}
elseif($cena == "1600-2000zł"){
	$cena1 = 2000;
	$cena2 = 1600;
}
elseif($cena == "2000zł i więcej"){
	$cena1 = 999999;
	$cena2 = 2000;
}


 $query = "SELECT id_ogloszenia, login, x,y FROM `ogloszenia` WHERE x > $x-$oblX AND x < $x+$oblX AND y > $y-$oblY AND y < $y+$oblY AND ilosc_pokoi = '$ilosc_pokoi' AND metraz <= $metraz1 AND metraz >= $metraz2 AND cena <= $cena1 AND cena >= $cena2"; 
        
        $result = mysqli_query($conn, $query);
        
      if($result->num_rows > 0){
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
	X <input type="text" name="txtX" /> <br />
	Y <input type="text" name="txtY" /> <br />
	Zasieg <input type="text" name="txtZasieg" /> <br />
	Ilosc pokoi <input type="text" name="txtIloscPokoi" /> <br />
	Metraz <input type="text" name="txtMetraz" /> <br />
	Cena <input type="text" name="txtCena" /> <br />
	<input type="submit" name="btnSubmit" value="Login" />
</form>
</body>
</html>
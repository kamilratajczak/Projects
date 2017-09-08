<?php

session_start();

if(empty($_SESSION['login'])){
	header('Location: zalogujsie.php');
}

if(empty($_SESSION['id']) && empty($_SESSION['id1']) && empty($_SESSION['id2']) && empty($_SESSION['id3']) && empty($_SESSION['id4'])){
	header('Location:index.php');
}
?>

<!DOCTYPE HTML>
<html lang = "pl">
<head> 

<meta charset = "utf-8" />
<title>Najlepszy w sieci sklep z ciuchami!</title>
<meta http-equi = "X-UA-Compatible" content = "IE = edge, chrome = 1" />
<link rel = "stylesheet" href = "style.css" type = "text/css" />
<link href="https://fonts.googleapis.com/css?family=Lato&amp;subset=latin-ext" rel="stylesheet"> 
<link rel = "stylesheet" href = "css/fontello.css" type = "text/css" />

</head>

<body>
	
<div id = "container">
	<div id = "header">	
		<a href = "najlepszy-sklep-z-ciuchami"class = "linki"><div id = "logo">
			<div id = "img"><img src = "logo.png"> </img> </div>
			<div id = "text"><span style = "color: #3095d3">jakis</span>sklep.com</div>
			<div style = "clear: both"> </div>
		</div>
		</a>
		
		<div id = "user">
		<?php
		if($_SESSION['login'] == "admin"){
			
			echo "<a href = 'admin.php' class = 'linki'><div id = 'admin'><i class = 'icon-laptop'></i><br />Panel admina</div></a>";
			echo "<div style = 'clear: both'></div>";
		}
		else{
			echo "<a href = 'account.php' class = 'linki'><div id = 'account'><i class = 'icon-user'></i><br />Moje konto</div></a>";
			echo "<a href = 'koszyk.php' class = 'linki'><div id = 'basket'><i class = 'icon-basket'></i><br />Koszyk</div></a>";
			echo "<div style = 'clear: both'></div>";
		}
		?>
		
		</div>
	
		<div style = "clear: both"></div>
	</div>
	
	<div id = "menu">
		<ol>
			<li><a href = "najlepszy-sklep-z-ciuchami">Strona główna</a></li>
			<li><a href = "#">Męskie</a>
				<ul>
					<li><a href = "ciuchy-meskie-spodnie">Spodnie</a></li>
					<li><a href = "ciuchy-meskie-bluzy">Bluzy</a></li>
					<li><a href = "ciuchy-meskie-koszulki">Koszulki</a></li>
					<li><a href = "ciuchy-meskie-buty">Buty</a></li>
				</ul>
			</li>
			<li><a href = "#">Damskie</a>
				<ul>
					<li><a href = "ciuchy-damskie-spodnie">Spodnie</a></li>
					<li><a href = "ciuchy-damskie-bluzy">Bluzy</a></li>
					<li><a href = "ciuchy-damskie-koszulki">Koszulki</a></li>
					<li><a href = "ciuchy-damskie-buty">Buty</a></li>
				</ul>
			</li>
			<li><a href = "#">Sportowe</a>
				<ul>
					<li><a href = "ciuchy-sportowe-spodnie">Spodnie</a></li>
					<li><a href = "ciuchy-sportowe-bluzy">Bluzy</a></li>
					<li><a href = "ciuchy-sportowe-koszulki">Koszulki</a></li>
				</ul>
			</li>
			<li><a href = "#">Gadżety</a>
				<ul>
					<li><a href = "gadzety-paski">Paski</a></li>
					<li><a href = "gadzety-portfele">Portfele</a></li>
					<li><a href = "gadzety-plecaki">Plecaki</a></li>
					<li><a href = "gadzety-sluchawki">Słuchawki</a></li>
				</ul>
			</li>
			<li><a href = "#">O nas</a></li>
		</ol>
		<div style = "clear: both"> </div>
	</div>
	<div id = "contents1">
	
	<?PHP
			include_once("connection.php");
			
			$login = $_SESSION['login'];

		$query9 = "SELECT * FROM `uzytkownicy` WHERE `login` = '$login'";
		
			$result9 = mysqli_query($conn, $query9);
				
if ($result9->num_rows > 0) {

	for($i=0; $i<$result9->num_rows; $i++){
		while($row9 = $result9->fetch_assoc()) {
			

			
		echo "<div id = 'zamowienie'>";
		echo "<div id = 'prodwys'>";
		echo "<div id = 'produktywysylka'>";
		echo "<h2 class = 'center'>Wybrane produkty</h2>";
		
		if(isset($_SESSION['id'])){
$id = $_SESSION['id'];
$query = "SELECT * FROM `produkty` WHERE `id` = '$id'";
$result = mysqli_query($conn, $query);
$row = $result->fetch_assoc();
}
if(isset($_SESSION['id1'])){
$id1 = $_SESSION['id1'];
$query1 = "SELECT * FROM `produkty` WHERE `id` = '$id1'";
$result1 = mysqli_query($conn, $query1);
$row1 = $result1->fetch_assoc();
}
if(isset($_SESSION['id2'])){
$id2 = $_SESSION['id2'];
$query2 = "SELECT * FROM `produkty` WHERE `id` = '$id2'";
$result2 = mysqli_query($conn, $query2);
$row2 = $result2->fetch_assoc();
}
if(isset($_SESSION['id3'])){
$id3 = $_SESSION['id3'];
$query3 = "SELECT * FROM `produkty` WHERE `id` = '$id3'";
$result3 = mysqli_query($conn, $query3);
$row3 = $result3->fetch_assoc();
}
if(isset($_SESSION['id4'])){
$id4 = $_SESSION['id4'];
$query4 = "SELECT * FROM `produkty` WHERE `id` = '$id4'";
$result4 = mysqli_query($conn, $query4);
$row4 = $result4->fetch_assoc();
}

if(isset($_SESSION['id']) || isset($_SESSION['id1']) || isset($_SESSION['id2'])|| isset($_SESSION['id3']) || isset($_SESSION['id4'])){
	echo "<div id = 'tablica1'>";
echo "<table>";
echo "<tr>";
echo "<th class = 'thprodukt1'>Produkt</th>";
echo "<th>Rozmiar</th>";
echo "<th>Cena</th>";
echo "</tr>";
if(isset($_SESSION['id'])){
echo "<tr>";
echo "<th><a href = 'produkt-zalogowany.php?id=$id'class = 'linki1'>" . $row['firma'] . " " . $row['model'] . "</a></th>";
echo "<th>" . $_SESSION['rozmiar'] . "</th>";
echo "<th>" . $row['cena'] . " zł</th>";
echo "</tr>";
}

if(isset($_SESSION['id1'])){
echo "<tr>";
echo "<th><a href = 'produkt-zalogowany.php?id=$id1'class = 'linki1'>" . $row1['firma'] . " " . $row1['model'] . "</a></th>";
echo "<th>" . $_SESSION['rozmiar1'] . "</th>";
echo "<th>" . $row1['cena'] . " zł</th>";
echo "</tr>";
}
if(isset($_SESSION['id2'])){
echo "<tr>";
echo "<th><a href = 'produkt-zalogowany.php?id=$id2'class = 'linki1'>" . $row2['firma'] . " " . $row2['model'] . "</a></th>";
echo "<th>" . $_SESSION['rozmiar2'] . "</th>";
echo "<th>" . $row2['cena'] . " zł</th>";
echo "</tr>";
}
if(isset($_SESSION['id3'])){
echo "<tr>";
echo "<th><a href = 'produkt-zalogowany.php?id=$id3'class = 'linki1'>" . $row3['firma'] . " " . $row3['model'] . "</a></th>";
echo "<th>" . $_SESSION['rozmiar3'] . "</th>";
echo "<th>" . $row3['cena'] . " zł</th>";
echo "</tr>";
}
if(isset($_SESSION['id4'])){
echo "<tr>";
echo "<th><a href = 'produkt-zalogowany.php?id=$id4'class = 'linki1'>" . $row4['firma'] . " " . $row4['model'] . "</a></th>";
echo "<th>" . $_SESSION['rozmiar4'] . "</th>";
echo "<th>" . $row4['cena'] . " zł</th>";
echo "</tr>";
}

echo "</table>";

echo "</div>";

if(isset($row['cena']))
	$cena = $row['cena'];
	else
	$cena = 0;

if(isset($row1['cena']))
	$cena1 = $row1['cena'];
	else
	$cena1 = 0;

if(isset($row2['cena']))
	$cena2 = $row2['cena'];
	else
	$cena2 = 0;

if(isset($row3['cena']))
	$cena3 = $row3['cena'];
	else
	$cena3 = 0;

if(isset($row4['cena']))
	$cena4 = $row4['cena'];
	else
	$cena4 = 0;


	$razem = $cena + $cena1 + $cena2 + $cena3 + $cena4;
	
	$_SESSION['razem'] = $razem;


echo "<h2 class = 'left'>Razem: " . $razem . " zł</h2>";
}

		echo "</div>";
		echo "</div>";
		
		echo "<div id = 'danewys'>";
		echo "<div id = 'danewysylki'>";
		echo "<h2 class = 'center1'>Dane do wysyłki</h2>";
		echo "<div id = 'lewa1'>";
			echo "<div class = 'txt1'>Imię: <br /></div>";
			echo "<div class = 'txt1'>Nazwisko: <br /></div>";
			echo "<div class = 'txt1'>Kod pocztowy: <br /></div>";
			echo "<div class = 'txt1'>Miasto: <br /></div>";
			echo "<div class = 'txt1'>Ulica: <br /></div>";
			echo "<div class = 'txt1'>Numer domu: <br /></div>";
			echo "<div class = 'txt1'>Numer telefonu: <br /></div>";
			echo "<div class = 'txt1'>Email: <br /></div>";
		echo "</div>";
		
		echo "<div id = 'prawa'>";
		echo "<form action='zlozone.php' method='post'>";
		
		if($row9['imie'] !== NULL)
        echo "<input class = 'input2'  type='text' class='input' name = 'imie' value =" . $row9['imie'] . "><br />";
		else
		echo "<input class = 'input2'  type='text' class='input' name = 'imie'><br />";
	
		if($row9['nazwisko'] !== NULL)
		echo "<input class = 'input2'  type='text' class='input' name='nazwisko'value =" . $row9['nazwisko'] . "><br />";
		else
		echo "<input class = 'input2'  type='text' class='input' name = 'nazwisko'><br />";
	
		if($row9['kod'] !== NULL)
		echo "<input class = 'input2'  type='text' class='input' name='kod'value =" . $row9['kod'] . "><br />";
		else
		echo "<input class = 'input2'  type='text' class='input' name = 'kod'><br />";
	
		if($row9['miasto'] !== NULL)
		echo "<input class = 'input2'  type='text' class='input' name='miasto'value =" . $row9['miasto'] . "><br />";
		else
		echo "<input class = 'input2'  type='text' class='input' name = 'miasto'><br />";
	
		if($row9['ulica'] !== NULL)
		echo "<input class = 'input2'  type='text' class='input' name='ulica'value =" . $row9['ulica'] . "><br />";
		else
		echo "<input class = 'input2'  type='text' class='input' name = 'ulica'><br />";
	
		if($row9['numer_domu'] !== NULL)
		echo "<input class = 'input2'  type='text' class='input' name='numer'value =" . $row9['numer_domu'] . "><br />";
		else
		echo "<input class = 'input2'  type='text' class='input' name = 'numer'><br />";
	
		if($row9['numer_telefonu'] !== NULL)
		echo "<input class = 'input2'  type='text' class='input' name='telefon'value =" . $row9['numer_telefonu'] . "><br />";
		else
		echo "<input class = 'input2'  type='text' class='input' name = 'telefon'><br />";
	
		if($row9['email'] !== NULL)
		echo "<input class = 'input2'  type='text' class='input' name='mail'value =" . $row9['email'] . "><br />";
		else
		echo "<input class = 'input2'  type='text' class='input' name = 'mail'><br />";
		
		echo "<input type='submit' class='button1' value='ZŁÓŻ ZAMÓWIENIE'>";
		echo "</form>";
		echo "</div>";
		
		echo "<div style = 'clear: both'></div>";
	
	
		echo "</div>";
		
		echo "</div>";
				
		echo "<div style = 'clear: both'></div>";
		
		}
	}
}	
	
?>

</div>
		
		<div id = "socials">
	<div id = "socialsdiv">
	<a href = "http://youtube.com" targer = "_blank" class = "linki"><div id = "yt"><i class = "icon-youtube"></i></div></a>
	<a href = "http://facebook.com" target = "_blank" class = "linki"><div id = "fb"><i class = "icon-facebook-squared"></i></div></a>
	<a href = "http://twitter.com" target = "_blank" class = "linki"><div id = "tw"><i class = "icon-twitter"></i></div></a>
	<a href = "http://googleplus.com" target = "_blank" class = "linki"><div id = "gplus"><i class = "icon-gplus"></i></div>	</a>
	<div style = "clear:both"></div>
	</div>
	</div>
	
	<div id = "footer"> Krzysztof Kurowski &copy; Wszelkie prawa zastrzeżone!</div>
</div>

<script src="jquery-1.11.3.min.js"></script>
	
<script>

	$(document).ready(function() {
	var NavY = $('#menu').offset().top;
	 
	var stickyNav = function(){
	var ScrollY = $(window).scrollTop();
		  
	if (ScrollY > NavY) { 
		$('#menu').addClass('sticky');
	} else {
		$('#menu').removeClass('sticky'); 
	}
	};
	 
	stickyNav();
	 
	$(window).scroll(function() {
		stickyNav();
	});
	});
	
</script>

</body>
</html>
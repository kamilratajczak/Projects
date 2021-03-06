<?php

session_start();

$login = $_SESSION['login'];

if($login !== "admin")
	header('Location: index.php');

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
	<div id = "contents">
	
		<div id = "menu3">
			<div id = "opcja1"><a href = "zamowieniaklientow.php" class = "linki1">Nowe</a></div>
			<div id = "opcja1"><a href = "zrealizowane.php" class = "linki1">Zrealizowane</a></div>
			<div id = "opcja2"><a href = "wyswietl.php" class = "linki1">Wyświetl produkty</a></div>
			<div id = "opcja2"><a href = "dodaj.php" class = "linki1">Dodaj produkty</a></div>
			<div id = "opcja2"><a href = "edytuj.php" class = "linki1">Edytuj produkty</a></div>
			<div id = "opcja3"><a href = "wyloguj.php" class = "linki1">Wyloguj</a></div>
		</div>
		
		<div id = "lewa">
			<div class = "txt">Firma: <br /></div>
			<div class = "txt">Model: <br /></div>
			<div class = "txt">Cena: <br /></div>
			<div class = "txt">Rozmiar S: <br /></div>
			<div class = "txt">Rozmiar M: <br /></div>
			<div class = "txt">Rozmiar L: <br /></div>
			<div class = "txt">Rozmiar XL: <br /></div>
			<div class = "txt">Rozmiar 40: <br /></div>
			<div class = "txt">Rozmiar 41: <br /></div>
			<div class = "txt">Rozmiar 42: <br /></div>
			<div class = "txt">Uniwersalny: <br /></div>
		</div>
		
		<div id = "prawa">
		
		<?PHP
			include_once("connection.php");
			
			$login = $_SESSION['login'];
			
			$idproduktu = $_GET['id'];
			
			$_SESSION['idzmiany'] = $idproduktu;

		$query9 = "SELECT * FROM `produkty` WHERE `id` = '$idproduktu'";
		
			$result9 = mysqli_query($conn, $query9);
				
if ($result9->num_rows > 0) {

	for($i=0; $i<$result9->num_rows; $i++){
		while($row9 = $result9->fetch_assoc()) {
			
		echo "<form action='zmianadanychproduktu.php' method='post'>";
		echo "<input class = 'input2'  type='text' class='input' name='firma'value =" . $row9['firma'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='model'value =" . $row9['model'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='cena'value =" . $row9['cena'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='s'value =" . $row9['s'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='m'value =" . $row9['m'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='l'value =" . $row9['l'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='xl'value =" . $row9['xl'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='40'value =" . $row9['40'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='41'value =" . $row9['41'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='42'value =" . $row9['42'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='uniwersalny'value =" . $row9['uniwersalny'] . "><br />";
		
		echo "<input type='submit' class='button' value='ZMIEŃ'>";
		echo "</form>";
		echo "</div>";
		
				echo "<div id = 'obrazek'><img src =" . $row9["duzezdjecie"] . " width='312' height='416'></div>";

				
			}
		}
	}
		
		
		?>
		<div style = "clear: both"></div>
	
	
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
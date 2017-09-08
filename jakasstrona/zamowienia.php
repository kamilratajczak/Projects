<?php

session_start();

include_once("connection.php");

if(empty($_SESSION['login']))
	header('Location: zalogujsie.php');

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
			<a href = "account.php" class = "linki"><div id = "account"><i class = "icon-user"></i><br />Moje konto</div></a>
			<a href = "koszyk.php" class = "linki"><div id = "basket"><i class = "icon-basket"></i><br />Koszyk</div></a>
			<div style = "clear: both"></div>
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
	
		<div id = "menu2">
			<div id = "opcja1"><a href = "account.php" class = "linki1">Moje dane</a></div>
			<div id = "opcja2"><a href = "zamowienia.php" class = "linki1">Moje zamówienia</a></div>
			<div id = "opcja3"><a href = "wyloguj.php" class = "linki1">Wyloguj</a></div>
		</div>
		
		<?PHP
		
		$login = $_SESSION['login'];
		
		$query = "SELECT * FROM `zamowienia` WHERE login = '$login' ORDER by data DESC";
		$result = mysqli_query($conn, $query);
		
		if ($result->num_rows > 0) {

		echo "<div id = 'mojezamowienia'>";
		
		for($i=0; $i<$result->num_rows; $i++){
		while($row = $result->fetch_assoc()) {
		
	echo "<div id = 'tablice'>";
	echo "<div id = 'tablica2'>";
	echo "<table>";	
	echo "<tr>";
	echo "<th class = 'data'>Data</th>";
	echo "<th class = 'produkt'>Produkt</th>";
	echo "<th class = 'cena'>Cena</th>";
	echo "</tr>";	
	echo "<tr>";
	echo "<th>" . $row['data'] . "</th>";
	if($row['produkt']){
	echo "<th class = 'thprodukt2'>" . $row['produkt'];
	echo "<div id = 'left'>Rozmiar: " . $row['rozmiar'] . "<br /></div>";
	}
	if($row['produkt1']){
	echo $row['produkt1'];
	echo "<div id = 'left'>Rozmiar: " . $row['rozmiar1'] . "<br /></div>";
	}
	if($row['produkt2']){
	echo $row['produkt2'];
	echo "<div id = 'left'>Rozmiar: " . $row['rozmiar2'] . "<br /></div>";
	}
	if($row['produkt3']){
	echo $row['produkt3'];
	echo "<div id = 'left'>Rozmiar: " . $row['rozmiar3'] . "<br /></div>";
	}
	if($row['produkt4']){
	echo $row['produkt4'];
	echo "<div id = 'left'>Rozmiar: " . $row['rozmiar4'] . "<br /></div>";
	}
	echo "<th>" . $row['razem'] . " zł</th>";
	echo "</tr>";
	echo "</table>";
	echo "</div>";	
	echo "</div>";	
		}
		}
		echo "</div>";
		}
		else
			echo "<div id = 'brakzamowien'>Nie masz żadnych zamówień</div>";
			
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
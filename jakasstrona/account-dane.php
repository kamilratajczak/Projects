<?php

session_start();

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

<script>

function noneError()
{
	document.getElementById('bladaccount').style.display = 'none';
}

</script>

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
			<a href = "#" onclick=ontop('zaloguj'); class = "linki"><div id = "account"><i class = "icon-user"></i><br />Moje konto</div></a>
			<a href = "http://facebook.com" class = "linki"><div id = "basket"><i class = "icon-basket"></i><br />Koszyk</div></a>
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
	
		<div id = "menu1">
			<div id = "opcja1"><a href = "account.php" class = "linki1">Moje dane</a></div>
			<div id = "opcja2"><a href = "zamowienia.php" class = "linki1">Moje zamówienia</a></div>
		</div>
		
		<div id = "calosc">
		
		<div id = "bladaccount">
		<span class="closeSM" onclick=noneError();>&times;</span>
		<div id = "blad">Dane zostały zmienione</div>
		</div>
		
		<div id = "lewa">
			<div class = "txt">Imię: <br /></div>
			<div class = "txt">Nazwisko: <br /></div>
			<div class = "txt">Kod pocztowy: <br /></div>
			<div class = "txt">Miasto: <br /></div>
			<div class = "txt">Ulica: <br /></div>
			<div class = "txt">Numer domu: <br /></div>
			<div class = "txt">Numer telefonu: <br /></div>
			<div class = "txt">Email: <br /></div>
		</div>
				
		<div id = "prawa">
		
		<?PHP
			include_once("connection.php");
			
			$login = $_SESSION['login'];

		$query9 = "SELECT * FROM `uzytkownicy` WHERE `login` = '$login'";
		
			$result9 = mysqli_query($conn, $query9);
				
if ($result9->num_rows > 0) {

	for($i=0; $i<$result9->num_rows; $i++){
		while($row9 = $result9->fetch_assoc()) {
			
		echo "<form action='zmianadanych.php' method='post'>";
        echo "<input class = 'input2'  type='text' class='input' name='imie' value =" . $row9['imie'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='nazwisko'value =" . $row9['nazwisko'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='kod'value =" . $row9['kod'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='miasto'value =" . $row9['miasto'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='ulica'value =" . $row9['ulica'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='numer'value =" . $row9['numer_domu'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='telefon'value =" . $row9['numer_telefonu'] . "><br />";
		echo "<input class = 'input2'  type='text' class='input' name='mail'value =" . $row9['email'] . "><br />";
		}
	}
}
		?>
		
		<input type="submit" class="button" value="ZMIEŃ DANE">
		</form>
		</div>
		
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
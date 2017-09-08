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
<meta name = "description" content = "Sranie w banie" />
<meta name = "keywords" content = "sranie, w, banie" />
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
	<div id = "content">
	
			<?PHP
			include_once("connection.php");

		$query = "SELECT * FROM `produkty` WHERE `kategoria` = 'Sportowe spodnie'";
		
			$result = mysqli_query($conn, $query);
				
if ($result->num_rows > 0) {

	for($i=0; $i<$result->num_rows; $i++){
		while($row = $result->fetch_assoc()) {
			
		if($row["s"]>0)
		$s = "S ";
		else
		$s = "";
	
	if($row["m"]>0)
		$m = "M ";
		else
		$m = "";
	
	if($row["l"]>0)
		$l= "L ";
		else
		$l = "";
	
	if($row["xl"]>0)
		$xl = "XL";
		else
		$xl = "";
		
		echo "<div class = 'produkty'>";
		echo "<a href = 'produkt-zalogowany.php?id={$row['id']}' class = 'linki'><div class = 'produkt'><img src =" . $row["duzezdjecie"] . " width='176' height='234'> </img></div></a>";
		echo "<div class = 'szczegoly'>" . $row["firma"] . "<br />" . $row["model"] .	 "<br />" . $s . $m . $l . $xl . "<br />" . $row["cena"] . " zł" . "</div>";
		echo "</div>";
		
		}
	}
}	
	else
		echo "Brak produktów";
	
?>
	
		</div>
		
			<div style = "clear: both"></div>
			
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

<div id="zaloguj">
    <span class="close" onclick=none();>&times;</span>
	<h3>MOJE KONTO</h3>
	<div id="opcja">
		<a href = "#" onclick=ontop('zaloguj1'); class = "linki"><div id="logowanie">LOGOWANIE</div></a>
		<a href = "#" onclick=ontop('zarejestruj'); class = "linki"><div id="rejestracja">REJESTRACJA</div></a>
		<div style="clear: both"></div>
		</div>
	<div id="inputy">
    <form action="logowanie.php" method="post">
	
	<?php
	
	if(!isset($_COOKIE["Login"]))
		echo "<input type='text' class='input' name='login' placeholder='Login:'><br />";
	else{
		$cookie_login = $_COOKIE["Login"];
		echo "<input type='text' class='input' name='login' value = $cookie_login><br />";	
	}
	?>
	
		<input type="password" class="input" name="haslo" placeholder="Hasło:"><br />
		<input type="submit" class="button" value="ZALOGUJ SIĘ">
	</form>
	</div>
	<div id="tekst">Nie masz konta?  <a href = "#" onclick=ontop('zarejestruj'); class = "linki">Zarejestruj sie!</a>
	</div>
</div>

<div id="zaloguj1">
    <span class="close" onclick=none();>&times;</span>
	<h3>MOJE KONTO</h3>
	<div id="opcja">
		<a href = "#" onclick=ontop('zaloguj1'); class = "linki"><div id="logowanie">LOGOWANIE</div></a>
		<a href = "#" onclick=ontop('zarejestruj'); class = "linki"><div id="rejestracja">REJESTRACJA</div></a>
		<div style="clear: both"></div>
		</div>	
	<div id="inputy">
    <form action="logowanie.php" method="post">	

	<?php
	
	if(!isset($_COOKIE["Login"]))
		echo "<input type='text' class='input' name='login' placeholder='Login:'><br />";
	else{
		$cookie_login = $_COOKIE["Login"];
		echo "<input type='text' class='input' name='login' value = $cookie_login><br />";	
	}
	?>
	
		<input type="password" class="input" name="haslo" placeholder="Hasło:"><br />
		<input type="submit" class="button" value="ZALOGUJ SIĘ">
	</form>
	</div>
	<div id="tekst">Nie masz konta?  <a href = "#" onclick=ontop('zarejestruj'); class = "linki">Zarejestruj sie!</a>
	</div>
</div>

<div id="zarejestruj">
    <span class="close" onclick=none();>&times;</span>
	<h3>MOJE KONTO</h3>
	<div id="opcja">
		<a href = "#" onclick=ontop('zaloguj1'); class = "linki"><div id="logowanie1">LOGOWANIE</div></a>
		<a href = "#" onclick=ontop('zarejestruj'); class = "linki"><div id="rejestracja1">REJESTRACJA</div></a>
		<div style="clear: both"></div>
		</div>
	<div id="inputy">
	<form action="rejestracja.php" method="post">
		<input type="text" class="input" name="login" placeholder="Login:"><br />
		<input type="password" class="input" name="haslo" placeholder="Hasło:"><br />
		<input type="password" class="input" name="haslo2" placeholder="Powtórz hasło:"><br />
		<input type="submit" class="button" value="ZAREJESTRUJ SIĘ">
	</form>
	</div>
	<div id="tekst">Masz konto?  <a href = "#" onclick=ontop('zaloguj1'); class = "linki">Zaloguj sie!</a>
	</div>
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
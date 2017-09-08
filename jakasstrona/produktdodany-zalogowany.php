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

<script>

function noneError()
{
	document.getElementById('bladaccount1').style.display = 'none';
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

	$id = $_GET['id'];
	
	if(isset($_SESSION['id'])){
		if(isset($_SESSION['id1'])){
			if(isset($_SESSION['id2'])){
				if(isset($_SESSION['id3'])){
					if(isset($_SESSION['id4'])){
					 header("Location: zaduzo-zalogowany.php?id=$id");
					}
					else{
					$_SESSION['id4'] = $id;
				}
				}
					else{
				$_SESSION['id3'] = $id;
			}
			}
			else{
			$_SESSION['id2'] = $id;
		}
		}
		else{
		$_SESSION['id1'] = $id;
	}
	}
		else{
	$_SESSION['id'] = $id;
		}
	
	$rozmiar = $_POST['rozmiar'];
	
	if(isset($_SESSION['rozmiar'])){
		if(isset($_SESSION['rozmiar1'])){
			if(isset($_SESSION['rozmiar2'])){
				if(isset($_SESSION['rozmiar3'])){
					if(isset($_SESSION['rozmiar4'])){
						header("Location: zaduzo-zalogowany.php?id=$id");
					}
					else{
					$_SESSION['rozmiar4'] = $rozmiar;
				}
				}
					else{
				$_SESSION['rozmiar3'] = $rozmiar;
			}
			}
			else{
			$_SESSION['rozmiar2'] = $rozmiar;
		}
		}
		else{
		$_SESSION['rozmiar1'] = $rozmiar;
	}
	}
		else{
	$_SESSION['rozmiar'] = $_POST['rozmiar'];
		}
	
		$query = "SELECT * FROM `produkty` WHERE `id` = '$id'";
		
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
	
	if($row["40"]>0)
		$r40 = "40 ";
		else
		$r40 = "";
	
	if($row["41"]>0)
		$r41 = "41 ";
		else
		$r41 = "";
	
	if($row["42"]>0)
		$r42= "42 ";
		else
		$r42 = "";
	if($row["uniwersalny"]>0)
		$u= "uniwersalny";
		else
		$u = "";
	
			echo "<div id = 'calosc'>";
		
			echo "<div id = 'bladaccount1'>";
			echo "<span class='closeSM' onclick=noneError();>&times;</span>";
			echo "<div id = 'blad'>Produkt został dodany do koszyka</div>";
			echo "</div>";
			
			echo "<div id = 'produkt'><img src =" . $row["duzezdjecie"] . " width='472' height='630'></img></div>";
			echo "<div id = 'info'>";
			echo "<div id ='firma'>" . $row['firma'] . "</div>";
			echo "<div id ='model'>" . $row['model'] . "</div>";
			echo "<div id = 'cena'>" . $row['cena'] . " zł</div>";
			echo "<div id = 'rozmiar'>Dostępne rozmiary: " . $s . $m . $l . $xl . $r40 . $r41 . $r42 . $u . "</div>";
			echo "<div id = 'opis'>Opis: <br />" . $row['opis'] . "</div>";
			
			echo "<div id = 'wybierzrozmiar'>";
			echo "<div id = 'wybor'>Wybierz rozmiar:</div>";
			echo "<div id = 'form'>";
			echo "<form action='produktdodany-zalogowany.php?id=$id' method ='post'>";
			echo "<select name='rozmiar'>";
			if($row["s"]>0)
			echo "<option>" . $s . "</option>";
			if($row["m"]>0)
			echo "<option>" . $m . "</option>";
			if($row["l"]>0)
			echo "<option>" . $l . "</option>";
			if($row["xl"]>0)
			echo "<option>" . $xl . "</option>";
			if($row["40"]>0)
			echo "<option>" . $r40 . "</option>";
			if($row["41"]>0)
			echo "<option>" . $r41 . "</option>";
			if($row["42"]>0)
			echo "<option>" . $r42 . "</option>";
			if($row["uniwersalny"]>0)
			echo "<option>" . $u . "</option>";
			echo "</select>";
			echo "</div>";
			echo "<div style = 'clear:both'></div>";
			echo "<input type='submit' class='dodaj' value='DODAJ DO KOSZYKA'>";
			echo "</form>";
			echo "</div>";
			echo "</div>";
			echo "<div style = 'clear:both'></div>";
			echo "</div>";
		}
	}
	}
		?>
		
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
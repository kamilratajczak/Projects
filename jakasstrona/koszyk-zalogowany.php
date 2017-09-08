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
	<div id = "contentablica">
	
	<?php

include_once("connection.php");

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
	echo "<div id = 'tablica'>";
echo "<table>";
echo "<tr>";
echo "<th>Zdjęcie</th>";
echo "<th>Kategoria</th>";
echo "<th class = 'thprodukt'>Produkt</th>";
echo "<th>Rozmiar</th>";
echo "<th>Cena</th>";
echo "<th>Usuń</th>";
echo "</tr>";
if(isset($_SESSION['id'])){
echo "<tr>";	
echo "<th><a href = 'produkt-zalogowany.php?id=$id'class = 'linki1'><img src =" . $row["duzezdjecie"] . " width='88' height='117'></img></a></th>";
echo "<th>" . $row['kategoria'] . "</th>";
echo "<th><a href = 'produkt-zalogowany.php?id=$id'class = 'linki1'>" . $row['firma'] . " " . $row['model'] . "</a></th>";
echo "<th>" . $_SESSION['rozmiar'] . "</th>";
echo "<th>" . $row['cena'] . " zł</th>";
echo "<th><a href = 'delete.php' class = 'linki1'><span class='closeU'>&times;</span></a></th>";
echo "</tr>";

$_SESSION['produkt'] = $row['firma'] . " " . $row['model'];

}

if(isset($_SESSION['id1'])){
echo "<tr>";
echo "<th><a href = 'produkt-zalogowany.php?id=$id1'class = 'linki1'><img src =" . $row1["duzezdjecie"] . " width='88' height='117'></img></a></th>";
echo "<th>" . $row1['kategoria'] . "</th>";
echo "<th><a href = 'produkt-zalogowany.php?id=$id1'class = 'linki1'>" . $row1['firma'] . " " . $row1['model'] . "</a></th>";
echo "<th>" . $_SESSION['rozmiar1'] . "</th>";
echo "<th>" . $row1['cena'] . " zł</th>";
echo "<th><a href = 'delete1.php' class = 'linki1'><span class='closeU'>&times;</span></a></th>";
echo "</tr>";

$_SESSION['produkt1'] = $row1['firma'] . " " . $row1['model'];

}
if(isset($_SESSION['id2'])){
echo "<tr>";
echo "<th><a href = 'produkt-zalogowany.php?id=$id2'class = 'linki1'><img src =" . $row2["duzezdjecie"] . " width='88' height='117'></img></a></th>";
echo "<th>" . $row2['kategoria'] . "</th>";
echo "<th><a href = 'produkt-zalogowany.php?id=$id2'class = 'linki1'>" . $row2['firma'] . " " . $row2['model'] . "</a></th>";
echo "<th>" . $_SESSION['rozmiar2'] . "</th>";
echo "<th>" . $row2['cena'] . " zł</th>";
echo "<th><a href = 'delete2.php' class = 'linki1'><span class='closeU'>&times;</span></a></th>";
echo "</tr>";

$_SESSION['produkt2'] = $row2['firma'] . " " . $row2['model'];

}
if(isset($_SESSION['id3'])){
echo "<tr>";
echo "<th><a href = 'produkt-zalogowany.php?id=$id3'class = 'linki1'><img src =" . $row3["duzezdjecie"] . " width='88' height='117'></img></a></th>";
echo "<th>" . $row3['kategoria'] . "</th>";
echo "<th><a href = 'produkt-zalogowany.php?id=$id3'class = 'linki1'>" . $row3['firma'] . " " . $row3['model'] . "</a></th>";
echo "<th>" . $_SESSION['rozmiar3'] . "</th>";
echo "<th>" . $row3['cena'] . " zł</th>";
echo "<th><a href = 'delete3.php' class = 'linki1'><span class='closeU'>&times;</span></a></th>";
echo "</tr>";

$_SESSION['produkt3'] = $row3['firma'] . " " . $row3['model'];
}
if(isset($_SESSION['id4'])){
echo "<tr>";
echo "<th><a href = 'produkt-zalogowany.php?id=$id4'class = 'linki1'><img src =" . $row4["duzezdjecie"] . " width='88' height='117'></img></a></th>";
echo "<th>" . $row4['kategoria'] . "</th>";
echo "<th><a href = 'produkt-zalogowany.php?id=$id4'class = 'linki1'>" . $row4['firma'] . " " . $row4['model'] . "</a></th>";
echo "<th>" . $_SESSION['rozmiar4'] . "</th>";
echo "<th>" . $row4['cena'] . " zł</th>";
echo "<th><a href = 'delete4.php' class = 'linki1'><span class='closeU'>&times;</span></a></th>";
echo "</tr>";

$_SESSION['produkt4'] = $row4['firma'] . " " . $row4['model'];

}

echo "</table>";

echo "<a href ='zamowienie.php' class = 'linki1'><div id = 'przycisk'>PODSUMOWANIE</div></a>";
echo "</div>";
}
else{
	echo "<div id = 'tablica'>Brak produktów w koszyku</div>";
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
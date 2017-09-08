<?php

session_start();

if(isset($_SESSION['login']))
	header('Location: zalogowany.php');

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

function ontop(id)
{
	document.getElementById('zarejestruj').style.display = 'none';
	document.getElementById('zaloguj').style.display = 'none';
	document.getElementById('zaloguj1').style.display = 'none';
	
	document.getElementById(id).style.display = 'block';
}

function none()
{
	document.getElementById('zarejestruj').style.display = 'none';
	document.getElementById('zaloguj').style.display = 'none';
	document.getElementById('zaloguj1').style.display = 'none';
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
	<div id = "content">
	

		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eget rhoncus mi, in vestibulum lorem. Nulla urna mauris, egestas nec erat vel, tempus ullamcorper dolor. Maecenas eu mattis arcu. Aliquam dapibus quis risus eget consequat. Curabitur eu convallis urna, vitae scelerisque est. Nunc eget posuere urna. Nulla facilisi. Phasellus blandit eleifend aliquet. Curabitur porttitor pharetra pretium. Nam ac eros laoreet, consequat felis at, auctor metus.</p>

		<p>Etiam condimentum sed lectus at laoreet. Fusce pellentesque porta purus a venenatis. Quisque erat augue, malesuada nec ultrices vitae, consequat sed metus. Donec at ipsum viverra mauris feugiat euismod. Morbi ultrices tellus libero, et gravida tortor laoreet eget. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Morbi auctor interdum ornare. Praesent vel urna volutpat, accumsan erat at, pharetra urna. Pellentesque egestas sodales nibh vitae sodales. Suspendisse laoreet risus neque, viverra dictum leo condimentum vitae. Sed sem diam, blandit eu vestibulum in, tempor nec lacus. Nullam lacinia commodo elit, sed euismod leo. Suspendisse porttitor sem mi, fringilla viverra diam tincidunt ut.</p>

		<p>In dui turpis, varius nec neque id, mollis cursus neque. Pellentesque eget laoreet nulla. Nam lectus ex, vehicula ut euismod et, rhoncus in lectus. Donec luctus, sapien a venenatis vulputate, sapien ante condimentum lectus, ut molestie enim velit vitae magna. Suspendisse varius neque pulvinar enim ornare, nec lobortis enim lobortis. Ut eu ex neque. Vestibulum feugiat ligula et arcu rhoncus, quis maximus mauris pellentesque. Vivamus fermentum ultrices lacus vel vulputate. Morbi ultrices dolor nulla, ac lobortis nisl vestibulum sed. Vestibulum iaculis, lectus eget condimentum sodales, lorem nulla fermentum tellus, volutpat congue lacus dolor et quam. Phasellus ac risus blandit nisi rutrum suscipit non eu mauris. Vestibulum fringilla non neque vitae vestibulum.</p>

		<p>Fusce quis vehicula purus, ut fermentum quam. Suspendisse cursus dui ac est convallis, sit amet egestas lorem sodales. Praesent nec nunc mattis, hendrerit mauris quis, dignissim nisi. Pellentesque semper faucibus urna vel tempus. Suspendisse egestas lacus ornare ligula mattis, et pulvinar urna sodales. Suspendisse tristique eget lacus sit amet dapibus. Nam quis imperdiet velit. Vestibulum consectetur rutrum tortor, sit amet fringilla nisi rhoncus id. Aenean sit amet odio elit. Nulla orci quam, eleifend quis sapien sed, vestibulum elementum urna. Sed dapibus ligula vitae turpis bibendum, in tempus magna bibendum. Aenean ut purus diam. Praesent porta velit ut dui fringilla egestas. Donec dignissim non sapien at imperdiet. Quisque bibendum massa ligula, vel elementum eros iaculis quis. Maecenas velit nisl, imperdiet vitae dui sed, convallis placerat enim.</p>

		<p>Nunc mollis, massa scelerisque elementum condimentum, mauris ipsum accumsan purus, in semper leo erat vel turpis. Etiam varius feugiat diam eu sagittis. Curabitur dapibus sollicitudin dictum. In tincidunt at mauris vel dictum. Vivamus id imperdiet sem. Nam viverra ac massa ac ultricies. Nam condimentum commodo faucibus. Integer eget facilisis massa, sit amet vulputate purus. Duis in eros pulvinar eros porttitor pellentesque non volutpat dui. Nam laoreet scelerisque leo, accumsan porttitor tortor dignissim tempus. Nunc sit amet rutrum lorem. Cras malesuada risus sit amet aliquet vestibulum. Donec tellus nibh, pretium sed diam vitae, aliquet tempus risus.</p>
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
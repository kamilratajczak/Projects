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

function onload()
{
	document.getElementById('zarejestruj').style.display = 'none';
	document.getElementById('zaloguj').style.display = 'none';
}

function ontop(id)
{
	document.getElementById('zarejestruj').style.display = 'none';
	document.getElementById('zaloguj').style.display = 'none';
	
	document.getElementById(id).style.display = 'block';
}

</script>

</head>

<body onload=onload();>
	
<div id="zaloguj">
	<h3>MOJE KONTO</h3>
	<div id="opcja">
		<div id="logowanie">LOGOWANIE</div>
		<div id="rejestracja">REJESTRACJA</div>
		<div style="clear: both"></div>
		</div>
	<div id="inputy">
	<form>
		<input type="text" class="input" name="login" placeholder="Login:"><br />
		<input type="password" class="input" name="haslo" placeholder="Hasło:"><br />
		<input type="submit" class="button" value="ZALOGUJ SIĘ">
	</form>
	</div>
	<div id="tekst">Nie masz konta?  Zarejestruj sie!
	</div>
</div>

<div id="zarejestruj">
	<h3>MOJE KONTO</h3>
	<div id="opcja">
		<div id="logowanie1">LOGOWANIE</div>
		<div id="rejestracja1">REJESTRACJA</div>
		<div style="clear: both"></div>
		</div>
	<div id="inputy">
	<form>
		<input type="text" class="input" name="login" placeholder="Login:"><br />
		<input type="password" class="input" name="haslo" placeholder="Hasło:"><br />
		<input type="password" class="input" name="haslo2" placeholder="Powtórz hasło:"><br />
		<input type="submit" class="button" value="ZAREJESTRUJ SIĘ">
	</form>
	</div>
	<div id="tekst">Masz konto?  Zaloguj sie!
	</div>
</div>

</body>
</html>
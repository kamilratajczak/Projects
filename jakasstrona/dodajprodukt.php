<?PHP

session_start();

include_once("connection.php");

$login = $_SESSION['login'];

if($login !== "admin")
	header('Location: index.php');

	if(isset($_POST['kategoria']) && isset($_POST['firma']) && isset($_POST['model']) && isset($_POST['cena']) && isset($_POST['s']) && isset($_POST['m']) && isset($_POST['l']) && isset($_POST['xl']) && isset($_POST['40']) && isset($_POST['41']) && isset($_POST['42']) && isset($_POST['uniwersalny']) && isset($_POST['opis'])){
	
	
	$photo = $_FILES['photo'];
	$firma = $_POST['firma'];
	$model = $_POST['model'];
	$cena = $_POST['cena'];
	$kategoria = $_POST['kategoria'];
	$s = $_POST['s'];
	$m = $_POST['m'];
	$l = $_POST['l'];
	$xl = $_POST['xl'];
	$r40 = $_POST['40'];
	$r41 = $_POST['41'];
	$r42 = $_POST['42'];
	$uniwersalny = $_POST['uniwersalny'];
	$opis = $_POST['opis'];
	
	$target_dir = "images/";
    $target_file = $target_dir . basename($_FILES["photo"]["name"]);

    move_uploaded_file($_FILES["photo"]["tmp_name"], $target_file);
           
    $sciezka = basename($_FILES["photo"]["name"]);
    
		$query = "INSERT INTO `produkty` VALUES (NULL, '$firma', '$model', '$cena', '$kategoria', '$s', '$m', '$l', '$xl', '$r40', '$r41', '$r42', '$uniwersalny', '$sciezka', '$opis')";
		
		$result = mysqli_query($conn, $query);
		
					
		header('Location: admindodany.php');
	}
	
	else
		header('Location: adminuzupelnij.php');
?>
<?PHP
include_once("connection.php");
if(isset($_POST['txtLogin']) && isset($_POST['txtX']) && isset($_POST['txtY']) && isset($_POST['txtAddress']) && isset($_POST['txtMiasto']) && isset($_POST['txtUlica']) && isset($_POST['txtNumer']) && isset($_POST['txtType']) && isset($_POST['txtSize']) && isset($_POST['txtPrice']) && isset($_POST['txtPhoto']) && isset($_POST['txtDate'])){
	$login = $_POST['txtLogin'];
	$x = $_POST['txtX'];
	$y = $_POST['txtY'];
	$address = $_POST['txtAddress'];
	$miasto = $_POST['txtMiasto'];
	$ulica = $_POST['txtUlica'];
	$numer = $_POST['txtNumer']; 
	$type = $_POST['txtType'];
	$size = $_POST['txtSize'];
	$price = $_POST['txtPrice'];
	$photo = $_POST['txtPhoto'];
	$date = $_POST['txtDate'];

	//-------------------------------------------------------------- Czesc odpowiedzialna za upload ----------------------------//

$myFile = "test.txt";
$fh = fopen($myFile, 'w') or die("can't open file");
fwrite($fh, $photo);
fclose($fh);


define('UPLOAD_DIR', 'images/');
	$img = $photo;
	$data = base64_decode($img);
	$file = UPLOAD_DIR . uniqid() . '.png';
	$success = file_put_contents($file, $data);
	$path = 'http://aplikacja.apkawwsis.nstrefa.pl/php/' . $file;

/*$image = base64_decode($photo);
$sourcePath = $_FILES[$image]['tmp_name'];
$targetPath = "images/".$_FILES[$image]['name'];
$sciezka = "http://aplikacja.apkawwsis.nstrefa.pl/php/".$targetPath;
move_uploaded_file($sourcePath,$targetPath); */


  //-------------------------------------------------------------------------------------------------------------------------//





	$queryF = "SELECT * FROM `ogloszenia` WHERE login = '$login'";
	
	$resultF = mysqli_query($conn, $queryF);
	
	$query = "INSERT INTO `ogloszenia` VALUES (NULL, '$login', '$x', '$y', '$address', '$miasto', '$ulica', '$numer', '$type', '$size', '$price', '$path', '$date')"; // $sciezka to url (llink)
	
	 if($resultF->num_rows < 5){
	$result = mysqli_query($conn, $query);
	
	if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){ 
	echo "success";
	exit;
	}
 }
}
?>

<html>
<body>
<form action="<?PHP $_PHP_SELF ?>" method="post">
	Login <input type="text" name="txtLogin" /> <br />
	X <input type="text" name="txtX" /> <br />
	Y <input type="text" name="txtY" /> <br />
	Adres <input type="text" name="txtAddress" /> <br />
	Miasto <input type="text" name="txtMiasto" /> <br />
	Ulica <input type="text" name="txtUlica" /> <br />
	Numer <input type="text" name="txtNumer" /> <br />
	Rodzaj <input type="text" name="txtType" /> <br />
	Metraz <input type="text" name="txtSize" /> <br />
	Cena <input type="text" name="txtPrice" /> <br />
	Url <input type="text" name="txtPhoto" /> <br />
	Data <input type="text" name="txtDate" /> <br />
	
	
	<input type="submit" name="btnSubmit" value="Login" />
</form>
</body>
</html>

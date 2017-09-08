<?PHP 
    include_once("connection.php"); 
	
	$login = $_POST['login'];
        
        $query = "SELECT * FROM `miejsca` WHERE `login` = '$login'"; 
        
        $result = mysqli_query($conn, $query);
        
      if($result->num_rows > 0){
        while($row = $result->fetch_assoc()) {	
		$data[] = $row;
		}
			if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){ 
		echo json_encode($data);
		exit;
			}
        } 
?>
<html>
<body>
<form action="<?PHP $_PHP_SELF ?>" method="post">
	Login <input type="text" name="login" /> <br />
	<input type="submit" name="btnSubmit" value="Login" />
</form>
</body>
</html>
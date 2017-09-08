<?PHP 
    include_once("connection.php"); 
        
		$login = $_POST['login'];

		$adres = $_POST['adres'];
		
		$city = $_POST['city'];
		
		$x = $_POST['x'];
		
		$y = $_POST['y'];
		
		$description = mysqli_real_escape_string($conn, $_POST['description']);

        
        $query = "SELECT * FROM miejsca WHERE login = '$login' AND adres = '$adres'"; 
        
        $result = mysqli_query($conn, $query);
        
      if($result->num_rows > 0){
            if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){ 
                echo "failed"; 
                exit; 
            } 
        } 
		else{ 
            
			$queryA = "INSERT INTO `miejsca` VALUES (NULL, '$login', '$adres', '$city', '$x', '$y', '$description')"; 
        
			$resultA = mysqli_query($conn, $queryA);
			
			 if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){ 
                echo "success"; 
                exit; 
            } 
			
        } 
?>

<html>
    <body>
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            login <input type="text" name="login" value="" /><br/>
            adres <input type="password" name="adres" value="" /><br/>
            city <input type ="password" name="city" value="" /><br/>
            x <input type="password" name="x" value="" /><br/>
            y <input type="password" name="y" value="" /><br/>
            description <input type="password" name="description" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>
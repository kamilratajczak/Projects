<?PHP 
    include_once("connection.php"); 
        
        $query = "SELECT id_ogloszenia, login, x, y FROM ogloszenia"; 
        
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

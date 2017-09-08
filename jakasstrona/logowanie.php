<?php

    session_start();
    
    include_once("connection.php"); 
    
		$login = mysqli_real_escape_string($conn, $_POST['login']);
        $haslo = mysqli_real_escape_string($conn, $_POST['haslo']);
        
        $query = "SELECT login, haslo FROM uzytkownicy WHERE login = '$login' AND haslo = '$haslo'"; 
        
        $result = mysqli_query($conn, $query);
        
      if($result->num_rows > 0){
		  
		  $_SESSION['login'] = $login;
		  
		  $cookie_login = $login;
		
		  setcookie("Login", $cookie_login, time()+(86400*30));
		  
                header('Location: zalogowany.php');

        } 
		else{ 
		
		    header('Location: blednedane.php');
        } 
?>
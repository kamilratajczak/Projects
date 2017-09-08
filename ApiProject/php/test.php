<?php
$arrContextOptions=array(
    "ssl"=>array(
        "verify_peer"=>false,
        "verify_peer_name"=>false,
    ),
);  

$response = file_get_contents("https://jsonplaceholder.typicode.com/users", false, stream_context_create($arrContextOptions));

if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){
		echo $response;
		exit;
			}
?>
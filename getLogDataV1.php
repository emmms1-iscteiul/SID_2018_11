<?php
header("Content-Type:application/json");			
	
	$data = get_data();	
	if(empty($data)){
		echo 'Data Not Found';
	}
	else{
		echo $data;
	}
	
function get_data()
{
	$url="127.0.0.1";
	$database="monitorizacao_de_culturas";
	$username="root";
	$password="root";
	$conn = mysqli_connect($url, $username, $password, $database);
	if (!$conn){
		die ("Connection Failled: ".$conn->connect_error);
	}
	$sql = "Select * from log";
	$result = mysqli_query($conn, $sql);
	$rows = array();
	if ($result) {
		if (mysqli_num_rows($result)>0){
			while($r=mysqli_fetch_assoc($result)){
				array_push($rows, $r);
			}
		}	
	}
	mysqli_close ($conn);
	return json_encode($rows);
}	
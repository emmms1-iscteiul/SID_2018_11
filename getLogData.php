<?php
header("Content-Type:application/json");

	if(!empty($_GET['where'])){
		$whereclause=$_GET['where'];
	}
	else{
		$whereclause=' 1=1';
	}
	if(!empty($_GET['order'])){
		$orderclause='order by '.$_GET['order'];
	}
	else{
		$orderclause='order by IDLog';
	}
	if(!empty($_GET['limit'])){
		$limit=' limit '.$_GET['limit'];
	}
	else{
		$limit= ' ';
	}			
	
	$data = get_data($whereclause,$orderclause, $limit);	
	if(empty($data)){
		echo 'Data Not Found';
	}
	else{
		echo $data;
		
	}
	
	

function get_data($whereclause,$orderclause, $limit)
{
	$url="127.0.0.1";
	$database="monitorizacao_de_culturas";
	$username="root";
	$password="";
	$conn = mysqli_connect($url, $username, $password, $database);
	if (!$conn){
		die ("Connection Failled: ".$conn->connect_error);
	}
	$sql = "Select * from log where".$whereclause;
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
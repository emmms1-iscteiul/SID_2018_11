<?php
		$url = "http://localhost/apiGetCliente.php";		
		$client = curl_init($url);
		curl_setopt($client,CURLOPT_RETURNTRANSFER,true);
		$response = curl_exec($client);		
		echo $response;
		
		$url = "http://localhost/apiPutCliente.php?data=".$response;
		$url = str_replace ( ' ', '%20', $url);
		$client = curl_init($url);
		curl_setopt($client,CURLOPT_RETURNTRANSFER,true);
		$response = curl_exec($client);		
		echo $response;

?>
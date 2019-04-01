<!DOCTYPE html>
<html>
<body>
<form name="form" action="" method="get">

IDVariaveisMedidas:<input name="idVM" type="number"/>
IDCultura:<input name="idC" type="number"/>
QuantidadeDados:<input name="dados" type="number"/>
<input type="submit" name="insert" value="insert" />
</form>
<?php

echo "Successful!"; echo "<br>";

	if($_GET){
		if(!empty($_GET['idVM']) && !empty($_GET['idC']) && !empty($_GET['dados'])) {
			$param1 = $_GET['idVM'];
			echo $param1;echo "<br>";
			$param3 = $_GET['idC'];
			echo $param3;echo "<br>";
			$param4 = $_GET['dados'];
			echo $param4;echo "<br>";
			
		}						
		insert($param1, $param3, $param4);		
	}
	
	function insert($param1, $param3, $param4){
		
		$db_host="localhost";
		$db_user="root";
		$db_pass="root";
		$originDB="monotorizacao_de_culturas";

		$con_origin = mysqli_connect($db_host, $db_user, $db_pass, $originDB);
		
		echo "Successful!"; echo "<br>";
		
		for($i=0;$i<$param4;$i++) {
			
			$param2 = "'".rand(0,10)."'";
			
			$sql = "INSERT INTO medicoes(IDVariaveisMedidas, ValorMedicaoMedicoes, IDCultura) VALUES ($param1, $param2, $param3);";
			echo $sql;
			
			mysqli_query($con_origin, $sql);
			
		}
			
		mysqli_close($con_origin);
		//$url = "http://localhost/ClienteExemplo/apiGetCliente.php?where=".$param."&order=".$_GET['order']."&limit=".$_GET['top'];
		//$client = curl_init($url);
		//curl_setopt($client,CURLOPT_RETURNTRANSFER,true);
		//$response = curl_exec($client);		
		//echo "<br>";
		//echo "<br>";
		//echo $response;				
	}
?>
</body>		
</html>




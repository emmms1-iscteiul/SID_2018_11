<?php
header("Content-Type:application/json");
if(!empty($_GET['data'])){
	$data=$_GET['data'];
	$result=put_data($data);
	echo $result . " records inserted."; 		
}
else{
	echo "Missing Pamameter";
}
	
	
function put_data($data){
	$data = json_decode($data);	
	$url="127.0.0.1";
	$database="monitorizacao_de_culturas(destino)";
	$username="root";
	$password="";
	$number_inserts = 0;
	$conn = mysqli_connect($url, $username, $password, $database);
	if ($conn){
		
		$sql = "select max(IDLog) as Maximo from logs;";
		$result = mysqli_query($conn, $sql);
		$rows = array();
		if (mysqli_num_rows($result)>0) {
			$r=mysqli_fetch_assoc($result);
			$next_record_id = $r["Maximo"]+1;	
		}
		else{
			$next_record_id = 1;
		}
		foreach ($data as $inv ) {
			if ($inv->IDLog >= $next_record_id){
				$id_log = $inv->IDLog;
				$string1 = $inv->DataHoraOperacao;
			$string2 = $inv->UtilizadorOperacao;
			$string3 = $inv->Operacao;
			$string4 = $inv->IDVariavelAntigo;
			$string5 = $inv->IDVariavelNovo;
			$string6 = $inv->NomeVariavelAntigo;
			$string7 = $inv->NomeVariavelNovo;
			$string8 = $inv->IDCulturaAntigo;
			$string9 = $inv->IDCulturaNovo;
			$string10 = $inv->NomeCulturaAntigo;
			$string11 = $inv->NomeCulturaNovo;
			$string12 = $inv->DescricaoCulturaAntiga;
			$string13 = $inv->DescricaoCulturaNova;
			$string14 = $inv->EmailUtilizadorAntigo;
			$string15 = $inv->EmailUtilizadorNovo;
			$string16 = $inv->NomeUtilizadorAntigo;
			$string17 = $inv->NomeUtilizadorNovo;
			$string18 = $inv->PasswordUtilizadorAntigo;
			$string19 = $inv->PasswordUtilizadorNovo;
			$string20 = $inv->IDVariaveisMedidasAntigo;
			$string21 = $inv->IDVariaveisMedidasNovo;
			$string22 = $inv->LimiteInferiorVariaveisMedidasAntigo;
			$string23 = $inv->LimiteInferiorVariaveisMedidasNovo;
			$string24 = $inv->LimiteSuperiorVariaveisMedidasAntigo;
			$string25 = $inv->LimiteSuperiorVariaveisMedidasNovo;
			$string26 = $inv->NumeroMedicaoAntigo;
			$string27 = $inv->NumeroMedicaoNovo;
			$string28 = $inv->DataHoraMedicaoMedicoesAntiga;
			$string29 = $inv->DataHoraMedicaoMedicoesNova;
			$string30 = $inv->ValorMedicaoMedicoesAntigo;
			$string31 = $inv->ValorMedicaoMedicoesNovo;
			$string32 = $inv->IDCategoriaProfissionalAntigo;
			$string33 = $inv->IDCategoriaProfissionalNovo;
			$string34 = $inv->NomeCategoriaProfissionalAntigo;
			$string35 = $inv->NomeCategoriaProfissionalNovo;
			$string36 = $inv->IDTipoCulturaAntigo;
			$string37 = $inv->IDTipoCulturaNovo;
			$string38 = $inv->NomeTipoCulturaAntigo;
			$string39 = $inv->NomeTipoCulturaNovo;
			$string40 = $inv->IDMedicaoLuminosidadeTemperaturaAntigo;
			$string41 = $inv->IDMedicaoLuminosidadeTemperaturaNovo;
			$string42 = $inv->DataHoraMedicaoMedicoesLuminosidadeTemperaturaAntiga;
			$string43 = $inv->DataHoraMedicaoMedicoesLuminosidadeTemperaturaNova;
			$string44 = $inv->ValorMedicaoLuminosidadeAntigo;
			$string45 = $inv->ValorMedicaoLuminosidadeNovo;
			$string46 = $inv->ValorMedicaoTemperaturaAntigo;
			$string47 = $inv->ValorMedicaoTemperaturaNovo;
			$string48 = $inv->IDSistemaAntigo;
			$string49 = $inv->IDSistemaNovo;
			$string50 = $inv->LimiteInferiorTemperaturaSistemaAntigo;
			$string51 = $inv->LimiteInferiorTemperaturaSistemaNovo;
			$string52 = $inv->LimiteInferiorLuminosidadeSistemaAntigo;
			$string53 = $inv->LimiteInferiorLuminosidadeSistemaNovo;
			$string54 = $inv->LimiteSuperiorTemperaturaSistemaAntigo;
			$string55 = $inv->LimiteSuperiorTemperaturaSistemaNovo;
			$string56 = $inv->LimiteSuperiorLuminosidadeSistemaAntigo;
			$string57 = $inv->LimiteSuperiorLuminosidadeSistemaNovo;
			
			$sql = "INSERT INTO logs(
			IDLog,
			DataHoraOperacao,
			UtilizadorOperacao,
			Operacao,
			IDVariavelAntigo,
			IDVariavelNovo,
			NomeVariavelAntigo,
			NomeVariavelNovo,
			IDCulturaAntigo,
			IDCulturaNovo,
			NomeCulturaAntigo,
			NomeCulturaNovo,
			DescricaoCulturaAntiga,
			DescricaoCulturaNova,
			EmailUtilizadorAntigo,
			EmailUtilizadorNovo,
			NomeUtilizadorAntigo,
			NomeUtilizadorNovo,
			PasswordUtilizadorAntigo,
			PasswordUtilizadorNovo,
			IDVariaveisMedidasAntigo,
			IDVariaveisMedidasNovo,
			LimiteInferiorVariaveisMedidasAntigo,
			LimiteInferiorVariaveisMedidasNovo,
			LimiteSuperiorVariaveisMedidasAntigo,
			LimiteSuperiorVariaveisMedidasNovo,
			NumeroMedicaoAntigo,
			NumeroMedicaoNovo,
			DataHoraMedicaoMedicoesAntiga,
			DataHoraMedicaoMedicoesNova,
			ValorMedicaoMedicoesAntigo,
			ValorMedicaoMedicoesNovo,
			IDCategoriaProfissionalAntigo,
			IDCategoriaProfissionalNovo,
			NomeCategoriaProfissionalAntigo,
			NomeCategoriaProfissionalNovo,
			IDTipoCulturaAntigo,
			IDTipoCulturaNovo,
			NomeTipoCulturaAntigo,
			NomeTipoCulturaNovo,
			IDMedicaoLuminosidadeTemperaturaAntigo,
			IDMedicaoLuminosidadeTemperaturaNovo,
			DataHoraMedicaoMedicoesLuminosidadeTemperaturaAntiga,
			DataHoraMedicaoMedicoesLuminosidadeTemperaturaNova,
			ValorMedicaoLuminosidadeAntigo,
			ValorMedicaoLuminosidadeNovo,
			ValorMedicaoTemperaturaAntigo,
			ValorMedicaoTemperaturaNovo,
			IDSistemaAntigo,
			IDSistemaNovo,
			LimiteInferiorTemperaturaSistemaAntigo,
			LimiteInferiorTemperaturaSistemaNovo,
			LimiteInferiorLuminosidadeSistemaAntigo,
			LimiteInferiorLuminosidadeSistemaNovo,
			LimiteSuperiorTemperaturaSistemaAntigo,
			LimiteSuperiorTemperaturaSistemaNovo,
			LimiteSuperiorLuminosidadeSistemaAntigo,
			LimiteSuperiorLuminosidadeSistemaNovo 
			) 
			VALUES (
			$id_log,
			'$string1',
			'$string2',
			'$string3',
			'$string4',
			'$string5',
			'$string6',
			'$string7',
			'$string8',
			'$string9',
			'$string10',
			'$string11',
			'$string12',
			'$string13',
			'$string14',
			'$string15',
			'$string16',
			'$string17',
			'$string18',
			'$string19',
			'$string20',
			'$string21',
			'$string22',
			'$string23',
			'$string24',
			'$string25',
			'$string26',
			'$string27',
			'$string28',
			'$string29',
			'$string30',
			'$string31',
			'$string32',
			'$string33',
			'$string34',
			'$string35',
			'$string36',
			'$string37',
			'$string38',
			'$string39',
			'$string40',
			'$string41',
			'$string42',
			'$string43',
			'$string44',
			'$string45',
			'$string46',
			'$string47',
			'$string48',
			'$string49',
			'$string50',
			'$string51',
			'$string52',
			'$string53',
			'$string54',
			'$string55',
			'$string56',
			'$string57'
			);";
			
			$res = mysqli_query($conn, $sql);
			
			}
			else{
				$res = false;
			}
			
			if(!$res){
				$result = new stdClass();
				$result->status = false;
				$result->msg = mysqli_error($conn);
				echo json_encode($result);
				exit;
			}
			$number_inserts = $number_inserts  + 1;
			$next_record_id =$next_record_id + 1;
	}
	mysqli_close ($conn);
	}
	return $number_inserts;
}
	
<?php
$DB_SRC_HOST='localhost';
$DB_SRC_USER='root';
$DB_SRC_PASS='';
$oldDB='monotorizacao_de_culturas';
$newDB='migracao';

$c = new mysqli($DB_SRC_HOST, $DB_SRC_USER, $DB_SRC_PASS);

if (mysqli_connect_errno()) {
  exit('Connect failed: '. mysqli_connect_error());
}
else	{ 
	echo 'Connection Sucessfuly !'.'<br>';
}	

$schema = "CREATE SCHEMA IF NOT EXISTS {$newDB};";
mysqli_query($c, $schema);
$con = mysqli_select_db($c, $newDB);

$sql = "CREATE TABLE logs like $oldDB.log";

mysqli_query($c, $sql);

$sql1 = "SELECT * from $oldDB.log";
$sql2 = "SELECT IDLog from logs";

$logarray = array();
$logsarray = array();

$result = mysqli_query($c, $sql1);
while ($row = mysqli_fetch_assoc($result)) {
   $logarray[] = $row;
}

$result2 = mysqli_query($c, $sql2);
while ($row2 = mysqli_fetch_assoc($result2)) { 
	$logsarray[] = $row2;
}

for($i=0;$i<count($logarray);$i++) {
	
	$int = $logarray[$i]['IDLog'];
	$int2 = $logsarray[$i]['IDLog'];
	
	if ($int != $int2) {
	$string1 = (string) $logarray[$i]['DataHoraOperacao'];
	$string2 = (string) $logarray[$i]['UtilizadorOperacao'];
	$string3 = (string) $logarray[$i]['Operacao'];
	$string4 = (string) $logarray[$i]['NomeVariavelAntigo'];
	$string5 = (string) $logarray[$i]['NomeVariavelNovo'];
	$string6 = (string) $logarray[$i]['NomeCulturaAntigo'];
	$string7 = (string) $logarray[$i]['NomeCulturaNovo'];
	$string8 = (string) $logarray[$i]['DescicaoCulturaAntiga'];
	$string9 = (string) $logarray[$i]['DescicaoCulturaNova'];
	$string10 = (string) $logarray[$i]['EmailUtilizadorAntigo'];
	$string11 = (string) $logarray[$i]['EmailUtilizadorNovo'];
	$string12 = (string) $logarray[$i]['NomeUtilizadorAntigo'];
	$string13 = (string) $logarray[$i]['NomeUtilizadorNovo'];
	$string14 = (string) $logarray[$i]['PasswordUtilizadorAntigo'];
	$string15 = (string) $logarray[$i]['PasswordUtilizadorNovo'];
	$string16 = (string) $logarray[$i]['NomeCategoriaProfissionalAntigo'];
	$string17 = (string) $logarray[$i]['NomeCategoriaProfissionalNovo'];
	$string18 = (string) $logarray[$i]['NomeTipoCulturaAntigo'];
	$string19 = (string) $logarray[$i]['NomeTipoCulturaNovo'];
	$string20 = (string) $logarray[$i]['DataHoraMedicaoMedicoesLuminosidadeTemperaturaAntiga'];
	$string21 = (string) $logarray[$i]['DataHoraMedicaoMedicoesLuminosidadeTemperaturaNova'];
	
	$sql3 = "insert into logs(
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
	DescicaoCulturaAntiga,
	DescicaoCulturaNova,
	EmailUtilizadorAntigo,
	EmailUtilizadorNovo,
	NomeUtilizadorAntigo,
	NomeUtilizadorNovo,
	PasswordUtilizadorAntigo,
	PasswordUtilizadorNovo,
	IDVariaveisMedidasNovo,
	IDVariaveisMedidasAntigo,
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
	values (
	'$logarray[$i][IDLog]',
	'$string1',
	'$string2',
	'$string3',
	'$logarray[$i][IDVariavelAntigo]',
	'$logarray[$i][IDVariavelNovo]',
	'$string4',
	'$string5',
	'$logarray[$i][IDCulturaAntigo]',
	'$logarray[$i][IDCulturaNovo]',
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
	'$logarray[$i][IDVariaveisMedidasNovo]',
	'$logarray[$i][IDVariaveisMedidasAntigo]',
	'$logarray[$i][LimiteInferiorVariaveisMedidasAntigo]',
	'$logarray[$i][LimiteInferiorVariaveisMedidasNovo]',
	'$logarray[$i][LimiteSuperiorVariaveisMedidasAntigo]',
	'$logarray[$i][LimiteSuperiorVariaveisMedidasNovo]',
	'$logarray[$i][NumeroMedicaoAntigo]',
	'$logarray[$i][NumeroMedicaoNovo]',
	'$logarray[$i][DataHoraMedicaoMedicoesAntiga]',
	'$logarray[$i][DataHoraMedicaoMedicoesNova]',
	'$logarray[$i][ValorMedicaoMedicoesAntigo]',
	'$logarray[$i][ValorMedicaoMedicoesNovo]',
	'$logarray[$i][IDCategoriaProfissionalAntigo]',
	'$logarray[$i][IDCategoriaProfissionalNovo]',
	'$string16',
	'$string17',
	'$logarray[$i][IDTipoCulturaAntigo]',
	'$logarray[$i][IDTipoCulturaNovo]',
	'$string18',
	'$string19',
	'$logarray[$i][IDMedicaoLuminosidadeTemperaturaAntigo]',
	'$logarray[$i][IDMedicaoLuminosidadeTemperaturaNovo]',
	'$string20',
	'$string21',
	'$logarray[$i][ValorMedicaoLuminosidadeAntigo]',
	'$logarray[$i][ValorMedicaoLuminosidadeNovo]',
	'$logarray[$i][ValorMedicaoTemperaturaAntigo]',
	'$logarray[$i][ValorMedicaoTemperaturaNovo]',
	'$logarray[$i][IDSistemaAntigo]',
	'$logarray[$i][IDSistemaNovo]',
	'$logarray[$i][LimiteInferiorTemperaturaSistemaAntigo]',
	'$logarray[$i][LimiteInferiorTemperaturaSistemaNovo]',
	'$logarray[$i][LimiteInferiorLuminosidadeSistemaAntigo]',
	'$logarray[$i][LimiteInferiorLuminosidadeSistemaNovo]',
	'$logarray[$i][LimiteSuperiorTemperaturaSistemaAntigo]',
	'$logarray[$i][LimiteSuperiorTemperaturaSistemaNovo]',
	'$logarray[$i][LimiteSuperiorLuminosidadeSistemaAntigo]',
	'$logarray[$i][LimiteSuperiorLuminosidadeSistemaNovo]'
	)";
	
	mysqli_query($c, $sql3);
	}
}

?>
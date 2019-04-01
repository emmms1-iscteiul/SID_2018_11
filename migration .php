<?php
$DB_SRC_HOST='localhost';
$DB_SRC_USER='root';
$DB_SRC_PASS='';
$oldDB='migracao_de_culturas';
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

$logarray = array();
$result = mysqli_query($c, $sql1);
while ($row = mysqli_fetch_assoc($result)) {
   $logarray[] = $row;
}

// print_r($logarray);

$sql2 = "insert into logs(
	IDLog,
	DataHoraOperação,
	UtilizadorOperação,
	Operação,
	NomeVariávelAntiga,
	NomeVariávelNova,
	NomeCulturaAntiga,
	NomeCulturaNova,
	DesciçãoCulturaAntiga,
	DesciçãoCulturaNova,
	NomeUtilizadorAntigo,
	NomeUtilizadorNovo,
	TipoUtilizadorAntigo,
	TipoUtilizadorNovo,
	CategoriaProfissionalUtilizadorAntiga,
	CategoriaProfissionalUtilizadorNova,
	LimiteInferiorVariáveisMedidasAntigo,
	LimiteInferiorVariáveisMedidasNovo,
	LimiteSuperiorVariáveisMedidasAntigo,
	LimiteSuperiorVariáveisMedidasNovo,
	DataHoraMediçãoMediçõesAntiga,
	DataHoraMediçãoMediçõesNova,
	ValorMediçãoMediçõesAntigo,
	ValorMediçãoMediçõesNovo,
	DataHoraMediçãoMediçõesLuminosidadeTemperaturaAntiga,
	DataHoraMediçãoMediçõesLuminosidadeTemperaturaNova,
	ValorMediçãoLuminosidadeAntigo,
	ValorMediçãoLuminosidadeNovo,
	ValorMediçãoTemperaturaAntigo,
	ValorMediçãoTemperaturaNovo,
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
	'$logarray[IDLog]',
	'$logarray[DataHoraOperação]',
	'$logarray[UtilizadorOperação]',
	'$logarray[Operação]',
	'$logarray[NomeVariávelAntiga]',
	'$logarray[NomeVariávelNova]',
	'$logarray[NomeCulturaAntiga]',
	'$logarray[NomeCulturaNova]',
	'$logarray[DesciçãoCulturaAntiga]',
	'$logarray[DesciçãoCulturaNova]',
	'$logarray[NomeUtilizadorAntigo]',
	'$logarray[NomeUtilizadorNovo]',
	'$logarray[TipoUtilizadorAntigo]',
	'$logarray[TipoUtilizadorNovo]',
	'$logarray[CategoriaProfissionalUtilizadorAntiga]',
	'$logarray[CategoriaProfissionalUtilizadorNova]',
	'$logarray[LimiteInferiorVariáveisMedidasAntigo]',
	'$logarray[LimiteInferiorVariáveisMedidasNovo]',
	'$logarray[LimiteSuperiorVariáveisMedidasAntigo]',
	'$logarray[LimiteSuperiorVariáveisMedidasNovo]',
	'$logarray[DataHoraMediçãoMediçõesAntiga]',
	'$logarray[DataHoraMediçãoMediçõesNova]',
	'$logarray[ValorMediçãoMediçõesAntigo]',
	'$logarray[ValorMediçãoMediçõesNovo]',
	'$logarray[DataHoraMediçãoMediçõesLuminosidadeTemperaturaAntiga]',
	'$logarray[DataHoraMediçãoMediçõesLuminosidadeTemperaturaNova]',
	'$logarray[ValorMediçãoLuminosidadeAntigo]',
	'$logarray[ValorMediçãoLuminosidadeNovo]',
	'$logarray[ValorMediçãoTemperaturaAntigo]',
	'$logarray[ValorMediçãoTemperaturaNovo]',
	'$logarray[LimiteInferiorTemperaturaSistemaAntigo]',
	'$logarray[LimiteInferiorTemperaturaSistemaNovo]',
	'$logarray[LimiteInferiorLuminosidadeSistemaAntigo]',
	'$logarray[LimiteInferiorLuminosidadeSistemaNovo]',
	'$logarray[LimiteSuperiorTemperaturaSistemaAntigo]',
	'$logarray[LimiteSuperiorTemperaturaSistemaNovo]',
	'$logarray[LimiteSuperiorLuminosidadeSistemaAntigo]',
	'$logarray[LimiteSuperiorLuminosidadeSistemaNovo]'
	)";

mysqli_query($c, $sql2);

?>
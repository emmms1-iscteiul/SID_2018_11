<?php
$DB_SRC_HOST='localhost';
$DB_SRC_USER='root';
$DB_SRC_PASS='root';
$oldDB='monotorizacao_de_culturas';
$newDB='monotorizacao_de_culturas_destino';

$con_origin = mysqli_connect($DB_SRC_HOST, $DB_SRC_USER, $DB_SRC_PASS, $oldDB);
$con_destiny = mysqli_connect($DB_SRC_HOST, $DB_SRC_USER, $DB_SRC_PASS, $newDB);

if (mysqli_connect_errno()) {
  exit('Connect failed: '. mysqli_connect_error());
}
else	{ 
	echo 'Connection Sucessfuly !'.'<br>';
}

$sql_log = "SELECT * from log;";
$result_origin = mysqli_query($con_origin, $sql_log);

while ($row = mysqli_fetch_assoc($result_origin)) {
	
	$string1 =  "'".$row['DataHoraOperacao']."'";
	$string2 = "'".$row['UtilizadorOperacao']."'";
	$string3 = "'".$row['Operacao']."'";
	$string4 = "'".$row['NomeVariavelAntigo']."'";
	$string5 = "'".$row['NomeVariavelNovo']."'";
	$string6 = "'".$row['NomeCulturaAntigo']."'";
	$string7 = "'".$row['NomeCulturaNovo']."'";
	$string8 = "'".$row['DescricaoCulturaAntiga']."'";
	$string9 = "'".$row['DescricaoCulturaNova']."'";
	$string10 = "'".$row['EmailUtilizadorAntigo']."'";
	$string11 = "'".$row['EmailUtilizadorNovo']."'";
	$string12 = "'".$row['NomeUtilizadorAntigo']."'";
	$string13 = "'".$row['NomeUtilizadorNovo']."'";
	$string14 = "'".$row['PasswordUtilizadorAntigo']."'";
	$string15 = "'".$row['PasswordUtilizadorNovo']."'";
	$string16 = "'".$row['DataHoraMedicaoMedicoesAntiga']."'";
	$string17 = "'".$row['DataHoraMedicaoMedicoesNova']."'";
	$string18 = "'".$row['NomeCategoriaProfissionalAntigo']."'";
	$string19 = "'".$row['NomeCategoriaProfissionalNovo']."'";
	$string20 = "'".$row['NomeTipoCulturaAntigo']."'";
	$string21 = "'".$row['NomeTipoCulturaNovo']."'";
	$string22 = "'".$row['DataHoraMedicaoMedicoesLuminosidadeTemperaturaAntiga']."'";
	$string23 = "'".$row['DataHoraMedicaoMedicoesLuminosidadeTemperaturaNova']."'";
	
	$insert_sql = "insert into log(
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
	values (
	".$row['IDLog'].",
	".$string1.",
	".$string2.",
	".$string3.",
	".$row['IDVariavelAntigo'].",
	".$row['IDVariavelNovo'].",
	".$string4.",
	".$string5.",
	".$row['IDCulturaAntigo'].",
	".$row['IDCulturaNovo'].",
	".$string6.",
	".$string7.",
	".$string8.",
	".$string9.",
	".$string10.",
	".$string11.",
	".$string12.",
	".$string13.",
	".$string14.",
	".$string15.",
	".$row['IDVariaveisMedidasAntigo'].",
	".$row['IDVariaveisMedidasNovo'].",
	".$row['LimiteInferiorVariaveisMedidasAntigo'].",
	".$row['LimiteInferiorVariaveisMedidasNovo'].",
	".$row['LimiteSuperiorVariaveisMedidasAntigo'].",
	".$row['LimiteSuperiorVariaveisMedidasNovo'].",
	".$row['NumeroMedicaoAntigo'].",
	".$row['NumeroMedicaoNovo'].",
	".$string16.",
	".$string17.",
	".$row['ValorMedicaoMedicoesAntigo'].",
	".$row['ValorMedicaoMedicoesNovo'].",
	".$row['IDCategoriaProfissionalAntigo'].",
	".$row['IDCategoriaProfissionalNovo'].",
	".$string18.",
	".$string19.",
	".$row['IDTipoCulturaAntigo'].",
	".$row['IDTipoCulturaNovo'].",
	".$string20.",
	".$string21.",
	".$row['IDMedicaoLuminosidadeTemperaturaAntigo'].",
	".$row['IDMedicaoLuminosidadeTemperaturaNovo'].",
	".$string22.",
	".$string23.",
	".$row['ValorMedicaoLuminosidadeAntigo'].",
	".$row['ValorMedicaoLuminosidadeNovo'].",
	".$row['ValorMedicaoTemperaturaAntigo'].",
	".$row['ValorMedicaoTemperaturaNovo'].",
	".$row['IDSistemaAntigo'].",
	".$row['IDSistemaNovo'].",
	".$row['LimiteInferiorTemperaturaSistemaAntigo'].",
	".$row['LimiteInferiorTemperaturaSistemaNovo'].",
	".$row['LimiteInferiorLuminosidadeSistemaAntigo'].",
	".$row['LimiteInferiorLuminosidadeSistemaNovo'].",
	".$row['LimiteSuperiorTemperaturaSistemaAntigo'].",
	".$row['LimiteSuperiorTemperaturaSistemaNovo'].",
	".$row['LimiteSuperiorLuminosidadeSistemaAntigo'].",
	".$row['LimiteSuperiorLuminosidadeSistemaNovo']."
	);";
	
	echo $insert_sql;
	
	mysqli_query($con_destiny, $insert_sql);
	
}

mysqli_close($con_origin);
mysqli_close($con_destiny);

?>
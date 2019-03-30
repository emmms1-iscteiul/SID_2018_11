<?php
$DB_SRC_HOST='localhost';
$DB_SRC_USER='root';
$DB_SRC_PASS='';
$oldDB='db_sid';
$newDB='migracao';
		 
$c = new mysqli($DB_SRC_HOST, $DB_SRC_USER, $DB_SRC_PASS);
		 
 // check connection
if (mysqli_connect_errno()) {
  exit('Connect failed: '. mysqli_connect_error());
}
else	{ 
	echo 'Connection Sucessfuly !'.'<br>';
}	
/* This function takes the database connection, an existing database, and the new database and duplicates everything in the new database. */

function copyDatabase($c, $oldDB, $newDB) {
	

    // creates the schema if it does not exist
    $schema = "CREATE SCHEMA IF NOT EXISTS {$newDB};";
    mysqli_query($c, $schema);

    // selects the new schema
    mysqli_select_db($c, $newDB);

    // gets log table from the old schema
    $tables = "SELECT log
               FROM information_schema.tables
               WHERE table_schema = '{$oldDB}'
               AND table_type = 'BASE TABLE'";
    $results = mysqli_query($c, $tables);

    // inserts the data into the table
    $data = "INSERT IGNORE INTO log SELECT * FROM {$oldDB}.log";
    mysqli_query($c, $data);

    // gets all foreign keys for a particular table in the old schema
    $fks = "SELECT constraint_name, column_name, table_name, referenced_table_name, referenced_column_name
			FROM information_schema.key_column_usage
            	WHERE referenced_table_name IS NOT NULL
                AND table_schema = '{$oldDB}'
                AND table_name = 'log'";
 					
    $fkResults = mysqli_query($c, $fks);

    $fkQuery = "ALTER TABLE log}                              
//                ADD CONSTRAINT {$fkRow[0]}
//                FOREIGN KEY ({$fkRow[1]}) REFERENCES {$newDB}.{$fkRow[3]}({$fkRow[1]})
                ON UPDATE CASCADE
                ON DELETE CASCADE;";
    mysqli_query($c, $fkQuery);

    // gets all views in the old schema
    $views = "SHOW FULL TABLES IN {$oldDB} WHERE table_type LIKE 'VIEW'";                
    $results = mysqli_query($c, $views);

    $view = "SHOW CREATE VIEW {$oldDB}.log";
    $viewResults = mysqli_query($c, $view);
    $viewRow = mysqli_fetch_array($viewResults);
    mysqli_query($c, preg_replace("/CREATE(.*?)VIEW/", "CREATE VIEW", str_replace($oldDB, $newDB, $viewRow[1])));

}

$eraseDB ='DROP DATABASE migracao';
if(mysqli_query($c, $eraseDB)){ 
    echo "Database was deleted successfully !!!".'<br>'; 
}  
else{ 
    echo "ERROR: Database was not deleted :( "  
                                   . mysqli_error($link); 
} 
$clone  = copyDatabase($c, $oldDB, $newDB);
echo 'Migration is Done !';
$c->close();

?>
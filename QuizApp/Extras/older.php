<?php

define('SERVER_NAME','localhost');
define('SERVER_USER','id7365911_scheduler');
define('SERVER_PASSWORD', 'helloworld');
define('DBNAME', 'id7365911_quizscheduler');
$mysqli = new mysqli(SERVER_NAME,SERVER_USER,SERVER_PASSWORD,DBNAME) or die($mysqli->connect_error);
// $date = '2018-10-06';
$date = $_POST['date'];
$query = "SELECT * FROM schedule WHERE date='$date'";
$result = $mysqli->query($query);
if($result->num_rows > 0){
$return = [];
for($i = 1 ; $i < $result->num_rows ; $i++){
    // $return .= $temp;
    $temp = $result->fetch_assoc();
    $return[$i] = json_encode($temp);
}
$temp = $result->fetch_assoc();
$return[$i] = json_encode($temp);
$final = json_encode($return);
echo $final;
//  	$temp = json_decode($final,true);
//  	var_dump($temp);
//  	$temp = $temp["1"];
//  	var_dump($temp);
//  	$temp = json_decode($temp,true);
//  	var_dump($temp);
//  	$temp = json_decode($temp["labs"],true);
//  	var_dump($temp);

} else {
 $return = json_encode("{}");
echo $return;	
}

?>

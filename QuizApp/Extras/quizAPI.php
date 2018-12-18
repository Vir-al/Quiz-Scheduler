<?php

define('SERVER_NAME','localhost');
	define('SERVER_USER','id7365911_scheduler');
	define('SERVER_PASSWORD', 'helloworld');
	define('DBNAME', 'id7365911_quizscheduler');
	$mysqli = new mysqli(SERVER_NAME,SERVER_USER,SERVER_PASSWORD,DBNAME) or die($mysqli->connect_error);

$response = array();

$date = $_POST['date'];
$query = "SELECT * FROM schedule WHERE date='$date'";
 $result = $mysqli->query($query);
 if($result->num_rows > 0){
 	$response["schedules"] = array();
 	while($current = $result->fetch_array()){
 		$schedule = array();
 		$schedule["sid"] = $current["sid"];
 		$schedule["date"] = $current["date"];
 		$schedule["time"] = $current["time"];
 		$schedule["labs"] = $current["labs"];
 		$schedule["subject"] = $current["subject"];
 		$schedule["fid"] = $current["fid"];
 		$schedule["title"] = $current["title"];
 		$schedule["description"] = $current["description"];
 		array_push($response["schedules"], $schedule);
 	}

	// var_dump($response);
 	echo json_encode($response);

//  	$temp = json_decode($response,true);
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
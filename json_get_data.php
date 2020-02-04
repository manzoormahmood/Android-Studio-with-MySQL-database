


<?php


$user="*****";
$password="*****";
$host ="*****";
$db="*****";

$sql="SELECT * FROM product_info;";

$con=mysqli_connect($host,$user,$password,$db);

$result=mysqli_query($con,$sql);

$response=array();

while($row = mysqli_fetch_array($result))
{
	
	array_push($response,array("name"=>$row[0],"age"=>$row[1],"height"=>$row[2],"weight"=>$row[3],"data"=>$row[4]));
	
}

echo json_encode(array("server_response"=>$response));
mysqli_close($con);


?>



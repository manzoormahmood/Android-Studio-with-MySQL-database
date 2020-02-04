<?php


$user="*****";
$password="*****";
$host ="*****";

$db="*****";


$con=mysqli_connect($host,$user,$password,$db);

if (!$con)
{
die("Error in connection" . mysqli_connect_error());
}

else
{
echo "<h3>Database connection Success....</h3>";
}



?>
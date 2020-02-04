
<?php

require "init.php";

$name = $_POST["name"];
$age = $_POST["age"];
$height = $_POST["height"];
$weight = $_POST["weight"];
$data = $_POST["data"];


$sql="INSERT INTO product_info(name,age,height,weight,data) VALUES('$name','$age','$height','$weight','$data')";


//$sql="INSERT INTO product_info(name,age,height,weight) VALUES('$name','$age','$height','$weight')";

if(mysqli_query($con,$sql))
{

echo "<h3><h3>One row inserted....</h3>";
}
else
{

echo "error in insertion..".mysqli_error($con);
}

?>
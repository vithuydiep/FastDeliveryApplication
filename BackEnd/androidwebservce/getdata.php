<?php
	
	require "dbCon.php";
	$type =$_POST['type'];
	//$type="getData";
	switch ($type) {
		case "getData":
			$query = "SELECT * FROM user ";
			$data = mysqli_query($connect, $query);
			$arrayUser = array();
			while ($row = mysqli_fetch_assoc($data)) {
			array_push($arrayUser, new User($row['id'], $row['name'], $row['gender'], $row['phone'], $row['address'], $row['email'], $row['idnumber'], $row['username'], $row['password'], $row['activationcode'], $row['resetpasswordcode'], $row['state'], $row['driverlicensenumber'], $row['typeofuser']));}
			echo json_encode($arrayUser);
			break;
		case "login":
			$username =$_POST['username'];
			$password=$_POST['password'];
			$query = "Select * from user where username = '$username' and password ='$password'";
			$data = mysqli_query($connect, $query);
			if(mysqli_num_rows($data) != 0){
				while ($row = mysqli_fetch_assoc($data)) {
					$user = new User($row['id'], $row['name'], $row['gender'], $row['phone'], $row['address'], $row['email'], $row['idnumber'], $row['username'], $row['password'], $row['activationcode'], $row['resetpasswordcode'], $row['state'], $row['driverlicensenumber'], $row['typeofuser']);}
				echo json_encode($user);
			}else{
				echo "Error";
			};

			break;
		case "register":
			$phone =$_POST['phone'];
			$username =$_POST['username'];
			$password=$_POST['password'];
			$activationcode=$_POST['activationcode'];
			$state=$_POST['state'];
			$typeofuser=$_POST['typeofuser'];
			$query = "Insert into user(phone,username,password,activationcode,state,typeofuser) values('$phone','$username','$password','$activationcode','$state','$typeofuser')";
			if(mysqli_query($connect,$query))
			{
				echo "Registration Successfully";
			}else{
				echo "Something went wrong";
			}
			break;
		case 'forgotpassword':
			$phone=$_POST['phone'];
			$newpassword=$_POST['newpassword'];
			$resetpassword=$_POST['resetpassword'];
			$query = "Update user set password='$newpassword', resetpasswordcode='$resetpassword' where phone='$phone' ";
			$data = mysqli_query($connect, $query);
			if(mysqli_query($connect,$query))
			{
				echo "Change password Successfully";
			}else{
				echo "Something went wrong";
			}
			break;
		default:
			echo "Lỗi";
			break;
	}


?>
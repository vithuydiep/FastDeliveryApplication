<?php
//$connect = mysqli_connect("mysql-27536-0.cloudclusters.net","vidiep","123456789","delivery");
$connect = mysqli_connect("localhost","root","","delivery");
mysqli_query($connect, "SET NAMES 'utf8");
class User{
		function __construct($id,$name, $gender, $phone, $address, $email, $idnumber, $username, $password, $activationcode, $resetpasswordcode, $state, $driverlicensenumber, $typeofuser){
			$this->ID = $id;
			$this->Name=$name;
			$this->Gender=$gender;
			$this->Phone=$phone;
			$this->Address=$address;
			$this->Email=$email;
			$this->IdNumber=$idnumber;
			$this->Username=$username;
			$this->Password=$password;
			$this->ActivationCode=$activationcode;
			$this->ResetPasswordCode=$resetpasswordcode;
			$this->State=$state;
			$this->DriverLicenseNumber=$driverlicensenumber;
			$this->TypeUser=$typeofuser;
		}
	}
?>
HƯỚNG DẪN THỰC THI CODE
-----------------------
Bước 1: Tải Xampp [https://www.apachefriends.org/index.html]
Bước 2: Sau khi tải thành công mở Xampp và start Apache và MySQL
Bước 3: Vào [http://localhost/phpmyadmin] tiến hành import file [https://github.com/vithuydiep/FastDeliveryApplication/blob/main/Database/delivery.sql]
Bước 4: Tải thư mục androidwebservice ở [https://github.com/vithuydiep/FastDeliveryApplication/tree/main/BackEnd/androidwebservce] và đặt vào [C:\xampp\htdocs]
Bước 5: Mở TVKDelivery ở Android Studio 
Bước 6: Thay đổi baseUrl bằng IPv4 ở src\main\java\com\example\TVK\Model\User\User.java
Bước 7: Chạy chương trình 
(Nếu muốn sử dụng chức năng auto-fill otp thì cần sử dụng trên máy thật, cách cài đặt trước khi thực thi:
+ Dùng cáp nối điện thoại (Android) vào máy tính, bật chế độ debug và chuyển tập tin cho điện thoại
+ Cả máy tính và điện thoại phải dùng chung wifi, hoặc có thể sử dụng mobile hostspot của máy tính phát wifi để điện thoại sử dụng hoặc ngược lại)
+ Tiến hành sử dụng


CÁC CHỨC NĂNG THỰC HIỆN ĐƯỢC
+ Đăng nhập:
+ Đăng ký
+ Quên mật khẩu


CÁC THƯ VIỆN SỬ DỤNG

com.google.gms:google-services:4.3.3: Plugin của google 

com.google.firebase:firebase-core:11.8.0 : Thư viện của Firebase để dùng các services của Firebase
com.google.firebase:firebase-auth:20.0.4

com.android.volley:volley:1.2.0 : Thư viện Volley để gọi API

com.google.code.gson:gson:2.8.6: Thư viện Gson để sử dụng kiểu dữ liệu Gson
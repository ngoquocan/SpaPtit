<!DOCTYPE HTML>
<%@page import="com.example.demo.model.NhanVien"%>
<%@page contentType="text/html; charset=UTF-8"%>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Trang chủ</title>
   </head>
   <% 
      	NhanVien nv = (NhanVien) session.getAttribute("NhanVien");
   %>
   <body>
      <h3>Welcome, <%out.print(nv.getUsername());%></h3>
      <a href="logout">Đăng xuất</a>
      <a href="chondichvu">Đặt lịch</a>
   </body>  
</html>
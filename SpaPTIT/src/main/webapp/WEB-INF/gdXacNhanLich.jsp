<!DOCTYPE HTML>
<%@page import="com.example.demo.model.NhanVien"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Trang chủ</title>
   </head>
   <% 
      	NhanVien nv = (NhanVien) session.getAttribute("NhanVien");
   %>
   <body>
      <a href="logout">Đăng xuất</a>
      <h3>Xác nhận đặt lịch</h3>
      	<h4>Tên khách hàng: ${lichhen.khachhang.ten }</h4>
      	<h4>SĐT: ${lichhen.khachhang.sdt }</h4>
		<h4>Ngày: ${lichhen.thoigian.ngay }</h4>
		<h4>Giờ: ${lichhen.thoigian.gio.gio }</h4>
      	<h4>Vị trí: ${lichhen.thoigian.slot.ten }</h4>
      	<h4>Dịch vụ: ${lichhen.dichvu.ten}</h4>
      	<h4>Giá tiền: ${lichhen.dichvu.gia}</h4>
      	<br>
      	<a href="luu">Đặt lịch</a>
   </body>  
</html>
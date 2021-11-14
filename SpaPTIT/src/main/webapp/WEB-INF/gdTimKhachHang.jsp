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
      <h3>Welcome,</h3>
      <a href="logout">Đăng xuất</a>
      <form action="timkhachhang" method="get">
      	<h4>Nhập thông tin: </h4>
      	<input type="text" name = "data">
      	<input type= "submit" value="Tìm kiếm">
      </form>
      <table border="1">
      	<tr>
      		<th>TT</th>
      		<th>Tên khách hàng</th>
      		<th>Số điện thoại</th>
      		<th>Email</th>
      	</tr>
      	<c:forEach items="${listKH}" var="khachhang" varStatus="loop">
      	<tr>
      		<td>${loop.index + 1}</td>
      		<td>${khachhang.ten }</td>
      		<td>${khachhang.sdt }</td>
      		<td>${khachhang.email }</td>
      		<td>
      			<a href="xacnhanlich?idKH=${loop.index }">Chọn</a>
      		</td>
      	</tr>
      	</c:forEach>
      </table>
   </body>  
</html>
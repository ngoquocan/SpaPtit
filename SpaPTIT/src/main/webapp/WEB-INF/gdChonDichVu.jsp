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
      <form action="chonslot" method="get">
      	<h4>Chọn ngày : </h4>
      	<input type="date" name = "ngay">
      	<br>
      	<h4>Chọn khung giờ :</h4>
      	<select name = "gio">
      		<c:forEach items="${listGio }" var="khunggio" varStatus="loop">
			<option value="${loop.index}">${khunggio.gio }</option>
			</c:forEach>
		</select>
		<br>
		<h4>Chọn dịch vụ :</h4>
      	<select name= "dichvu">
      		<c:forEach items="${listDV }" var="dv" varStatus="loop">
			<option value="${loop.index}" >${dv.ten }</option>
			</c:forEach>
		</select>
      	<br>
      	<input type="submit" value="Tiếp tục">
      </form> 
   </body>  
</html>
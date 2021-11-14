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
      
      <table border="1">
      	<tr>
      		<th>TT</th>
      		<th>Tên ghế trống</th>
      	</tr>
      	<c:forEach items="${listSlot}" var="slot" varStatus="loop">
      	<tr>
      		<td>${loop.index + 1}</td>
      		<td>${slot.ten }</td>
      		<td>
      			<a href="timkhachhang?idSlot=${loop.index }">Chọn</a>
      		</td>
      	</tr>
      	</c:forEach>
      </table>
   </body>  
</html>
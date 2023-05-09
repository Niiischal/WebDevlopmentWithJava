<%@page import="controller.statemanagement.SessionManagement"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Including the core tag library-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--Including the formatting tag library-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!--Including the SQL tag library-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%!SessionManagement mySession = new SessionManagement();%>
<%
String mainPath = request.getContextPath();
%>

<%
String user = (String) session.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/cart.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/nav.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	<%
	String log = "LogIn"; // default value
	if (mySession.checkUser(user)) {
		log = "LogOut"; // override default value
	}
	%>
	<div class="nav">
		<input type="checkbox" id="nav-check" />
		<div class="nav-header">
			<div class="nav-title">Kapada Bhandar</div>
		</div>
		<div class="nav-btn">
			<label for="nav-check"> <span></span> <span></span> <span></span>
			</label>
		</div>
		<div class="nav-links">
			<a href="../index.jsp">Home</a> <a href="search.jsp">Search</a>
			<%
			if (mySession.checkUser(user)) {
			%>
			<a href="">View Cart</a> <a href="user.jsp">Profile</a>
			<%
			}
			%>
			<form
				action=" <%if (!mySession.checkUser(user)) {
	out.print(mainPath);%>/login.jsp<%} else {
							out.print(mainPath);%>/LogoutServlet<%}%>"
				method="post">
				<input type="submit" value="<%=log%>" class="btnn">
			</form>
		</div>
	</div>
	<div class="container">
		<h1>Shopping Cart</h1>
		<div class="cartdetails">
			<sql:setDataSource var="dbconnection" driver="com.mysql.jdbc.Driver"
				url="jdbc:mysql://localhost:3306/coursework" user="root" password="" />
			<sql:query var="orders" dataSource="${dbconnection}">
          		SELECT orderID, productID,  username , productName, productPrice FROM orders WHERE username = "${user}";
          	</sql:query>
			<table class="cart-display-table">
				<thead>
					<tr>
						<th>order id</th>
						<th>product id</th>
						<th>username</th>
						<th>product name</th>
						<th>price</th>
					</tr>
				</thead>
				<c:forEach var="order" items="${orders.rows}">
					<tr>
						<td><c:out value="${order.orderID}"></c:out></td>
						<td><c:out value="${order.productID}"></c:out></td>
						<td><c:out value="${order.username}"></c:out></td>
						<td><c:out value="${order.productName}"></c:out></td>
						<td><c:out value="${order.productPrice}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
<%@page import="controller.statemanagement.SessionManagement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/home.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/nav.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/footer.css">
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
		<input type="checkbox" id="nav-check">
		<div class="nav-header">
			<div class="nav-title">Kapada Bhandar</div>
		</div>
		<div class="nav-btn">
			<label for="nav-check"> <span></span> <span></span> <span></span>
			</label>
		</div>

		<div class="nav-links">
			<a href="index.jsp" target="_blank">Home</a> <a
				href="pages/search.jsp">Search</a>
			<%
			if (mySession.checkUser(user)) {
			%>
			<a href="pages/cart.jsp" target="_blank">View Cart</a> <a
				href="pages/user.jsp" target="_blank">Profile</a>
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
	<main>
		<div class="container">
			<div class="image">
				<div class="text">
					<h1>Welcome to Kapada Bhandar</h1>
					<p>Get the clothes you want</p>
				</div>
			</div>
			<h1>Products</h1>
			<div class="box-container">
				<!-- Database Connection using taglib directive -->
				<sql:setDataSource var="dbconnection" driver="com.mysql.jdbc.Driver"
					url="jdbc:mysql://localhost:3306/coursework" user="root"
					password="" />
				<sql:query var="products" dataSource="${dbconnection}">
          	SELECT  productID, productName, productDescriptioon, productPrice, productCategory, productImage FROM product;
          </sql:query>
				<c:forEach var="product" items="${products.rows}">
					<div class="box">
						<img src="${product.productImage}" alt="Product Image">
						<h2>${product.productName}</h2>
						<p>Price: ${product.productPrice}</p>
						<form action="<%=request.getContextPath()%>/AddToCart"
							method="post">
							<input type="submit" value="Add to Cart" class="btn"> <input
								type="hidden" name="username" value="${user}"> <input
								type="hidden" name="productID" value="${product.productID}">
							<input type="hidden" name="productName"
								value="${product.productName}"> <input type="hidden"
								name="productPrice" value="${product.productPrice}">
						</form>
					</div>
				</c:forEach>
			</div>
		</div>
	</main>
	<div>
		<section class="footer">
			<div class="social">
				<a href="#"><i class="fab fa-instagram"></i></a> <a href="#"><i
					class="fab fa-snapchat"></i></a> <a href="#"><i
					class="fab fa-twitter"></i></a> <a href="#"><i
					class="fab fa-facebook-f"></i></a>
			</div>
			<p class="copyright">Kapada Bhandar @ 2023</p>
		</section>
	</div>

</body>
</html>
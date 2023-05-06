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
 
 
<%! SessionManagement mySession = new SessionManagement(); %>
<% String mainPath = request.getContextPath(); %>
<% String user = (String) session.getAttribute("user");%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/search.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/nav.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/footer.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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
		  <div class="nav-title">
			Kapada Bhandar
		  </div>
		</div>
		<div class="nav-btn">
		  <label for="nav-check">
			<span></span>
			<span></span>
			<span></span>
		  </label>
		</div>
		
		<div class="nav-links">
		  <a href="../index.jsp" target="_blank">Home</a>
		  <a href="">Search</a>
		  <% if (mySession.checkUser(user)) { %>
		  <a href="">View Cart</a>
		  <a href="user.jsp" target="_blank">Profile</a>
		  <% } %>
		  <form action=" <%if(!mySession.checkUser(user)){
			out.print(mainPath);%>/login.jsp<%
		   } 
		else {
			out.print(mainPath);%>/LogoutServlet<%
		   }%>"	
		method="post">
		<input type="submit" value="<%= log %>" class="btnn">
		</form>		  	  
		</div>
	  </div>
	  <div class="container">
		<div class="options">
				<form class="searchfield" action = "search.jsp">
					<input type= "text" name= "productName" placeholder="Enter the product name"/>
					<input type="submit" value="Search" >
				</form>
				<form class="searches" action = "menSearch.jsp">
					<input type="submit" value="For men" >
				</form>
				<form class="searches" action = "womenSearch.jsp">
					<input type="submit" value="For women" >
				</form>
				<form class="searches" action = "childrenSearch.jsp">
					<input type="submit" value="For children" >
				</form>
				<form class="searches" action = "priceSort.jsp">
					<input type="submit" value="Sort by Price" >
				</form>
		</div>
		<div class="box-container">
			<% String productName = request.getParameter("productName");%>
			<!-- Database Connection using taglib directive -->
				  <sql:setDataSource var="dbconnection" driver="com.mysql.jdbc.Driver"
					  url="jdbc:mysql://localhost:3306/coursework" user="root"
					  password="" />
				  <sql:query var="products" dataSource="${dbconnection}">
				SELECT  * from product WHERE productCategory="Child";
			</sql:query>
			<c:forEach var="product" items="${products.rows}">
				<div class="box">
					<img src="../${product.productImage}" alt="Product Image">
					<h2>${product.productName}</h2>
					<p>Price: ${product.productPrice}</p>
          	<form action = "<%= request.getContextPath() %>/AddToCart" method ="post">
           	 	<input type="submit" value="Add to Cart" class="btn">
           	 	<input type="hidden" name="productID" value="${product.productID}">
           	 	<input type="hidden" name="productName" value="${product.productName}">
           	 	<input type="hidden" name="productPrice" value="${product.productPrice}">
          	</form>
		  </div>
			</c:forEach>		
		</div>
		</div>
		  <div>
	  <section class="footer">
      <div class="social">
        <a href="#"><i class="fab fa-instagram"></i></a>
        <a href="#"><i class="fab fa-snapchat"></i></a>
        <a href="#"><i class="fab fa-twitter"></i></a>
        <a href="#"><i class="fab fa-facebook-f"></i></a>
      </div>
      <p class="copyright">Kapada Bhandar @ 2023</p>
    </section>
	  </div>
</body>
</html>
	 
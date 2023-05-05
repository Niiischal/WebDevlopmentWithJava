<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/nav.css" >
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css" >
</head>
<body>
    <main>
    <div class="container">
	<div class="loginform">
   	<form action="<%= request.getContextPath() %>/LoginServlet" method="post">
      	<h3 class="title">Sign In</h3>
      	<input type="text" class="box" name="uName" placeholder="enter the username">
      	<input type="password" class="box" name="pwd" placeholder="enter the password">
      	<input type="submit" value="Sign In" class="btn">
      	<a href = "<%= request.getContextPath() %>/pages/registration.jsp" > <h2> Don't have an account? </h2> </a>
   </form>
   </div>
   </div>
   </main>

</body>
</html>
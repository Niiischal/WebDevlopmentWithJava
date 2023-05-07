<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/nav.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login.css">
</head>
<body>
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
			<a href="index.jsp"> Home</a>
		</div>
	</div>
	<%
	String errorMessage = (String) request.getAttribute("errorMessage");
	%>
	<%
	if (errorMessage != null) {
	%>
	<div id="error-message"><%=errorMessage%></div>
	<%
	}
	%>
	<%
	String registerMessage = (String) request.getAttribute("registerMessage");
	%>
	<%
	if (registerMessage != null) {
	%>
	<div id="error-message"><%=registerMessage%></div>
	<%
	}
	%>
	<main>
		<div class="container">
			<div class="loginform">
				<form action="<%=request.getContextPath()%>/LoginServlet"
					method="post">
					<h3 class="title">Sign In</h3>
					<input type="text" class="box" name="uName"
						placeholder="enter the username"> <input type="password"
						class="box" name="pwd" placeholder="enter the password"> <input
						type="submit" value="Sign In" class="btn"> <a
						href="<%=request.getContextPath()%>/pages/registration.jsp">
						<h2>Don't have an account?</h2>
					</a>
				</form>
			</div>
		</div>
	</main>

</body>
</html>
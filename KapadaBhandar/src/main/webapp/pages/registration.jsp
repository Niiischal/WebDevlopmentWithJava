<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/register.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/nav.css">
</head>
<body>
	<header>
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
				<a href="../index.jsp" target="_blank">Home</a> 
			</div>
		</div>
	</header>
	<main>
		<div class="container">
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
			<div class="registrationform">
				<form action="<%=request.getContextPath()%>/Register"
					method="post" enctype="multipart/form-data">
					<h3 class="title">Sign Up</h3>
					<input type="text" class="box" name="fname"
						placeholder="enter the first name"> <input type="text"
						class="box" name="lname" placeholder="enter the last name">
					<input type="text" class="box" name="address"
						placeholder="enter your address"> <input type="text"
						class="box" name="e_mail" placeholder="enter the email address">
					<input type="text" class="box" name="uname"
						placeholder="enter the username"> <input type="password"
						class="box" name="pwd" placeholder="enter your password">
					<input type="file" class="box" name="ProfilePhoto" accept="image/*">
					<input type="submit" value="Sign Up" class="btn"> <a
						href="<%=request.getContextPath()%>/login.jsp">
						<h2>Already have an account?</h2>
					</a>
				</form>
			</div>
		</div>
	</main>
</body>
</html>
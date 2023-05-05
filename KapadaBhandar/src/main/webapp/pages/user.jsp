<%@page import="controller.statemanagement.SessionManagement"%>
<%@page import="model.Encryption"%>
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

<% 
  String user = (String) session.getAttribute("user");
  String password = (String) session.getAttribute("password");
  
%>
<c:set var="encPassword" value="<%= Encryption.encrypt(password) %>" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/nav.css"/>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/user.css"/>
</head>
<body>
<sql:setDataSource var="dbconnection" driver="com.mysql.cj.jdbc.Driver"
					url="jdbc:mysql://localhost:3306/coursework" user="root"
					password="" />
			<sql:query var="users" dataSource="${dbconnection}">
          	SELECT userID FROM registration WHERE username= '${registration}' AND password = '${encPassword}';
          </sql:query>
<c:if test="${not empty users}">
  <c:set var="firstRow" value="${users.rows[0]}" /> 
</c:if>
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
      <label for="nav-check">
        <span></span>
        <span></span>
        <span></span>
      </label>
    </div>
    <div class="nav-links">
      <a href="../index.jsp">Home</a>
      <a href="">Search</a>
 	<% if (mySession.checkUser(user)) { %>
 <a href="">View Cart</a>
 <a href="pages/user.jsp">Profile</a>
<% } %>
      <a href="">About Us</a>
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
	<sql:setDataSource var="dbconnection" driver="com.mysql.cj.jdbc.Driver"
					url="jdbc:mysql://localhost:3306/coursework" user="root"
					password="" />
			<sql:query var="users" dataSource="${dbconnection}">
          	SELECT userID FROM registration WHERE username = '${registration}' AND password = '${encPassword}';
          </sql:query>
	<c:if test="${not empty users}">
  	<c:set var="firstRow" value="${users.rows[0]}" />
	</c:if>
	
	<sql:query var="user" dataSource = "${dbconnection}">
	SELECT firstName, lastName, address, email, username, password, rePassword, image FROM registration WHERE userID = ${firstRow.userID}
	</sql:query>
	<c:if test="${not empty user}">
  	<c:set var="userData" value="${user.rows[0]}" />
  	   
	</c:if>

    <div class="userprofileForm">
        <form action="" method="post" enctype="multipart/form-data">
            <input type="hidden" name="userID" value="${firstRow.userID}" >
            <input type="hidden" name="oldImage" value="${userData.image}" >
            <input class="box" placeholder="${userData.firstName}" name="firstName"/>
            <input class="box" placeholder="${userData.lastName}" name="lastName"> 
            <input class="box" placeholder="${userData.address}" name="address">
            <input class="box" placeholder="${userData.email}" name="email">             
            <input class="box" placeholder="${userData.username}" name="username"> 
            <input class="box" placeholder="Password" name="password">
            <input type="file" class="box" accept="image/*" name="image">
            <input type="submit" value="Update Profile" class="btn" />         
        </form>
    </div>
    <div class="image">
      <img src="../${userData.image}" alt="User image"/>
    </div>
    </div>
   </body>
   </html>
  
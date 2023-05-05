<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Including the core tag library-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--Including the formatting tag library-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!--Including the SQL tag library-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/nav.css">
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin.css">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
    <header>
        <div class="nav">
          <input type="checkbox" id="nav-check">
          <div class="nav-header">
            <div class="nav-title">
              ADMIN PANEL
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
            <a href="../LogoutServlet">Logout</a>
          </div>
        </div>
    </header>
    <main>
<div class="container">
<div class="admin-product-form-container">
   <form action="<%= request.getContextPath() %>/AddProducts" method="post" enctype="multipart/form-data">
      <h3 class="title">Add the product</h3>
      <input type="text" class="box" name="productName" placeholder="enter the product name">
      <input type="text" class="box" name="productDescriptioon" placeholder="enter the product description">
      <input type="text" class="box" name="productPrice" placeholder="enter the product price">
      <input type="text" class="box" name="productCategory" placeholder="enter the catogery id">     
      <input type="file" class="box" name="productImage"  accept="image/*">
      <input type="submit" value="Add product" class="btn">
   </form>
</div>
    <div class="product-display">
        <!-- Database Connection using taglib directive -->
        <sql:setDataSource var="dbconnection" driver="com.mysql.cj.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/coursework" user="root" password="" />
        <sql:query var="products" dataSource="${dbconnection}">
    		SELECT productID, productName, productDescriptioon, productPrice, productCategory, productImage FROM product;
    	</sql:query>
       <table class="product-display-table">
          <thead>
          <tr>
             <th>product id</th>
             <th>product name</th>
             <th>product description</th>
             <th>product price</th>
             <th>product category</th>
             <th>action</th>
          </tr>
          </thead>
          <c:forEach var="product" items="${products.rows}">          
          <tr>
            <td><c:out value="${product.productID}" /></td>
            <td><c:out value="${product.productName}" /></td>
            <td><c:out value="${product.productDescriptioon}" /></td>
            <td><c:out value="${product.productPrice}" /></td>
            <td><c:out value="${product.productCategory}" /></td>
             <td>
             	   <form action="<%= request.getContextPath() %>/pages/adminupdate.jsp">
				      <input type="hidden" name="productID" value="${product.productID}" >
				      <input type="hidden" name="productName" value="${product.productName}" >
				      <input type="hidden" name="productDescriptioon" value="${product.productDescriptioon}" >
				      <input type="hidden" name="productPrice" value="${product.productPrice}">
				      <input type="hidden" name="productCategory" value="${product.productCategory}">
				      <input type="hidden" name="productImage" value="${product.productImage}">				      
				      <input type="submit" class="btn" value="Edit">       
				   </form>
					<form action="<%=request.getContextPath()%>/DeleteProduct" method="post">
					<input type="hidden" name="productID" value="${product.productID}" >
						<input type="submit" value="Delete" class="btn">
					</form>
             </td>
          </tr>
          </c:forEach>
       </table>
    </div>
 </div>                
</main>
</body>
</html>
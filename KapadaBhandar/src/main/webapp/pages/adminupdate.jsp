<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin.css">
   <link rel="stylesheet" href="<%= request.getContextPath() %>/nav.css">
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
          <form action= "<%=request.getContextPath()%>/LogoutServlet" method = "post" style = "width:100%;">
            <input type="submit" value="Logout" style = "width:100%;"/>
          </form>
          </div>
        </div>
    </header>
    <main>
<div class="container1">
<div class="updateform">
   <form action="<%=request.getContextPath()%>/UpdateProduct" method="post" enctype="multipart/form-data">
      <h3 class="title">update the product</h3>
      <input type="text" class="box" name="productName" placeholder=<%= request.getParameter("productName")%> >
      <input type="text" class="box" name="productDescriptioon" placeholder=<%= request.getParameter("productDescriptioon")%> >
      <input type="text" class="box" name="productPrice" placeholder=<%= request.getParameter("productPrice")%> >
      <input type="text" class="box" name="productCategory" placeholder=<%= request.getParameter("productCategory")%> >     
      <input type="file" class="box" name="productImage"  accept="image/*">
      <input type="submit" value="Update Product" class="btn">
      <input type="hidden" name="oldImage" value="<%= request.getParameter("productImage") %>">
      <input type="hidden" name="productID" value="<%= request.getParameter("productID") %>">
   </form>
</div>
   <div class="image">
    	      <img src="../<%= request.getParameter("productImage") %>" style= "height:100%; width:100%;" >
   </div>
</div>
</main>
</body>
</html>
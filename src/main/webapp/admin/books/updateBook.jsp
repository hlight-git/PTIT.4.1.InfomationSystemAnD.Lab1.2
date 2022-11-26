<%@ page import="lab1.e2_bookmng.model.Book" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/2/2022
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Book editingBook = (Book) request.getAttribute("editingBook");%>
<html>
<head>
    <title>Book management</title>
</head>
<body>
<h1><%=(editingBook == null) ? "Add" : "Edit"%> book</h1>
<form action="<%=request.getContextPath()%>/admin/books" method="post">
    <div>
        <label>ID:</label>
        <input
                name="id"
                value="<%=(editingBook != null)?
                    ((Book)request.getAttribute("editingBook")).getId():""%>"
                readonly>
    </div>
    <div>
        <label>Title:</label>
        <input
                name="title"
                value="<%=(editingBook != null)?
                    ((Book)request.getAttribute("editingBook")).getTitle():""%>"
        >
    </div>
    <div>
        <label>Price:</label>
        <input
                name="price"
                value="<%=(editingBook != null)?
                    (String.format(".2%f", ((Book)request.getAttribute("editingBook")).getPrice())):""%>"
        >
    </div>
    <button>Add</button>
</form>
</body>
</html>

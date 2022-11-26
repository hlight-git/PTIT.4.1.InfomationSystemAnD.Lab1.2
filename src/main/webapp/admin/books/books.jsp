<%@ page import="lab1.e2_bookmng.model.Book" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/2/2022
  Time: 4:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<div>
    <h1>Books list</h1>
    <form action="<%=request.getContextPath()%>/admin/books" method="get">
        <label>Search:</label>
        <input name="search">
        <button>Search</button>
    </form>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%for (Book b: (List <Book>)request.getAttribute("books")) {%>
                <tr>
                    <td><%=b.getId()%></td>
                    <td><%=b.getTitle()%></td>
                    <td><%=String.format("%.2f", b.getPrice())%></td>
                    <td>
                        <form action="<%=request.getContextPath()%>/admin/books" method="get">
                            <button name="editId" value="<%=b.getId()%>">Edit</button>
                        </form>
                        <form action="<%=request.getContextPath()%>/admin/books" method="post">
                            <button name="deleteId" value="<%=b.getId()%>">Delete</button>
                        </form>
                    </td>
                </tr>
            <%}%>
        </tbody>
    </table>
</div>
<div>
    <a href="books/updateBook.jsp">Add book</a>
</div>
</body>
</html>

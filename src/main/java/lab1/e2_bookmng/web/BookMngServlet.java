package lab1.e2_bookmng.web;

import lab1.e2_bookmng.dao.BookDAO;
import lab1.e2_bookmng.model.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/books")
public class BookMngServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDAO bookDao;

    public void init(){
        bookDao = new BookDAO();
    }
    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            bookDao.delete(Integer.parseInt(request.getParameter("deleteId")));
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        showBooksList(request, response);
    }
    private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            bookDao.insert(
                    new Book(
                            request.getParameter("title"),
                            Float.parseFloat(request.getParameter("price"))
                    )
            );
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        showBooksList(request, response);
    }
    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        float price = Float.parseFloat(request.getParameter("price"));
        try {
            bookDao.update(
                    new Book(
                            id,
                            title,
                            price
                    )
            );
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        showBooksList(request, response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("editId"));
        request.setAttribute("editingBook", bookDao.selectByID(id));
        request.getRequestDispatcher("books/updateBook.jsp").forward(request, response);
        request.removeAttribute("editingBook");
    }
    private void showBooksList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        if (search != null){
            String conditions =
                    "id LIKE '%%s%' OR title LIKE '%%s%' OR price LIKE '%%s%';"
                            .replace("%s", search);
            request.setAttribute("books", bookDao.selectWhere(conditions));
        }
        else {
            request.setAttribute("books", bookDao.selectAll());
        }
        request.getRequestDispatcher("books/books.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("deleteId") != null) {
            deleteBook(request, response);
        } else if (request.getParameter("id").equals("")) {
            addBook(request, response);
        } else {
            updateBook(request, response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // read - select
        if (request.getParameter("editId") != null){
            showEditForm(request, response);
        } else {
            showBooksList(request, response);
        }
    }
}

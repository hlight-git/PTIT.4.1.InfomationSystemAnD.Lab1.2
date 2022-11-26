package lab1.e2_bookmng;

import lab1.e2_bookmng.dao.BaseDAO;
import lab1.e2_bookmng.dao.BookDAO;
import lab1.e2_bookmng.dao.Query;
import lab1.e2_bookmng.model.Book;

import java.sql.SQLException;
import java.util.Arrays;

public class Test {
    public static void a(String... cols){
        String values = "(" + String.join(", ", cols) + ")";
        System.out.println(values);
    }
    public static void main(String[] args) {
        BookDAO dao = new BookDAO();
//        try {
//            dao.insert(new Book("book3", 1000));
//            dao.insert(new Book("book4", 5000));
//            dao.insert(new Book("book5", 5000));
//            dao.insert(new Book("note", 1000));
//        } catch (SQLException | ClassNotFoundException e){
//            e.printStackTrace();
//        }
        System.out.println(new Query(dao).UPDATE());
    }
}

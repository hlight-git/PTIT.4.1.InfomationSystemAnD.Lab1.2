package lab1.e2_bookmng.dao;

import lab1.e2_bookmng.model.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends BaseDAO<Book, Integer>{
    public BookDAO(){
        super("books", "id", "title", "price");
    }

    @Override
    public List<Book> selectAll() {
        List<Book> res = new ArrayList<>();
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(query.SELECT_ALL())){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                res.add(
                    new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getFloat("price")
                    )
                );
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<Book> selectWhere(String conditions) {
        List<Book> res = new ArrayList<>();
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(query.SELECT_WHERE(conditions))){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                res.add(
                        new Book(
                                rs.getInt("id"),
                                rs.getString("title"),
                                rs.getFloat("price")
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Book selectByID(Integer id) {
        Book res = null;
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(query.SELECT_BY_ID())){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                res = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getFloat("price")
                );
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int insert(Book book) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        query.INSERT("title", "price")
                );
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setFloat(2, book.getPrice());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int update(Book book) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement(query.UPDATE());
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setFloat(2, book.getPrice());
        preparedStatement.setInt(3, book.getId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Integer id) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        query.DELETE()
                );
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();
    }
}
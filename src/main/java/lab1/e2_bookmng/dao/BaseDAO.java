package lab1.e2_bookmng.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class BaseDAO <T, ID>{
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/ex2?useSSL=false";
    private static final String jdbcUser = "root";
    private static final String jdbcPass = "Hung001201023360.";
    private String table;
    private String idCol;
    private List<String> columns;
    protected Query query;
    protected BaseDAO(String table, String idCol, String... otherCols){
        this.table = table;
        this.idCol = idCol;
        columns = new ArrayList<>();
        columns.add(idCol);
        columns.addAll(Arrays.asList(otherCols));
        query = new Query(this);
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getIdCol() {
        return idCol;
    }

    public void setIdCol(String idCol) {
        this.idCol = idCol;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
    }
    public abstract List<T> selectAll();
    public abstract List<T> selectWhere(String conditions);
    public abstract T selectByID(ID id);
    public abstract int insert(T t) throws ClassNotFoundException, SQLException;
    public abstract int update(T t) throws ClassNotFoundException, SQLException;
    public abstract int delete(ID id) throws ClassNotFoundException, SQLException;


}

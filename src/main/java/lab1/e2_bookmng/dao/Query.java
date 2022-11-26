package lab1.e2_bookmng.dao;

import java.util.Arrays;

public final class Query {
    private BaseDAO dao;

    public Query(BaseDAO dao) {
        this.dao = dao;
    }

    public String SELECT_ALL() {
        return String.format("SELECT * FROM %s;", dao.getTable());
    }

    public String SELECT_BY_ID() {
        return String.format("SELECT * FROM %s WHERE %s = ?;", dao.getTable(), dao.getIdCol());
    }

    public String SELECT_WHERE(String conditions) {
        return String.format("SELECT * FROM %s WHERE %s;", dao.getTable(), conditions);
    }

    public String INSERT(String... columns) {
        String cols = "(" + String.join(", ", columns) + ")";
        String values = "(" +
                String.join(", ",
                        Arrays.asList(
                                new String(new char[columns.length])
                                        .replace("\0", "?")
                                        .split("")
                        )
                ) +
                ")";
        return String.format("INSERT INTO %s %s VALUES %s;", dao.getTable(), cols, values);
    }

    public String UPDATE() {
        String set = "";
        for (int i=1; i<dao.getColumns().size(); i++){
            if (i > 1){
                set += ", ";
            }
            set += dao.getColumns().get(i) + " = ?";
        }
        return String.format("UPDATE %s SET %s WHERE %s = ?;", dao.getTable(), set, dao.getIdCol());
    }

    public String DELETE() {
        return String.format("DELETE FROM %s WHERE %s = ?;", dao.getTable(), dao.getIdCol());
    }
}

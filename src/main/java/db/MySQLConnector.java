package db;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class MySQLConnector {

    protected Connection getConnection() throws SQLException {
        String url = System.getenv("URL_DB");
        String dbUser = System.getenv("USER_DB").split(":")[0];
        String dbPw = System.getenv("USER_DB").split(":")[1];
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUrl(url);
        return ds.getConnection(dbUser, dbPw);
    }

    protected void closeResources(AutoCloseable... sqlResources) {
        for (AutoCloseable a : sqlResources) {
            if (a != null) {
                try {
                    a.close();
                } catch (Exception e) {
                    System.out.println("Exception " + e.getMessage());
                }
            }
        }
    }
}

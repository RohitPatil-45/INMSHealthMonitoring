
import java.sql.Connection;
import java.sql.DriverManager;
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//import oracle.jdbc.pool.OracleDataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author VIJAY
 */
public class Datasource55 {

    public static Connection getConnection() {
        Connection con = null;
         //      Connection con = null;

        // JDBC URL for MySQL 8
        String jdbcURL = "jdbc:mysql://localhost:3306/bescom?useSSL=false";
        String username = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            con = DriverManager.getConnection(jdbcURL, username, password);
        } catch (Exception e) {
            System.out.println("Excep DB Connection" + e);
        }

        return con;
    }
}

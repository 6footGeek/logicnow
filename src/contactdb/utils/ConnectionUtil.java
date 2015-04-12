package contactdb.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by andy on 4/8/2015.
 *
 *          Simple ConnectionUtil class for connecting to the db (SQL) with auth.
 */


public class ConnectionUtil {
    public static Connection getConnection() {
        Connection c = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
}

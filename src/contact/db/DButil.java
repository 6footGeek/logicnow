package contact.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Andy on 12/04/15.
 *
 *      Simple Connection classs for the DB
 *      contactCreateTable() is here because it should only need to be created once so more of a util rather than being carried by contact class
 */
public class DButil {
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


    //only required once so stick in here
    public void contactCreateTable() {
        Connection c = null;
        Statement sql = null;

        try {
            c = getConnection();
            sql = c.createStatement();

            sql.execute("CREATE TABLE IF NOT EXISTS contact (id int primary key unique auto_increment, first_name varchar(100), last_name varchar(100))");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sql != null) {
                try {
                    sql.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

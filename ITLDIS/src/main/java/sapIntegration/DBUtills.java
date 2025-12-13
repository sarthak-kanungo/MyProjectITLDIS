package sapIntegration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chandan.sharma
 */
public class DBUtills
{

    public static Connection getConnection() throws SQLException
    {
        Connection conn = null;
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = null;
        String userName = null;
        String password = null;
        try
        {
            url = "jdbc:sqlserver://192.168.16.12:1433;databaseName=SPASVOLTAS;useUnicode=true;characterEncoding=utf8;";
            userName = "eman_test";
            password = "eman123";
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, userName, password);
            return conn;
        }
        catch (ClassNotFoundException ex)
        {
            
            ex.printStackTrace();
           // Logger.getLogger(DBUtills.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;

    }
}

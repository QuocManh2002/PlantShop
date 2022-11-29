/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class DBUtils {
    public static Connection makeConnection() throws ClassNotFoundException, SQLException{
        Connection cn = null;
        String IP = "localhost";
        String instanceName = "LAPTOP-4E8LH4SO\\SQLEXPRESS";
        String port = "1433";
        String uid = "sa";
        String pwd = "12345";
        String db = "PlantShop";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://"+IP+"\\"+instanceName+":"+port+
                ";databasename="+db+";user="+uid+";password="+pwd;
        Class.forName(driver);
//        Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
        cn = DriverManager.getConnection(url);
        return cn;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class SQLServerProvider {
    private Connection connection;
    
    public static Connection openConnection() throws Exception{
        String strServerName = "DACTHANH";
        String strDBName = "QL_BANSACH";
        String strUserName = "sa";
        String strPassword = "123";
        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //Đường dẫn
        String connURL = "jdbc:sqlserver://" + strServerName +
                    ":1433; databaseName = " + strDBName + ";user = " + strUserName + ";password = " + strPassword  ;
        //--
        Connection con = DriverManager.getConnection(connURL);
        
        return con;
    }
    
}

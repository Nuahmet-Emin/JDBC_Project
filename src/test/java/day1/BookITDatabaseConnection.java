package day1;

import utility.ConfigurationReader;

import java.sql.*;

public class BookITDatabaseConnection {

    public static void main(String[] args) throws SQLException {

        String url = ConfigurationReader.getProperty("bookit.database.url");
        String username = ConfigurationReader.getProperty("bookit.database.username") ;
        String password = ConfigurationReader.getProperty("bookit.database.password");

        Connection con = DriverManager.getConnection(url, username, password) ;
        Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs  = stm.executeQuery("SELECT * FROM room") ;

        rs.next();
        System.out.println("rs.getString(2) = " + rs.getString(2));


    }

}

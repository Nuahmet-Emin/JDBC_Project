package day1;

import utility.ConfigurationReader;

import java.sql.*;

class LibraryDatabaseConnection {

    public static void main(String[] args) throws SQLException {

        String url      = ConfigurationReader.getProperty("library1.database.url") ;
        String username = ConfigurationReader.getProperty("library1.database.username") ;
        String password = ConfigurationReader.getProperty("library1.database.password") ;

        Connection con = DriverManager.getConnection(url, username, password) ;
        Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs  = stm.executeQuery("SELECT * FROM books") ;

        rs.next() ;
        System.out.println("rs.getString(2) = " + rs.getString(2) );

    }

}

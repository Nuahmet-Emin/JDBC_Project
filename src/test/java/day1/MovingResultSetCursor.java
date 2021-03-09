package day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;

public class MovingResultSetCursor {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:oracle:thin:@54.172.209.6:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection con = DriverManager.getConnection(url,username,password);

        Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stm.executeQuery("SELECT * FROM REGIONS");

        /*rs.next();
        rs.previous();
        rs.first();
        rs.last();
        rs.beforeFirst();
        rs.afterLast();
        rs.absolute(3);*/

        rs.first();
        System.out.println("First Row "+rs.getString(2));

        rs.last();
        System.out.println("last Row "+rs.getString(2));

        rs.previous();
        System.out.println("Previous Row "+rs.getString(2));

        rs.beforeFirst();

        while(rs.next()){
            System.out.println("2nd Column data = " + rs.getString(2));
        }

       rs.absolute(2);
        System.out.println("2nd Row Region Name is: " + rs.getString(2));


    }
}

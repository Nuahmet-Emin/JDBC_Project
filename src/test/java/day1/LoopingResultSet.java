package day1;

import java.sql.*;

public class LoopingResultSet {

    public static void main(String[] args) throws SQLException {


        String url = "jdbc:oracle:thin:@54.172.209.6:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection con = DriverManager.getConnection(url,username,password);

        Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stm.executeQuery("SELECT * FROM REGIONS");

        //rs.next();
       // System.out.println( "Region Value at this row is "+rs.getString("REGION_NAME"));

        // checking if there is next row or not

        while(rs.next()){
            System.out.println("REGION ID at this row is " + rs.getString("REGION_ID"));
            System.out.println( "Region Value at this row is "+rs.getString("REGION_NAME"));
        }

         rs = stm.executeQuery("SELECT * FROM JOBS");

        while (rs.next()){
            System.out.print(rs.getString(1));
            System.out.println("\t\t " + rs.getString(2));
        }

    }
}

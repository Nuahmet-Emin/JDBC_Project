package day1;

import java.sql.*;

public class JDBC_FirstStep {


    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@54.172.209.6:1521:XE";


        try{
            Connection con = DriverManager.getConnection(url, "hr","hr");
            // this way creating statement object
            //will allow us to move forward and backword
            Statement stmnt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet  rs = stmnt.executeQuery("SELECT * From REGIONS");

            rs.next();

            System.out.println("First Row Region is " + rs.getString("REGION_NAME"));

            rs.next();
            System.out.println("First Row Region is " + rs.getString("REGION_NAME"));

            System.out.println("Connection Successful");
        }catch (SQLException e){
            System.out.println("ERROR OCCURRED" + e.getMessage()) ;
        }


    }
}

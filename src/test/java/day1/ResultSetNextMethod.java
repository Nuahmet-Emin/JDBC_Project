package day1;

import java.sql.*;

public class ResultSetNextMethod {



    public static void main(String[] args) throws SQLException {

        String url = "jdbc:oracle:thin:@54.236.150.168:1521:XE" ;
        String username = "hr" ;
        String password = "hr" ;

        Connection con = DriverManager.getConnection(url, username, password) ;
        Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs  = stm.executeQuery("SELECT * FROM REGIONS") ;

//        rs.beforeFirst();
        while(rs.next()){

            //System.out.println(rs.next() ); // DO NOT DO THIS , IT WILL MOVE YOUR CURSOR
            System.out.println("rs.getString(1) = " + rs.getString(1));

        }
        // There are two ways to get cell data
        // 1. using column name  for ResultSet object's  getString(column name here)
        // 2. using column index for ResultSet object's  getString(column index here)
        // SQL INDEX START WITH 1 !!!!!!!


        // How do you loop backward from last row till first row
//        rs.afterLast();
        while( rs.previous() ){
            System.out.println("Backward rs.getString(1) = " + rs.getString(1));
        }



    }

}

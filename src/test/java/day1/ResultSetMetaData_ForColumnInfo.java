package day1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultSetMetaData_ForColumnInfo {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:oracle:thin:@54.236.150.168:1521:XE" ;
        String username = "hr" ;
        String password = "hr" ;

        Connection con = DriverManager.getConnection(url, username, password) ;
        Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs  = stm.executeQuery("SELECT * FROM COUNTRIES") ;


        // ResultSet object only contains the data comes from the query result
        // It does not have any method to get information about columns or columns datatype and so on
        // ResultSetMeta data , which is obtained by ResultSet object contains column information

        ResultSetMetaData rsmd = rs.getMetaData() ;
        // 2 methods we care about using ResultSetMetaData is
        // getColumnCount to count how many column we have
        // getColumnName or getColumnLabel
        System.out.println("rsmd.getColumnCount() = " + rsmd.getColumnCount() );
//        System.out.println("rsmd.getColumnName(1) = " + rsmd.getColumnName(1) );
//        System.out.println("rsmd.getColumnName(2) = " + rsmd.getColumnName(2) );

        // print out all the column names


        int colCount = rsmd.getColumnCount();
        for(int col = 1; col<= colCount; col++){
            System.out.println("Column " + col + " Name = " + rsmd.getColumnName(col));
        }

        List<String> allColumns = new ArrayList<>();
        for(int col = 1; col<= colCount; col++){
           allColumns.add(rsmd.getColumnName(col));
        }

        System.out.println("allColumns = " + allColumns);

    }
}

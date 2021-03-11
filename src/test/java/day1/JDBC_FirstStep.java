package day1;

import java.sql.*;

public class JDBC_FirstStep {




        public static void main(String[] args) {

            String url = "jdbc:oracle:thin:@54.236.150.168:1521:XE" ;
            try {
                Connection con = DriverManager.getConnection(url, "hr", "hr");
                // This way creating statement object
                // will allow us to move forward and backward easily when navigating through the result we got
                Statement stmnt  = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY) ;

                ResultSet rs = stmnt.executeQuery("SELECT * FROM REGIONS")  ;

                // IN ORDER TO ACCESS RESULTSET , WE NEED TO KNOW WHICH ROW WE ARE AT
                // BEFORE ENTERING THE FIRST ROW , THE CURSOR OF THE RESULTSET IS AT THE LOCAITON KNOWN AS --BEFORE FIRST
                // IN ORDER TO MOVE TO NEXT LINE  WE NEED TO CALL next method of resultset
                // IR ORDER TO GET ANY COLUMN DATA WE NEED TO CALL method getString or getDouble .... and so on
                rs.next() ;
                // now we are at the first row
                System.out.println("FIRST ROW REGION_NAME IS "  +  rs.getString("REGION_NAME") ) ;

                rs.next() ;
                System.out.println("SECOND ROW REGION_NAME IS "  +  rs.getString("REGION_NAME") ) ;

                rs.next() ;
                System.out.println("THIRD ROW REGION_NAME IS "  +  rs.getString("REGION_NAME") ) ;

                rs.next() ;
                System.out.println("FORTH ROW REGION_NAME IS "  +  rs.getString("REGION_NAME") ) ;

                rs.next() ; // THIS WILL MOVE THE CURSOR TO THE LOCATION KNOW AS -- AFTER LAST
                // BELOW LINE WILL NOT WORK!!!! THERE AINT NO ROW
                //  System.out.println("FIFTH ROW REGION_NAME IS "  +  rs.getString("REGION_NAME") ) ;



                System.out.println("CONNECTION SUCCESSFUL");
                rs.close();
                stmnt.close();
                con.close();

            }catch(SQLException e){
                System.out.println("ERROR OCCURRED "+ e.getMessage());
            }


        }
}

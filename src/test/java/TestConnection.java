import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@3.88.130.74:1521:XE";


        try{
            Connection con = DriverManager.getConnection(url, "hr","hr");
           // this way creating statement object
            //will allow us to move forward and backward
            Statement stmnt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
           ResultSet  rs = stmnt.executeQuery("SELECT * From REGIONS");

           rs.next();

            System.out.println("First Row Region is " + rs.getString("REGION_NAME"));

            rs.next();
            System.out.println("First Row Region is " + rs.getString("REGION_NAME"));

            System.out.println("Connection Successful");
            con.close();
        }catch (SQLException e){
            System.out.println("Connection has Failed" + e.getMessage()) ;
        }


    }
}

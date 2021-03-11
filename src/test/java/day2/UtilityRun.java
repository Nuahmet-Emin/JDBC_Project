package day2;

import utility.ConfigurationReader;
import utility.DB_Utility;

import java.sql.*;

public class UtilityRun {

    public static void main(String[] args) throws SQLException {

//       DB_Utility.createConnection() ;


        String url      = ConfigurationReader.getProperty("hr.database.url") ;
        String username = ConfigurationReader.getProperty("hr.database.username") ;
        String password = ConfigurationReader.getProperty("hr.database.password") ;


        DB_Utility.createConnection(url, username, password);

        ResultSet result = DB_Utility.runQuery("SELECT * FROM REGIONS") ;
        result.next();
        System.out.println("region name " + result.getString("REGION_NAME"));

        int totalRow = DB_Utility.getRowCount() ;
        System.out.println("totalRow = " + totalRow);

        int totalColumn = DB_Utility.getColumnCount() ;
        System.out.println("totalColumn = " + totalColumn);

        System.out.println("DB_Utility.getColumnNames() = "
                + DB_Utility.getColumnNames());

        System.out.println("DB_Utility.getColumnData = "
                + DB_Utility.getColumnDataAsList(2));

        System.out.println("DB_Utility.getColumnDataAtRow(2,2) = "
                + DB_Utility.getColumnDataAtRow(2, 2));

       // System.out.println("RIGHT NOW AT ROW : " + result.getRow());

        System.out.println("DB_Utility.getColumnDataAsList(2) = " + DB_Utility.getColumnDataAsList(2));

        System.out.println("DB_Utility.getColumnDataAsList(\"REGION_NAME\") = " + DB_Utility.getColumnDataAsList("REGION_NAME"));

        //DB_Utility.displayAllData();

        System.out.println("DB_Utility.getRowMap(1) = " + DB_Utility.getRowMap(1));


        DB_Utility.destroy();

    }

}

package utility;

import org.checkerframework.checker.units.qual.A;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DB_Utility {

    private static Connection con;
    private static Statement stm;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;

    /**
     * Create connection method , just checking one connection successful or not
     */
    public static void createConnection(){

        String url      = ConfigurationReader.getProperty("hr.database.url") ;
        String username = ConfigurationReader.getProperty("hr.database.username") ;
        String password = ConfigurationReader.getProperty("hr.database.password") ;


        /*try {
            Connection con = DriverManager.getConnection(url , username, password) ;
            System.out.println("CONNECTION SUCCESSFUL");
        } catch (SQLException e) {
            System.out.println("CONNECTION HAS FAILED " + e.getMessage() );
        }*/

        createConnection(url,username,password);

    }

    public static void createConnection(String url, String username, String password) {

        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Successful");
        } catch (SQLException e) {
            System.out.println("Connection has Failed" + e.getMessage());
        }
    }

    public static ResultSet runQuery(String query) {

        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(query);
            rsmd = rs.getMetaData();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING RESULTSET " + e.getMessage());
        }

        return rs;
    }

    public static void destroy(){

            try {
                if(rs!= null) {
                    rs.close();
                }
                if(stm != null){
                   stm.close();
                }
                if(con != null){
                    con.close();
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    // reset cursor

    private static void resetCursor(){
        try {
            rs.beforeFirst();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static int getRowCount(){
        int rowCount = 0;
        try {
            rs.last();
            rowCount= rs.getRow();
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ROW COUNT " + e.getMessage());
        }finally {
            resetCursor();
        }
        return rowCount;

    }

    public static int getColumnCount(){
        int columnCount = 0;

        try {
            columnCount = rsmd.getColumnCount();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE COUNTING THE COLUMN " + e.getMessage());
        }
        return columnCount;
    }

    public static List<String>  getColumnNames(){
        
        List<String> columnNamesList = new ArrayList<>();
        
        try {

            for (int colNum = 1; colNum <= rsmd.getColumnCount() ; colNum++) {

                String colName =rsmd.getColumnName(colNum);
                columnNamesList.add(colName);
            }
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING COLUMN NAMES" + e.getMessage());
        }

        return  columnNamesList;

    }

    public static List<String> getRowDataAsList(int rowNum){
        List<String> rowDataList = new ArrayList<>();
        try {
            rs.absolute(rowNum);

            for (int colNum = 1; colNum <= getColumnCount() ; colNum++) {
                String cellValue = rs.getString(colNum  );
                rowDataList.add(cellValue);
            }

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getRowDataList" + e.getMessage());
        }finally {
            resetCursor();
        }

        return rowDataList;
    }

    public static String getCellValue(int rowNum , int columnIndex) {

        String cellValue = "" ;

        try {
            rs.absolute(rowNum) ;
            cellValue = rs.getString(columnIndex ) ;

        } catch (SQLException e) {
            System.out.println("ERROR OCCURRED WHILE getCellValue " + e.getMessage() );
        }finally {
            resetCursor();
        }
        return cellValue ;

    }



    public static String getCellValue(int rowNum, String columnName){

        String cellValue = "";

        try{
            rs.absolute(rowNum);
            cellValue = rs.getString(columnName);

        }catch (SQLException e){
            System.out.println("ERROR OCCURRED WHILE getCellValue" + e.getMessage());
        }finally {
            resetCursor();
        }

        return cellValue;
    }

    // get first cell value from First row First Column
    public static String getFirstRowFirstColumn(){

        return getCellValue(1,1);
    }

    public static String getColumnDataAtRow(int rowNum, int columnIndex){
        String result = "";

        try {
            rs.absolute(rowNum);
            result = rs.getString(columnIndex);
            rs.beforeFirst();

        } catch (SQLException throwables) {
            System.out.println("Error While getColumnDataAtRow");
        }finally {
            resetCursor();
        }
        return  result;
    }

    public static String getColumnDataAtRow(int rowNum, String columnName){

        String result = "";

        try {
            rs.absolute(rowNum);
            result = rs.getString(columnName);

            rs.beforeFirst();

        } catch (SQLException throwables) {
            System.out.println("Error While getColumnDataAtRow");
        }finally {
            resetCursor();
        }
        return  result;
    }



    public static List<String> getColumnDataAsList(int columnIndex){

        List<String> columnDataList = new ArrayList<>();

        try {
         rs.beforeFirst();
         while (rs.next()){
             String celValue = rs.getString(columnIndex);
             columnDataList.add(celValue);
         }
            rs.beforeFirst();
        }catch (SQLException e){
            System.out.println("ERROR WHILE getColumnDataAsList " + e.getMessage());
        }finally {
            resetCursor();
        }

        return columnDataList;
    }


    public static List<String> getColumnDataAsList(String columnName){

        List<String> columnDataList = new ArrayList<>();

        try {
            rs.beforeFirst();
            while (rs.next()){
                String celValue = rs.getString(columnName);
                columnDataList.add(celValue);
            }
            rs.beforeFirst();
        }catch (SQLException e){
            System.out.println("ERROR WHILE getColumnDataAsList " + e.getMessage());
        }finally {
            resetCursor();
        }

        return columnDataList;
    }



    // display all data
    public static  void displayAllData(){
        resetCursor();
        try {
            while (rs.next()){ //get each raw
                for (int colNum = 1;  colNum <= getColumnCount(); colNum++) {

                    //System.out.print( rs.getString(colIndex) + "\t" );
                    System.out.printf("%-25s", rs.getString(colNum));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ALL DATA " + e.getMessage());
        }finally {
            resetCursor();
        }

    }



    public static Map<String,String> getRowMap(int rowNum){

        Map<String, String> rowMap = new LinkedHashMap<>();

        try {
            rs.absolute(rowNum);

            for (int colNum = 1; colNum <= getColumnCount() ; colNum++) {

                String colName = rsmd.getColumnName(colNum);
                String cellValue = rs.getString(colNum);
                rowMap.put(colName,cellValue);
            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR AT ROW MAP FUNCTION " + e.getMessage());
        }finally {
            resetCursor();
        }

        return rowMap;
    }

    public static List<Map<String,String>> getAllRowAsListOfMap(){

        List<Map<String,String>> allRowListOfMap = new ArrayList<>();

        int rowCount = getRowCount();
        // move from first row till last row
        // get each row as map object and add it to the list

        for (int rowIndex = 1; rowIndex <= rowCount ; rowIndex++) {
            Map<String,String> rowMap = getRowMap(rowIndex);

            allRowListOfMap.add(rowMap);

        }
        resetCursor();

        return allRowListOfMap;
    }




}

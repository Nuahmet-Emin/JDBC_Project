package day2;

import utility.DB_Utility;

public class PracticingUtility {

    public static void main(String[] args) {

        DB_Utility.createConnection();

        DB_Utility.runQuery("SELECT * FROM EMPLOYEES");

        //DB_Utility.displayAllData();

        System.out.println("DB_Utility.getRowCount() = " + DB_Utility.getRowCount());

        System.out.println("DB_Utility.getColumnCount() = " + DB_Utility.getColumnCount());

        System.out.println("DB_Utility.getColumnNames() = " + DB_Utility.getColumnNames());

        System.out.println("DB_Utility.getRowDataAsList(1) = " + DB_Utility.getRowDataAsList(1));

        System.out.println("DB_Utility.getCellValue(8,5) = " + DB_Utility.getCellValue(8, 5));

        System.out.println("DB_Utility.getCellValue(8,\"PHONE_NUMBER\") = " + DB_Utility.getCellValue(8, "PHONE_NUMBER"));

        System.out.println("DB_Utility.getColumnDataAsList(2) = " + DB_Utility.getColumnDataAsList(2));

        System.out.println("DB_Utility.getColumnDataAsList(\"FIRST_NAME\") = " + DB_Utility.getColumnDataAsList("FIRST_NAME"));

        System.out.println("DB_Utility.getRowMap(1) = " + DB_Utility.getRowMap(1));

        System.out.println("DB_Utility.getAllRowAsListOfMap() = " + DB_Utility.getAllRowAsListOfMap());

        DB_Utility.destroy();

    }


}

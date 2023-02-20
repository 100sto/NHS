package utils;

import org.junit.Test;

import java.sql.*;
import java.util.*;

public class JDBCUtil {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static ResultSetMetaData rSetMetaData;

    public static void establishConnection(){
        try {
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@georgedatabase.cjstba6anwip.us-east-2.rds.amazonaws.com:1521/ORCL",
                    "georgestoleru",
                    ""
            );
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e){
            System.out.println("Database connection error occurred");
            e.printStackTrace();
        }
    }

    public static List<Map<String,Object>> executeQuery(String query){
        try {
            resultSet = statement.executeQuery(query);
            rSetMetaData = resultSet.getMetaData();
            List<Map<String, Object>> allTable = new ArrayList<>();
            resultSet.beforeFirst();
            while (resultSet.next()) {
                Map<String, Object> currentRow = new LinkedHashMap<>();
                for (int i = 1; i <= rSetMetaData.getColumnCount(); i++) {
                    currentRow.put(rSetMetaData.getColumnName(i), resultSet.getObject(i));
                    // 'i' is the number for column and our row number is defined by resultSet next method
                    // to be able to get columnName we will use resultSetMeta data object with index number of column
                    // to be able to get column's value we will use resultSet.getObject method with index number of column
                }
                allTable.add(currentRow);
            }
            return allTable;
        } catch (SQLException e){
            System.out.println("Error occurred when getting data from the table");
            e.printStackTrace();
            return null;
        }
    }


    public static void closeConnection(){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }catch (SQLException e){
            System.out.println("Error occurred when connection is closing");
            e.printStackTrace();
        }

    }

    //Ignore the once below ------------------------------------------------------------------------------------

    @Test
    public void jdbcTest(){
        //printout all the names from employees that are starting with s

        List<Map<String,Object>> nameS = executeQuery("select first_name from employees where first_name like 'S%'");
        for(int i=0; i<nameS.size(); i++){
            System.out.println(nameS.get(i).get("FIRST_NAME"));
        }

        closeConnection(); //it will be in hook class or @After Method
    }

    /* Connection -> it will connect us to database and will allow us to enter our credentials
     *  Statement -> we are going to define our condition to get the data(result) from database
     *  ResultSet -> we store the data from database to java object

     *  The above 3 are interfaces in java which comes from JDBC dependency
     * in the URL section->"jdbc:oracle:thin:@'endpoint':'portNumber'/ORCL"
     * */

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@georgedatabase.cjstba6anwip.us-east-2.rds.amazonaws.com:1521/ORCL",
                "georgestoleru",
                ""
        );

        // Create a connection before the statement and past this code in the main method it will work
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select first_name, email, department_name\n" +
                "from employees e right join departments d\n" +
                "on d.department_id = e.department_id");
        resultSet.next(); // will return true if there is a next row otherwise it will return false.
        // it will take us to the next row
        System.out.println(resultSet.getString("first_name"));
        // using get string we can get our column value from the row we are in
        System.out.println(resultSet.getRow());
        resultSet.next();
        System.out.println(resultSet.getRow());
        // If you want to go to the last row we could use resultSet.last() method
        resultSet.last();
        System.out.println(resultSet.getString("first_name"));
        resultSet.first(); //-> This method will take the row as first
        System.out.println(resultSet.getString("first_name"));
        //resultSet.deleteRow();// If we want to delete the row
        // MetaData
        ResultSetMetaData rSetMetaData = resultSet.getMetaData();
        // count of the columns for your table
        System.out.println(rSetMetaData.getColumnCount());
        System.out.println(rSetMetaData.getColumnName(1));// It gives column name in upper case
        // How can I print all the column names from employees table?
        for (int i = 1; i <=rSetMetaData.getColumnCount() ; i++) {
            System.out.println(rSetMetaData.getColumnName(i));
        }

        // Creating a map for one row
        Map<String,Object> oneRow= new HashMap();
        System.out.println(resultSet.getRow());
        for (int i = 1; i <=rSetMetaData.getColumnCount() ; i++) {
            oneRow.put(rSetMetaData.getColumnName(i),resultSet.getObject(i));
            // i is the number for column and our row number is defined by resultSet next method
            // to be able to get columnName we will use resultSetMeta data object wiht index number of column
            // to be able to get column's value we will use resultset.getObject method with index number of column
        }

        System.out.println(oneRow);
        List<Map<String,Object>> allTable= new ArrayList<>();
        while (resultSet.next()){
            Map<String,Object> currentRow= new HashMap();
            for (int i = 1; i <=rSetMetaData.getColumnCount() ; i++) {
                currentRow.put(rSetMetaData.getColumnName(i),resultSet.getObject(i));
                // i is the number for column and our row number is defined by resultSet next method
                // to be able to get columnName we will use resultSetMeta data object wiht index number of column
                // to be able to get column's value we will use resultset.getObject method with index number of column
                allTable.add(currentRow);
            }
        }
        System.out.println(allTable);

    }
}

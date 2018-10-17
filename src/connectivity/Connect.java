package connectivity;

import java.sql.*;

public class Connect {

    public Connection connection;

    public Connection getConnection(){

        String dbName = "Prerequisite";
        String userName = "root";
        String password = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}

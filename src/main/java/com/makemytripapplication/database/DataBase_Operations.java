package com.makemytripapplication.database;

import com.makemytripapplication.base.BaseClass;
import com.makemytripapplication.constants.IConstants;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase_Operations extends BaseClass {

    private void databaseSetup() {
        try {
            //created a database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            //get connection to database
            connection = DriverManager.getConnection(IConstants.DB_URL, IConstants.DB_USERNAME, IConstants.DB_PASSWORD);
            //statement object is created to send request to database
            statement = connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String[][] retrieveData() {
        databaseSetup();
        String[][] array = new String[0][];
        try {
            sqlQuery = "select * from login_credentials";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            System.out.println(resultSet.getFetchSize());
            array = new String[3][2];
            int i = 0;
            while (resultSet.next()) {
                array[i][0] = resultSet.getString(1);
                array[i][1] = resultSet.getString(2);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseTearDown();
        }

        return array;
    }

    private void databaseTearDown() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.example.restapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database    {

    public static String query() throws Exception {
        String message = "";
        Connection con = getConnection();
        PreparedStatement s = con.prepareStatement("select * from country where GNP > 500000");
        s.execute();
        ResultSet result = s.executeQuery();
        while(result.next()) {

            message += result.getString("Name") + ", " +  result.getString("GNP");

        }
        return message;
    }



    public static Connection getConnection() throws Exception{

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false";
            String username = "root";
            String password = "password";
            Class.forName(driver);

            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
            return con;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;

    }
}

package com.example.helloworld;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class Connect {
    //private static Object JDBCTutorialUtilities;

    public  Connection connect(){
        String jdbcURL = "jdbc:postgresql://localhost:5432/Try";
        String userName = "postgres";
        String password = "password";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.printf("There is connection");

            return connection;
        } catch (SQLException e) {
            System.out.printf("Error!!");
            throw new RuntimeException(e);

        }

    }
    public void viewTable(Connection con) throws SQLException  {
        String query = "select * from data";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String dd = rs.getString("dd");
                int ID = rs.getInt("id");
                System.out.println(dd + ", " +ID);
            }
            con.close();
        } catch (SQLException e) {
            System.out.printf("Error!!");
            throw new RuntimeException(e);
        }
    }
    public void insertTable(Connection con,String dd)  throws SQLException{
        String d=dd;
        String query = "insert into data (dd)\n" +
                "VALUES ('"+d+"');";
        try (Statement stmt = con.createStatement()) {
            int rs = stmt.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            System.out.printf("Error!!");
            throw new RuntimeException(e);
        }
    }
    public void UpdateTable(Connection con,int eid)  {
        //String query1 = "UPDATE data" + "SET dd='hhhhhhh' + "+"WHERE ID=4";
        //int t=eid;
        String query = "update data SET dd= 'vnjdv' where id =4";

        try (Statement stmt = con.createStatement()) {
            int rs = stmt.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            System.out.printf("Error!!");
            throw new RuntimeException(e);
        }
    }
    public void DeleteTable(Connection con)  {
        //String query = "UPDATE data" + "SET dd='hhhhhhh' + "WHERE ID=4;
        String query = "DELETE FROM data WHERE dd='hey';";

        try (Statement stmt = con.createStatement()) {
            int rs = stmt.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            System.out.printf("Error!!");
            throw new RuntimeException(e);
        }
    }
}


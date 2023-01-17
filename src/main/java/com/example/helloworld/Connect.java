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
    public void insertTable(Connection con,String name)  throws SQLException{
        /*
        String query = "insert into data (dd)\n" +
                //"VALUES ('"+d+"');";

         */
        String query="INSERT INTO data(dd)"+
                "VALUES ('"+name+"')";
        try (Statement stmt = con.createStatement()) {
            int rs = stmt.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            System.out.printf("Error!!");
            throw new RuntimeException(e);
        }
    }
    public void UpdateTable(Connection con,int eid,String TextToUp)  {
        //String query1 = "UPDATE data" + "SET dd='hhhhhhh' + "+"WHERE ID=4";
        //String query = "update data SET dd= 'vnjdv' where id =4";
        String qw= Integer.toString(eid);
        String query=String.format("UPDATE \"data\""+
                "SET dd='%s'"+
                "WHERE id= %s;",TextToUp,Integer.toString(eid));

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


package com.example.helloworld;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;

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
    public boolean inList(int id,ArrayList<EnTable> data){
        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i).getId())==id){
                return false;
            }
        }
        return true;
    }
    public void Link_information(Connection con, ArrayList<EnTable> data) throws SQLException  {
        String query = "select * from data";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            int i=0;
            while (rs.next()) {
                String dd = rs.getString("dd");
                int ID = rs.getInt("id");
                System.out.println(dd + ", " +ID);
                if ((inList(ID,data))) {
                    data.add(new EnTable(ID,dd));
                }
                if ((i<data.size())&&((data.get(i).getId())==0)){
                    data.get(i).setId(ID);;
                }
                if ((i<data.size())&&data.get(i).getDd()!=dd){
                    data.get(i).setDd(dd);
                }
                i++;
            }
            con.close();
        } catch (SQLException e) {
            System.out.printf("Error!!");
            throw new RuntimeException(e);
        }
        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i).getId())==0){
                insertTable(connect(),(data.get(i).getDd()),data);
            }
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
                //data.add(new EnTable(ID,dd));
            }
            con.close();
        } catch (SQLException e) {
            System.out.printf("Error!!");
            throw new RuntimeException(e);
        }
    }

    public void insertTable(Connection con,String name, ArrayList<EnTable> data)  throws SQLException{
        /*
        String query = "insert into data (dd)\n" +
                //"VALUES ('"+d+"');";

         */
        String query="INSERT INTO data(dd)"+
                "VALUES (?)";
        PreparedStatement statement =con.prepareStatement(query);
        statement.setString(1,name);
        try (Statement stmt = con.createStatement()) {
            statement.executeUpdate();
            Link_information(connect(),data);
            con.close();
        } catch (SQLException e) {
            System.out.printf("Error!!");
            throw new RuntimeException(e);
        }
    }
    public void UpdateTable(Connection con,int eid,String TextToUp,String cltup, ArrayList<EnTable> data)  {
        /*
        String query=String.format("UPDATE \"data\""+
                "SET dd='%s'"+
                "WHERE id= %s;",TextToUp,Integer.toString(eid));
        */
        try (Statement stmt = con.createStatement()) {
            String sqlUpdate = "UPDATE data\n" +
                    "\tSET  dd= ?" +
                    "\tWHERE id = ?;";
            PreparedStatement statement =con.prepareStatement(sqlUpdate);
            statement.setString(1,TextToUp);
            statement.setInt(2,eid);
            statement.executeUpdate();
            Link_information(con,data);

            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void DeleteTable(Connection con,String str,ArrayList<EnTable> data) throws SQLException {
        //String query = "UPDATE data" + "SET dd='hhhhhhh' + "WHERE ID=4;
        try (Statement stmt = con.createStatement()) {
            //int rs = stmt.executeUpdate(query);
            String query = "DELETE FROM data WHERE dd=?;";
            PreparedStatement statement =con.prepareStatement(query);
            statement.setString(1,str);
            statement.executeUpdate();
            data.indexOf(str);
            con.close();
        } catch (SQLException e) {
            System.out.printf("Error!!");
            throw new RuntimeException(e);
        }
    }
}


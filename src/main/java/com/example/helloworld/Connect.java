package com.example.helloworld;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;

public class Connect {
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

    public void Link_information(Connection con, ArrayList<EnTable> data,int flag) throws SQLException  {
        String query = "select * from data";

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            int j=0;
            while (rs.next()) {
                String dd = rs.getString("dd");
                int ID = rs.getInt("id");
                //System.out.println(dd + ", " +ID);
                if ((inList(ID,data))) {
                    data.add(new EnTable(ID,dd));
                }
                if ((j<data.size())&&((data.get(j).getId())==0&&flag==1)){
                    data.get(j).setId(ID);
                }
                if ((j<data.size())&&data.get(j).getDd()!=dd){
                    data.get(j).setDd(dd);
                }
                j++;
            }
            int s=data.size();
            for (int i = 0; i < s; i++) {
                I(con,(data.get(i).getId()),(data.get(i).getDd()),data);
                //System.out.println("1");
            }
        } catch (SQLException e) {
            System.out.printf("Error!!");
            throw new RuntimeException(e);
        }

        con.close();
    }
    public void checkVaild(int where_id,ArrayList<EnTable> data,String name) throws SQLException {
        //ArrayList<EnTable> d=viewTable(connect(),data);
        for (EnTable e:data) {
            if (e.getId()==where_id){return;}
        }
        insertTable(connect(),name,data,0);
    }
    public void I(Connection con,int id,String name,ArrayList<EnTable> data){
        int flag=0;
        String query = "select * from data";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String dd = rs.getString("dd");
                int ID = rs.getInt("id");
                //System.out.println(dd + ", " +ID);
                if (ID==id){return;}
            }
            insertTable(con,name,data,0);
            con.close();
            //return data;
        } catch (SQLException e) {
            System.out.printf("Error!!");
            throw new RuntimeException(e);
        }

    }
    public ArrayList<EnTable> viewTable(Connection con, ArrayList<EnTable> data) throws SQLException  {
        String query = "select * from data";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String dd = rs.getString("dd");
                int ID = rs.getInt("id");
                //System.out.println(dd + ", " +ID);
                data.add(new EnTable(ID,dd));
            }
            con.close();
            return data;
        } catch (SQLException e) {
            System.out.printf("Error!!");
            throw new RuntimeException(e);
        }

    }
    public void insertTable(Connection con,String name, ArrayList<EnTable> data,int flag)  throws SQLException{
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
            if (flag==1){Link_information(connect(),data,0);}
            else {Link_information(connect(),data,1);}
            //li(con,data);
            statement.close();
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
            Link_information(con,data,0);
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
            //data.remove(data.indexOf(str));

            con.close();
        } catch (SQLException e) {
            System.out.printf("Error!!");
            throw new RuntimeException(e);
        }
    }
}
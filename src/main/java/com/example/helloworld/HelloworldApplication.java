package com.example.helloworld;

import com.mycompany.systemlogin.SystemLogin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author arielDobkin ID:214776668
 */
@SpringBootApplication
public class HelloworldApplication {

    public static void main(String[] args) throws SQLException {
        Connect con = new Connect();
        ArrayList<EnTable> data = new ArrayList<EnTable>();
        con.Link_information(con.connect(), data);
//        for (int i = 0; i < data.size(); i++) {
//            System.out.println(data.get(i).getId());
//            System.out.println(data.get(i).getDd());
//        }
        //con.viewTable(con.connect());
        con.insertTable(con.connect(), "new11",data);
        con.UpdateTable(con.connect(), 4,"list11","dd",data);
        for (int i = 0; i < data.size(); i++) {
            System.out.print(data.get(i).getId());
            System.out.print(data.get(i).getDd());
        }
        con.DeleteTable(con.connect(),"new",data);
//        SpringApplication.run(HelloworldApplication.class, args);
//        EnTable e=new EnTable("hgjhn");

//        for (int i = 0; i < 5; i++) {
//            data.add(new EnTable(1,"fhdj"));
//        }

        System.out.println("Hello world");
        System.out.println("My name is Ariel.");
        System.out.println("I'm your partner, my name is Rom Netanel Vinnitski");
        System.out.println("lkygfhgh");

    }
}


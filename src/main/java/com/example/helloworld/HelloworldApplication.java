package com.example.helloworld;

import com.mycompany.systemlogin.SystemLogin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author arielDobkin ID:214776668
 */
@SpringBootApplication
public class HelloworldApplication {

    public static void main(String[] args) throws SQLException {
        Connect con = new Connect();
        con.viewTable(con.connect());
        con.insertTable(con.connect(), "he");
        con.UpdateTable(con.connect(), 4);
        con.DeleteTable(con.connect());
        //SpringApplication.run(HelloworldApplication.class, args);
        System.out.println("Hello world");
        System.out.println("My name is Ariel.");
        System.out.println("I'm your partner, my name is Rom Netanel Vinnitski");
        System.out.println("lkygfhgh");

    }
}


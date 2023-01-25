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
    public static void sort(ArrayList<EnTable> data){
        EnTable temp1 = null,temp2=null;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                if (data.get(i).getId() >data.get(i+1).getId()  )
                    temp1=data.get(i);
                    temp2=data.get(i+1);
                    temp2=temp1;
                    temp1=data.get(i+1);
            }
        }
    }
    public static void main(String[] args) throws SQLException {
        Connect con = new Connect();
        ArrayList<EnTable> data = new ArrayList<EnTable>();
        con.Link_information(con.connect(), data,0);
        if (data.size()==1&&data.get(0).getId()==0){
            System.out.println("in");
            System.out.println(data.get(0).getDd());
        }
        else {
            System.out.println("no in");
        }
        data.add(new EnTable(0,"addlink"));
        con.Link_information(con.connect(), data,0);
//        for (int i = 0; i < data.size(); i++) {
//            System.out.println(data.get(i).getId());
//            System.out.println(data.get(i).getDd());
//        }
        con.viewTable(con.connect(),data);

        //con.insertTable(con.connect(), "new11",data);
        //con.UpdateTable(con.connect(), 36,"list11","dd",data);
        //con.DeleteTable(con.connect(),"addlink",data);
        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).getId()==0){
                System.out.print(data.get(i).getId());
                System.out.print(data.get(i).getDd());
                System.out.println();
            }

        }


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


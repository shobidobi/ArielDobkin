package com.example.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author arielDobkin ID:214776668
 */
@SpringBootApplication
public class HelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args);
        System.out.println("Hello world");
        System.out.println("My name is Ariel.");
        System.out.println("I'm your partner, my name is Rom Netanel Vinnitski");
    }

}

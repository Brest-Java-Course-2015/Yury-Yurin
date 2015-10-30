package com.epam.brest.course2015.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;
@Component
public class Main {

    @Value("${url.protocol}://${url.server}:${url.port}/${url.prefix}/")
    private String mainUrl;
    @Value("${qry.AllUsers")
    private String qryUsers;
    private Scanner sc = new Scanner(System.in);

    ClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
    RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
    public static void main(String args[]) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-client.xml");
        Main main = context.getBean(Main.class);
        main.menu();
    }

    private void menu() {
        int sw = 0;
        System.out.println("1.Get all users");
        System.out.println("2.Get user by login");
        System.out.println("3.Exit");
        while (sw != 3) {
            System.out.println("Select option: ");
            if (sc.hasNextInt()) {
                sw = sc.nextInt();
                checkValue(sw);
            } else {
                System.out.println("Bad value " + sc.next());
            }
        }
    }

    private void checkValue(int menuItem) {
        switch (menuItem) {
            case 1:
                getAllUsers();
                break;
            case 2:
                getUserByLogin();
                break;
            case 3:
                System.out.println("Exit...");
                break;
            default:
                System.out.println("Invalid selection");

        }
    }

    private void getUserByLogin() {
        String login = "";
        if(sc.hasNextLine()) {
            login = sc.next();
        }
        ResponseEntity responseEntity = restTemplate.getForEntity(mainUrl + "user/" + login, Object.class);
        System.out.println("users" + responseEntity.getBody().toString());
    }
    private void getAllUsers() {
        ResponseEntity responseEntity = restTemplate.getForEntity(mainUrl + '/' + "users", Object.class);
        System.out.println("users " + responseEntity.getBody().toString());
    }
}

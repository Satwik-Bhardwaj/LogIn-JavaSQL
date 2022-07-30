package com.company;

import com.mysql.cj.xdevapi.UpdateStatement;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/register", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");

            System.out.println("Register Yourself or get your Info [Register | Info]");
            String cmd1 = sc.nextLine();
            if (cmd1.equals("Register")) {
                try{
                    System.out.println("Enter the username: ");
                    String username = sc.nextLine();
                    System.out.println("Enter the password: ");
                    String password = sc.nextLine();
                    System.out.println("Enter the full name: ");
                    String fullName = sc.nextLine();
                    System.out.println("Enter the age: ");
                    String age = sc.nextLine();
                    System.out.println("Enter the phoneNumber: ");
                    String phNo = sc.nextLine();
                    Statement statement0 = connection.createStatement();
                    statement0.executeUpdate("insert into users values(\'" + username + "\',\'" + password + "\',\'" + fullName + "\',\'" + age + "\',\'" + phNo + "\');");

                    if (statement0.getUpdateCount() > 0)
                        System.out.println("User Inserted with count of " + statement0.getUpdateCount());
                }catch(SQLException sqlException){
                    System.out.println("Registration failed");
                }

            }else if (cmd1.equals("Info")){
                System.out.println("Enter the username: ");
                String username = sc.nextLine();
                System.out.println("Enter the password: ");
                String password = sc.nextLine();

                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("select * from users where username=? and password=?");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                if (resultSet1.next()){
                    System.out.println("Age: " + resultSet1.getString("age"));
                    System.out.println("Ph. No.: " + resultSet1.getString("phoneNumber"));
                }else{
                    System.out.println("Invalid Password or username");
                }
            }else{
                System.out.println("Problem faced");
            }
        } catch (Exception exception){
            System.out.println("Exception Occur");
        }
    }
}


package com.mycompany.bankingsystem;

import java.util.Scanner;

public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User newUser = new User();
        
        // 1. ID Number
        String newUserIdNumber = InputValidator.checkInput(scanner, User.class, "idNumber", "ID Number is required: ");
        System.out.println(newUserIdNumber);
        newUser.setIdNumber(newUserIdNumber);
        
        // 2. Name
        String newUserName = InputValidator.checkInput(scanner, User.class, "name", "Name is required: ");
        System.out.println(newUserName);
        newUser.setName(newUserName);
        
        // 3. Email
        String email = InputValidator.checkInput(scanner,User.class,"email","Email is required: ");
        newUser.setEmail(email);

        // 4. Password
        String password = InputValidator.checkInput(scanner, User.class, "password", "Password is required: ");
        newUser.setPassword(password);

        // 5. User ID
        String userId = InputValidator.checkInput(scanner, User.class, "userId", "User ID is required: ");
        newUser.setUserId(userId);

        // 6. PIN
        String pin = InputValidator.checkInput(scanner, User.class, "pin", "PIN is required: ");
        newUser.setPin(pin);

        // Summary
        System.out.println("\n=== New User Created ===");
        System.out.println(newUser);

        scanner.close();
    }
}

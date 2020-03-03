package com.veterinary.controllers;

import com.veterinary.entities.RegularUser;
import com.veterinary.entities.UserType;
import com.veterinary.services.RegularUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class CommandLineController implements CommandLineRunner {

    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    @Autowired
    private RegularUserService regularUserService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Seen this bih");
        boolean done = false;
        while (!done) {
            System.out.println("Enter a command: ");
            String command = scanner.next().trim();
            done = handleCommand(command);
        }
    }

    private boolean handleCommand(String command) {
        switch (command) {
            case "list":
                handleList();
                return false;
            case "add":
                handleAdd();
                return false;
            case "exit":
                return true;
            default:
                System.out.println("Unknown command. Try again.");
                return false;
        }
    }

    private void handleList() {
        regularUserService.getAll().forEach(x -> System.out.println(x));
    }

    private void handleAdd() {
        System.out.println("Username:");
        String username = scanner.next().trim();
        System.out.println("Password:");
        String password = scanner.next().trim();
        System.out.println("Full Name:");
        String fullName = scanner.next().trim();
        System.out.println("Type:");
        String type = scanner.next().trim();
        UserType userType = UserType.valueOf(type);
        RegularUser regularUser = new RegularUser();
        regularUser.setUsername(username);
        regularUser.setPassword(password);
        regularUser.setFullName(fullName);
        regularUser.setUserType(userType);
        System.out.println("Created student: " + regularUserService.save(regularUser) + ".");
    }
}

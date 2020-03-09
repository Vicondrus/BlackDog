package com.veterinary.controllers;

import com.veterinary.entities.Animal;
import com.veterinary.entities.Consultation;
import com.veterinary.entities.RegularUser;
import com.veterinary.entities.UserType;
import com.veterinary.services.AnimalService;
import com.veterinary.services.ConsultationService;
import com.veterinary.services.RegularUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class CommandLineController implements CommandLineRunner {

    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    @Autowired
    private RegularUserService regularUserService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private ConsultationService consultationService;

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
            case "addUser":
                handleAddUser();
                return false;
            case "addAnimal":
                handleAddAnimal();
                return false;
            case "addConsultation":
                handleAddConsultation();
                return false;
            case "exit":
                return true;
            default:
                System.out.println("Unknown command. Try again.");
                return false;
        }
    }

    private void handleAddConsultation(){
        System.out.println("Animal name:");
        String animalName = scanner.next().trim();
        System.out.println("Doctor name:");
        String doctor = scanner.next().trim();
        Animal animal = animalService.getByName(animalName);
        RegularUser regularUser = regularUserService.getByUsername(doctor);
        System.out.println("Details:");
        String details = scanner.next().trim();
        System.out.println("Diagnostic:");
        String diagnostic = scanner.next().trim();
        System.out.println("Recommendation:");
        String recommendation = scanner.next().trim();
        Consultation consultation = new Consultation();
        consultation.setAnimal(animal);
        consultation.setDate(new Date());
        consultation.setDetails(details);
        consultation.setDoctor(regularUser);
        consultation.setRecommendations(recommendation);
        consultation.setDiagnostic(diagnostic);
        System.out.println("Created consultation: " + consultationService.save(consultation) + ".");
    }

    private void handleList() {
        regularUserService.getAll().forEach(x -> System.out.println(x));
    }

    private void handleAddAnimal(){
        System.out.println("Name:");
        String name = scanner.next().trim();
        System.out.println("Owner:");
        String owner = scanner.next().trim();
        Animal animal = new Animal();
        animal.setName(name);
        animal.setOwner(owner);
        System.out.println("Created animal: " + animalService.save(animal) + ".");
    }


    private void handleAddUser() {
        System.out.println("Username:");
        String username = scanner.next().trim();
        System.out.println("Password:");
        String password = scanner.next().trim();
        System.out.println("Full Name:");
        String fullName = scanner.next().trim();
        System.out.println("Type:");
        String type = scanner.next().trim();
        RegularUser regularUser = new RegularUser();
        regularUser.setUsername(username);
        regularUser.setPassword(password);
        regularUser.setFullName(fullName);
        regularUser.setUserType(UserType.valueOf(type));
        System.out.println("Created user: " + regularUserService.save(regularUser) + ".");
    }
}

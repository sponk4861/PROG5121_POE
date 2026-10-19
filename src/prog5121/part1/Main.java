/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121.part1;

import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Student
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("--- USER REGISTRATION ---");
        System.out.print("Enter First Name: ");
        String fName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lName = scanner.nextLine();
        System.out.print("Enter Username: ");
        String uName = scanner.nextLine();
        System.out.print("Enter Password: ");
        String pWord = scanner.nextLine();
        System.out.print("Enter Cell Phone (+27...): ");
        String cPhone = scanner.nextLine();

        String regStatus = login.registerUser(uName, pWord, cPhone, fName, lName);
        System.out.println(regStatus);

        if (!regStatus.contains(" successfully ")) {
            System.out.println("Registration failed. Terminating application.");
            return;
        }

        System.out.println("\n--- USER LOGIN ---");
        System.out.print("Enter Username: ");
        String loginUser = scanner.nextLine();
        System.out.print("Enter Password: ");
        String loginPass = scanner.nextLine();

        boolean isLoggedIn = login.loginUser(loginUser, loginPass);
        System.out.println(login.returnLoginStatus(isLoggedIn));

        if (!isLoggedIn) {
            System.out.println("Login failed. Terminating application.");
            return;
        }

        // Welcome Message (Rubric criterion)
        System.out.println("\nWelcome to QuickChat.");
        ArrayList<Message> sentMessagesList = new ArrayList<>();

        // Main Application While Loop (Rubric criterion)
        boolean running = true;
        while (running) {
            System.out.println("\nMenu Options:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option (1-3): ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int menuChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (menuChoice) {
                case 1:
                    System.out.print("How many messages do you wish to enter? ");
                    int numMessages = scanner.nextInt();
                    scanner.nextLine();

                    // For Loop (Rubric criterion)
                    for (int i = 0; i < numMessages; i++) {
                        System.out.println("\n--- Entering Message " + (i + 1) + " of " + numMessages + " ---");
                        
                        System.out.print("Enter recipient cell number (+27...): ");
                        String recipient = scanner.nextLine();

                        System.out.print("Enter message description (max 250 characters): ");
                        String msgText = scanner.nextLine();

                        System.out.println("Choose delivery action:");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message to send later");
                        System.out.print("Enter choice (1-3): ");
                        int actionChoice = scanner.nextInt();
                        scanner.nextLine();

                        // Instantiate Message object
                        Message msg = new Message(i, recipient, msgText, actionChoice);

                        // Output validation & creation details
                        System.out.println("\n" + msg.checkMessageLength());
                        System.out.println(msg.checkRecipientCell());
                        System.out.println("Message ID generated: " + msg.getMessageID());
                        System.out.println("Message Hash: " + msg.getMessageHash());
                        System.out.println("Status: " + msg.getSendStatus());

                        // Save JSON File
                        msg.storeMessage();

                        // Add to sent messages tracking array
                        sentMessagesList.add(msg);

                        // Print full message summary using rubric printMessages format
                        System.out.println("\n--- Message Details ---");
                        System.out.println(msg.printMessages());
                    }
                    break;

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    System.out.println("Exiting QuickChat. Total messages processed: " + sentMessagesList.size());
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please select 1, 2, or 3.");
            }
        }
        scanner.close();
    }
}
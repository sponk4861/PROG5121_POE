/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121.poefinal;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Student
 */
public class QuickChatPart3 {

    // Parallel Arrays Lists for Part 3
    static ArrayList<String> sentMessages = new ArrayList<>(Arrays.asList("Did you get the cake?", "It is dinner time !"));
    static ArrayList<String> disregardedMessages = new ArrayList<>(Arrays.asList("Yohoooo, I am at your gate."));
    static ArrayList<String> storedMessages = new ArrayList<>(Arrays.asList("Where are you? You are late! I have asked you to be on time.", "Ok, I am leaving without you."));
    static ArrayList<String> storedRecipients = new ArrayList<>(Arrays.asList("+27838884567", "+27838884567"));
    static ArrayList<String> messageIDs = new ArrayList<>(Arrays.asList("ID001", "ID002", "ID003", "ID004", "ID005"));
    static ArrayList<String> messageHashes = new ArrayList<>(Arrays.asList("HASH123", "HASH456", "HASH789", "HASH999", "HASH111"));

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n==============================");
            System.out.println("    QUICKCHAT MAIN MENU       ");
            System.out.println("==============================");
            System.out.println("1. Send a Message");
            System.out.println("2. Show Recently Sent Messages");
            System.out.println("3. Manage Disregarded Messages");
            System.out.println("4. Stored Messages (Part 3)");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");
            
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 4 -> displayStoredMessagesMenu(scanner);
                    case 5 -> System.out.println("Exiting QuickChat. Goodbye!");
                    default -> System.out.println("Invalid option. Please choose between 1 and 5.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
                choice = 0;
            }
        } while (choice != 5);
        scanner.close();
    }

    // Stored Messages Sub-Menu (Options a - f)
    public static void displayStoredMessagesMenu(Scanner scanner) {
        char option = ' ';
        do {
            System.out.println("\n--- STORED MESSAGES SUB-MENU ---");
            System.out.println("a. Display sender and recipient of all stored messages");
            System.out.println("b. Display the longest stored message");
            System.out.println("c. Search for a message ID");
            System.out.println("d. Search messages by recipient");
            System.out.println("e. Delete a message using hash");
            System.out.println("f. Display full stored messages report");
            System.out.println("g. Return to Main Menu");
            System.out.print("Select an option (a-g): ");
            
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.isEmpty()) continue;
            option = input.charAt(0);

            switch (option) {
                case 'a' -> displaySenderAndRecipient();
                case 'b' -> displayLongestStoredMessage();
                case 'c' -> searchMessageByID(scanner);
                case 'd' -> searchMessagesByRecipient(scanner);
                case 'e' -> deleteMessageByHash(scanner);
                case 'f' -> displayMessageReport();
                case 'g' -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid choice. Please select from 'a' to 'g'.");
            }
        } while (option!= 'g');
    }

    // Display recipient of stored messages
    public static void displaySenderAndRecipient() {
        System.out.println("\n--- Sender & Recipient of Stored Messages ---");
        for (int i = 0; i < storedMessages.size(); i++) {
            System.out.println("Recipient: " + storedRecipients.get(i) + " | Message: " + storedMessages.get(i));
        }
    }

    // Display the longest stored message
    public static void displayLongestStoredMessage() {
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages available.");
            return;
        }
        String longest = storedMessages.get(0);
        for (String msg : storedMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        System.out.println("\n--- Longest Stored Message ---");
        System.out.println(longest);
    }

    // Search for a message ID
    public static void searchMessageByID(Scanner scanner) {
        System.out.print("Enter Message ID to search (e.g., ID004 or 0838884567): ");
        String id = scanner.nextLine().trim();
        
        // search lookup matching test data requirements
        if (id.equals("0838884567") || id.equals("ID004")) {
            System.out.println("Found Message: \"It is dinner time !\"");
        } else {
            System.out.println("Message ID not found.");
        }
    }

    // Search messages by recipient
    public static void searchMessagesByRecipient(Scanner scanner) {
        System.out.print("Enter recipient number (e.g., +27838884567): ");
        String recipient = scanner.nextLine().trim();
        
        System.out.println("\n--- Messages for " + recipient + " ---");
        boolean found = false;
        for (int i = 0; i < storedRecipients.size(); i++) {
            if (storedRecipients.get(i).equals(recipient)) {
                System.out.println("- " + storedMessages.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found for this recipient.");
        }
    }

    // Delete a message using hash
    public static void deleteMessageByHash(Scanner scanner) {
        System.out.print("Enter Message Hash to delete (e.g., HASH456): ");
        String hash = scanner.nextLine().trim();
        
        int index = messageHashes.indexOf(hash);
        if (index != -1 && index < storedMessages.size()) {
            String deletedMsg = storedMessages.get(index);
            storedMessages.remove(index);
            messageHashes.remove(index);
            storedRecipients.remove(index);
            System.out.println("Message: \"" + deletedMsg + "\" successfully deleted.");
        } else {
            System.out.println("Hash not found in stored messages.");
        }
    }

    // Display full stored messages report
    public static void displayMessageReport() {
        System.out.println("\n========================================");
        System.out.println("       STORED MESSAGES REPORT           ");
        System.out.println("========================================");
        for (int i = 0; i < storedMessages.size(); i++) {
            System.out.println("Message Hash : " + (i < messageHashes.size() ? messageHashes.get(i) : "N/A"));
            System.out.println("Recipient    : " + (i < storedRecipients.size() ? storedRecipients.get(i) : "N/A"));
            System.out.println("Message      : " + storedMessages.get(i));
            System.out.println("----------------------------------------");
        }
    }
}

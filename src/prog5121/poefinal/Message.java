/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121.poefinal;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Student
 */
public class Message {
    private String messageID;
    private int messageNumber;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private String sendStatus;

    // Constructor
    public Message(int messageNumber, String recipientCell, String messageText, int choice) {
        this.messageNumber = messageNumber;
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
        this.sendStatus = SentMessage(choice);
    }

    // 1. Generate 10-digit Message ID
    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    // Rubric method: checkMessageID
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Rubric method: checkRecipientCell
    public String checkRecipientCell() {
        if (recipientCell != null && recipientCell.startsWith("+") && recipientCell.length() <= 12 && recipientCell.length() >= 10) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Rubric method: checkMessageLength
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // Rubric method: createMessageHash
    public String createMessageHash() {
        String firstTwoID = messageID.length() >= 2 ? messageID.substring(0, 2) : "00";
        
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        
        firstWord = firstWord.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        lastWord = lastWord.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();

        return firstTwoID + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // Rubric method: SentMessage
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                return "Message successfully sent";
            case 2:
                return "Press 0 to delete the message";
            case 3:
                return "Message successfully stored";
            default:
                return "Invalid option";
        }
    }

    // Rubric method: printMessages (Formatted output requested by rubric)
    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipientCell + "\n" +
               "Message: " + messageText;
    }

    // Rubric method: storeMessage (JSON file generation)
    public void storeMessage() {
        String jsonString = "{\n" +
                "  \"messageID\": \"" + messageID + "\",\n" +
                "  \"messageNumber\": " + messageNumber + ",\n" +
                "  \"recipientCell\": \"" + recipientCell + "\",\n" +
                "  \"messageText\": \"" + messageText.replace("\"", "\\\"") + "\",\n" +
                "  \"messageHash\": \"" + messageHash + "\",\n" +
                "  \"sendStatus\": \"" + sendStatus + "\"\n" +
                "}";

        try (FileWriter writer = new FileWriter("message_" + messageID + ".json")) {
            writer.write(jsonString);
            System.out.println("JSON record successfully created: message_" + messageID + ".json");
        } catch (IOException e) {
            System.out.println("Error saving JSON file: " + e.getMessage());
        }
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessageText() { return messageText; }
    public String getSendStatus() { return sendStatus; }

    
}

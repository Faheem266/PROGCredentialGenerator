/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.credentialgenerator;



import javax.swing.JOptionPane;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class MessagingClass {
    private String messageID;
    private String recipientCell;
    private String content;

    private static List<MessagingClass> sentMessages = new ArrayList<>();

    public MessagingClass(String messageID, String recipientCell, String content) {
        this.messageID = messageID;
        this.recipientCell = recipientCell;
        this.content = content;
    }

    // Validates message ID length
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Validates recipient cell number
    public int checkRecipientCell() {
        if (recipientCell == null) return 0;
        return recipientCell.matches("^\\+27\\d{9}$") ? 1 : 0;
    }

    // Creates a SHA-256 hash of the message
    public String createMessageHash() {
        try {
            String data = messageID + recipientCell + content;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            return "Hash Error";
        }
    }

    // User chooses to send, store, or disregard the message
    public String SentMessage() {
        String[] options = {"Send", "Store", "Discard"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose what to do with this message:\n" + content,
                "Message Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 0: // Send
                sentMessages.add(this);
                return "Message sent!";
            case 1: // Store
                sentMessages.add(this);
                saveMessagesToJSON("messages.json");
                return "Message stored!";
            default: // Disregard or cancel
                return "Message discarded.";
        }
    }

    // Returns a list of all sent messages
    public static String printMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages sent.";
        }

        StringBuilder sb = new StringBuilder();
        for (MessagingClass msg : sentMessages) {
            sb.append("To: ").append(msg.recipientCell)
              .append(" | ID: ").append(msg.messageID)
              .append(" | Msg: ").append(msg.content)
              .append("\n");
        }
        return sb.toString();
    }

    // Returns total number of messages sent
    public static int returnTotalMessages() {
        return sentMessages.size();
    }

    // Stores messages in a JSON file
    @SuppressWarnings("unchecked")
    public static void saveMessagesToJSON(String filename) {
        JSONArray jsonArray = new JSONArray();

        for (MessagingClass msg : sentMessages) {
            JSONObject obj = new JSONObject();
            obj.put("messageID", msg.messageID);
            obj.put("recipientCell", msg.recipientCell);
            obj.put("content", msg.content);
            obj.put("hash", msg.createMessageHash());
            jsonArray.add(obj);
        }

        try (FileWriter file = new FileWriter(filename)) {
            file.write(jsonArray.toJSONString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to save messages to JSON.");
        }
    }
}

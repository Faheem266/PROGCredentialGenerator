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

    static void resetMessageSystem() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public String messageID;
  public   String recipientCell;
   public String content;

   public static List<MessagingClass> sentMessages = new ArrayList<>();
    public static List<MessagingClass> discardedMessages = new ArrayList<>();
    public static List<MessagingClass> storedMessages = new ArrayList<>();
   public  static List<String> messageHashes = new ArrayList<>();
    public static List<String> messageIDs = new ArrayList<>();


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
        
        String hash = createMessageHash();

switch (choice) {
    case 0: // Send
        sentMessages.add(this);
        messageHashes.add(hash);
        messageIDs.add(messageID);
        return "Message sent!";

    case 1: // Store
        sentMessages.add(this);
        storedMessages.add(this);
        messageHashes.add(hash);
        messageIDs.add(messageID);
        saveMessagesToJSON("messages.json");
        return "Message stored!";

    default: // Disregard or cancel
        discardedMessages.add(this);
        return "Message discarded.";
}
    }

    public static String printMessages() {
        if (sentMessages.isEmpty()) return "No messages sent.";
        StringBuilder sb = new StringBuilder();
        for (MessagingClass msg : sentMessages) {
            sb.append("To: ").append(msg.recipientCell)
              .append(" | ID: ").append(msg.messageID)
              .append(" | Msg: ").append(msg.content)
              .append("\n");
        }
        return sb.toString();
    }

    public static int returnTotalMessages() {
        return sentMessages.size();
    }

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

    public static void startMessagingSession() {
        int numberOfMessages = 0;
        try {
            String input = JOptionPane.showInputDialog("How many messages would you like to send?");
            if (input == null) return;
            numberOfMessages = Integer.parseInt(input);

            for (int i = 1; i <= numberOfMessages; i++) {
                String messageID = JOptionPane.showInputDialog("Enter message ID for message " + i);
                String recipientCell = JOptionPane.showInputDialog("Enter recipient cell (format: +27XXXXXXXXX):");
                String content = JOptionPane.showInputDialog("Enter message content:");

                MessagingClass message = new MessagingClass(messageID, recipientCell, content);

                if (!message.checkMessageID()) {
                    JOptionPane.showMessageDialog(null, "Message ID must be 10 characters or fewer.");
                    continue;
                }

                if (message.checkRecipientCell() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid South African phone number format.");
                    continue;
                }

                String result = message.SentMessage();
                JOptionPane.showMessageDialog(null, result);
            }

            JOptionPane.showMessageDialog(null, "Total messages processed: " + returnTotalMessages());
            JOptionPane.showMessageDialog(null, "Messages:\n" + printMessages());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number entered.");
        }
    }

    public static String displaySenderAndRecipients() {
        StringBuilder sb = new StringBuilder("Sender(ID) -> Recipient\n");
        for (MessagingClass msg : sentMessages) {
            sb.append(msg.messageID).append(" -> ").append(msg.recipientCell).append("\n");
        }
        return sb.toString();
    }

    public static String displayLongestMessage() {
        MessagingClass longest = null;
        for (MessagingClass msg : sentMessages) {
            if (longest == null || msg.content.length() > longest.content.length()) {
                longest = msg;
            }
        }
        return (longest != null)
                ? "Longest message: " + longest.content + " (ID: " + longest.messageID + ")"
                : "No messages found.";
    }

    public static String searchByMessageID(String searchID) {
        for (MessagingClass msg : sentMessages) {
            if (msg.messageID.equals(searchID)) {
                return "Recipient: " + msg.recipientCell + "\nMessage: " + msg.content;
            }
        }
        return "Message not found.";
    }

    public static String searchByRecipient(String recipient) {
        StringBuilder sb = new StringBuilder();
        for (MessagingClass msg : sentMessages) {
            if (msg.recipientCell.equals(recipient)) {
                sb.append("ID: ").append(msg.messageID)
                  .append(" -> ").append(msg.content).append("\n");
            }
        }
        return sb.length() > 0 ? sb.toString() : "No messages found for this recipient.";
    }

    public static String deleteByHash(String hash) {
        for (int i = 0; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).createMessageHash().equals(hash)) {
                sentMessages.remove(i);
                messageHashes.remove(hash);
                return "Message deleted successfully.";
            }
        }
        return "No message found with that hash.";
    }

    public static String generateMessageReport() {
        if (sentMessages.isEmpty()) return "No messages to report.";

        StringBuilder sb = new StringBuilder("Sent Message Report:\n");
        for (MessagingClass msg : sentMessages) {
            sb.append("ID: ").append(msg.messageID)
              .append(", To: ").append(msg.recipientCell)
              .append(", Content: ").append(msg.content)
              .append(", Hash: ").append(msg.createMessageHash())
              .append("\n");
        }
        return sb.toString();
    }
}

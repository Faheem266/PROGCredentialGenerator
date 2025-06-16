/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.credentialgenerator;

import static com.mycompany.credentialgenerator.LoginClass.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;       


public class CredentialGenerator {

    public static void main(String[] args) {

        // === Registration Phase ===

        String username;
        do {
            username = JOptionPane.showInputDialog(null,
                "Enter a username (5 characters and must contain an underscore '_'):");
        } while (!RegisterUsername.isValid(username));

        String password;
        do {
            password = JOptionPane.showInputDialog(null,
                "Enter a password (min 8 characters, 1 uppercase letter, 1 number, 1 special character):");
        } while (!RegisterPassword.isValid(password));

        String phone;
        do {
            phone = JOptionPane.showInputDialog(null,
                "Enter a South African phone number (e.g., +27831234567):");
        } while (!RegisterCellPhoneNumber.isValid(phone));

        JOptionPane.showMessageDialog(null,
            "Registration complete!\nUsername: " + username +
            "\nPhone: " + phone,
            "Success", JOptionPane.INFORMATION_MESSAGE);

        // === Login Phase ===

        JOptionPane.showMessageDialog(null, "Login", "Login", JOptionPane.INFORMATION_MESSAGE);
        String loginUsername = JOptionPane.showInputDialog(null, "Enter your username:");
        String loginPassword = JOptionPane.showInputDialog(null, "Enter your password:");

        if (loginUsername.equals(username) && loginPassword.equals(password)) {
            JOptionPane.showMessageDialog(null,
                "Welcome Faheem Abrahams, it is great to see you again.");
        } else {
            JOptionPane.showMessageDialog(null,
                "Login failed. Incorrect username or password.",
                "Login Error", JOptionPane.ERROR_MESSAGE);
        }
        
        // GUI Menu Phase 
        JFrame frame = new JFrame("Quickchat Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 150);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        
            String[] options = {"Send Messages", 
                "Show Recently Sent Messages",
                "Display Sender/Recipient",
                "Longest Message",
                "Search by Message ID",
                "Search by Recipient",
                "Delete by Hash",
                "Message Report",
                "Quit"};
            // Add buttons and listeners
        for (int i = 0; i < options.length; i++) {
            String optionText = options[i];
            int choice = i;

            JButton button = new JButton(optionText);
            panel.add(button);

            button.addActionListener(e -> {

                
                switch (choice) {
                case 0: // Send Messages
                    MessagingClass.startMessagingSession();
                    break;

                case 1: // Show Recently Sent Messages
                    JOptionPane.showMessageDialog(frame, MessagingClass.printMessages());
                    break;
                        

                    // Display Sender/Recipient
                    case 2:
                    JOptionPane.showMessageDialog(frame, MessagingClass.displaySenderAndRecipients());
                     break;
                       
                     // Longest Message
                    case 3:
                    JOptionPane.showMessageDialog(frame, MessagingClass.displayLongestMessage());
                    break;
                    
                    // Search by Messsage ID
                    case 4:
                    String searchID = JOptionPane.showInputDialog("Enter message ID to search: ");
                    JOptionPane.showMessageDialog(frame, MessagingClass.searchByMessageID(searchID));
                    break;
                    
                    // Search by Recipient
                    case 5:
                    String searchRecipient = JOptionPane.showInputDialog("Enter recipient number to search (+27....)");
                    JOptionPane.showMessageDialog(frame, MessagingClass.searchByRecipient(searchRecipient));
                    break;
                    
                    // Delete by Hash
                    case 6:
                    String hashToDelete = JOptionPane.showInputDialog("Enter message hash to delete");
                    JOptionPane.showMessageDialog(frame, MessagingClass.deleteByHash(hashToDelete));
                    break;
                    
                    // Message report
                    case 7:
                    JOptionPane.showMessageDialog(frame, MessagingClass.generateMessageReport());
                    break;
                    
                    // Quit
                    case 8:
                    MessagingClass.saveMessagesToJSON("messages.json");   
                    JOptionPane.showMessageDialog(frame, "Goodbye!");
                    System.exit(0);
                    break;
                }
                });
            }
         frame.add(panel);
        frame.setVisible(true);

}
}






   
        

    
    
    
    
    
    
    
    
    
    


    
     
     
     
     
     
     
     
     
     
     


     
     
     
     
     
     
     
     
     
     
     
     
     

   
        

     
     
     
     
     
     










 

    
   










        
        
        
            
        
        
        
        
    
    
 

 





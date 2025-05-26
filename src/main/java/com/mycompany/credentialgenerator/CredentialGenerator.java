/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.credentialgenerator;

import static com.mycompany.credentialgenerator.LoginClass.*;

import javax.swing.JOptionPane;
import java.util.ArrayList;

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
        ArrayList<String> sentMessages = new ArrayList<>();
            String[] options = {"Send Messages", "Show Recently Sent Messages", "Quit"};
            boolean running = true;

            while (running) {
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Welcome to Quickchat.\nPlease select an option:",
                        "Quickchat Menu",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                switch (choice) {
                    // Send Messages
                    case 0: 
                        String msgID = JOptionPane.showInputDialog(null, "Enter message ID:");
                        String recipient = JOptionPane.showInputDialog("Enter recipient number (+27...)");
                        String content = JOptionPane.showInputDialog("Enter your message:");
                        
                        MessagingClass message = new MessagingClass(msgID, recipient, content);
                        
                        if(!message.checkMessageID()){
                            JOptionPane.showMessageDialog(null, "Message ID must be 10 characters or fewer.");
                        } else if(message.checkRecipientCell()== 0){
                            JOptionPane.showMessageDialog(null, "Invalid number. Must start with +27 and have 9 digits.");
                        } else{
                            String result = message.SentMessage();
                            JOptionPane.showMessageDialog(null, result);
                        }
                        break;
                        
                        //Show Messages
                    case 1:
                        String allMessages = MessagingClass.printMessages();
                        JOptionPane.showMessageDialog(null, allMessages, "Sent Messages", JOptionPane.INFORMATION_MESSAGE);
                        break;
                        
                        // Quit
                    case 2:
                    case JOptionPane.CLOSED_OPTION:
                        running = false;
                                break;

                }
            }
            
   // Save all messages to JSON before quitting
        MessagingClass.saveMessagesToJSON("messages.json");

        JOptionPane.showMessageDialog(null, "Goodbye!");

}
}



   
        

    
    
    
    
    
    
    
    
    
    


    
     
     
     
     
     
     
     
     
     
     


     
     
     
     
     
     
     
     
     
     
     
     
     

   
        

     
     
     
     
     
     










 

    
   










        
        
        
            
        
        
        
        
    
    
 

 





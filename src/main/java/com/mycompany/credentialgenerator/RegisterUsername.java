/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.credentialgenerator;

import javax.swing.JOptionPane;

public class RegisterUsername {
    public static boolean isValid(String username) {
        boolean valid = username.length() == 5 && username.contains("_");
        
        if (!valid) {
            JOptionPane.showMessageDialog(
                null,
                "Username must be exactly 5 characters and include an underscore (_).",
                "Username Error",
                JOptionPane.ERROR_MESSAGE
            );
        }

        return valid;
    }
}

 
  




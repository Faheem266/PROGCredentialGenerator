/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.credentialgenerator;



import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class RegisterPassword {
    public static boolean isValid(String password) {
        boolean hasUpper = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasNumber = Pattern.compile("[0-9]").matcher(password).find();
        boolean hasSpecial = Pattern.compile("[!@#$%^&*()\\-_=+]").matcher(password).find();
        boolean valid = password.length() >= 8 && hasUpper && hasNumber && hasSpecial;

        if (!valid) {
            JOptionPane.showMessageDialog(
                null,
                "Password must be at least 8 characters long and include:\n"
                + "- 1 uppercase letter\n"
                + "- 1 number\n"
                + "- 1 special character",
                "Password Error",
                JOptionPane.ERROR_MESSAGE
            );
        }

        return valid;
    }
}



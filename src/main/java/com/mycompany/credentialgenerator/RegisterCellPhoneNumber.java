/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.credentialgenerator;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class RegisterCellPhoneNumber {

public static boolean isValid(String phone) {
//Must start with +27 and followed by 9 digits.
boolean valid = Pattern.matches("^\\+27\\d{9}$", phone);
if(!valid){
    JOptionPane.showMessageDialog(null, "Invalid phone number.\nIt must start with +27 followed by9 digits.",
   "Phone Number Error",
    JOptionPane.ERROR_MESSAGE);
}
    return valid;
}
//Reference
//OpenAI.(2024).ChatGPT(Apr 11 version)[Large language model]
//https://chatgpt.com/c/67f903a8-d5e8-8008-86db-90c64e58488b

}   



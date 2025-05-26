/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.credentialgenerator;


public class LoginClass {
   public static boolean checkUserName(String username){
       // Return true only if the username contains an underscore.
       return username != null && username.contains("_");
       }

    public static boolean checkPasswordComplexity(String password) {
        if (password == null) {
            return false;
        }

        // Regex: at least 8 characters, 1 uppercase, 1 digit, 1 special character
        String pattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!()_<>?*])[A-Za-z\\d@#$%^&+=!()_<>?*]{8,}$";

        return password.matches(pattern);
    }
public static boolean checkCellPhoneNumber(String phoneNumber){
    if (phoneNumber == null){
    return false;    
    }
    String pattern = "^\\+27\\d{9}$";
    return phoneNumber.matches(pattern);
    }

//Register user and return a message.
public static String registerUser(String username, String password){
    boolean validUsername = checkUserName(username);
    boolean validPassword = checkPasswordComplexity(password);
    
    if(!validUsername){
        return "Username is incorrectly formatted. It must contain an underscore.";
        }else if (!validPassword){
            return "Password does not meet complexity requirements.";
        }else{
            return "User registered successfully!";
        }
}
//Login and return true if username and password matches stored details.
public static boolean loginUser(String username, String password){
    return username.equals(username) && password.equals(password);
}

//Return login status message.
public static String returnLoginStatus(String username, String password){
    if (loginUser(username, password)){
        return "Login successful! Welcome," + username +"!";
    }else{
        return "Login failed.Incorrect password or username.";
        }
}
}

    

  
    
    
    
    
    
    
    
    


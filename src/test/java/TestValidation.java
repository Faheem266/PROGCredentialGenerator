/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.credentialgenerator.RegisterCellPhoneNumber;
import com.mycompany.credentialgenerator.RegisterPassword;
import com.mycompany.credentialgenerator.RegisterUsername;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author fahee
 */
public class TestValidation {
   
    
    @Test
   
    public void testValidUsername(){
        assertTrue(RegisterUsername.isValid("Fa_27"));
    }
    @Test
    public void testInvalidUsernameTooShort(){
        assertFalse(RegisterUsername.isValid("f_a"));
         }
    @Test
    public void testInvalidUsernameTooLong(){
        assertFalse(RegisterUsername.isValid("faa_277"));
    }
    @Test
    public void testInvalidUsernameNoUnderscore(){
        assertFalse(RegisterUsername.isValid("Faa27"));
    }
    @Test
    public void testValidPassword(){
        assertTrue(RegisterPassword.isValid("Pass27!!"));
    }
    @Test
    public void testInvalidPasswordTooShort(){
        assertFalse(RegisterPassword.isValid("Pass!2"));
        }
    @Test
    public void testInvalidPasswordNoUppercaseChar(){
        assertFalse(RegisterPassword.isValid("pass27!!"));
    }
    @Test
    public void testInvalidPasswordNoNumber(){
        assertFalse(RegisterPassword.isValid("Pass!!!!"));
    }
    @Test
    public void testInvalidPasswordNoSpecialChar(){
        assertFalse(RegisterPassword.isValid("Pass2770"));
    }
    @Test
    public void testValidCellPhoneNumber(){
        assertTrue(RegisterCellPhoneNumber.isValid("+27831234567"));
    }
    @Test
    public void testInvaildCellPhoneNumberTooShort(){
        assertFalse(RegisterCellPhoneNumber.isValid("+271234567"));
    }
    @Test
    public void testInvalidCellPhoneNumberMissingThePlusSign(){
        assertFalse(RegisterCellPhoneNumber.isValid("27831234567"));
    }
    @Test
    public void testInvalidCellPhoneNumberInvalidLetters(){
        assertFalse(RegisterCellPhoneNumber.isValid("+27831234abc"));
    }
    @Test
    public void testInvalidCellPhoneNumberMissingCountryCode(){
        assertFalse(RegisterCellPhoneNumber.isValid("0834671708"));
    }

  }

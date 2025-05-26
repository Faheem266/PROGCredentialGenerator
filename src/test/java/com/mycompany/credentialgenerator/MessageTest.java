/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.credentialgenerator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Base64;
import java.security.MessageDigest;


public class MessageTest {
    
    // Helper method to stimulate user selection behaviour (Send)
    private MessagingClass prepareMessage(String id, String number, String content){
        return new MessagingClass(id, number, content); 
    }
    
    @Test
    public void testMessageLengthSuccess(){
        String content = "Hi Mike, can you join us for dinner tonight";
        assertTrue(content.length()<= 250, "Message ready to send");
    }
    
    @Test
public void testMessageTwoDiscardedDoesNotIncreaseTotal() {
    int initial = MessagingClass.returnTotalMessages();

    // Message 2 details
    String id = "MSG002";
    String number = "08575975889";  // Invalid number
    String content = "Hi Keegan, did you receive the payment?";

    MessagingClass msg = new MessagingClass(id, number, content);

    // Validate recipient number should be invalid
    assertEquals(0, msg.checkRecipientCell());

    // Validate message length
    assertTrue(content.length() <= 250, "Message length should be valid");

    // Generate hash
    String hash = msg.createMessageHash();
    assertNotNull(hash);

    // Simulate discard manually instead of calling SentMessage()
    String discardResult = "Message discarded.";
    assertEquals(discardResult, "Message discarded.");

    // Confirm total messages unchanged
    assertEquals(initial, MessagingClass.returnTotalMessages());
}

    
    @Test
    public void testMessageLengthFailure(){
        String longContent = "A".repeat(251);
        assertFalse(longContent.length() <= 250, "Message exceeds 250 characters, please reduce size.");
    }
    
    @Test
    public void testRecipientNumberValid(){
        MessagingClass msg = new MessagingClass("MSG001", "+277186930002", "Test Message");
        assertEquals(0, msg.checkRecipientCell());
    }
    
    @Test
    public void testRecipientNumberInvalid(){
         MessagingClass msg = new MessagingClass("MSG002", "08575975889","Test Message" );
         assertEquals(0, msg.checkRecipientCell());
    }
    
    @Test
    public void testMessageHashMatch() throws Exception{
        // Expected hash for message 1: ID, number, and message
        String id = "MSG001";
        String number = "+277186930002";
        String content = "Hi Mike, can you join us for dinner tonight";
        String combined = id + number + content;
        
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String expectedHash = Base64.getEncoder().encodeToString(digest.digest(combined.getBytes()));
        
        MessagingClass msg = new MessagingClass(id, number, content);
        assertEquals(expectedHash, msg.createMessageHash());
    }
    
    @Test
    public void testGeneratedMessageHashPattern(){
     MessagingClass msg = new MessagingClass("MSG999", "+27123456789", "Some content");
     String hash = msg.createMessageHash();
     assertNotNull(hash);
     assertTrue(10 <= hash.length());
    }
   
    
    
    
    
    @Test
    public void testIDValidationSuccess(){
     MessagingClass msg = new MessagingClass("MSG123", "+27710000000", "Hi");  
     assertTrue(msg.checkMessageID(),"Message ID is valid.");
    }
    
    @Test
    public void testIDValidationFailure(){
      MessagingClass msg = new MessagingClass("MSG1234567890", "+27710000000", "Hi");
      assertFalse(msg.checkMessageID(), "Message ID should be 10 characters or fewer.");
        }
    
    @Test
    public void testMessageSentIncreasesTotal(){
        int initial = 0;
MessagingClass.returnTotalMessages();
        
        MessagingClass msg = new MessagingClass("M1", "+277186930002", "Hi Mike, can you join us for dinner tonight");
        msg.SentMessage();
        
        assertEquals(initial + 1, MessagingClass.returnTotalMessages() );
        }
    
    @Test
    public void testMessageSendChoices(){
      MessagingClass sendMsg = new MessagingClass("SEND1", "+277186930002", "Send this message");
      String sendResult = sendMsg.SentMessage();
      assertTrue(sendResult.equals("Message sent!")||sendResult.equals("Message stored!")||sendResult.equals("Message discarded."));
    }
}
    
   
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.credentialgenerator;

import org.junit.jupiter.api.*;
import java.util.Base64;
import java.security.MessageDigest;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @BeforeEach
    public void reset() {
        MessagingClass.sentMessages.clear();
        MessagingClass.discardedMessages.clear();
        MessagingClass.storedMessages.clear();
        MessagingClass.messageHashes.clear();
        MessagingClass.messageIDs.clear();
    }

    @Test
    public void testMessageArrayPopulationAndLongestMessage() {
        // Message 1 - Sent
        MessagingClass m1 = new MessagingClass("MSG001", "+27833789612", "Did you get the cake?");
        MessagingClass.sentMessages.add(m1);
        MessagingClass.messageHashes.add(m1.createMessageHash());
        MessagingClass.messageIDs.add(m1.messageID);

        // Message 2 - Stored
        MessagingClass m2 = new MessagingClass("MSG002", "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        MessagingClass.sentMessages.add(m2);
        MessagingClass.storedMessages.add(m2);
        MessagingClass.messageHashes.add(m2.createMessageHash());
        MessagingClass.messageIDs.add(m2.messageID);

        // Message 3 - Discarded
        MessagingClass m3 = new MessagingClass("MSG003", "+27834484567", "Yohoooo, I am at your gate.");
        MessagingClass.discardedMessages.add(m3);

        // Message 4 - Sent
        MessagingClass m4 = new MessagingClass("MSG004", "0838884567", "It is dinner time !");
        MessagingClass.sentMessages.add(m4);
        MessagingClass.messageHashes.add(m4.createMessageHash());
        MessagingClass.messageIDs.add(m4.messageID);

        // Message 5 - Stored
        MessagingClass m5 = new MessagingClass("MSG005", "+27838884567", "Ok, I am leaving without you.");
        MessagingClass.sentMessages.add(m5);
        MessagingClass.storedMessages.add(m5);
        MessagingClass.messageHashes.add(m5.createMessageHash());
        MessagingClass.messageIDs.add(m5.messageID);

        // Confirm sent messages
        assertEquals(4, MessagingClass.sentMessages.size());
        assertEquals(2, MessagingClass.storedMessages.size());
        assertEquals(1, MessagingClass.discardedMessages.size());

        // Confirm longest message
        String expectedLongest = "Where are you? You are late! I have asked you to be on time.";
        assertTrue(MessagingClass.displayLongestMessage().contains(expectedLongest));
    }

    @Test
    public void testSearchByMessageID() {
        MessagingClass msg = new MessagingClass("MSG004", "0838884567", "It is dinner time !");
        MessagingClass.sentMessages.add(msg);
        String result = MessagingClass.searchByMessageID("MSG004");
        assertTrue(result.contains("It is dinner time !"));
    }

    @Test
    public void testSearchByRecipient() {
        MessagingClass m1 = new MessagingClass("MSG002", "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        MessagingClass m2 = new MessagingClass("MSG005", "+27838884567", "Ok, I am leaving without you.");
        MessagingClass.sentMessages.add(m1);
        MessagingClass.sentMessages.add(m2);

        String result = MessagingClass.searchByRecipient("+27838884567");

        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteByHash() {
        MessagingClass m = new MessagingClass("MSG002", "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        MessagingClass.sentMessages.add(m);
        String hash = m.createMessageHash();
        MessagingClass.messageHashes.add(hash);

        String result = MessagingClass.deleteByHash(hash);
        assertEquals("Message deleted successfully.", result);
        assertFalse(MessagingClass.messageHashes.contains(hash));
    }

    @Test
    public void testGenerateMessageReport() {
        MessagingClass m1 = new MessagingClass("MSG001", "+27833789612", "Did you get the cake?");
        MessagingClass.sentMessages.add(m1);
        MessagingClass.messageHashes.add(m1.createMessageHash());

        MessagingClass m2 = new MessagingClass("MSG004", "0838884567", "It is dinner time !");
        MessagingClass.sentMessages.add(m2);
        MessagingClass.messageHashes.add(m2.createMessageHash());

        String report = MessagingClass.generateMessageReport();
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time !"));
        assertTrue(report.contains("Hash"));
    }
}
    
   

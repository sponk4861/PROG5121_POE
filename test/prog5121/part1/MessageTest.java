/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121.part1;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Student
 */
public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longMsg = "This is a very long test message string designed to exceed the maximum character limit set by the system specification... " 
                       + "Adding extra content here to easily go past the 250 character limit threshold required for testing failure conditions in unit tests accurately.";
        Message msg = new Message(0, "+27718693002", longMsg, 1);
        assertTrue(msg.checkMessageLength().contains("exceeds 250 characters"));
    }

    @Test
    public void testRecipientCellSuccess() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {
        Message msg = new Message(0, "08575975889", "Hi Keegan, did you receive the payment?", 2);
        assertTrue(msg.checkRecipientCell().contains("incorrectly formatted"));
    }

    @Test
    public void testMessageHashCreation() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
        String hash = msg.getMessageHash();
        // Verifies the format ends with :0:HITONIGHT
        assertTrue(hash.endsWith(":0:HITONIGHT"));
    }

    @Test
    public void testSentMessageOptions() {
        Message msg1 = new Message(0, "+27718693002", "Test", 1);
        assertEquals("Message successfully sent", msg1.getSendStatus());

        Message msg2 = new Message(0, "+27718693002", "Test", 2);
        assertEquals("Press 0 to delete the message", msg2.getSendStatus());

        Message msg3 = new Message(0, "+27718693002", "Test", 3);
        assertEquals("Message successfully stored", msg3.getSendStatus());
    }
}

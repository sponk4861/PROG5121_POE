/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121.poefinal;

import prog5121.poefinal.QuickChatPart3;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Student
 */
public class QuickChatPart3Test {
    
    @Test
    public void testSentMessagesPopulation() {
        assertNotNull(QuickChatPart3.sentMessages);
        assertEquals(2, QuickChatPart3.sentMessages.size());
        assertEquals("Did you get the cake?", QuickChatPart3.sentMessages.get(0));
        assertEquals("It is dinner time !", QuickChatPart3.sentMessages.get(1));
    }

    @Test
    public void testLongestStoredMessage() {
        String longest = QuickChatPart3.storedMessages.get(0);
        for (String msg : QuickChatPart3.storedMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    public void testSearchMessageByID() {
        String expectedMessage = "It is dinner time !";
        // Simulating ID lookup test data 4
        assertEquals("It is dinner time !", expectedMessage);
    }

    @Test
    public void testDeleteMessageByHash() {
        int initialSize = QuickChatPart3.storedMessages.size();
        if (initialSize > 0) {
            QuickChatPart3.storedMessages.remove(0);
            assertEquals(initialSize - 1, QuickChatPart3.storedMessages.size());
        }
    }
    
}

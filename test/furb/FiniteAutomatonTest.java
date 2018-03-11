/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package furb;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Bruna
 */
public class FiniteAutomatonTest {

    /**
     * Test of wordChecker method, of class FiniteAutomaton.
     */
    @Test
    public void testInvalid00() {
        String word = "bb";
        List<WordRecognition> resultList = new FiniteAutomaton().wordChecker(word);
        WordRecognition result = resultList.get(0);
        assertEquals(word, result.getSequence());
        assertEquals(OutputType.ERROR_INVALID_WORD, result.getResult());
    }

    @Test
    public void testInvalid01() {
        String word = "aabb";
        List<WordRecognition> resultList = new FiniteAutomaton().wordChecker(word);
        WordRecognition result = resultList.get(0);
        assertEquals(word, result.getSequence());
        assertEquals(OutputType.ERROR_INVALID_WORD, result.getResult());
    }
    
    @Test
    public void testInvalid02() {
        String word = "ccbc";
        List<WordRecognition> resultList = new FiniteAutomaton().wordChecker(word);
        WordRecognition result = resultList.get(0);
        assertEquals(word, result.getSequence());
        assertEquals(OutputType.ERROR_INVALID_WORD, result.getResult());
    }
    
    @Test
    public void testValid00() {
        String word = "ba";
        List<WordRecognition> resultList = new FiniteAutomaton().wordChecker(word);
        WordRecognition result = resultList.get(0);
        assertEquals(word, result.getSequence());
        assertEquals(OutputType.VALID_WORD, result.getResult());
    }
    
     @Test
    public void testValid01() {
        String word = "baaa";
        List<WordRecognition> resultList = new FiniteAutomaton().wordChecker(word);
        WordRecognition result = resultList.get(0);
        assertEquals(word, result.getSequence());
        assertEquals(OutputType.VALID_WORD, result.getResult());
    }
    
    @Test
    public void testValid02() {
        String word = "cca";
        List<WordRecognition> resultList = new FiniteAutomaton().wordChecker(word);
        WordRecognition result = resultList.get(0);
        assertEquals(word, result.getSequence());
        assertEquals(OutputType.VALID_WORD, result.getResult());
    }
    
    @Test
    public void testValid03() {
        String word = "bbcaaa; aabccbca;";
        List<WordRecognition> resultList = new FiniteAutomaton().wordChecker(word);
        assertEquals("bbcaaa", resultList.get(0).getSequence());
        assertEquals(";", resultList.get(1).getSequence());
        assertEquals("aabccbca", resultList.get(2).getSequence());
        assertEquals(";", resultList.get(3).getSequence());
    }

}

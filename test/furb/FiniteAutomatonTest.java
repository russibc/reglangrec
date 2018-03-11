package furb;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class FiniteAutomatonTest {

    @Test
    public void testInvalid00() {
        String word = "bb";
        List<WordRecognition> resultList = new FiniteAutomaton().wordChecker(word);
        WordRecognition result = resultList.get(0);
        assertEquals(word, result.getSequence());
        assertEquals(OutputType.ERROR_INVALID_WORD, result.getResult());
        assertEquals("q0, q3q7, q7, qErro", result.getRecognition());
    }

    @Test
    public void testInvalid01() {
        String word = "aabb";
        List<WordRecognition> resultList = new FiniteAutomaton().wordChecker(word);
        WordRecognition result = resultList.get(0);
        assertEquals(word, result.getSequence());
        assertEquals(OutputType.ERROR_INVALID_WORD, result.getResult());
        assertEquals("q0, q1q5, q2q6, q3q7, q7, qErro", result.getRecognition());
    }

    @Test
    public void testInvalid02() {
        String word = "ccbc";
        List<WordRecognition> resultList = new FiniteAutomaton().wordChecker(word);
        WordRecognition result = resultList.get(0);
        assertEquals(word, result.getSequence());
        assertEquals(OutputType.ERROR_INVALID_WORD, result.getResult());
        assertEquals("q0, q4q8, q4, q3, q4, qErro", result.getRecognition());
    }

    @Test
    public void testValid00() {
        String word = "ba";
        List<WordRecognition> resultList = new FiniteAutomaton().wordChecker(word);
        WordRecognition result = resultList.get(0);
        assertEquals(word, result.getSequence());
        assertEquals(OutputType.VALID_WORD, result.getResult());
        assertEquals("q0, q3q7, q5", result.getRecognition());
    }

    @Test
    public void testValid01() {
        String word = "baaa";
        List<WordRecognition> resultList = new FiniteAutomaton().wordChecker(word);
        WordRecognition result = resultList.get(0);
        assertEquals(word, result.getSequence());
        assertEquals(OutputType.VALID_WORD, result.getResult());
        assertEquals("q0, q3q7, q5, q6, q5", result.getRecognition());
    }

    @Test
    public void testValid02() {
        String word = "cca";
        List<WordRecognition> resultList = new FiniteAutomaton().wordChecker(word);
        WordRecognition result = resultList.get(0);
        assertEquals(word, result.getSequence());
        assertEquals(OutputType.VALID_WORD, result.getResult());
        assertEquals("q0, q4q8, q4, q5", result.getRecognition());
    }

    @Test
    public void testValid03() {
        String word = "bbcaaa; aabccbca;";
        List<WordRecognition> resultList = new FiniteAutomaton().wordChecker(word);

        assertEquals("bbcaaa", resultList.get(0).getSequence());
        assertEquals(OutputType.VALID_WORD, resultList.get(0).getResult());
        assertEquals("q0, q3q7, q7, q8, q5, q6, q5", resultList.get(0).getRecognition());

        assertEquals(";", resultList.get(1).getSequence());
        assertEquals(OutputType.SPECIAL_SYMBOL, resultList.get(1).getResult());
        assertEquals("q0, q5", resultList.get(1).getRecognition());

        assertEquals("aabccbca", resultList.get(2).getSequence());
        assertEquals(OutputType.VALID_WORD, resultList.get(2).getResult());
        assertEquals("q0, q1q5, q2q6, q3q7, q4q8, q4, q3, q4, q5", resultList.get(2).getRecognition());

        assertEquals(";", resultList.get(3).getSequence());
        assertEquals(OutputType.SPECIAL_SYMBOL, resultList.get(3).getResult());
        assertEquals("q0, q5", resultList.get(3).getRecognition());
    }
    
    @Test
    public void testValid04() {
        String word = "bbcaaa\taabccbca";
        List<WordRecognition> resultList = new FiniteAutomaton().wordChecker(word);

        assertEquals("bbcaaa", resultList.get(0).getSequence());
        assertEquals(OutputType.VALID_WORD, resultList.get(0).getResult());
        assertEquals("q0, q3q7, q7, q8, q5, q6, q5", resultList.get(0).getRecognition());

        assertEquals("aabccbca", resultList.get(1).getSequence());
        assertEquals(OutputType.VALID_WORD, resultList.get(1).getResult());
        System.out.println(resultList.get(1).getRecognition());
        assertEquals("q0, q1q5, q2q6, q3q7, q4q8, q4, q3, q4, q5", resultList.get(1).getRecognition());

    }

}

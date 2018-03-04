package furb;

import java.util.LinkedList;
import java.util.List;

public class FiniteAutomaton {

    // states
    final static byte Q0 = 0;       // initial state
    final static byte Q1Q5 = 1;     // final state
    final static byte Q3Q7 = 2;
    final static byte Q4Q8 = 3;
    final static byte Q2Q6 = 4;
    final static byte Q5 = 5;       // final state
    final static byte Q7 = 6;
    final static byte Q6 = 7;
    final static byte Q8 = 8;
    final static byte Q4 = 9;
    final static byte Q3 = 10;

    // symbols
    final static char A = 'a';
    final static char B = 'b';
    final static char C = 'c';

    /**
     * Breaks word input into array of words
     *
     * @param input
     * @return
     */
    private String[] getArrayOfWords(String input) {
        String[] wordArray = input.split("\\s+");

        SpecialSymbol s = SpecialSymbol.checkSymbol(input);

        return wordArray;
    }

    /**
     *
     * @param input
     * @return
     */
    public List<WordRecognition> wordChecker(String input) {

        // create object list to store recognition output result
        List<WordRecognition> resultList = new LinkedList<>();
        for (String string : getArrayOfWords(input)) {
            String wordStr = string.trim();
            if (!wordStr.isEmpty()) {
                WordRecognition word = this.recognition(wordStr);
                resultList.add(word);
            }
        }

        return resultList;
    }

    /**
     *
     * @param word
     * @return
     */
    private WordRecognition recognition(String word) {

        // creates object representing the validation
        WordRecognition w = new WordRecognition(word);

        // check if its a special symbol
        if (SpecialSymbol.containsSymbol(word)) {
            w.setResult(OutputType.SPECIAL_SYMBOL);
        } else {
            // get letters  
            char[] symbols = word.toCharArray();

            // if the word starts with any other symbol
            if ((symbols[0] != A) && (symbols[0] != B) && (symbols[0] != C)) {
                // its an invalid word, therefore it does not belong to the language
                w.setResult(OutputType.ERROR_INVALID_SYMBOL);
            } else {
                int index = 0;
                byte state = Q0;  // initial state
                StringBuilder sb = new StringBuilder(); // sequence
                boolean ok = true;

                // do this for all the symbols of the word
                while (index < symbols.length) {

                    sb.append("q0,"); // {q0}
                    while ((state == Q0) && (index < symbols.length) && (ok == true)) {
                        switch (symbols[index]) {
                            case A:
                                state = Q1Q5;
                                break;
                            case B:
                                state = Q3Q7;
                                break;
                            case C:
                                state = Q4Q8;
                                break;
                            default:
                                w.setResult(OutputType.ERROR_INVALID_WORD);
                                ok = false;
                                break;
                        }
                        index++;
                    }

                    sb.append("q1q5,");  // {q1q5}
                    while ((state == Q1Q5) && (index < symbols.length) && (ok == true)) {
                        if (symbols[index] == A) {
                            state = Q2Q6;
                        } else {
                            w.setResult(OutputType.ERROR_INVALID_WORD);
                            ok = false;
                        }
                        index++;
                    }

                    sb.append("q3q7,"); // {q3q7}
                    while ((state == Q3Q7) && (index < symbols.length && (ok == true))) {
                        switch (symbols[index]) {
                            case A:
                                state = Q5;
                                break;
                            case B:
                                state = Q7;
                                break;
                            case C:
                                state = Q4Q8;
                                break;
                            default:
                                w.setResult(OutputType.ERROR_INVALID_WORD);
                                ok = false;
                                break;
                        }
                        index++;
                    }

                    sb.append("q4q8,"); // {q4q8}
                    while ((state == Q4Q8) && (index < symbols.length) && (ok == true)) {
                        switch (symbols[index]) {
                            case A:
                                state = Q5;
                                break;
                            case B:
                                state = Q3Q7;
                                break;
                            case C:
                                state = Q4;
                                break;
                            default:
                                w.setResult(OutputType.ERROR_INVALID_WORD);
                                ok = false;
                                break;
                        }
                        index++;
                    }

                    sb.append("q2q6,"); // {q2q6}
                    while ((state == Q2Q6) && (index < symbols.length) && (ok == true)) {
                        switch (symbols[index]) {
                            case A:
                                state = Q1Q5;
                                break;
                            case B:
                                state = Q3Q7;
                                break;
                            case C:
                                state = Q4Q8;
                                break;
                            default:
                                w.setResult(OutputType.ERROR_INVALID_WORD);
                                ok = false;
                                break;
                        }
                        index++;
                    }

                    sb.append("q5,");  // {q5}
                    while ((state == Q5) && (index < symbols.length) && (ok == true)) {
                        switch (symbols[index]) {
                            case A:
                                state = Q6;
                                break;
                            default:
                                w.setResult(OutputType.ERROR_INVALID_WORD);
                                break;
                        }
                        index++;
                    }

                    sb.append("q7,"); // {q7}
                    while ((state == Q7) && (index < symbols.length) && (ok == true)) {
                        switch (symbols[index]) {
                            case A:
                                state = Q5;
                                break;
                            case B:
                                state = Q7;
                                break;
                            case C:
                                state = Q8;
                                break;
                            default:
                                w.setResult(OutputType.ERROR_INVALID_WORD);
                                ok = false;
                                break;
                        }
                        index++;
                    }

                    sb.append("q6,"); // {q6}
                    while ((state == Q6) && (index < symbols.length) && (ok == true)) {
                        switch (symbols[index]) {
                            case A:
                                state = Q5;
                                break;
                            default:
                                w.setResult(OutputType.ERROR_INVALID_WORD);
                                ok = false;
                                break;
                        }
                        index++;
                    }

                    sb.append("q8,"); // {q8}
                    while ((state == Q8) && (index < symbols.length) && (ok == true)) {
                        switch (symbols[index]) {
                            case A:
                                state = Q5;
                                break;
                            case B:
                                state = Q7;
                                break;
                            default:
                                w.setResult(OutputType.ERROR_INVALID_WORD);
                                ok = false;
                                break;
                        }
                        index++;
                    }

                    sb.append("q4,"); // {q4}
                    while ((state == Q4) && (index < symbols.length) && (ok == true)) {
                        switch (symbols[index]) {
                            case A:
                                state = Q5;
                                break;
                            case B:
                                state = Q3;
                                break;
                            case C:
                                state = Q4;
                                break;
                            default:
                                w.setResult(OutputType.ERROR_INVALID_WORD);
                                ok = false;
                                break;
                        }
                        index++;
                    }

                    sb.append("q3"); // {q3}
                    while ((state == Q3) && (index < symbols.length) && (ok == true)) {
                        switch (symbols[index]) {
                            case A:
                                state = Q5;
                                break;
                            case C:
                                state = Q3;
                                break;
                            default:
                                w.setResult(OutputType.ERROR_INVALID_WORD);
                                ok = false;
                                break;
                        }
                        index++;
                    }

                    if (ok == false) {
                        break;
                    }

                    if (index < symbols.length) {
                        sb.append(",");
                    }
                }

                w.setRecognition(sb.toString());

                /**
                 * The while loop ended. It means that all the symbols of the
                 * input were read so, if the current state is a final state,
                 * the input is accepted.
                 */
                if ((state == Q1Q5) || (state == Q5)) {
                    w.setResult(OutputType.VALID_WORD);
                    return w;
                }

                w.setResult(OutputType.ERROR_INVALID_WORD);
            }
        }

        return w;
    }

    public static void main(String[] args) {
        // words starting with a
//        System.out.println("01: " + wordChecker("a"));
//        System.out.println("02: " + wordChecker("aaa"));
//        System.out.println("03: " + wordChecker("aaba"));
//        System.out.println("04: " + wordChecker("aabaaa"));
//        System.out.println("05: " + wordChecker("aabbcba"));
//        System.out.println("06: " + wordChecker("aaca"));
//        System.out.println("07: " + wordChecker("aaccbca"));
//        System.out.println("08: " + wordChecker("aacbaaa"));

        // words starting with b
//        System.out.println("09: " + wordChecker("bbca"));
//        System.out.println("10: " + wordChecker("ba"));
//        System.out.println("11: " + wordChecker("bccbcaaa"));
        // words starting with c
//        System.out.println("12: " + wordChecker("cccbca"));
//        System.out.println("13: " + wordChecker("ca"));
//        System.out.println("14: " + wordChecker("cbbcbaaa"));
        // errors
        //recognizer("aa");
        //recognizer("b");
        //recognizer("c");
        //recognizer("bbc");;
        //recognizer("cbac");
        //recognizer("ab");
        //recognizer("ac");
        //recognizer("x");
        //recognizer("y");
        //recognizer("z");
    }
}

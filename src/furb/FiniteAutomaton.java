package furb;

import java.util.ArrayList;
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
    private ArrayList<ArrayList<String>> getArrayOfWords(String input) {

        // creates an array of arrays (like a matrix).
        // Each array inside it represents a line of the input.
        ArrayList<ArrayList<String>> wordArray = new ArrayList<>();

        // creates the first arraylist of the matrix (the first line)
        wordArray.add(new ArrayList<>());

        // breaks input into array of char
        char[] chars = input.toCharArray();

        // the lines of the arraylist matrix 
        int index = 0;

        String word = "";
        for (int i = 0; i < chars.length; i++) {                    // run through all the chars of the input
            if ((chars[i] != ' ') && (chars[i] != '\n')) {          // if the char isn't a white space or new line
                word += chars[i];                                   // append it to the word 
            } else if (!word.isEmpty()) {                           // otherwise, if the word is not empty,
                wordArray.get(index).add(word);                     // add it to the current arraylist (line)
                word = "";                                          // and reset the word variable
            }
            if (chars[i] == '\n') { // if the next char is a new line
                if (!word.isEmpty()) {                              // check again if the word is not empty, if it's not,
                    wordArray.get(index).add(word);                 // add it to the current arraylist (line)
                }
                wordArray.add(new ArrayList<>());                   // then creates a new arraylist (new line),
                index++;                                            // moves to this new arraylist (new line) 
                word = "";                                          // and reset the word variable again
            }
        }
        if (!word.isEmpty()) {                                      // the last word can't be added inside the for loop
            wordArray.get(index).add(word);                         // so, if it is not empty, add it
        }

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

        // creates the matrix of lines and words
        ArrayList<ArrayList<String>> array = getArrayOfWords(input);

        for (int i = 0; i < array.size(); i++) {    // first run through the arraylists 
            for (String string : array.get(i)) {    // and then through its elements
                String wordStr = string.trim();
                if (!wordStr.isEmpty()) {
                    WordRecognition word = this.recognition(wordStr);
                    word.setLine(i + 1);          // the line will be the current arraylist index + 1
                    resultList.add(word);
                }
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

        // get letters  
        char[] symbols = word.toCharArray();

        StringBuilder sb = new StringBuilder(); // sequence

        int index = 0;
        byte state = Q0;  // initial state
        boolean ok = true;
        while (index < symbols.length) {

            // check if its a special symbol
            if (SpecialSymbol.containsSymbol(word)) {
                w.setResult(OutputType.SPECIAL_SYMBOL);
                sb.append("q0,");
                sb.append("qErro");
                break;
            }

            // check if the word starts with any other symbol
            if ((symbols[0] != A) && (symbols[0] != B) && (symbols[0] != C)) {
                w.setResult(OutputType.ERROR_INVALID_SYMBOL);
                sb.append("q0,");
                sb.append("qErro");
                break;
            }

            // {q0}
            while ((state == Q0) && (index < symbols.length) && (ok == true)) {
                switch (symbols[index]) {
                    case A:
                        sb.append("q0,");
                        state = Q1Q5;
                        break;
                    case B:
                        sb.append("q0,");
                        state = Q3Q7;
                        break;
                    case C:
                        sb.append("q0,");
                        state = Q4Q8;
                        break;
                    default:
                        sb.append("qErro");
                        w.setResult(OutputType.ERROR_INVALID_WORD);
                        ok = false;
                        break;
                }
                index++;
            }

            // {q1q5}
            while ((state == Q1Q5) && (index < symbols.length) && (ok == true)) {
                if (symbols[index] == A) {
                    sb.append("q1q5,");
                    state = Q2Q6;
                } else {
                    sb.append("qErro");
                    w.setResult(OutputType.ERROR_INVALID_WORD);
                    ok = false;
                }
                index++;
            }

            // {q3q7}
            while ((state == Q3Q7) && (index < symbols.length && (ok == true))) {
                switch (symbols[index]) {
                    case A:
                        sb.append("q3q7,");
                        state = Q5;
                        break;
                    case B:
                        sb.append("q3q7,");
                        state = Q7;
                        break;
                    case C:
                        sb.append("q3q7,");
                        state = Q4Q8;
                        break;
                    default:
                        sb.append("qErro");
                        w.setResult(OutputType.ERROR_INVALID_WORD);
                        ok = false;
                        break;
                }
                index++;
            }

            // {q4q8}
            while ((state == Q4Q8) && (index < symbols.length) && (ok == true)) {
                switch (symbols[index]) {
                    case A:
                        sb.append("q4q8,");
                        state = Q5;
                        break;
                    case B:
                        sb.append("q4q8,");
                        state = Q3Q7;
                        break;
                    case C:
                        sb.append("q4q8,");
                        state = Q4;
                        break;
                    default:
                        sb.append("qErro");
                        w.setResult(OutputType.ERROR_INVALID_WORD);
                        ok = false;
                        break;
                }
                index++;
            }

            // {q2q6}
            while ((state == Q2Q6) && (index < symbols.length) && (ok == true)) {
                switch (symbols[index]) {
                    case A:
                        sb.append("q2q6,");
                        state = Q1Q5;
                        break;
                    case B:
                        sb.append("q2q6,");
                        state = Q3Q7;
                        break;
                    case C:
                        sb.append("q2q6,");
                        state = Q4Q8;
                        break;
                    default:
                        sb.append("qErro");
                        w.setResult(OutputType.ERROR_INVALID_WORD);
                        ok = false;
                        break;
                }
                index++;
            }

            // {q5}
            while ((state == Q5) && (index < symbols.length) && (ok == true)) {
                switch (symbols[index]) {
                    case A:
                        sb.append("q5,");
                        state = Q6;
                        break;
                    default:
                        sb.append("qErro");
                        w.setResult(OutputType.ERROR_INVALID_WORD);
                        ok = false;
                        break;
                }
                index++;
            }

            // {q7}
            while ((state == Q7) && (index < symbols.length) && (ok == true)) {
                switch (symbols[index]) {
                    case A:
                        sb.append("q7,");
                        state = Q5;
                        break;
                    case B:
                        sb.append("q7,");
                        state = Q7;
                        break;
                    case C:
                        sb.append("q7,");
                        state = Q8;
                        break;
                    default:
                        sb.append("qErro");
                        w.setResult(OutputType.ERROR_INVALID_WORD);
                        ok = false;
                        break;
                }
                index++;
            }

            // {q6}
            while ((state == Q6) && (index < symbols.length) && (ok == true)) {
                switch (symbols[index]) {
                    case A:
                        sb.append("q6,");
                        state = Q5;
                        break;
                    default:
                        sb.append("qErro");
                        w.setResult(OutputType.ERROR_INVALID_WORD);
                        ok = false;
                        break;
                }
                index++;
            }

            // {q8}
            while ((state == Q8) && (index < symbols.length) && (ok == true)) {
                switch (symbols[index]) {
                    case A:
                        sb.append("q8,");
                        state = Q5;
                        break;
                    case B:
                        sb.append("q8,");
                        state = Q7;
                        break;
                    default:
                        sb.append("qErro");
                        w.setResult(OutputType.ERROR_INVALID_WORD);
                        ok = false;
                        break;
                }
                index++;
            }

            // {q4}
            while ((state == Q4) && (index < symbols.length) && (ok == true)) {
                switch (symbols[index]) {
                    case A:
                        sb.append("q4,");
                        state = Q5;
                        break;
                    case B:
                        sb.append("q4,");
                        state = Q3;
                        break;
                    case C:
                        sb.append("q4,");
                        state = Q4;
                        break;
                    default:
                        sb.append("qErro");
                        w.setResult(OutputType.ERROR_INVALID_WORD);
                        ok = false;
                        break;
                }
                index++;
            }

            // {q3}
            while ((state == Q3) && (index < symbols.length) && (ok == true)) {
                switch (symbols[index]) {
                    case A:
                        sb.append("q3,");
                        state = Q5;
                        break;
                    case C:
                        sb.append("q3,");
                        state = Q3;
                        break;
                    default:
                        sb.append("qErro");
                        w.setResult(OutputType.ERROR_INVALID_WORD);
                        ok = false;
                        break;
                }
                index++;
            }

            if (ok == false) {
                break;
            }

            if (index == symbols.length) {
                sb.append("q");
                sb.append(state);
            }

            /**
             * The while loop ended. It means that all the symbols of the input
             * were read so, if the current state is a final state, the input is
             * accepted.
             */
            if ((state == Q1Q5) || (state == Q5)) {
                w.setResult(OutputType.VALID_WORD);
            }

            w.setResult(OutputType.ERROR_INVALID_WORD);
        }

        w.setRecognition(sb.toString());

        return w;
    }
}

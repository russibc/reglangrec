package furb;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FiniteAutomaton {

    // states
    private final static String Q0 = "q0";                                      // initial state
    private final static String Q1Q5 = "q1q5";                                  // final state
    private final static String Q3Q7 = "q3q7";
    private final static String Q4Q8 = "q4q8";
    private final static String Q2Q6 = "q2q6";
    private final static String Q5 = "q5";                                      // final state
    private final static String Q7 = "q7";
    private final static String Q6 = "q6";
    private final static String Q8 = "q8";
    private final static String Q4 = "q4";
    private final static String Q3 = "q3";
    private final static String QERRO = "qErro";

    // symbols
    private final static char A = 'a';
    private final static char B = 'b';
    private final static char C = 'c';

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
        for (int i = 0; i < chars.length; i++) {                                // run through all the chars of the input
            if (SpecialSymbol.containsSymbol(chars[i])) {                       // if the current char is a special symbol
                if (!word.isEmpty()) {                                          // check if the word is not empty, if it's not,
                    wordArray.get(index).add(word);
                }
                // add it to the current arraylist (line)
                word = "" + chars[i];                                           // reset the word variable and add the special symbol found
                wordArray.get(index).add(word);                                 // and add it to the array
                word = "";                                                      // then reset the word variable again
            } else {
                if (!Character.isWhitespace(chars[i])) {                         // makes several validation to check if the char isn't whitespace
                    word += chars[i];                                           // append it to the word 
                } else if (!word.isEmpty()) {                                   // otherwise, if the word is not empty,
                    wordArray.get(index).add(word);                             // add it to the current arraylist (line)
                    word = "";                                                  // and reset the word variable
                }
                if (chars[i] == '\n') {                                         // if the current char is a new line
                    if (!word.isEmpty()) {                                       // check again if the word is not empty, if it's not,
                        wordArray.get(index).add(word);
                    }
                    // add it to the current arraylist (line)
                    wordArray.add(new ArrayList<>());                           // then creates a new arraylist (new line),
                    index++;                                                    // moves to this new arraylist (new line) 
                    word = "";                                                  // and reset the word variable again
                }
            }
        }
        if (!word.isEmpty()) // the last word can't be added inside the for loop
        {
            wordArray.get(index).add(word);                                     // so, if it is not empty, add it
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

        String wordStr;
        for (int i = 0; i < array.size(); i++) {                                // first run through the arraylists 
            for (String string : array.get(i)) {                                // and then through its elements
                wordStr = string.trim();
                if (!wordStr.isEmpty()) {
                    WordRecognition word = this.recognition(wordStr);
                    word.setLine(i + 1);                                        // the line will be the current arraylist index + 1
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

        StringBuilder sb = new StringBuilder();                                 // sequence

        int index = 0;
        String state = Q0;                                                      // initial state
        boolean specialSymbol = false;
        while (!state.equals(QERRO) && (index < symbols.length)) {
            // {q0}
            if ((state.equals(Q0)) && (index < symbols.length)) {
                sb.append(state).append(", ");
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
                        if (SpecialSymbol.containsSymbol(symbols[index])) {
                            specialSymbol = true;
                            state = Q5;
                        } else {
                            state = QERRO;
                        }
                        break;
                }
                index++;
            }

            // {q1q5}
            if ((state.equals(Q1Q5)) && (index < symbols.length)) {
                sb.append(state).append(", ");
                if (symbols[index] == A) {
                    state = Q2Q6;
                } else {
                    state = QERRO;
                }
                index++;
            }

            // {q3q7}
            if ((state.equals(Q3Q7)) && (index < symbols.length)) {
                sb.append(state).append(", ");
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
                        state = QERRO;
                        break;
                }
                index++;
            }

            // {q4q8}
            if ((state.equals(Q4Q8)) && (index < symbols.length)) {
                sb.append(state).append(", ");
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
                        state = QERRO;
                        break;
                }
                index++;
            }

            // {q2q6}
            if ((state.equals(Q2Q6)) && (index < symbols.length)) {
                sb.append(state).append(", ");
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
                        state = QERRO;
                        break;
                }
                index++;
            }

            // {q5}
            if ((state.equals(Q5)) && (index < symbols.length)) {
                sb.append(state).append(", ");
                switch (symbols[index]) {
                    case A:
                        state = Q6;
                        break;
                    default:
                        if (!specialSymbol) {
                            state = QERRO;
                        }
                        break;
                }
                index++;
            }

            // {q7}
            while ((state.equals(Q7)) && (index < symbols.length)) {
                sb.append(state).append(", ");
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
                        state = QERRO;
                        break;
                }
                index++;
            }

            // {q6}
            if ((state.equals(Q6)) && (index < symbols.length)) {
                sb.append(state).append(", ");
                switch (symbols[index]) {
                    case A:
                        state = Q5;
                        break;
                    default:
                        state = QERRO;
                        break;
                }
                index++;
            }

            // {q8}
            if ((state.equals(Q8)) && (index < symbols.length)) {
                sb.append(state).append(", ");
                switch (symbols[index]) {
                    case A:
                        state = Q5;
                        break;
                    case B:
                        state = Q7;
                        break;
                    default:
                        state = QERRO;
                        break;
                }
                index++;
            }

            // {q4}
            while ((state.equals(Q4)) && (index < symbols.length)) {
                sb.append(state).append(", ");
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
                        state = QERRO;
                        break;
                }
                index++;
            }

            // {q3}
            while ((state.equals(Q3)) && (index < symbols.length)) {
                sb.append(state).append(", ");
                switch (symbols[index]) {
                    case A:
                        state = Q5;
                        break;
                    case C:
                        state = Q4;
                        break;
                    default:
                        state = QERRO;
                        break;
                }
                index++;
            }
        }

        if ((state.equals(Q1Q5)) || (state.equals(Q5))) {
            if (!specialSymbol) {
                w.setResult(OutputType.VALID_WORD);
            } else {
                w.setResult(OutputType.SPECIAL_SYMBOL);
            }
        } else {

            if (index == 1) {
                w.setResult(OutputType.ERROR_INVALID_SYMBOL);
            } else {
                w.setResult(OutputType.ERROR_INVALID_WORD);
            }
            if (!state.equals(QERRO)) {
                sb.append(state).append(", ");
                state = QERRO;
            }
        }

        sb.append(state);

        w.setRecognition(sb.toString());
        return w;
    }
}

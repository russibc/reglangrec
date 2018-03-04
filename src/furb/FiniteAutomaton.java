package furb;

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

    // special symbols
    final static char S1 = ';';
    final static char S2 = ',';
    final static char S3 = '=';
    
    public WordRecognition wordChecker(String word) {

        // get letters  
        char[] symbols = word.toCharArray();

        // creates object representing the validation
        WordRecognition w = new WordRecognition(word);

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
                
                if(index < symbols.length) {
                    sb.append(",");
                }
            }
            
            w.setRecognition(sb.toString());

            /* The while loop ended. It means that all the symbols of the word 
           were read so, if the current state is a final state, the word is accepted.
             */
            if ((state == Q1Q5) || (state == Q5)) {
                w.setResult(OutputType.VALID_WORD);
                return w;
            }
            
            w.setResult(OutputType.ERROR_INVALID_WORD);
        }
        
        return w;
    }

    /*
    private static void specialSymbolsAndError (char symbol) {
        if ((symbol == S1) || (symbol == S2) || (symbol == S3))
            System.out.println("Símbolo especial: " + symbol);
    }
     */
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

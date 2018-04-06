package furb;

public enum SpecialSymbol {

    COMMA(','), SEMICOLON(';'), EQUALS('=');

    private final char description;

    SpecialSymbol (char description) {
        this.description = description;
    }

    public char getDescription () {
        return description;
    }
    
    public static boolean containsSymbol (char str) {
        return (checkSymbol(str) != null);
    }

    public static SpecialSymbol checkSymbol (char str) {
        for (SpecialSymbol s : SpecialSymbol.values()) {
            if (str == s.getDescription())
                return s;
        }
        return null;
    }

}

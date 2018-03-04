package furb;

public enum OutputType {
    
    VALID_WORD("palavra válida"), 
    ERROR_INVALID_WORD("erro: palávra inválida"), 
    ERROR_INVALID_SYMBOL("erro: símbolo(s) inválido(s)"), 
    SPECIAL_SYMBOL("símbolo especial");

    public String description;

    OutputType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}

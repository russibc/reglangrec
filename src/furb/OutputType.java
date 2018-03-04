/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

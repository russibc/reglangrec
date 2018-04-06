package furb;

public class WordRecognition {

    private int line;
    private OutputType result;
    private String sequence;
    private String recognition;
    
    public WordRecognition(String sequence) {
        this.sequence = sequence;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setResult(OutputType result) {
        this.result = result;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    public int getLine() {
        return line;
    }

    public OutputType getResult() {
        return result;
    }

    public String getRecognition() {
        return recognition;
    }

    public String getSequence() {
        return sequence;
    }

}

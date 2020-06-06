package novi.basics;

public class Field {
    private int position;
    private char value;

    public Field (int position){
        this.position = position;
        value = (char) ('0' + position);
    }

    public void setToken(char token){
        value = token;
    }

    public char getValue() {
        return value;
    }

}

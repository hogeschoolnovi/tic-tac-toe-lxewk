package novi.basics;

public class Player {
    // Attributen: info verzamelen  // altijd private maken , zodat ze buiten de class niet verandert kunnen worden
    private String name;
    private char token;
    private int score;

    // Constructor                 // public: de class kan overal aangepast worden
    public Player(String name, char token) {   // parameters : alleen die voor elke speler anders zijn.
        this.name = name;
        this.token = token;
        score = 0;
    }

    // Methoden: acties die de speler uit kan voeren
    // get methoden zijn methodes die iets terug geven
    public String getName() {
        return name;
    }

    public char getToken() {
        return token;
    }

    public int getScore() {
        return score;
    }

    /*
            // set methoden // Methoden die info veranderen
            public void setScore(int score) {
                this.score = score;
            }
    */
    public void addScore() {
        score++;
    }

}



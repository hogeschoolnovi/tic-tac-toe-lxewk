package novi.basics;

import java.util.InputMismatchException;

import static novi.basics.Main.PLAYERINPUT;

public class Game {
    private int maxRounds;
    private Field[] newBoard = new Field[9];

    private Player player1;
    private Player player2;
    private Player activePlayer;

    private boolean winner;
    private int draw;

    // Constructor
    public Game(Player player1, Player player2) {
        maxRounds = newBoard.length;
        this.player1 = player1;
        this.player2 = player2;
        activePlayer = player1;
        winner = false;
        draw = 0;
        // Array newBoard van de class Field vullen met de waardes van de Field constructor
        defaultBoard();
    }


    public void play() {
        for (int maxTurns = 0; maxTurns < 100; maxTurns++) {

            // preset speelbord zonder tokens
            System.out.println();
            defaultBoard();

            // het speelbord tonen
            printBoard();

            // starten met de beurt (maximaal 9 beurten)
            for (int round = 0; round < maxRounds; round++) {

                String activePlayerName = activePlayer.getName();
                // actieve speler vragen om een veld te kiezen (1 - 9)
                System.out.println(activePlayerName + ", please choose a field");

                // gekozen veld van de actieve speler opslaan
                int chosenField = -1;
                if(chosenField == -1) {
                    try {
                        chosenField = PLAYERINPUT.nextInt();
                    } catch (InputMismatchException inputMismatchException) {
                        inputMismatchException.printStackTrace();
                        chosenField = -1;
                        System.out.println("Please select a number between 1 and 9.");
                    }
                }

                int chosenIndex = chosenField - 1;

                // als het veld bestaat
                if (checkValidInput(chosenIndex)) {
                    // bij een leeg veld ,dus zonder een token
                    if (newBoard[chosenIndex].getValue() != 'x' && newBoard[chosenIndex].getValue() != 'o') {
                        // de token van de actieve speler op het gekozen veld plaatsen
                        newBoard[chosenIndex].setToken(activePlayer.getToken());

                        // het nieuwe speelbord tonen
                        printBoard();

                        // controleren of er een winnaar is
                        winner = isWinner(chosenIndex);

                        // als het spel gewonnen is
                        if (winner) {
                            // tonen wie er gewonnen heeft (de actieve speler)
                            System.out.println();
                            System.out.println(activePlayer.getName() + " has won!");
                            // de actieve speler krijgt een punt
                            activePlayer.addScore();
                            // de scores van de spelers tonen
                            System.out.println();
                            System.out.println(player1.getName() + " score: " + player1.getScore());
                            System.out.println(player2.getName() + " score: " + player2.getScore());
                            // spelers vragen of ze nog een keer willen spelen
                            System.out.println();
                            System.out.println("Play again? y/n");
                            try {
                                String again = PLAYERINPUT.next();
                                playAgain(again);
                            }
                            catch (Exception e) {
                                PLAYERINPUT.next();
                                System.out.println("Please enter y(es) or n(o)");
                            }

                            break;
                        }

                        // wanneer we in de laatste beurt zijn en niemand gewonnen heeft
                        else if (round == maxRounds - 1) {
                            // aantal keer gelijk spel ophogen
                            draw++;
                            // aantal keer gelijk spel tonen
                            System.out.println("Draw " + draw + " time(s)");
                            // de beurt doorgeven aan de volgende speler (van speler wisselen)
                            changeActivePlayer();
                        }
                        else {
                            changeActivePlayer();
                        }
                    }
                    // een veld bezet met een token
                    else {
                        maxRounds++;
                        System.out.println("this field is not available, choose another");
                    }
                }
                // als het veld niet bestaat
                else{
                    // het mamimale aantal beurten verhogen
                    maxRounds++;
                    // foutmelding tonen aan de speler
                    System.out.println("the chosen field does not exist, try again");
                }
            }
        }
    }

    // het speelbord vullen met de getallen 1 t/m 9
    public void defaultBoard() {
        for (int i = 0; i < 9; i++) {
            Field field = new Field(i + 1);
            newBoard[i] = field;
        }
    }

    // het speelbord printen (inclusief geplaatste tokens)
    public void printBoard () {
        for (int fieldIndex = 0; fieldIndex < newBoard.length; fieldIndex++) {
            Field tempField = newBoard[fieldIndex];
            System.out.print(tempField.getValue() + " ");
            // als het tweede veld geprint is of het vijfde veld geprint is, dan door naar de volgende regel
            if (fieldIndex == 2 || fieldIndex == 5) {
                System.out.println();
            }
        }
        System.out.println();
    }

    // controleren of er een winnaar is
    public boolean isWinner ( int chosenIndex){
        switch (chosenIndex) {
            case 0:
                if (newBoard[1].getValue() == activePlayer.getToken() && newBoard[2].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                if (newBoard[3].getValue() == activePlayer.getToken() && newBoard[6].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                if (newBoard[4].getValue() == activePlayer.getToken() && newBoard[8].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                break;
            case 1:
                if (newBoard[0].getValue() == activePlayer.getToken() && newBoard[2].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                if (newBoard[4].getValue() == activePlayer.getToken() && newBoard[7].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                break;
            case 2:
                if (newBoard[0].getValue() == activePlayer.getToken() && newBoard[1].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                if (newBoard[5].getValue() == activePlayer.getToken() && newBoard[8].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                if (newBoard[4].getValue() == activePlayer.getToken() && newBoard[6].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                break;
            case 3:
                if (newBoard[0].getValue() == activePlayer.getToken() && newBoard[6].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                if (newBoard[4].getValue() == activePlayer.getToken() && newBoard[5].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                break;
            case 4:
                if (newBoard[0].getValue() == activePlayer.getToken() && newBoard[8].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                if (newBoard[2].getValue() == activePlayer.getToken() && newBoard[6].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                if (newBoard[3].getValue() == activePlayer.getToken() && newBoard[5].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                if (newBoard[1].getValue() == activePlayer.getToken() && newBoard[7].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                break;
            case 5:
                if (newBoard[3].getValue() == activePlayer.getToken() && newBoard[4].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                if (newBoard[2].getValue() == activePlayer.getToken() && newBoard[8].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                break;
            case 6:
                if (newBoard[0].getValue() == activePlayer.getToken() && newBoard[3].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                if (newBoard[7].getValue() == activePlayer.getToken() && newBoard[8].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                if (newBoard[2].getValue() == activePlayer.getToken() && newBoard[4].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                break;
            case 7:
                if (newBoard[6].getValue() == activePlayer.getToken() && newBoard[8].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                if (newBoard[1].getValue() == activePlayer.getToken() && newBoard[4].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                break;
            case 8:
                if (newBoard[0].getValue() == activePlayer.getToken() && newBoard[4].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                if (newBoard[6].getValue() == activePlayer.getToken() && newBoard[7].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                if (newBoard[2].getValue() == activePlayer.getToken() && newBoard[5].getValue() == activePlayer.getToken()) {
                    return winner = true;
                }
                break;

        }
        return false;
    }

    // van (actieve) speler wisselen
    public void changeActivePlayer () {
        // als de actieve speler, speler 1 is:
        if (activePlayer == player1) {
            // maak de actieve speler, speler 2
            activePlayer = player2;
        }
        // anders
        else {
            // maak de actieve speler weer speler 1
            activePlayer = player1;
        }
    }

    // nogmaals spelen?
    public void playAgain (String again) {
        if (again.equals("y")) {
            changeActivePlayer();
        }
        else {
            System.out.println("Thank you for playing, have a nice day.");
            System.exit(0);
        }
    }

    public boolean checkValidInput(int chosenIndex) {
        if(chosenIndex < 9 && chosenIndex >= 0) {
                return true;
        }
        return false;
    }

}



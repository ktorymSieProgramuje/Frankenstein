import java.util.Scanner;

public class Priest implements Card {
    private int cardValue = 2;
    private String cardName = "priest";

    @Override
    public int getCardValue() {
        return this.cardValue;
    }

    @Override
    public String getCardName() {
        return this.cardName;
    }

    @Override
    public int specialFunction(Player currentPlayer, Player targetPlayer1, Player targetPlayer2, Player targetPlayer3, int length, Card[] deck) {

        currentPlayer.setPlaying(false);
        System.out.println("Skip turn.");
        return length;
    }
}
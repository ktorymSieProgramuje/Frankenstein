public class Countess implements Card {
    private int cardValue = 7;
    private String cardName = "countess";

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

        System.out.println(currentPlayer.getPlayerName() + " has discarded a Countess");
        return length;
    }
}

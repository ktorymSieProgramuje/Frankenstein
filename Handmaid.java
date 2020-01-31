public class Handmaid implements Card {
    private int cardValue = 4;
    private String cardName = "handmaid";

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
        currentPlayer.setPlayedHandmaid(true);
        System.out.println("MOVE");
        return length;
    }
}

public class Princess implements Card {
    private int cardValue = 8;
    private String cardName = "princess";


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

        System.out.println("You have discarded a Princess \nYou are out of the round!");
        currentPlayer.setPlaying(false);
        return length;
    }
}
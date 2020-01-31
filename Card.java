public interface Card  {

    int getCardValue();
    String getCardName();

    int specialFunction(Player currentPlayer, Player targetPlayer1, Player targetPlayer2, Player targetPlayer3, int length, Card[] deck);


}
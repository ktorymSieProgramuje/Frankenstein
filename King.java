import java.util.Scanner;

public class King implements Card {
    private int cardValue = 6;
    private String cardName = "king";
    Scanner sc = new Scanner(System.in);


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

        while (true) {
            System.out.println("Current player: " + currentPlayer.getPlayerName());
            System.out.println("You can target:");
            if(targetPlayer1.getIsPlaying() && !targetPlayer1.isPlayedHandmaid()){
                System.out.println(targetPlayer1.getPlayerName());
            }
            if(targetPlayer2.getIsPlaying() && !targetPlayer2.isPlayedHandmaid()){
                System.out.println(targetPlayer2.getPlayerName());
            }
            if(targetPlayer3.getIsPlaying() && !targetPlayer3.isPlayedHandmaid()){
                System.out.println(targetPlayer3.getPlayerName());
            }
            if((!targetPlayer1.getIsPlaying() || targetPlayer1.isPlayedHandmaid()) && (!targetPlayer2.getIsPlaying() || targetPlayer2.isPlayedHandmaid()) && (!targetPlayer3.getIsPlaying() || targetPlayer3.isPlayedHandmaid())){
                System.out.println("ERROR");
                break;
            }

            System.out.println("Choose a player");
            String playerChoice = sc.nextLine();
            playerChoice = playerChoice.toLowerCase();

            if (playerChoice.equals(currentPlayer.getPlayerName())) {
                System.out.println("ERROR");
            }

            else if (playerChoice.equals(targetPlayer1.getPlayerName())) {
                if (!targetPlayer1.getIsPlaying()) {
                    System.out.println("ERROR");
                }
                else if(targetPlayer1.isPlayedHandmaid()){
                    System.out.println("ERROR");
                }
                else {
                    if(currentPlayer.getCard1().getCardName().equals("king")){
                        Card temp = targetPlayer1.getCard1();
                        targetPlayer1.setCard1(currentPlayer.getCard2());
                        currentPlayer.setCard1(temp);
                    }
                    else{
                        Card temp = targetPlayer1.getCard1();
                        targetPlayer1.setCard1(currentPlayer.getCard1());
                        currentPlayer.setCard1(temp);
                    }
                    System.out.println("You have traded hands with " + targetPlayer1.getPlayerName());
                }
            }

            else if (playerChoice.equals(targetPlayer2.getPlayerName())) {
                if (!targetPlayer2.getIsPlaying()) {
                    System.out.println("ERROR");
                }
                else if(targetPlayer2.isPlayedHandmaid()){
                    System.out.println("ERROR");
                }
                else {
                    if(currentPlayer.getCard1().getCardName().equals("king")){
                        Card temp = targetPlayer2.getCard1();
                        targetPlayer2.setCard1(currentPlayer.getCard2());
                        currentPlayer.setCard1(temp);
                    }
                    else{
                        Card temp = targetPlayer2.getCard1();
                        targetPlayer2.setCard1(currentPlayer.getCard1());
                        currentPlayer.setCard1(temp);
                    }
                    System.out.println("You have traded hands with " + targetPlayer2.getPlayerName());
                }
            }

            else if (playerChoice.equals(targetPlayer3.getPlayerName())) {
                if (!targetPlayer3.getIsPlaying()) {
                    System.out.println("ERROR");
                }
                else if(targetPlayer3.isPlayedHandmaid()){
                    System.out.println("ERROR");
                }
                else {
                    if(currentPlayer.getCard1().getCardName().equals("king")){
                        Card temp = targetPlayer3.getCard1();
                        targetPlayer3.setCard1(currentPlayer.getCard2());
                        currentPlayer.setCard1(temp);
                    }
                    else{
                        Card temp = targetPlayer3.getCard1();
                        targetPlayer3.setCard1(currentPlayer.getCard1());
                        currentPlayer.setCard1(temp);
                    }
                    System.out.println("You have traded hands with " + targetPlayer3.getPlayerName());
                }
            }
            break;
        }
        return length;
    }
}

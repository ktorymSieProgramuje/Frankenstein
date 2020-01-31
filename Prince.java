import java.util.Scanner;

public class Prince implements Card {
    private int cardValue = 5;
    private String cardName = "prince";

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


        while(true) {
            System.out.println("Current player: " + currentPlayer.getPlayerName());
            System.out.println("You can target:");
            System.out.println("Yourself");
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

            if (playerChoice.equals(currentPlayer.getPlayerName())){
                if(currentPlayer.getCard1().getCardName().equals("prince") && currentPlayer.getCard2().getCardName().equals("princess")){
                        currentPlayer.setPlaying(false);
                        System.out.println("ELIMINATED " +currentPlayer.getPlayerName());
                        break;
                }
                else if(currentPlayer.getCard2().getCardName().equals("prince") && currentPlayer.getCard1().getCardName().equals("princess")){
                        currentPlayer.setPlaying(false);
                        System.out.println("ELIMINATED " + currentPlayer.getPlayerName());
                        break;
                }
                else {
                    currentPlayer.setCard1(deck[length]);
                    length--;
                    System.out.println("You have discarded your hand and drawn a " + currentPlayer.getCard1().getCardName());
                    break;
                }
            }
            else if (playerChoice.equals(targetPlayer1.getPlayerName())) {

                if (!targetPlayer1.getIsPlaying()) {
                    System.out.println("ERROR");
                }
                else if(targetPlayer1.isPlayedHandmaid()){
                    System.out.println("ERROR");
                }
                else {
                    if (playerChoice.equals(targetPlayer1.getPlayerName())){
                        if(targetPlayer1.getCard1().getCardName().equals("princess")){
                            System.out.println("ELIMINATED "+ targetPlayer1.getPlayerName());
                            targetPlayer1.setPlaying(false);
                            break;
                        }
                        else{
                            targetPlayer1.setCard1(deck[length]);
                            length--;
                            System.out.println(targetPlayer1.getPlayerName() + " has discarded their hand and drawn a new one from the deck");
                            break;
                        }
                    }
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
                    if (playerChoice.equals(targetPlayer2.getPlayerName())){
                        if(targetPlayer2.getCard1().getCardName().equals("princess")){
                            System.out.println("ELIMINATED "+ targetPlayer2.getPlayerName());
                            targetPlayer2.setPlaying(false);
                            break;
                        }
                        else{
                            targetPlayer2.setCard1(deck[length]);
                            length--;
                            System.out.println(targetPlayer2.getPlayerName() + " has discarded their hand and drawn a new one from the deck");
                            break;
                        }
                    }
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
                    if (playerChoice.equals(targetPlayer3.getPlayerName())){
                        if(targetPlayer3.getCard1().getCardName().equals("princess")){
                            System.out.println("ELIMINATED "+ targetPlayer3.getPlayerName());
                            targetPlayer3.setPlaying(false);
                            break;
                        }
                        else{
                            targetPlayer3.setCard1(deck[length]);

                            System.out.println(targetPlayer3.getPlayerName() + " has discarded their hand and drawn a new one from the deck");
                            break;
                        }
                    }
                }
            }
        }
        return length;
    }
}

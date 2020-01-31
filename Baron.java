import java.util.Scanner;

public class Baron implements Card {
    private int cardValue = 3;
    private String cardName = "baron";


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


        Scanner sc = new Scanner(System.in);


        while(true)
        {
            System.out.println("Current player: " + currentPlayer.getPlayerName());
            System.out.println("Choose player: ");
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


            if(playerChoice.equals(currentPlayer.getPlayerName()))
            {
                System.out.println("ERROR");
            }

            else if(playerChoice.equals(targetPlayer1.getPlayerName()))
            {

                if(!targetPlayer1.getIsPlaying())
                {
                    System.out.println("ERROR");

                }
                else if(targetPlayer1.isPlayedHandmaid()){
                    System.out.println("ERROR");
                }
                else
                {
                    if(!currentPlayer.getCard1().getCardName().equals("baron"))
                    {
                        if(currentPlayer.getCard1().getCardValue() > targetPlayer1.getCard1().getCardValue())
                        {
                            targetPlayer1.setPlaying(false);
                            System.out.println("ELIMINATED " + targetPlayer1.getPlayerName());
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer1.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            System.out.println("ELIMINATED " + currentPlayer.getPlayerName());
                        }
                        else
                        {
                            System.out.println(" ");
                        }
                    }
                    else if(!currentPlayer.getCard2().getCardName().equals("baron"))
                    {
                        if(currentPlayer.getCard2().getCardValue() > targetPlayer1.getCard1().getCardValue())
                        {
                            targetPlayer1.setPlaying(false);
                            System.out.println("ELIMINATED " + targetPlayer1.getPlayerName());
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer1.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            System.out.println("ELIMINATED " + currentPlayer.getPlayerName());

                        }
                        else
                        {
                            System.out.println(" ");
                        }
                    }
                    break;
                }
            }
            else if(playerChoice.equals(targetPlayer2.getPlayerName()))
            {

                if(!targetPlayer2.getIsPlaying())
                {
                    System.out.println("ERROR");

                }
                else if(targetPlayer2.isPlayedHandmaid()){
                    System.out.println("ERROE");
                }
                else
                {
                    if(!currentPlayer.getCard1().getCardName().equals("baron"))
                    {
                        if(currentPlayer.getCard1().getCardValue() > targetPlayer2.getCard1().getCardValue())
                        {
                            targetPlayer2.setPlaying(false);
                            System.out.println("ELIMINATED " + targetPlayer2.getPlayerName());
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer2.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            System.out.println("ELIMINATED "+ currentPlayer.getPlayerName());
                        }
                        else
                        {
                            System.out.println(" ");
                        }
                    }
                    else if(!currentPlayer.getCard2().getCardName().equals("baron"))
                    {
                        if(currentPlayer.getCard2().getCardValue() > targetPlayer2.getCard1().getCardValue())
                        {
                            targetPlayer2.setPlaying(false);
                            System.out.println("ELIMINATED " + targetPlayer2.getPlayerName());
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer2.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            System.out.println("ELIMINATED " + currentPlayer.getPlayerName());

                        }
                        else
                        {
                            System.out.println(" ");
                        }

                    }
                    break;
                }

            }

            else if(playerChoice.equals(targetPlayer3.getPlayerName()))
            {

                if(!targetPlayer3.getIsPlaying())
                {
                    System.out.println("ERROR");

                }
                else if(targetPlayer3.isPlayedHandmaid()){
                    System.out.println("ERROR");
                }
                else
                {
                    if(!currentPlayer.getCard1().getCardName().equals("baron"))
                    {
                        if(currentPlayer.getCard1().getCardValue() > targetPlayer3.getCard1().getCardValue())
                        {
                            targetPlayer3.setPlaying(false);
                            System.out.println("ELIMINATED " + targetPlayer3.getPlayerName());
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer3.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            System.out.println("ELIMINATED " + currentPlayer.getPlayerName());
                        }
                        else
                        {
                            System.out.println(" ");
                        }
                    }
                    else if(!currentPlayer.getCard2().getCardName().equals("baron"))
                    {
                        if(currentPlayer.getCard2().getCardValue() > targetPlayer3.getCard1().getCardValue())
                        {
                            targetPlayer3.setPlaying(false);
                            System.out.println("ELIMINATED " + targetPlayer3.getPlayerName());
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer3.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            System.out.println("ELIMINATED " + currentPlayer.getPlayerName());
                        }
                        else
                        {
                            System.out.println(" ");
                        }

                    }
                    break;
                }

            }

            else if(!playerChoice.equals(targetPlayer1.getPlayerName()) || !playerChoice.equals(targetPlayer2.getPlayerName()) || !playerChoice.equals(targetPlayer3.getPlayerName()) || !playerChoice.equals(currentPlayer.getPlayerName()))
            {
                System.out.println("ERROR");
            }


        }

        return length;

    }
}
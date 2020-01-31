import java.util.Scanner;

public class Guard implements Card {

    private int cardValue = 1;
    private String cardName = "guard";

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
                    while(true)
                    {
                        System.out.println("Name a card to guess");
                        String cardChoice = sc.nextLine();
                        cardChoice = cardChoice.toLowerCase();

                        if(cardChoice.equals(this.cardName))
                        {
                            System.out.println("ERROR");
                        }

                        else if(cardChoice.equals(targetPlayer1.getCard1().getCardName()))
                        {
                            System.out.println("ELIMINATED " + targetPlayer1.getPlayerName());
                            targetPlayer1.setPlaying(false);
                            break;
                        }


                        else if((cardChoice.equals("priest") || cardChoice.equals("baron") || cardChoice.equals("handmaid") || cardChoice.equals("prince") || cardChoice.equals("king") || cardChoice.equals("countess") || cardChoice.equals("princess") ))
                        {
                            System.out.println("Player " +  targetPlayer1.getPlayerName() + " does not have that card.");
                            break;
                        }

                        else
                        {
                            System.out.println("ERROR");
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
                    System.out.println("ERROR");
                }
                else
                {
                    while(true)
                    {
                        System.out.println("Name a card to guess");
                        String cardChoice = sc.nextLine();
                        cardChoice = cardChoice.toLowerCase();

                        if(cardChoice.equals(this.cardName))
                        {
                            System.out.println("ERROR");
                        }

                        else if(cardChoice.equals(targetPlayer2.getCard1().getCardName()))
                        {
                            System.out.println("ELIMINATED  " + targetPlayer2.getPlayerName());
                            targetPlayer2.setPlaying(false);
                            break;
                        }


                        else if((cardChoice.equals("priest") || cardChoice.equals("baron") || cardChoice.equals("handmaid") || cardChoice.equals("prince") || cardChoice.equals("king") || cardChoice.equals("countess") || cardChoice.equals("princess") ))
                        {
                            System.out.println("Player " +  targetPlayer2.getPlayerName() + " does not have that card.");
                            break;
                        }

                        else
                        {
                            System.out.println("ERROR");
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
                    while(true)
                    {
                        System.out.println("Name a card to guess");
                        String cardChoice = sc.nextLine();
                        cardChoice = cardChoice.toLowerCase();

                        if(cardChoice.equals(this.cardName))
                        {
                            System.out.println("ERROR");
                        }

                        else if(cardChoice.equals(targetPlayer3.getCard1().getCardName()))
                        {
                            System.out.println("ELIMINATED " + targetPlayer3.getPlayerName());
                            targetPlayer3.setPlaying(false);
                            break;
                        }

                        else if((cardChoice.equals("priest") || cardChoice.equals("baron") || cardChoice.equals("handmaid") || cardChoice.equals("prince") || cardChoice.equals("king") || cardChoice.equals("countess") || cardChoice.equals("princess") ))
                        {
                            System.out.println("Player " +  targetPlayer3.getPlayerName() + " does not have that card.");
                            break;
                        }

                        else
                        {
                            System.out.println("ERROR");
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
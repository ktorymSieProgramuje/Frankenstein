import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Baron implements Card {
    private int cardValue = 3;
    private String cardName = "baron";
    private ArrayList<ClientHandler> clientThread;
    private BufferedReader in;
    private PrintWriter out;
    private Socket client;
    

    @Override
    public int getCardValue() {
        return this.cardValue;
    }


    @Override
    public String getCardName() {
        return this.cardName;
    }

    @Override
    public int specialFunction(Player currentPlayer, Player targetPlayer1, Player targetPlayer2, Player targetPlayer3, int length, Card[] deck, BufferedReader in, PrintWriter out, Socket client, ArrayList<ClientHandler> clientThread) throws IOException {

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);


        while(true)
        {
            serverMessageToAll("Current player: " + currentPlayer.getPlayerName());
            serverMessageToAll("Choose: ");
            if(targetPlayer1.getIsPlaying() && !targetPlayer1.isPlayedHandmaid()){
                serverMessageToAll(targetPlayer1.getPlayerName());
            }
            if(targetPlayer2.getIsPlaying() && !targetPlayer2.isPlayedHandmaid()){
                serverMessageToAll(targetPlayer2.getPlayerName());
            }
            if(targetPlayer3.getIsPlaying() && !targetPlayer3.isPlayedHandmaid()){
                serverMessageToAll(targetPlayer3.getPlayerName());
            }
            if((!targetPlayer1.getIsPlaying() || targetPlayer1.isPlayedHandmaid()) && (!targetPlayer2.getIsPlaying() || targetPlayer2.isPlayedHandmaid()) && (!targetPlayer3.getIsPlaying() || targetPlayer3.isPlayedHandmaid())){
                serverMessageToAll("ERROR");
                break;
            }

            serverMessageToAll("Choose player");
            String playerChoice = in.readLine();
            playerChoice = playerChoice.toLowerCase();


            if(playerChoice.equals(currentPlayer.getPlayerName()))
            {
                serverMessageToAll("ERROR");
            }

            else if(playerChoice.equals(targetPlayer1.getPlayerName()))
            {

                if(!targetPlayer1.getIsPlaying())
                {
                    serverMessageToAll("ERROR");

                }
                else if(targetPlayer1.isPlayedHandmaid()){
                    serverMessageToAll("ERROR");
                }
                else
                {
                    if(!currentPlayer.getCard1().getCardName().equals("baron"))
                    {
                        if(currentPlayer.getCard1().getCardValue() > targetPlayer1.getCard1().getCardValue())
                        {
                            targetPlayer1.setPlaying(false);
                            serverMessageToAll("ELIMINATED " + targetPlayer1.getPlayerName());
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer1.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            serverMessageToAll("ELIMINATED " + currentPlayer.getPlayerName());
                        }
                        else
                        {
                            serverMessageToAll(" ");
                        }
                    }
                    else if(!currentPlayer.getCard2().getCardName().equals("baron"))
                    {
                        if(currentPlayer.getCard2().getCardValue() > targetPlayer1.getCard1().getCardValue())
                        {
                            targetPlayer1.setPlaying(false);
                            serverMessageToAll("ELIMINATED " + targetPlayer1.getPlayerName());
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer1.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            serverMessageToAll("ELIMINATED " + currentPlayer.getPlayerName());

                        }
                        else
                        {
                            serverMessageToAll(" ");
                        }
                    }
                    break;
                }
            }
            else if(playerChoice.equals(targetPlayer2.getPlayerName()))
            {

                if(!targetPlayer2.getIsPlaying())
                {
                    serverMessageToAll("ERROR");

                }
                else if(targetPlayer2.isPlayedHandmaid()){
                    serverMessageToAll("ERROE");
                }
                else
                {
                    if(!currentPlayer.getCard1().getCardName().equals("baron"))
                    {
                        if(currentPlayer.getCard1().getCardValue() > targetPlayer2.getCard1().getCardValue())
                        {
                            targetPlayer2.setPlaying(false);
                            serverMessageToAll("ELIMINATED " + targetPlayer2.getPlayerName());
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer2.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            serverMessageToAll("ELIMINATED "+ currentPlayer.getPlayerName());
                        }
                        else
                        {
                            serverMessageToAll(" ");
                        }
                    }
                    else if(!currentPlayer.getCard2().getCardName().equals("baron"))
                    {
                        if(currentPlayer.getCard2().getCardValue() > targetPlayer2.getCard1().getCardValue())
                        {
                            targetPlayer2.setPlaying(false);
                            serverMessageToAll("ELIMINATED " + targetPlayer2.getPlayerName());
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer2.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            serverMessageToAll("ELIMINATED " + currentPlayer.getPlayerName());

                        }
                        else
                        {
                            serverMessageToAll(" ");
                        }

                    }
                    break;
                }

            }

            else if(playerChoice.equals(targetPlayer3.getPlayerName()))
            {

                if(!targetPlayer3.getIsPlaying())
                {
                    serverMessageToAll("ERROR");

                }
                else if(targetPlayer3.isPlayedHandmaid()){
                    serverMessageToAll("ERROR");
                }
                else
                {
                    if(!currentPlayer.getCard1().getCardName().equals("baron"))
                    {
                        if(currentPlayer.getCard1().getCardValue() > targetPlayer3.getCard1().getCardValue())
                        {
                            targetPlayer3.setPlaying(false);
                            serverMessageToAll("ELIMINATED " + targetPlayer3.getPlayerName());
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer3.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            serverMessageToAll("ELIMINATED " + currentPlayer.getPlayerName());
                        }
                        else
                        {
                            serverMessageToAll(" ");
                        }
                    }
                    else if(!currentPlayer.getCard2().getCardName().equals("baron"))
                    {
                        if(currentPlayer.getCard2().getCardValue() > targetPlayer3.getCard1().getCardValue())
                        {
                            targetPlayer3.setPlaying(false);
                            serverMessageToAll("ELIMINATED " + targetPlayer3.getPlayerName());
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer3.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            serverMessageToAll("ELIMINATED " + currentPlayer.getPlayerName());
                        }
                        else
                        {
                            serverMessageToAll(" ");
                        }

                    }
                    break;
                }

            }

            else if(!playerChoice.equals(targetPlayer1.getPlayerName()) || !playerChoice.equals(targetPlayer2.getPlayerName()) || !playerChoice.equals(targetPlayer3.getPlayerName()) || !playerChoice.equals(currentPlayer.getPlayerName()))
            {
                serverMessageToAll("ERROR");
            }


        }

        return length;

    }

    private void serverMessageToAll(String msg) {
        for (ClientHandler aClient : clientThread) {
            aClient.out.println("S:" + msg);
        }
    }
}
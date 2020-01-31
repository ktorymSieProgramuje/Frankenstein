import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Prince implements Card {
    private int cardValue = 5;
    private String cardName = "prince";
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clientThread;


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
        


        while(true) {
            serverMessageToAll("Current player: " + currentPlayer.getPlayerName());
            serverMessageToAll("You can choose:");
            serverMessageToAll("Yourself");
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

            serverMessageToAll("Choose a player");
            String playerChoice = in.readLine();
            playerChoice = playerChoice.toLowerCase();

            if (playerChoice.equals(currentPlayer.getPlayerName())){
                if(currentPlayer.getCard1().getCardName().equals("prince") && currentPlayer.getCard2().getCardName().equals("princess")){
                        currentPlayer.setPlaying(false);
                        serverMessageToAll("ELIMINATED " +currentPlayer.getPlayerName());
                        break;
                }
                else if(currentPlayer.getCard2().getCardName().equals("prince") && currentPlayer.getCard1().getCardName().equals("princess")){
                        currentPlayer.setPlaying(false);
                        serverMessageToAll("ELIMINATED " + currentPlayer.getPlayerName());
                        break;
                }
                else {
                    currentPlayer.setCard1(deck[length]);
                    length--;
                    serverMessageToAll("MOVE " + currentPlayer.getCard1().getCardName());
                    break;
                }
            }
            else if (playerChoice.equals(targetPlayer1.getPlayerName())) {

                if (!targetPlayer1.getIsPlaying()) {
                    serverMessageToAll("ERROR");
                }
                else if(targetPlayer1.isPlayedHandmaid()){
                    serverMessageToAll("ERROR");
                }
                else {
                    if (playerChoice.equals(targetPlayer1.getPlayerName())){
                        if(targetPlayer1.getCard1().getCardName().equals("princess")){
                            serverMessageToAll("ELIMINATED "+ targetPlayer1.getPlayerName());
                            targetPlayer1.setPlaying(false);
                            break;
                        }
                        else{
                            targetPlayer1.setCard1(deck[length]);
                            length--;
                            serverMessageToAll(targetPlayer1.getPlayerName() + " MOVE ");
                            break;
                        }
                    }
                }
            }
            else if (playerChoice.equals(targetPlayer2.getPlayerName())) {

                if (!targetPlayer2.getIsPlaying()) {
                    serverMessageToAll("ERROR");
                }
                else if(targetPlayer2.isPlayedHandmaid()){
                    serverMessageToAll("ERROR");
                }
                else {
                    if (playerChoice.equals(targetPlayer2.getPlayerName())){
                        if(targetPlayer2.getCard1().getCardName().equals("princess")){
                            serverMessageToAll("ELIMINATED "+ targetPlayer2.getPlayerName());
                            targetPlayer2.setPlaying(false);
                            break;
                        }
                        else{
                            targetPlayer2.setCard1(deck[length]);
                            length--;
                            serverMessageToAll(targetPlayer2.getPlayerName() + " MOVE");
                            break;
                        }
                    }
                }
            }
            else if (playerChoice.equals(targetPlayer3.getPlayerName())) {

                if (!targetPlayer3.getIsPlaying()) {
                    serverMessageToAll("ERROR");
                }
                else if(targetPlayer3.isPlayedHandmaid()){
                    serverMessageToAll("ERROR");
                }
                else {
                    if (playerChoice.equals(targetPlayer3.getPlayerName())){
                        if(targetPlayer3.getCard1().getCardName().equals("princess")){
                            serverMessageToAll("ELIMINATED "+ targetPlayer3.getPlayerName());
                            targetPlayer3.setPlaying(false);
                            break;
                        }
                        else{
                            targetPlayer3.setCard1(deck[length]);

                            serverMessageToAll(targetPlayer3.getPlayerName() + " MOVE ");
                            break;
                        }
                    }
                }
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

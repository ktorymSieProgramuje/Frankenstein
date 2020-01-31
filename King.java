import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class King implements Card {
    private int cardValue = 6;
    private String cardName = "king";
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

        while (true) {
            serverMessageToAll("Current player: " + currentPlayer.getPlayerName());
            serverMessageToAll("You can target:");
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

            if (playerChoice.equals(currentPlayer.getPlayerName())) {
                serverMessageToAll("ERROR");
            }

            else if (playerChoice.equals(targetPlayer1.getPlayerName())) {
                if (!targetPlayer1.getIsPlaying()) {
                    serverMessageToAll("ERROR");
                }
                else if(targetPlayer1.isPlayedHandmaid()){
                    serverMessageToAll("ERROR");
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
                    serverMessageToAll("MOVE King");
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
                    serverMessageToAll("MOVE King");
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
                    serverMessageToAll("MOVE King");
                }
            }
            break;
        }
        return length;
    }


    private void serverMessageToAll(String msg) {
        for (ClientHandler aClient : clientThread) {
            aClient.out.println("S:" + msg);
        }
    }
}

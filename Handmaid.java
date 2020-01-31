import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Handmaid implements Card {
    private int cardValue = 4;
    private String cardName = "handmaid";
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

        currentPlayer.setPlayedHandmaid(true);
        serverMessageToAll("MOVE");
        return length;
    }


    private void serverMessageToAll(String msg) {
        for (ClientHandler aClient : clientThread) {
            aClient.out.println("S:" + msg);
        }
    }
}



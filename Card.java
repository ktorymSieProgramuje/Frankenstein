import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;

public interface Card  {

    int getCardValue();
    String getCardName();

    int specialFunction(Player currentPlayer, Player targetPlayer1, Player targetPlayer2, Player targetPlayer3, int length, Card[] deck, BufferedReader out, PrintWriter in, Socket client, ArrayList<ClientHandler> clientThread) throws IOException;

}
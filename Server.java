import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private static final int PORT = 9090;
    private static ArrayList<String> logins = new ArrayList<>();
    private static ArrayList<Integer> usersId = new ArrayList<>(6);
    private static ArrayList<Player> players = new ArrayList<>();

    private static BufferedReader sc;
    private static PrintWriter pr;

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);


        Player player1 = new Player("x");
        Player player2 = new Player("x");
        Player player3 = new Player("x");
        Player player4 = new Player("x");
        Player player5 = new Player("x");

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);


        while(true) {
            System.out.println("S: Waiting for client connection...");
            Socket client = listener.accept();
            System.out.println("S:Client Connected");
            ClientHandler clientThread = new ClientHandler(client, clients, logins, usersId, players);
            clients.add(clientThread);

            pool.execute(clientThread);



        }
    }





}

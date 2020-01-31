import java.awt.print.PrinterIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class ClientHandler implements Runnable {

    private Socket client;
    private BufferedReader in;
    public PrintWriter out;
    private ArrayList<ClientHandler> clients;
    private String login;
    private ArrayList<String> logins;
    private ArrayList<Integer> usersId;
    private ArrayList<Player> players;
    private int userId;

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients, ArrayList<String> logins, ArrayList<Integer> usersId, ArrayList<Player> players) throws IOException {
        this.client = clientSocket;
        this.clients = clients;
        this.logins = logins;
        this.usersId = usersId;
        this.players = players;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    }

    public String getLogin(){
        return login;
    }

    public int getUserId(){
        return userId;
    }

    @Override
    public void run() {


        out.println("S:CONNECT");

        Player player1 = players.get(0);
        Player player2 = players.get(1);
        Player player3 = players.get(2);
        Player player4 = players.get(3);
        Player player5 = players.get(4);

        try {

            while (true) {
                out.println("S:ENTER YOUR LOGIN: ");
                login = in.readLine();

                if (!logins.contains(login) && !login.isEmpty()) {
                    logins.add(login);
                    serverMessageToAll("OK");
                    /*userId = getRandomNumberInRange(1,4);
                    while (true){
                        if(usersId.contains(userId)){
                            userId = getRandomNumberInRange(1,4);
                        }
                        else{
                            usersId.add(userId);
                            break;
                        }
                    }*/

                    break;
                } else {
                    serverMessage("ERROR");
                }

            }

            int i = 0;

            while (logins.size() < 5) {

                if (i % 41 == 0) {
                    serverMessage("Waiting for players...");
                }
                Thread.sleep(400);
                i++;
            }



            //serverMessageToAll("START " + usersId.equals(1));

            for (Player player : players) {
                if (player.getPlayerName().equals("x")) {
                    player.setPlayerName(login);
                    break;
                }
            }

            int b = 1;
            for (Player player : players) {
                serverMessage("player" + b + " " + player.getPlayerName());
                b++;
            }

            Deck mainDeck = new Deck();
            mainDeck.populateDeck();

            Player[] playerOrder = new Player[5];
            playerOrder = randomPlayer(playerOrder, player1, player2, player3, player4, player5);


            while (true) {
                while (true) {

                    mainDeck.shuffleDeck();
                    Card[] deck1 = mainDeck.getDeck();

                    int deckLength = deck1.length - 1;

                    deckLength = burnCard(deckLength);

                    deckLength = dealCards(player1, player2, player3, player4, player5, deckLength, deck1);

                    int turnOrder = 0;
                    int turnOrder2 = 1;
                    int turnOrder3 = 2;
                    int turnOrder4 = 3;
                    int turnOrder5 = 4;

                    while (true) {


                        if (playerOrder[turnOrder].getIsPlaying()) {{
                            playerOrder[turnOrder].setPlayedHandmaid(false);
                            deckLength = dealCard2(playerOrder[turnOrder], deckLength, deck1);
                            serverMessage("START " + playerOrder[turnOrder].getPlayerName());
                            serverMessage(playerOrder[turnOrder].getPlayerName() + " YOUR MOVE " + playerOrder[turnOrder].getCard1().getCardName() + " OR " + playerOrder[turnOrder].getCard2().getCardName());

                            int choice = 0;

                            while (true) {
                                choice = in.read();
                                if (playerOrder[turnOrder].getCard1().getCardName().equals("countess")) {
                                    if (playerOrder[turnOrder].getCard2().getCardName().equals("prince") || playerOrder[0].getCard2().getCardName().equals("king")) {
                                        if (choice == 2) {
                                            serverMessage("ERROR");
                                        } else {
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                } else if (playerOrder[turnOrder].getCard2().getCardName().equals("countess")) {
                                    if (playerOrder[turnOrder].getCard1().getCardName().equals("prince") || playerOrder[turnOrder].getCard1().getCardName().equals("king")) {
                                        if (choice == 1) {
                                            serverMessage("ERROR");
                                        } else {
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }

                            if (choice == 1) {
                                deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder], playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength, deck1,in ,out, client, clients);
                                playerOrder[turnOrder].setCard1(playerOrder[turnOrder].getCard2());
                            } else {
                                deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder], playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength, deck1,in ,out, client, clients);
                            }

                            int isOutCount = 0;

                            if (!player1.getIsPlaying()) {
                                isOutCount++;
                            }
                            if (!player2.getIsPlaying()) {
                                isOutCount++;
                            }
                            if (!player3.getIsPlaying()) {
                                isOutCount++;
                            }
                            if (!player4.getIsPlaying()) {
                                isOutCount++;
                            }
                            if (!player5.getIsPlaying()) {
                                isOutCount++;
                            }

                            if (isOutCount == 3) {


                                if (player1.getIsPlaying()) {
                                    player1.setScore(player1.getPlayerScore() + 1);
                                    serverMessage("ROUND WINNER " + player1.getPlayerName());
                                    break;
                                } else if (player2.getIsPlaying()) {
                                    player2.setScore(player2.getPlayerScore() + 1);
                                    serverMessage("ROUND WINNER " + player2.getPlayerName());
                                    break;
                                } else if (player3.getIsPlaying()) {
                                    player3.setScore(player3.getPlayerScore() + 1);
                                    serverMessage("ROUND WINNER " + player3.getPlayerName());
                                    break;
                                } else if (player3.getIsPlaying()) {
                                    player4.setScore(player4.getPlayerScore() + 1);
                                    serverMessage("ROUND WINNER " + player4.getPlayerName());
                                    break;
                                } else {
                                    player4.setScore(player5.getPlayerScore() + 1);
                                    serverMessage("ROUND WINNER " + player5.getPlayerName());
                                    break;
                                }
                            }


                            if (deckLength == 0) {
                                int cardVal1 = 0;
                                int cardVal2 = 0;
                                int cardVal3 = 0;
                                int cardVal4 = 0;
                                int cardVal5 = 0;

                                if (player1.getIsPlaying()) {
                                    cardVal1 = player1.getCard1().getCardValue();
                                }
                                if (player2.getIsPlaying()) {
                                    cardVal2 = player2.getCard1().getCardValue();
                                }
                                if (player3.getIsPlaying()) {
                                    cardVal3 = player3.getCard1().getCardValue();
                                }
                                if (player4.getIsPlaying()) {
                                    cardVal4 = player4.getCard1().getCardValue();
                                }
                                if (player5.getIsPlaying()) {
                                    cardVal5 = player5.getCard1().getCardValue();
                                }

                                if (cardVal1 > cardVal2 && cardVal1 > cardVal3 && cardVal1 > cardVal4 && cardVal1 > cardVal5) {
                                    player1.setScore(player1.getPlayerScore() + 1);
                                    serverMessage("ROUND WINNER " + player1.getPlayerName());
                                } else if (cardVal2 > cardVal1 && cardVal2 > cardVal3 && cardVal2 > cardVal4 && cardVal2 > cardVal5) {
                                    player2.setScore(player2.getPlayerScore() + 1);
                                    serverMessage("ROUND WINNER " + player2.getPlayerName());
                                } else if (cardVal3 > cardVal1 && cardVal3 > cardVal2 && cardVal3 > cardVal4 && cardVal3 > cardVal5) {
                                    player3.setScore(player3.getPlayerScore() + 1);
                                    serverMessage("ROUND WINNER " + player3.getPlayerName());
                                } else if (cardVal4 > cardVal1 && cardVal4 > cardVal2 && cardVal4 > cardVal3 && cardVal4 > cardVal5) {
                                    player4.setScore(player4.getPlayerScore() + 1);
                                    serverMessage("ROUND WINNER " + player4.getPlayerName());
                                } else {
                                    serverMessage("ROUND RESULTS " + player1.getPlayerName() + player1.getPlayerScore());
                                    serverMessage("ROUND RESULTS " + player2.getPlayerName() + player2.getPlayerScore());
                                    serverMessage("ROUND RESULTS " + player3.getPlayerName() + player3.getPlayerScore());
                                    serverMessage("ROUND RESULTS " + player4.getPlayerName() + player4.getPlayerScore());
                                    serverMessage("ROUND RESULTS " + player5.getPlayerName() + player5.getPlayerScore());
                                    break;
                                }

                                serverMessage("ROUND RESULTS " + player1.getPlayerName() + player1.getPlayerScore());
                                serverMessage("ROUND RESULTS " + player2.getPlayerName() + player2.getPlayerScore());
                                serverMessage("ROUND RESULTS " + player3.getPlayerName() + player3.getPlayerScore());
                                serverMessage("ROUND RESULTS " + player4.getPlayerName() + player4.getPlayerScore());
                                serverMessage("ROUND RESULTS " + player5.getPlayerName() + player5.getPlayerScore());
                                break;
                            }
                        }

                        if (turnOrder == 3) {
                            turnOrder = 0;
                        } else {
                            turnOrder++;
                        }

                        if (turnOrder2 == 3) {
                            turnOrder2 = 0;
                        } else {
                            turnOrder2++;
                        }

                        if (turnOrder3 == 3) {
                            turnOrder3 = 0;
                        } else {
                            turnOrder3++;
                        }

                        if (turnOrder4 == 3) {
                            turnOrder4 = 0;
                        } else {
                            turnOrder4++;
                        }

                        if (turnOrder5 == 3) {
                            turnOrder5 = 0;
                        } else {
                            turnOrder5++;
                        }
                    }

                        else{
                            if (turnOrder == 3) {
                                turnOrder = 0;
                            } else {
                                turnOrder++;
                            }
                            if (turnOrder2 == 3) {
                                turnOrder2 = 0;
                            } else {
                                turnOrder2++;
                            }

                            if (turnOrder3 == 3) {
                                turnOrder3 = 0;
                            } else {
                                turnOrder3++;
                            }

                            if (turnOrder4 == 3) {
                                turnOrder4 = 0;
                            } else {
                                turnOrder4++;
                            }
                            if (turnOrder5 == 3) {
                                turnOrder5 = 0;
                            } else {
                                turnOrder5++;
                            }

                        }
                    }
                    serverMessage("ROUND RESULTS " + player1.getPlayerName() + player1.getPlayerScore());
                    serverMessage("ROUND RESULTS " + player2.getPlayerName() + player2.getPlayerScore());
                    serverMessage("ROUND RESULTS " + player3.getPlayerName() + player3.getPlayerScore());
                    serverMessage("ROUND RESULTS " + player4.getPlayerName() + player4.getPlayerScore());
                    serverMessage("ROUND RESULTS " + player5.getPlayerName() + player5.getPlayerScore());

                    player1.setPlayedHandmaid(false);
                    player1.setPlaying(true);
                    player2.setPlayedHandmaid(false);
                    player2.setPlaying(true);
                    player3.setPlayedHandmaid(false);
                    player3.setPlaying(true);
                    player4.setPlayedHandmaid(false);
                    player4.setPlaying(true);
                    player5.setPlayedHandmaid(false);
                    player5.setPlaying(true);

                    if (player1.getPlayerScore() == 4) {
                        serverMessage("GAME WINNER " + player1.getPlayerName());
                        break;
                    } else if (player2.getPlayerScore() == 4) {
                        serverMessage("GAME WINNER " + player2.getPlayerName());
                        break;
                    } else if (player3.getPlayerScore() == 4) {
                        serverMessage("GAME WINNER " + player3.getPlayerName());
                        break;
                    } else if (player4.getPlayerScore() == 4) {
                        serverMessage("GAME WINNER " + player4.getPlayerName());
                        break;
                    } else if (player5.getPlayerScore() == 4) {
                        serverMessage("GAME WINNER " + player5.getPlayerName());
                        break;
                    }
                }
            }
        }

         catch (IOException e) {
            System.err.println("IO exepction in client handler");
            System.err.println(e.getStackTrace());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void outToAll(String msg) {
        for (ClientHandler aClient : clients) {
            aClient.out.println(login + ": " + msg);
        }
    }

    private void serverMessageToAll(String msg) {
        for (ClientHandler aClient : clients) {
            aClient.out.println("S:" + msg);
        }
    }

    private void serverMessage(String msg) {
        for (ClientHandler aClient : clients) {
            if(aClient.login.equals(login)){
                aClient.out.println(msg);
            }
        }

    }


    public static Player [] randomPlayer(Player[] array, Player pOne, Player pTwo, Player pThree, Player pFour, Player pFive){
        int random =(int)(Math.random()*4);

        if(random == 1){
            array[0] = pOne;
            array[1] = pTwo;
            array[2] = pThree;
            array[3] = pFour;
            array[4] = pFive;
        }
        else if(random == 2){
            array[0] = pTwo;
            array[1] = pThree;
            array[2] = pFour;
            array[3] = pOne;
            array[4] = pFive;
        }
        else if(random == 3){
            array[0] = pThree;
            array[1] = pFour;
            array[2] = pOne;
            array[3] = pTwo;
            array[4] = pFive;
        }else{
            array[0] = pFour;
            array[1] = pOne;
            array[2] = pTwo;
            array[3] = pThree;
            array[4] = pFive;
        }

        return array;
    }

    public static int burnCard(int deckLength)
    {
        deckLength--;
        return deckLength;
    }

    public static int dealCards(Player pOne, Player pTwo, Player pThree, Player pFour, Player pFive, int deckLength, Card[] deck)
    {
        pOne.setCard1(deck[deckLength]);
        deckLength--;
        pTwo.setCard1(deck[deckLength]);
        deckLength--;
        pThree.setCard1(deck[deckLength]);
        deckLength--;
        pFour.setCard1(deck[deckLength]);
        deckLength--;
        pFive.setCard1(deck[deckLength]);
        deckLength--;
        return deckLength;
    }

    public static int dealCard2(Player player, int deckLength, Card[] deck){
        player.setCard2(deck[deckLength]);
        deckLength--;
        return deckLength;
    }







}
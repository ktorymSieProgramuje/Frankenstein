public class Deck {

    Card[] deck = new Card[25];

    public Card[] getDeck() {
        return deck;
    }


    public void shuffleDeck()
    {
        for(int i=0; i<deck.length; i++)
        {
            int ranNum = (int) (Math.random() * deck.length);
            Card temp;
            temp = deck[i];
            deck[i] = deck[ranNum];
            deck[ranNum] = temp;
        }
    }

    public void populateDeck()
    {

        for(int i=0; i<10; i++)
        {
            deck[i] = new Guard();
        }

        for(int i=10; i<13; i++)
        {
            deck[i] = new Priest();
        }

        for(int i=13; i<17; i++)
        {
            deck[i] = new Baron();
        }

        for(int i=17; i<19; i++)
        {
            deck[i] = new Handmaid();
        }

        for(int i=19; i<21; i++)
        {
            deck[i] = new Prince();
        }

        deck[13] = new King();
        deck[14] = new Countess();
        deck[15] = new Princess();
    }

}

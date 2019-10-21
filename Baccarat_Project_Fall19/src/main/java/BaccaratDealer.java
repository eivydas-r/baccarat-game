import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BaccaratDealer {
    ArrayList<Card> deck;
    // 52 cards total
    // 4 suits (diamonds, hearts, clubs, spades
    // 13 cards in each suit (1-10, J, Q, K)

    BaccaratDealer(){
        deck = new ArrayList<Card>();
    }

    // ---------------

    // generate a new standard 52 card deck where each card is an
    // instance of the Card class in the ArrayList<Card> deck
    public void generateDeck(){
        // generate hearts
        for(int i = 1; i <= 13; i++){
            Card card = new Card("H", i);
            deck.add(card);
        }

        // generate diamonds
        for(int i = 1; i <= 13; i++){
            Card card = new Card("D", i);
            deck.add(card);
        }

        // generate spades
        for(int i = 1; i <= 13; i++){
            Card card = new Card("S", i);
            deck.add(card);
        }

        // generate clubs
        for(int i = 1; i <= 13; i++){
            Card card = new Card("C", i);
            deck.add(card);
        }
    }

    // deal two cards and return them in an ArrayList<Card>
    public ArrayList<Card> dealHand(){
        ArrayList<Card> temp = new ArrayList<>();
        Card card1 = deck.get(0); // get 1st and 2nd card
        Card card2 = deck.get(1);
        temp.add(card1);
        temp.add(card2);
        deck.remove(0); // delete from deck
        deck.remove(0);

        return temp;
    }

    // deal a single card and return it
    public Card drawOne(){
        Card card = deck.get(0); // get top card
        deck.remove(0); // delete from deck
        return card;
    }

    // create a new deck of 52 cards and “shuffle”;
    // randomize the cards in that ArrayList<Card>
    public void shuffleDeck(){
        Collections.shuffle(deck); // shuffle method that shuffles an ArrayList
    }

    // return how many cards are in this.deck at any given time
    public int deckSize(){
        return this.deck.size(); // return the deck size
    }
}























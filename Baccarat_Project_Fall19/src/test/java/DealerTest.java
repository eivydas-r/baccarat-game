import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

// 2 units tests per method
// 1 per class constructor
class DealerTest {
	//BaccaratDealer dealer = new BaccaratDealer();
	//ArrayList<Card> hand1;
	//ArrayList<Card> hand2;


	@BeforeEach
	void init(){
	    /*hand1 = new ArrayList<Card>();
		hand2 = new ArrayList<Card>();
	    hand1.add(card1);
		hand1.add(card2);
		hand1.add(card3);
		hand2.add(card4);
		hand2.add(card5);
		hand2.add(card6);*/
	}

	@Test // Card constructor
    void testCardConstructor(){
		Card card1 = new Card("H",4);
		Card card2 = new Card("D",9);
		Card card3 = new Card("S",1);
	    assertEquals(4, card1.value, "Card value didn't initialize correctly");
        assertEquals(9, card2.value, "Card value didn't initialize correctly");
        assertEquals(1, card3.value, "Card value didn't initialize correctly");
    }

	// BaccaratDealer tests

	@Test // BaccaratDealer constructor
	void testBaccaratDealerConstructor(){
		BaccaratDealer dealer = new BaccaratDealer();
		assertNotNull(dealer.deck);
	}

	@Test // BaccaratDealer -> generateDeck()
	void testGenerateDeck1(){
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();

		assertEquals(1,dealer.deck.get(0).value, "wrong card value");
		assertEquals("H",dealer.deck.get(0).suite,"wrong card suite");
		assertEquals(2,dealer.deck.get(1).value, "wrong card value");
		assertEquals("H",dealer.deck.get(1).suite,"wrong card suite");
		assertEquals(13,dealer.deck.get(12).value, "wrong card value");
		assertEquals("H",dealer.deck.get(12).suite,"wrong card suite");

		assertEquals(1,dealer.deck.get(13).value, "wrong card value");
		assertEquals("D",dealer.deck.get(13).suite,"wrong card suite");
		assertEquals(2,dealer.deck.get(14).value, "wrong card value");
		assertEquals("D",dealer.deck.get(14).suite,"wrong card suite");
		assertEquals(13,dealer.deck.get(25).value, "wrong card value");
		assertEquals("D",dealer.deck.get(25).suite,"wrong card suite");
	}

	@Test // BaccaratDealer -> generateDeck()
	void testGenerateDeck2(){
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();

		assertEquals(1,dealer.deck.get(26).value, "wrong card value");
		assertEquals("S",dealer.deck.get(26).suite,"wrong card suite");
		assertEquals(2,dealer.deck.get(27).value, "wrong card value");
		assertEquals("S",dealer.deck.get(27).suite,"wrong card suite");
		assertEquals(13,dealer.deck.get(38).value, "wrong card value");
		assertEquals("S",dealer.deck.get(38).suite,"wrong card suite");

		assertEquals(1,dealer.deck.get(39).value, "wrong card value");
		assertEquals("C",dealer.deck.get(39).suite,"wrong card suite");
		assertEquals(2,dealer.deck.get(40).value, "wrong card value");
		assertEquals("C",dealer.deck.get(40).suite,"wrong card suite");
		assertEquals(13,dealer.deck.get(51).value, "wrong card value");
		assertEquals("C",dealer.deck.get(51).suite,"wrong card suite");
	}

	@Test // BaccaratDealer -> dealHand()
	void testDealHand1(){
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		ArrayList<Card> hand = dealer.dealHand();

		assertEquals(1,hand.get(0).value, "wrong card value");
		assertEquals("H",hand.get(0).suite,"wrong card suite");
		assertEquals(2,hand.get(1).value, "wrong card value");
		assertEquals("H",hand.get(1).suite,"wrong card suite");
	}

	@Test // BaccaratDealer -> dealHand()
	void testDealHand2(){
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		dealer.deck.remove(0);
		dealer.deck.remove(0);
		ArrayList<Card> hand = dealer.dealHand();


		assertEquals(3,hand.get(0).value, "wrong card value");
		assertEquals("H",hand.get(0).suite,"wrong card suite");
		assertEquals(4,hand.get(1).value, "wrong card value");
		assertEquals("H",hand.get(1).suite,"wrong card suite");
	}

	@Test // BaccaratDealer -> drawOne()
	void testDrawOne1(){
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();

		Card card = dealer.drawOne();

		assertEquals(1,card.value, "wrong card value");
		assertEquals("H",card.suite,"wrong card suite");
	}

	@Test // BaccaratDealer -> drawOne()
	void testDrawOne2(){
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		dealer.deck.remove(0);

		Card card = dealer.drawOne();

		assertEquals(2,card.value, "wrong card value");
		assertEquals("H",card.suite,"wrong card suite");
	}

	@Test // BaccaratDealer -> shuffleDeck()
	void testShuffleDeck1(){
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		Card cardTemp1 = dealer.deck.get(0);

		assertEquals(1,cardTemp1.value, "wrong card value");
		assertEquals("H",cardTemp1.suite,"wrong card suite");

		dealer.shuffleDeck();
		Card cardTemp2 = dealer.deck.get(0);

		assertNotSame(cardTemp1,cardTemp2, "cards same, CHANCE THAT CARDS SAME POSSIBLE");
	}

	@Test // BaccaratDealer -> shuffleDeck()
	void testShuffleDeck2(){
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		dealer.deck.remove(0);
		Card cardTemp1 = dealer.deck.get(0);

		assertEquals(2,cardTemp1.value, "wrong card value");
		assertEquals("H",cardTemp1.suite,"wrong card suite");

		dealer.shuffleDeck();
		Card cardTemp2 = dealer.deck.get(0);

		assertNotSame(cardTemp1,cardTemp2, "cards same");
	}

	@Test // BaccaratDealer -> deckSize()
	void testDeckSize1(){
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();

		assertEquals(52, dealer.deckSize(),"wrong deck size");

		dealer.deck.remove(0);

		assertEquals(51, dealer.deckSize(),"wrong deck size");
	}

	@Test // BaccaratDealer -> deckSize()
	void testDeckSize2(){
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();

		assertEquals(52, dealer.deckSize(),"wrong deck size");

		dealer.deck.remove(0);
		dealer.deck.remove(0);
		dealer.deck.remove(0);
		dealer.deck.remove(0);

		assertEquals(48, dealer.deckSize(),"wrong deck size");
	}

	// BaccaratGameLogic tests

	@Test // BaccaratGameLogic -> whoWon()
	void testWhoWon1(){
		BaccaratGameLogic logic = new BaccaratGameLogic();
		Card card1 = new Card("H",4);
		Card card2 = new Card("D",4); // 9
		Card card3 = new Card("S",1);
		Card card4 = new Card("C",5);
		Card card5 = new Card("D",5); // 2
		Card card6 = new Card("S",2);
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
	    hand1.add(card1);
		hand1.add(card2);
		hand1.add(card3);
		hand2.add(card4);
		hand2.add(card5);
		hand2.add(card6);

		String winner = logic.whoWon(hand1,hand2);

		assertEquals("Banker",winner,"wrong winner");

	}

	@Test // BaccaratGameLogic -> whoWon()
	void testWhoWon2(){
		BaccaratGameLogic logic = new BaccaratGameLogic();
		Card card1 = new Card("H",4);
		Card card2 = new Card("D",2); // 7
		Card card3 = new Card("S",1);
		Card card4 = new Card("C",9);
		Card card5 = new Card("D",8); // 7
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand1.add(card1);
		hand1.add(card2);
		hand1.add(card3);
		hand2.add(card4);
		hand2.add(card5);

		String winner = logic.whoWon(hand1,hand2);

		assertEquals("Draw",winner,"wrong winner");
	}

	@Test // BaccaratGameLogic -> handTotal()
	void testHandTotal1(){
		BaccaratGameLogic logic = new BaccaratGameLogic();
		Card card1 = new Card("H",4);
		Card card2 = new Card("D",8);
		Card card3 = new Card("S",1);
		Card card4 = new Card("C",10);
		Card card5 = new Card("D",12);
		Card card6 = new Card("S",2);
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand1.add(card1);
		hand1.add(card2);
		hand1.add(card3);
		hand2.add(card4);
		hand2.add(card5);
		hand2.add(card6); // hand 1 = 1, hand 2 = 2

		assertEquals(3,logic.handTotal(hand1), "wrong hand total");
		assertEquals(2,logic.handTotal(hand2), "wrong hand total");
	}

	@Test // BaccaratGameLogic -> handTotal()
	void testHandTotal2(){
		BaccaratGameLogic logic = new BaccaratGameLogic();
		Card card1 = new Card("H",4);
		Card card2 = new Card("D",2); // 7
		Card card3 = new Card("S",1);
		Card card4 = new Card("C",9);
		Card card5 = new Card("D",8); // 7
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand1.add(card1);
		hand1.add(card2);
		hand1.add(card3);
		hand2.add(card4);
		hand2.add(card5);

		assertEquals(7,logic.handTotal(hand1), "wrong hand total");
		assertEquals(7,logic.handTotal(hand2), "wrong hand total");
	}

	@Test // BaccaratGameLogic -> evaluateBankerDraw()
	void testEvaluateBankerDraw1(){
		BaccaratGameLogic logic = new BaccaratGameLogic();
		Card card1 = new Card("H",2);
		Card card3 = new Card("S",2);
		Card card4 = new Card("C",2);
		Card card5 = new Card("D",8);
		Card card6 = new Card("C",4);
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		ArrayList<Card> hand3 = new ArrayList<Card>();
		hand1.add(card1); // hand1: 4
		hand1.add(card3);
		hand2.add(card4); // hand2: 10
		hand2.add(card5);
		hand3.add(card1); // hand3: 6
		hand3.add(card6);

		assertEquals(true,logic.evaluateBankerDraw(hand1,card4),"should not draw");
		assertEquals(true,logic.evaluateBankerDraw(hand2,card5),"should draw");
		assertEquals(false,logic.evaluateBankerDraw(hand3,card5),"should draw");
	}

	@Test // BaccaratGameLogic -> evaluateBankerDraw()
	void testEvaluateBankerDraw2(){
		BaccaratGameLogic logic = new BaccaratGameLogic();
		Card card1 = new Card("H",2);
		Card card3 = new Card("S",3);
		Card card4 = new Card("C",2);
		Card card5 = new Card("D",6);
		Card card6 = new Card("C",1);
		Card card7 = new Card("C",4);
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		ArrayList<Card> hand3 = new ArrayList<Card>();
		hand1.add(card1); // hand1: 5, card7 = 4
		hand1.add(card3);
		hand2.add(card4); // hand2: 8, card5 = 6
		hand2.add(card5);
		hand3.add(card1); // hand3: 3, card 5 = 6
		hand3.add(card6);

		assertEquals(true,logic.evaluateBankerDraw(hand1,card7),"should draw");
		assertEquals(false,logic.evaluateBankerDraw(hand2,card5),"should not draw");
		assertEquals(true,logic.evaluateBankerDraw(hand3,card5),"should draw");
	}

	@Test // BaccaratGameLogic -> evaluatePlayerDraw()
	void testEvaluatePlayerDraw1(){
		BaccaratGameLogic logic = new BaccaratGameLogic();
		Card card1 = new Card("H",4);
		Card card3 = new Card("S",1);
		Card card4 = new Card("C",9);
		Card card5 = new Card("D",8);
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand1.add(card1);
		hand1.add(card3);
		hand2.add(card4);
		hand2.add(card5);

		assertEquals(true,logic.evaluatePlayerDraw(hand1),"should draw another card");
		assertEquals(false,logic.evaluatePlayerDraw(hand2),"should not draw another card");
	}

	@Test // BaccaratGameLogic -> evaluatePlayerDraw()
	void testEvaluatePlayerDraw2(){
		BaccaratGameLogic logic = new BaccaratGameLogic();
		Card card1 = new Card("H",9);
		Card card3 = new Card("S",5); // 4
		Card card4 = new Card("C",3);
		Card card5 = new Card("D",1);
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand1.add(card1);
		hand1.add(card3);
		hand2.add(card4);
		hand2.add(card5);

		assertEquals(true,logic.evaluatePlayerDraw(hand1),"should draw another card");
		assertEquals(true,logic.evaluatePlayerDraw(hand2),"should draw another card");
	}

	// BaccaratGame tests

	@Test // BaccaratGame -> evaluateWinnings()
	void testEvaluateWinnings1(){
		BaccaratGame game = new BaccaratGame();
		ArrayList<Card> playerHand = new ArrayList<>();
		ArrayList<Card> bankerHand = new ArrayList<>();
		Card card1 = new Card("H",4);
		Card card3 = new Card("S",1);
		Card card4 = new Card("C",9);
		Card card5 = new Card("D",4);
		playerHand.add(card1); // playerHand = 5
		playerHand.add(card3);
		bankerHand.add(card4); // bankerHand = 3
		bankerHand.add(card5);
		game.playerHand.addAll(playerHand);
		game.bankerHand.addAll(bankerHand);

		game.currentBet = 50; // $50 bet
		game.bettingOn = "Player";

		assertEquals(50,game.evaluateWinnings(),"wrong winning amount");

		ArrayList<Card> playerHand2 = new ArrayList<>();
		ArrayList<Card> bankerHand2 = new ArrayList<>();
		playerHand2.add(card1);
		bankerHand2.add(card5);
		game.playerHand.clear();
		game.bankerHand.clear();
		game.playerHand.addAll(playerHand2);
		game.bankerHand.addAll(bankerHand2);

		game.bettingOn = "Draw";
		assertEquals(50.0*8,game.evaluateWinnings(),"wrong winning amount");
	}

	@Test
	void testEvaluateWinnings2(){
		BaccaratGame game = new BaccaratGame();
		ArrayList<Card> playerHand = new ArrayList<>();
		ArrayList<Card> bankerHand = new ArrayList<>();
		Card card1 = new Card("H",1);
		Card card3 = new Card("S",2);
		Card card4 = new Card("C",4);
		Card card5 = new Card("D",4);
		playerHand.add(card1); // playerHand = 3
		playerHand.add(card3);
		bankerHand.add(card4); // bankerHand = 8
		bankerHand.add(card5);
		game.playerHand.addAll(playerHand);
		game.bankerHand.addAll(bankerHand);

		game.currentBet = 50; // $50 bet
		game.bettingOn = "Player";

		assertEquals(-50,game.evaluateWinnings(),"wrong winning amount");
		game.bettingOn = "Banker";
		assertEquals(50.0,game.evaluateWinnings(),"wrong winning amount");
	}

	@Test // BaccaratGame constructor
	void testBaccaratGameConstructor(){
		BaccaratGame game = new BaccaratGame();
		assertNotNull(game.playerHand);
		assertNotNull(game.bankerHand);
		assertNotNull(game.theDealer);
		assertNotNull(game.gameLogic);
	}
}


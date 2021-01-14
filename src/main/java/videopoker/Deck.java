package videopoker;

import java.util.Random;

public class Deck {
	
	private static int deckSize = 52;
	private Card [] cardDeck = new Card[deckSize];
	private static int shuffleCount = 1000;
	private int position = 0;
	
	public Deck() {
		Card.fillDeck(cardDeck);
	}
	
	public void shuffle () {
		Random random = new Random();
		for (int i = 0; i < shuffleCount; i ++) {
			int a = random.nextInt(deckSize);
			int b = random.nextInt(deckSize);
			Card c = cardDeck[a];
			cardDeck[a] = cardDeck[b];
			cardDeck[b] = c;
		}
	}
		
	public Card getTopCard () {
		return cardDeck[position++];
	}
	
	public Card getCard (int pos) {
		return cardDeck[pos];
	}
	

}

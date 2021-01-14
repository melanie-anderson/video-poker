package videopoker;

public class Card {
	
	private Rank rank;
	private Suit suit;
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public Card(Card c) {
		this.rank = c.getRank();
		this.suit = c.getSuit();
	}
	
	public Rank getRank() {
		return rank;
	}
	public Suit getSuit() {
		return suit;
	}
	
	public static void fillDeck (Card [] deck) {
		int i = 0;
		for (Suit suitNum : Suit.values()) {
			for (Rank rankNum : Rank.values()) {
				deck[i] = new Card(rankNum, suitNum);
				i++;
			}
		}
	}
	
	public int compareRank (Card card) {
		return getRank().compareTo(card.getRank());
	}
	
	@Override
	public String toString() {
		String strOutput = "";
		switch(rank) {
			case Ace : 
				strOutput = "A";
				break;
			case Two:
				strOutput = "2";
				break;
			case Three:
				strOutput = "3";
				break;
			case Four:
				strOutput = "4";
				break;
			case Five:
				strOutput = "5";
				break;
			case Six:
				strOutput = "6";
				break;
			case Seven:
				strOutput = "7";
				break;
			case Eight:
				strOutput = "8";
				break;
			case Nine:
				strOutput = "9";
				break;
			case Ten:
				strOutput = "10";
				break;
			case Jack:
				strOutput = "J";
				break;
			case Queen:
				strOutput = "Q";
				break;
			case King:
				strOutput = "K";
				break;
		}
		char c = ' ';
		switch(suit) {
			case Heart :
				c = '\u2665';
				break;
			case Diamond :
				c = '\u2666';
				break;
			case Club :
				c = '\u2663';
				break;
			case Spade :
				c = '\u2660';
				break;
		}
		return strOutput + c;
	}
	
	
}

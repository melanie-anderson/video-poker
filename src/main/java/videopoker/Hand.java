package videopoker;

public class Hand {
	
	private int handSize = 5;
	private Card[] hand = new Card[handSize];
	
	public Hand(int handSize, Deck deck) {
		this.handSize = handSize;
		for (int i = 0; i < handSize; i++) {
			hand[i] = deck.getTopCard();
		}
	}
	
	public void displayHand () {
		String strOut = "Hand: ";
		for (Card card: hand) {
			strOut += card + " ";
		}
		System.out.println(strOut);
	}
	
	public void replaceCard (int pos, Deck deck) {
		hand[pos] = deck.getTopCard();
	}
	
	public Card getCard (int pos) {
		return hand[pos];
	}
	
	public void sortHand () {
		boolean switched = true;
		while(switched == true) {
			switched = false;
			for (int i = 1; i < handSize; i++) {
				if (hand[i].compareRank(hand[i-1]) < 0) {
					Card tempCard = new Card(hand[i]);
					hand[i] = hand[i-1];
					hand[i-1] = tempCard;
					switched = true;
				}
			}
		}
	}
	
	public boolean isFlush () {
		for(int i = 1; i < hand.length; i++) {
			if (hand[0].getSuit().compareTo(hand[i].getSuit()) != 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isStraight () {
		if(hand[4].getRank() != Rank.Ace) {
			for(int i = 1; i < hand.length; i++) {
				if (hand[i].getRank().ordinal() - hand[i-1].getRank().ordinal() != 1) {
					return false;
				}
			}
		}
		else {
			return (hand[0].getRank() == Rank.Two && hand[1].getRank() == Rank.Three && hand[2].getRank() == Rank.Four && hand[3].getRank() == Rank.Five);		
		}
		return true;
	}
	
	public boolean isFourOfAKind () {
		boolean hasFour = false;
		Rank firstRank = hand[0].getRank();
		Rank lastRank = hand[4].getRank();
		if(hand[1].getRank().compareTo(firstRank) == 0 && hand[2].getRank().compareTo(firstRank) == 0 && hand[3].getRank().compareTo(firstRank) == 0) {
			hasFour = true;
		}else if (hand[3].getRank() == lastRank && hand[2].getRank() == lastRank && hand[1].getRank() == lastRank) {
			hasFour = true;
		}
		return hasFour;
	}
	
	public boolean isThreeOfAKind () {
		boolean hasThree = false;
		if(hand[1].compareRank(hand[0]) == 0 && hand[2].compareRank(hand[0]) == 0) {
			hasThree = true;
		}else if(hand[2].compareRank(hand[1]) == 0 && hand[3].compareRank(hand[1]) == 0) {
			hasThree = true;
		}else if(hand[3].compareRank(hand[2]) == 0 && hand[4].compareRank(hand[2]) == 0) {
			hasThree = true;
		}
		return hasThree;
	}
	
	public boolean isTwoPair () {
		boolean hasTwoPair = false;
		if(hand[0].getRank() == hand[1].getRank() && hand[2].getRank() == hand[3].getRank()) {
			hasTwoPair = true;
		}else if(hand[1].getRank() == hand[2].getRank() && hand[3].getRank() == hand[4].getRank()) {
			hasTwoPair = true;
		}
		return hasTwoPair;
	}
	
	public boolean isFrontPair () {
		return hand[0].getRank() == hand[1].getRank();
	}
	
	public boolean isBackPair () {
		return hand[3].getRank() == hand[4].getRank();
	}
	
	public boolean isJackOrHigherPair () {
		boolean pair = false;
		for(int i = 0; i < handSize - 1; i++) {
			if(hand[i].getRank().compareTo(Rank.Jack) >= 0) {
				if(hand[i].compareRank(hand[i+1]) == 0) {
					pair = true;
				}
			}
		}
		return pair;
	}
	
	public boolean isFullHouse () {
		boolean hasFullHouse = false;
		if(isFrontPair() && isThreeOfAKind() && hand[0].compareRank(hand[2]) != 0) {
				hasFullHouse = true; 
		}
		else if (isBackPair() && isThreeOfAKind() && hand[4].compareRank(hand[2]) != 0) {
			hasFullHouse = true;
		}
		return hasFullHouse;
	}

}

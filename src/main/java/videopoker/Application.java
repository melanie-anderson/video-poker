package videopoker;

import java.util.Scanner;

public class Application {
	
	private static int defaultBankroll = 1000;
	private static int handSize = 5;
	
	public static void main(String[] args) {
		Deck deck = new Deck();
		int bankroll = getBankroll(defaultBankroll);
		boolean userQuits = false;
		
		while (bankroll > 0 && userQuits == false) {
			int bet = getBet(bankroll);
			bankroll -= bet;
			deck.shuffle();
			Hand hand = new Hand(handSize, deck);
			hand.displayHand();
			int [] cardsToChange = getCardsToChange(handSize);
			for (int num : cardsToChange) {
				hand.replaceCard(num, deck);
			}
			hand.displayHand();
			WinningHands handType = checkHandForWinner(hand);
			int winnings = calculateWinnings(handType, bet);
			bankroll += winnings;
			userQuits = doesUserQuit();
		}
		
		System.out.println("Final Bankroll: $" + bankroll + ". Thanks for playing!");
		
	}

	private static boolean doesUserQuit() {
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("Do you want to quit?");
		String toQuit = inputScanner.nextLine().toLowerCase();
		boolean bQuit = false;
		if (toQuit.charAt(0) == 'y') {
			bQuit = true;
		}
//		inputScanner.close();
		return bQuit;
	}

	private static int calculateWinnings(WinningHands handType, int bet) {
		int winnings = 0;
		switch(handType) {
		case RoyalFlush :
			winnings = bet *250;
			break;
		case StraightFlush :
			winnings = bet *50;
		case FourOfAKind :
			winnings = bet *25;
			break;
		case FullHouse :
			winnings = bet *9;
			break;
		case Flush :
			winnings = bet *6;
			break;
		case Straight :
			winnings = bet *4;
			break;
		case ThreeOfAKind :
			winnings = bet *3;
			break;
		case TwoPair :
			winnings = bet *2;
			break;
		case JacksOrBetterPair :
			winnings = bet;
			break;
		case NoWin :
			break;
		}
		return winnings;
	}

	private static WinningHands checkHandForWinner(Hand hand) {
//		System.out.println("Should be sorted");
		hand.sortHand();
//		hand.displayHand();
		WinningHands handType = WinningHands.NoWin;
		if (hand.isFlush()) {
			if (hand.isStraight()) {
				if(hand.getCard(0).getRank() == Rank.Ten) {
					handType = WinningHands.RoyalFlush;
				} else{
				handType = WinningHands.StraightFlush;
				}
			}else {
				handType = WinningHands.Flush;
			}
		}else if (hand.isStraight()) {
			handType = WinningHands.Straight;
		}else if (hand.isFullHouse()) {
			handType = WinningHands.FullHouse;
		}else if (hand.isFourOfAKind()) {
			handType = WinningHands.FourOfAKind;
		}else if (hand.isTwoPair()) {
			handType = WinningHands.TwoPair;
		}else if (hand.isThreeOfAKind()) {
			handType = WinningHands.ThreeOfAKind;
		}else if (hand.isJackOrHigherPair()) {
			handType = WinningHands.JacksOrBetterPair;
		}
		return handType;
	}

	private static int [] getCardsToChange(int handSize) {
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("Please enter the position of the cards you want to change separated by a comma.");
		String ctcinput = inputScanner.nextLine();
		if (ctcinput.length() != 0) {
			String [] cardsToChangeStr= ctcinput.split(",");
			int [] cardsToChange = new int[cardsToChangeStr.length];
			for (int i = 0; i < cardsToChangeStr.length; i++) {
				cardsToChange[i] = Integer.parseInt(cardsToChangeStr[i])-1;
			}
			return cardsToChange;
		}
//		inputScanner.close();
		int [] emptyArray = new int[0];
		return emptyArray;
		
	}

	private static int getBet(int bankroll) {
		Scanner inputScanner = new Scanner(System.in);
		int bet = 0;
		while (bet == 0) {
		System.out.println("Please enter the amount you would like to bet: ");
		bet = inputScanner.nextInt();
		if (bet > bankroll) {
			System.out.println("Sorry you do not have enough bankroll for that bet.");
			bet = 0;
			}
		}
//		inputScanner.close();
		return bet;
	}

	private static int getBankroll(int defaultBankroll) {
		Scanner inputScanner = new Scanner(System.in);
		int bankroll = defaultBankroll;
		System.out.println ("Welcome to Video Poker!");
		System.out.println("Enter starting bankroll: (1000)");
		if (inputScanner.hasNextInt() == true) {
			bankroll = inputScanner.nextInt();
		}
//		inputScanner.close();
		return bankroll;
	}

//	private static void displayHand (Card [] hand) {
//		String strOut = "Hand: ";
//		for (Card card: hand) {
//			strOut += card + " ";
//		}
//		System.out.println(strOut);
//	}
	
}

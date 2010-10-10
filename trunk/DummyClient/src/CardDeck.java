public class CardDeck {
	int pointerCount;
	Card[] cardDeck = new Card[52];

	/**
	 * 1-13 Brick 14-26 Club 27-39 Heart 40-52 Spade
	 */
	CardDeck() {
		pointerCount=0;
	}

	protected void fillCards() {
		pointerCount = 0;
		for (int i = 0; i < 52; i++) {
			cardDeck[i] = new Card(i + 1);
			// System.out.println(cardDeck[i]);
		}
	}

	public void reset() {
		pointerCount = 0;
	}

	public void movePointer() {
		pointerCount++;
		;
	}

	public int getPointerCount() {
		return pointerCount;
	}

	public Card alloteCard() {
		System.out.println("alloteCard() pointerCount="+pointerCount);
		return cardDeck[pointerCount++];		
	}

	public Card[] getCardDeck() {
		return cardDeck;
	}

	public Card getCard(int i) {
		return cardDeck[i];
	}

	public void setCardDeck(Card[] cardDeck) {
		this.cardDeck = cardDeck;
	}

	public void setCard(Card cardDeck) {
		this.cardDeck[pointerCount++] = cardDeck;
	}

	public void printCardDeck() {
		for (int i = 0; i < 52; i++) {
			System.out.println(cardDeck[i].getCardNumber());
		}
	}
	public Card getLastCardOfDeck() {
		return cardDeck[pointerCount-1];
	}
	public Card getAndDeleteLastCardOfDeck() {
		Card card=cardDeck[pointerCount-1];
		System.out.println("getAndDeleteLastCardOfDeck card"+card.getCardNumber());
		cardDeck[--pointerCount]=null;
		return card;
	}


}

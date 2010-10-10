import java.util.Random;

public class SuffeledCardDeck extends CardDeck {
	Random rgen = new Random();

	SuffeledCardDeck(){
		this.fillCards();
		this.getSuffeledCardDeck();

	}

	public CardDeck getSuffeledCardDeck() {
		for (int i = 0; i < cardDeck.length; i++) {
			int randomPosition = rgen.nextInt(cardDeck.length);
			Card temp = cardDeck[i];
			cardDeck[i] = cardDeck[randomPosition];
			cardDeck[randomPosition] = temp;
		}
		return this;
	}

	public static void main(String srgv[]) {
		SuffeledCardDeck scd = new SuffeledCardDeck();
		scd.printCardDeck();
	}
}

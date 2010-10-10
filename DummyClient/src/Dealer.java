import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

public class Dealer {
	public static boolean deal(CardDeck cardDeck, int num,
			UserGameProfile userGameProfile) {
		System.out.println("CardDeck Dealer  number of cards=" + num);
		for (int i = 0; num > 0; num--, i++) {
			userGameProfile.setUserCard(cardDeck.alloteCard());
		}
		return false;
	}

	public static void drawCards(Game game, Graphics graphic,
			Image[] cardImages, UserGameProfile userGameProfile) {
		userGameProfile.drawCards(game, graphic, cardImages);
	}

	public static void pickAndMoveCards(Game game, Graphics graphic,
			Image[] cardImages, UserGameProfile userGameProfile,
			int pickedCardX, int pickedCardY, int currentX, int currentY) {
		userGameProfile.pickAndMoveCards(game, graphic, cardImages,
				userGameProfile, pickedCardX, pickedCardY, currentX, currentY);
	}

	public static void drawCards(Game game, Graphics g, Image[] cardImage,
			CardDeck cardDeck) {
		int pointerPosition = cardDeck.getPointerCount();
		System.out.println("pointerPosition=" + pointerPosition);
		if (pointerPosition == 0)
			return;
		g.drawImage(
				cardImage[cardDeck.getLastCardOfDeck().getCardNumber() - 1],
				450, 150, game);
	}
	public static void drawLeftCards(Game game, Graphics g, Image[] cardImage,
			Card[] card) {
		System.out.println("drawLeftCards");
		if (card[0] == null)
			return;
		System.out.println("drawLeftCards1");
		for(int i=0;i<card.length;i++){
		if(card[i]==null) return;	
		System.out.println("drawLeftCards card="+i +" value="+card[i].getCardNumber());
		g.drawImage(
				cardImage[card[i].getCardNumber()-1],
				470, 95+20*i, game);
	   }
	}
	public static void drawRightCards(Game game, Graphics g, Image[] cardImage,
			Card[] card) {
		if (card[0] == null)
			return;
		for(int i=0;i<card.length;i++){
		if(card[i]==null) return;	
		g.drawImage(
				cardImage[card[i].getCardNumber()-1],
				545, 95+20*i, game);
	   }
	}

	public static boolean isCardsInSequence(Card[] cards, boolean isSorted) {
		return true;
//		boolean isSequence = false;
//		if (!isSorted) {
//			for (int i = 0; i < cards.length - 1; i++) {
//				for (int j = i + 1; j < cards.length; j++) {
//					if (cards[i].getCardNumber() > cards[j].getCardNumber()) {
//						Card temp = cards[j];
//						cards[j] = cards[i];
//						cards[i] = temp;
//					}
//				}
//			}
//		}
//		for (int i = 0; i < cards.length - 1; i++) {
//			if (cards[i].getCardNumber() != cards[i].getCardNumber() - 1)
//				return false;
//			if (i == cards.length - 1) {
//				isSequence = true;
//			}
//		}
//		return isSequence;
	}

	public static boolean isSameFace(Card[] cards) {
		return true;
//		int noOfNumFaces = 0;
//		int numFaceValue = -1;
//		for (Card card : cards) {
//			if (isNumberFace(card)) {
//				if (noOfNumFaces == 0) {
//					numFaceValue = card.getCardNumber() % 10;
//					noOfNumFaces++;
//				} else if (card.getCardNumber() % 10 != numFaceValue) {
//					return false;
//				}
//			} else {
//				if (noOfNumFaces == 0) {
//					numFaceValue = (card.getCardNumber() - 40) % 3;
//				} else if ((card.getCardNumber() - 40) % 3 != numFaceValue) {
//					return false;
//				}
//			}
//		}
//		return (noOfNumFaces==cards.length || noOfNumFaces==0);
	}

	public static boolean isNumberFace(Card card) {
		return (card.getCardNumber() <= 40 && card.getCardNumber() > 40);
	}
}

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

public class UserGameProfile {
	int playerId;
	int cardCount;
	// make it configurable
	Card[] userCards = new Card[8];

	UserGameProfile(int uniqueid) {
		playerId = uniqueid;
		cardCount = 0;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public Card[] getUserCards() {
		return userCards.clone();
	}

	public void drawCards(Game game, Graphics g, Image[] cardImage) {
		for (int i = 0, j = 0; i < userCards.length; i++) {
			if (userCards[i] == null||!userCards[i].isShowCard()) {
				continue;
			}
			g.drawImage(cardImage[userCards[i].getCardNumber() - 1],
					250 + j++ * 20, 250, game);
		}
	}

	public void setUserCards(Card[] userCards) {
		this.userCards = userCards;
	}

	public void reArrengeCards(int card1Postion, int card2Postion) {
		int filledCardIndex = cardCount - 1;
		if (card1Postion > filledCardIndex)
			card1Postion = filledCardIndex;
		if (card2Postion > filledCardIndex)
			card2Postion = filledCardIndex;
		System.out.println("reArrengeCards card1Postion=" + card1Postion
				+ " card2Postion=" + card2Postion);
		System.out.println("reArrengeCards card1="
				+ userCards[card1Postion].getCardNumber() + " card2="
				+ userCards[card2Postion].getCardNumber());
		Card tmp = userCards[card1Postion];
		userCards[card1Postion] = userCards[card2Postion];
		userCards[card2Postion] = tmp;
	}

	public boolean setUserCard(Card userCard) {
		for (int i = 0; i < userCards.length; i++) {
			if (userCards[i] == null) {
				System.out.println("ith element of user card replaced by "+userCard.getCardNumber());
				userCards[i] = userCard;
				userCards[i].setShowCard(true);
				cardCount++;
				return true;
			}
		}
		return false;
	}

	public boolean removeUserCard(int cardPosition, GameTable gametable,boolean showCardTray) {
		if(showCardTray){
			userCards[cardPosition].setShowCard(false);
			System.out.println("setting setShowCard= false for ith elasement i="+cardPosition);
			return true;
		}
		if (cardCount != userCards.length) {
			return false;
		}
		if (cardPosition >= 0 && cardPosition <= userCards.length) {
			gametable.getClosedCardDeck().setCard(userCards[cardPosition]);
			for (int i = cardPosition; i < userCards.length - 1; i++) {
				userCards[i] = userCards[i + 1];
			}
			userCards[userCards.length - 1] = null;
			cardCount--;
			return true;
		}
		return false;
	}

	public void pickAndMoveCards(Game game, Graphics graphic,
			Image[] cardImages, UserGameProfile userGameProfile,
			int pickedCardX, int pickedCardY, int currentX, int currentY) {
		int pickedCardIndex = (pickedCardX - 250) / 20;
		if (pickedCardIndex > cardCount - 1)
			pickedCardIndex = cardCount - 1;
		for (int i = 0, j = 0; i < userCards.length; i++) {
			if (userCards[i] == null||!userCards[i].isShowCard()) {
				continue;
			}
			if (i == pickedCardIndex) {
				int diffX = pickedCardX - (250 + j++ * 20);
				int diffY = pickedCardY - 250;
				graphic.drawImage(cardImages[userCards[i].getCardNumber() - 1],
						currentX-diffX, currentY-diffY, game);
				System.out.println("currentX-diffX" + (currentX-diffX));
				System.out.println("currentY-diffY" + (currentY-diffY));
				continue;
			}
			graphic.drawImage(cardImages[userCards[i].getCardNumber() - 1],
					250 + j++ * 20, 250, game);
		}
	}
  public void resetCardsShowStatus(){
	  for(int i=0;i<cardCount;i++){
		  userCards[i].setShowCard(true);
	  }
  }
}

public class Card {
	
	int cardNumber;
	boolean showCard;
	Card(){
		cardNumber=0;
		showCard=true;
	}
	Card(int number){
		cardNumber=number;
	}
	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public boolean isShowCard() {
		return showCard;
	}
	public void setShowCard(boolean showCard) {
		this.showCard = showCard;
	}

}

public class GameTable {
	CardDeck gameCardDeck;
	CardDeck closedCardDeck;
	UserGameProfile[] userGameProfile = new UserGameProfile[5];
	int userCount = 0;

	GameTable() {
		userCount = 0;
		closedCardDeck=new CardDeck();
		gameCardDeck = getCardDeck();
		gameCardDeck.printCardDeck();
	}

	public CardDeck getGameCardDeck() {
		return gameCardDeck;
	}

	public void setGameCardDeck(CardDeck gameCardDeck) {
		this.gameCardDeck = gameCardDeck;
	}

	private CardDeck getCardDeck() {
		return new SuffeledCardDeck();
	}

	public UserGameProfile getUserGameProfile(int playerId) {
		return userGameProfile[playerId];
	}

	public int joinGameTable(UserGameProfile userProfile) {
		if (userCount >= userGameProfile.length) {
			return -1;
		}
		for (int i = 0; i < userGameProfile.length; i++) {
			if (userGameProfile[i] == null) {
				userProfile.setPlayerId(i);
				userGameProfile[i] = userProfile;
				userCount++;
				return i;
			}
		}
		return -1;
	}

	public int joinGameTable() {
		if (userCount >= userGameProfile.length) {
			return -1;
		}
		for (int i = 0; i < userGameProfile.length; i++) {
			if (userGameProfile[i] == null) {
				UserGameProfile userProfile = new UserGameProfile(i);
				userGameProfile[i] = userProfile;
				userCount++;
				return i;
			}
		}
		return -1;
	}

	public boolean leaveGameTable(UserGameProfile userProfile) {
		for (int i = 0; i < userGameProfile.length; i++) {
			if (userGameProfile[i].getPlayerId() == userProfile.getPlayerId()) {
				userGameProfile[i] = null;
				userCount--;
				return true;
			}
		}
		return false;
	}

	public boolean leaveGameTable(int playerId) {
		if (playerId > 0 && playerId <= userGameProfile.length) {
			userGameProfile[playerId] = null;
			return true;
		}
		return false;
	}
	
	public void pickCardFromClosedDeck(int playerId) {		
		UserGameProfile userProfile;
		System.out.println("pickCardFromClosedDeck playerId "+playerId);
		System.out.println("pickCardFromClosedDeck userGameProfile.length "+userGameProfile.length);
		if (playerId >= 0 && playerId <= userGameProfile.length){
			System.out.println(" pickCardFromClosedDeck ");
			userProfile=getUserGameProfile(playerId);
			userProfile.setUserCard(closedCardDeck.getAndDeleteLastCardOfDeck());
		}
	}
   public boolean winCheck(int playerId){
	   boolean winStatus=false;
	   
	   return winStatus;
   }
	public CardDeck getClosedCardDeck() {
		return closedCardDeck;
	}

	public void setClosedCardDeck(CardDeck closedCardDeck) {
		this.closedCardDeck = closedCardDeck;
	}

	public UserGameProfile[] getUserGameProfile() {
		return userGameProfile;
	}

	public void setUserGameProfile(UserGameProfile[] userGameProfile) {
		this.userGameProfile = userGameProfile;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
}

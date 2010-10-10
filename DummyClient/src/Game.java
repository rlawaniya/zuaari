import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Game extends Applet implements KeyListener, MouseListener,
		MouseMotionListener {
	int height, width;
	int mx, my; // the mouse coordinates
	Image backGroundImage;
	Image cardBack;
	Image[] cardImages = new Image[52];
	Image[] wait=new Image[5];
 	Card[] playerFinishCard;
	Card[] leftTrayCards = new Card[4];
	Card[] rightTrayCards = new Card[3];
	Image trayOn, trayOff;
	GameTable gameTable;
	CardDeck cardDeck;
	boolean isTrayWaitOn = false;
	boolean showCardTray = false;
	boolean cardDroppedInTray = false;
	boolean matchFinished = false;
	boolean moveCardOn = true;
	int playerId1;
	boolean isMouseClicked = false;
	boolean chanceFinished = false;
	int pressedPositionX, pressedPositionY, currentPositionX, currentPositionY,
			releasedPositionX, releasedPositionY;

	public void init() {
		setSize(780, 449);
		setBackground(Color.magenta);
		width = getSize().width;
		height = getSize().height;
		gameTable = new GameTable();
		cardDeck = gameTable.getGameCardDeck();
		// hard coded user this activity should happen on Login
		playerId1 = gameTable.joinGameTable();
		Dealer.deal(cardDeck, 7, gameTable.getUserGameProfile(playerId1));
		setCardImage();
		setWaitImage();
		repaint();
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void paint(Graphics g) {
		setBackGroundImage();
		g.drawImage(backGroundImage, 0, 0, this);
		if (matchFinished) {
			Image winner = getImage(getDocumentBase(), "win/congratulation.png");
			g.drawImage(winner, 250, 60, this);
			return;
		}
		for (int i = 0; i < 7; i++) {
			g.drawImage(cardBack, 150 + i * 3, 150, this);
		}
		if (showCardTray) {
			Dealer.drawLeftCards(this, g, cardImages, leftTrayCards);
			Dealer.drawRightCards(this, g, cardImages, rightTrayCards);
		} else {
			Dealer.drawCards(this, g, cardImages, gameTable.getClosedCardDeck());
            //Dummy player cards
			for (int i = 0; i < 7; i++) {
				g.drawImage(cardBack, 250 + i * 20, 80, this);
			}
		}
		if (isMouseClicked) {
			System.out.println("mousePressed" + isMouseClicked + "X="
					+ currentPositionX + " Y=" + currentPositionY);
			if(moveCardOn){
			Dealer.pickAndMoveCards(this, g, cardImages,
					gameTable.getUserGameProfile(playerId1), pressedPositionX,
					pressedPositionY, currentPositionX, currentPositionY);
			System.out.println("pickAndMoveCards  moveCardOn="+moveCardOn);
			} else{
				Dealer.drawCards(this, g, cardImages,
						gameTable.getUserGameProfile(playerId1));
				moveCardOn=true;
				System.out.println("drawCards  moveCardOn="+moveCardOn);
			}
		} else {
			Dealer.drawCards(this, g, cardImages,
					gameTable.getUserGameProfile(playerId1));
			System.out.println("drawCards");
		}

		if (isTrayWaitOn) {
			if (showCardTray) {
				g.drawImage(trayOn, 600, 300, this);
			} else {
				g.drawImage(trayOn, 600, 75, this);
			}
			isTrayWaitOn = false;
		} else {
			if (showCardTray) {
				g.drawImage(trayOff, 600, 300, this);
			} else {
				g.drawImage(trayOff, 600, 75, this);
			}
			isTrayWaitOn = true;
		}
		if (chanceFinished) {
			System.out.println("chanceFinished = " +chanceFinished);
			for (int i = 1; i < 30; i++) {
				for (int j = 0; j < 3000; j++){
					g.drawImage(wait[i%4+1], 300, 175, this);
				}
				
			}
			gameTable.getClosedCardDeck().setCard(cardDeck.alloteCard());
			g.drawImage(wait[0], 300, 175, this);
			Dealer.drawCards(this, g, cardImages, gameTable.getClosedCardDeck());
			chanceFinished = false;
			moveCardOn=false;
		}

	}

	private void setBackGroundImage() {
		if (showCardTray)
			backGroundImage = getImage(getDocumentBase(),
					"table/backgroundWithTray.png");
		else
			backGroundImage = getImage(getDocumentBase(),
					"table/background.png");
	}

	public void update(Graphics g) {
		paint(g);
	}

	private void setCardImage() {
		cardBack = getImage(getDocumentBase(), "card/0.png");
		for (int i = 0; i < 52; i++) {
			cardImages[i] = getImage(getDocumentBase(), "card/"
					+ cardDeck.getCard(i).getCardNumber() + ".png");
		}
		trayOn = getImage(getDocumentBase(), "buttons/" + "on.png");
		trayOff = getImage(getDocumentBase(), "buttons/" + "off.png");
	}
    
	private void setWaitImage() {
		for (int i = 0; i < 5; i++) {
			wait[i] = getImage(getDocumentBase(), "wait/wait"
					+ i + ".png");
		}
	}

	public void mouseDragged(MouseEvent e) {
		System.out.println("------mouseDragged-----");
		currentPositionX = e.getX();
		currentPositionY = e.getY();
		if (clickInsidePlayerDeck(pressedPositionX, pressedPositionY, playerId1)) {
			repaint();
		}
		System.out.println("draggedPositionX= " + currentPositionX);
	}

	public void mouseMoved(MouseEvent e) {
		currentPositionX = e.getX();
		currentPositionY = e.getY();
		showStatus("Mouse at (" + currentPositionX + "," + currentPositionY
				+ ")");
		// repaint();
		e.consume();
	}

	public void mouseClicked(MouseEvent e) {
		if (clickInsideFoldDeck(e)) {
			System.out.println("clickedInsideFoldDeck");
			gameTable.getUserGameProfile(playerId1).setUserCard(
					cardDeck.alloteCard());
			repaint();
		}
		if (clickInsidePlayerDeck(e, playerId1) || clickInsideTray(e)) {
			System.out.println("clickedInsidePlayerDeck");
			int cardPosition = (mx - 250) / 20;
			if (cardPosition > 7)
				cardPosition = 7;
			boolean result=gameTable.getUserGameProfile(playerId1).removeUserCard(
					cardPosition, gameTable, false);
			if(result){
			chanceFinished = true;
			}
			repaint();
		}
		if (clickClosedDeck(e)) {
			System.out.println("clickedClosedDeck");
			gameTable.pickCardFromClosedDeck(playerId1);
			repaint();
		}
		if (clickTrayOn(e)) {
			System.out.println("clickTrayOn");
			showCardTray = true;
			playerFinishCard = gameTable.getUserGameProfile(playerId1)
					.getUserCards();
			for (int i = 0; i < playerFinishCard.length; i++) {
				System.out.println("playerFinishCard " + i + " ="
						+ playerFinishCard[i]);
			}
			repaint();
		}
		if (clickOnFinish(e)) {
			System.out.println("clickOnFinish");
			showCardTray = false;
			if (Dealer.isCardsInSequence(leftTrayCards, false)
					&& Dealer.isSameFace(leftTrayCards)) {
				matchFinished = true;
			} else {
				gameTable.getUserGameProfile(playerId1).resetCardsShowStatus();
			}

			repaint();
		}

	}

	public void mouseReleased(MouseEvent e) {
		releasedPositionX = e.getX();
		releasedPositionY = e.getY();
		isMouseClicked = false;
		if (clickInsidePlayerDeck(pressedPositionX, pressedPositionY, playerId1)
				&& clickInsidePlayerDeck(releasedPositionX, releasedPositionY,
						playerId1)) {
			if (pressedPositionX == releasedPositionX
					&& pressedPositionY == releasedPositionY) {
				return;
			}
			int cardPositionMousePressed = (pressedPositionX - 250) / 20;
			if (cardPositionMousePressed > 7)
				cardPositionMousePressed = 7;
			int cardPositionMouseReleased = (releasedPositionX - 250) / 20;
			if (cardPositionMouseReleased > 7)
				cardPositionMouseReleased = 7;
			System.out.println("------start-----");
			System.out.println("cardPositionMousePressed="
					+ cardPositionMousePressed);
			System.out.println("cardPositionMouseReleased="
					+ cardPositionMouseReleased);
			System.out.println("------end-----");
			gameTable.getUserGameProfile(playerId1).reArrengeCards(
					cardPositionMousePressed, cardPositionMouseReleased);
			repaint();
		}
		if (showCardTray
				&& clickInsidePlayerDeck(pressedPositionX, pressedPositionY,
						playerId1)
				&& clickInsideLeftTray(releasedPositionX, releasedPositionY)) {
			int cardPositionMousePressed = (pressedPositionX - 250) / 20;
			if (cardPositionMousePressed > 6)
				cardPositionMousePressed = 6;
			for (int i = 0; i < leftTrayCards.length; i++) {
				if (leftTrayCards[i] == null) {
					leftTrayCards[i] = playerFinishCard[cardPositionMousePressed];
					System.out.println("clickInsideLeftTray added A card "
							+ playerFinishCard[cardPositionMousePressed]);
					gameTable.getUserGameProfile(playerId1).removeUserCard(
							cardPositionMousePressed, gameTable, true);
					break;
				}
			}
			for (int i = cardPositionMousePressed; i < playerFinishCard.length - 1; i++) {
				playerFinishCard[i] = playerFinishCard[i + 1];
			}
			playerFinishCard[playerFinishCard.length - 1] = null;
		}
		if (showCardTray
				&& clickInsidePlayerDeck(pressedPositionX, pressedPositionY,
						playerId1)
				&& clickInsideRightTray(releasedPositionX, releasedPositionY)) {
			int cardPositionMousePressed = (pressedPositionX - 250) / 20;
			if (cardPositionMousePressed > 6)
				cardPositionMousePressed = 6;
			for (int i = 0; i < rightTrayCards.length; i++) {
				if (rightTrayCards[i] == null) {
					rightTrayCards[i] = playerFinishCard[cardPositionMousePressed];
					System.out.println("clickInsideRightTray added A card ");
					gameTable.getUserGameProfile(playerId1).removeUserCard(
							cardPositionMousePressed, gameTable, true);
					break;
				}
			}
			for (int i = cardPositionMousePressed; i < playerFinishCard.length - 1; i++) {
				playerFinishCard[i] = playerFinishCard[i + 1];
			}
			playerFinishCard[playerFinishCard.length - 1] = null;

		}
		
	}

	private boolean clickInsideFoldDeck(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		if (mx > 150 && mx < 198 && my > 150 && my < 190)
			return true;
		else
			return false;
	}

	private boolean clickClosedDeck(MouseEvent e) {
		if (!showCardTray)
			return false;
		mx = e.getX();
		my = e.getY();
		if (mx > 450 && mx < 500 && my > 150 && my < 218)
			return true;
		else
			return false;
	}

	private boolean clickTrayOn(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		if (mx > 600 && mx < 615 && my > 75 && my < 85)
			return true;
		else
			return false;
	}

	private boolean clickOnFinish(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		if (mx > 600 && mx < 615 && my > 300 && my < 310)
			return true;
		else
			return false;
	}

	private boolean clickInsidePlayerDeck(MouseEvent e, int playerId) {
		mx = e.getX();
		my = e.getY();
		if (mx > 250 && mx < 440 && my > 250 && my < 318)
			return true;
		else
			return false;
	}

	private boolean clickInsidePlayerDeck(int xCoordinate, int yCoordinate,
			int playerId) {
		mx = xCoordinate;
		my = yCoordinate;
		if (mx > 250 && mx < 440 && my > 250 && my < 318)
			return true;
		else
			return false;
	}

	private boolean clickInsideLeftTray(int xCoordinate, int yCoordinate) {
		mx = xCoordinate;
		my = yCoordinate;
		if (mx > 470 && mx < 540 && my > 95 && my < 220)
			return true;
		else
			return false;
	}

	private boolean clickInsideRightTray(int xCoordinate, int yCoordinate) {
		mx = xCoordinate;
		my = yCoordinate;
		if (mx > 540 && mx < 613 && my > 95 && my < 220)
			return true;
		else
			return false;
	}

	private boolean clickInsideTray(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		if (mx > 470 && mx < 613 && my > 95 && my < 220)
			return true;
		else
			return false;
	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent e) {
		isMouseClicked = true;
		pressedPositionX = e.getX();
		pressedPositionY = e.getY();
		System.out.println("mousePressed  X=" + pressedPositionX + " Y="
				+ pressedPositionY);
	}

	public void keyPressed(KeyEvent arg0) {

	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

}

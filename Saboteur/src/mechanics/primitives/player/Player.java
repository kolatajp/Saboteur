package mechanics.primitives.player;

import java.util.ArrayList;

import mechanics.primitives.cards.Card;

public class Player {
	boolean hasAxe, hasCart, hasLamp;
	boolean isSaboteur;
	int cardsNumber = 0;
	ArrayList<Card> hand;

	public Player( boolean isSaboteur ) {
		hasAxe = true;
		hasCart = true;
		hasLamp = true;
		this.isSaboteur = isSaboteur;

		this.hand = new ArrayList<>();
	}

	public void addCard( Card c ) {
		this.hand.add( c );
		this.cardsNumber++;
	}

	public Card removeCard( int index ) {
		this.cardsNumber--;
		return this.hand.remove( index );
	}

	public void incrementCardsNumber() {
		this.cardsNumber++;
	}

	public void decrementCardsNumber() {
		this.cardsNumber--;
	}

	public int getCardsNumber() {
		return cardsNumber;
	}
}

package mechanics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import mechanics.primitives.board.Table;
import mechanics.primitives.cards.Card;
import mechanics.primitives.cards.SpecialCard;
import mechanics.primitives.cards.TunnelCard;
import mechanics.primitives.player.Player;

public class Mechanics {
	Table table;
	ArrayList<Card> stack;
	Player[] players;

	private void initStack() {
		// special cards
		for ( int i = 0; i < 3; i++ ) {
			this.stack.add( new SpecialCard( Card.SpecialType.BREAK_AXE ) );
			this.stack.add( new SpecialCard( Card.SpecialType.REPAIR_AXE ) );

			this.stack.add( new SpecialCard( Card.SpecialType.BREAK_CART ) );
			this.stack.add( new SpecialCard( Card.SpecialType.REPAIR_CART ) );

			this.stack.add( new SpecialCard( Card.SpecialType.BREAK_LAMP ) );
			this.stack.add( new SpecialCard( Card.SpecialType.REPAIR_LAMP ) );
		}

		for ( int i = 0; i < 5; i++ ) {
			this.stack.add( new SpecialCard( Card.SpecialType.COLLAPSE ) );
		}

		// creating tunnels
		for ( int i = 0; i < 5; i++ ) {
			this.stack.add(
					new TunnelCard( Card.TunnelType.TUNNEL, false, true, false, true, true ) );
			this.stack.add(
					new TunnelCard( Card.TunnelType.TUNNEL, true, true, false, false, true ) );
			this.stack.add(
					new TunnelCard( Card.TunnelType.TUNNEL, true, false, false, true, true ) );
			this.stack.add(
					new TunnelCard( Card.TunnelType.TUNNEL, false, true, true, false, true ) );
			this.stack.add(
					new TunnelCard( Card.TunnelType.TUNNEL, false, false, true, true, true ) );
			this.stack
					.add( new TunnelCard( Card.TunnelType.TUNNEL, true, false, true, true, true ) );
			this.stack
					.add( new TunnelCard( Card.TunnelType.TUNNEL, true, true, true, false, true ) );
			this.stack.add(
					new TunnelCard( Card.TunnelType.TUNNEL, true, false, true, false, true ) );
			this.stack
					.add( new TunnelCard( Card.TunnelType.TUNNEL, true, true, true, true, true ) );
			// dead end
			this.stack
					.add( new TunnelCard( Card.TunnelType.TUNNEL, true, true, true, true, false ) );
		}

		// shuffle
		this.shuffleStack();
	}

	private void shuffleStack() {
		ArrayList<Card> newStack = new ArrayList<>();
		while ( !this.stack.isEmpty() ) {
			newStack.add( this.stack.remove( new Random().nextInt( this.stack.size() ) ) );
		}
		this.stack = newStack;
	}

	public Card popCard() {
		return this.stack.remove( this.stack.size() - 1 );
	}

	public Mechanics( int numberOfPlayers ) {
		this.players = new Player[numberOfPlayers];
		this.table = new Table();

		// inserting start, 1xgold, 2xcoal and shuffling
		ArrayList<TunnelCard> goldAndCoal = new ArrayList<>();
		goldAndCoal.add( new TunnelCard( Card.TunnelType.COAL_COVERED ) );
		goldAndCoal.add( new TunnelCard( Card.TunnelType.COAL_COVERED ) );
		goldAndCoal.add( new TunnelCard( Card.TunnelType.GOLD_COVERED ) );
		ArrayList<TunnelCard> newGoldAndCoal = new ArrayList<>();
		while ( !goldAndCoal.isEmpty() ) {
			newGoldAndCoal.add( goldAndCoal.remove( new Random().nextInt( goldAndCoal.size() ) ) );
		}
		goldAndCoal = newGoldAndCoal;

		this.table.board[13][3] = goldAndCoal.get( 0 );
		this.table.board[13][5] = goldAndCoal.get( 1 );
		this.table.board[13][7] = goldAndCoal.get( 2 );

		this.table.board[5][5] = new TunnelCard( Card.TunnelType.START );

		// players and saboteurs
		ArrayList<Player> playersAndSaboteurs = new ArrayList<>();
		if ( numberOfPlayers < 5 ) {
			playersAndSaboteurs.add( new Player( true ) );
			for ( int i = 0; i < numberOfPlayers - 1; i++ ) {
				playersAndSaboteurs.add( new Player( false ) );
			}
		} else {
			playersAndSaboteurs.add( new Player( true ) );
			playersAndSaboteurs.add( new Player( true ) );
			for ( int i = 0; i < numberOfPlayers - 2; i++ ) {
				playersAndSaboteurs.add( new Player( false ) );
			}
		}

		// radom players get saboteurs
		for ( int i = 0; !playersAndSaboteurs.isEmpty(); i++ ) {
			this.players[i] = playersAndSaboteurs
					.remove( new Random().nextInt( playersAndSaboteurs.size() ) );
		}
	}

	private Map<String, TunnelCard> getNeighbours( int x, int y ) {
		// I assume that x and y are correct coordinates
		Map<String, TunnelCard> neighbours = new HashMap<>();
		// left
		if ( x - 1 >= 0 ) {
			neighbours.put( "left", this.table.board[x - 1][y] );
		} else {
			neighbours.put( "left", null );
		}
		// up
		if ( y - 1 >= 0 ) {
			neighbours.put( "up", this.table.board[x][y - 1] );
		} else {
			neighbours.put( "up", null );
		}
		// right
		if ( x + 1 < this.table.board.length ) {
			neighbours.put( "right", this.table.board[x + 1][y] );
		} else {
			neighbours.put( "right", null );
		}
		// down
		if ( y + 1 < this.table.board[0].length ) {
			neighbours.put( "down", this.table.board[x][y + 1] );
		} else {
			neighbours.put( "down", null );
		}

		return neighbours;
	}

	private boolean canCardBePlaced( TunnelCard c, int x, int y ) {
		// if some card already exists in specified place
		if ( this.table.board[x][y] != null ) {
			return false;
		}
		Map<String, TunnelCard> neighbours = this.getNeighbours( x, y );
		// if no neighbours then false
		if ( neighbours.size() == 0 ) {
			return false;
		} else {
			TunnelCard left = neighbours.get( "left" );
			TunnelCard up = neighbours.get( "up" );
			TunnelCard right = neighbours.get( "right" );
			TunnelCard down = neighbours.get( "down" );

			if ( left != null && left.isFacingRight() != c.isFacingLeft() ) {
				return false;
			}
			if ( up != null && up.isFacingDown() != c.isFacingUp() ) {
				return false;
			}
			if ( right != null && right.isFacingLeft() != c.isFacingRight() ) {
				return false;
			}
			if ( down != null && down.isFacingUp() != c.isFacingDown() ) {
				return false;
			}
			return true;
		}
	}

	public boolean placeCard( TunnelCard c, int x, int y ) {
		if ( canCardBePlaced( c, x, y ) ) {
			this.table.board[x][y] = c;
			return true;
		} else {
			return false;
		}
	}
}

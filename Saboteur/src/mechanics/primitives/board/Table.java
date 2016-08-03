package mechanics.primitives.board;

import mechanics.primitives.cards.TunnelCard;

public class Table {
	public TunnelCard[][] board;	//[x][y]
	
	public Table() {
		this.board = new TunnelCard[20][11];
	}
}

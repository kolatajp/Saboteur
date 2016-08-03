package mechanics.primitives.cards;

public class TunnelCard extends Card {
	boolean facingUp, facingDown, facingLeft, facingRight;
	boolean accessible;
	Card.TunnelType type;

	public TunnelCard( Card.TunnelType type, boolean facingUp, boolean facingRight,
			boolean facingDown, boolean facingLeft, boolean accessible ) {
		this.type = type;
		this.facingDown = facingDown;
		this.facingUp = facingUp;
		this.facingLeft = facingLeft;
		this.facingRight = facingRight;
		this.accessible = accessible;
	}
	
	public TunnelCard( Card.TunnelType type ) {
		// only gold or coal
		this.type = type;
	}
	
	public boolean isAccessible() {
		return accessible;
	}
	
	public boolean isFacingDown() {
		return facingDown;
	}
	
	public boolean isFacingLeft() {
		return facingLeft;
	}
	
	public boolean isFacingRight() {
		return facingRight;
	}
	
	public boolean isFacingUp() {
		return facingUp;
	}
}

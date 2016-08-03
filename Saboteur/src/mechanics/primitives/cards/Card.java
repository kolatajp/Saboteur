package mechanics.primitives.cards;

public class Card {
	public enum TunnelType {
		TUNNEL, START, GOLD_COVERED, COAL_COVERED, GOLD_REVEALED, COAL_REVEALED;
	}

	public enum SpecialType {
		BREAK_LAMP, REPAIR_LAMP, BREAK_CART, REPAIR_CART, BREAK_AXE, REPAIR_AXE, COLLAPSE;
	}
}

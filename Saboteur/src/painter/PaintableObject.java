package painter;

import java.awt.image.BufferedImage;

public class PaintableObject {
	private BufferedImage image;
	private int x = 0, y = 0;
	private String id;

	// constructors
	public PaintableObject( BufferedImage image ) {
		this.image = image;
	}

	public PaintableObject( BufferedImage image, int x, int y ) {
		this.image = image;
		this.x = x;
		this.y = y;
	}

	public PaintableObject( BufferedImage image, String id, int x, int y ) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.id = id;
	}

	// custom methods
	public boolean inBounds( int x, int y ) {
		if ( x >= this.x && x < ( this.x + this.getWidth() ) && y >= this.y
				&& y < ( this.y + this.getHeight() ) ) {
			return true;
		} else {
			return false;
		}
	}

	// gets, sets
	public void setX( int x ) {
		this.x = x;
	}

	public void setY( int y ) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return this.image.getWidth();
	}

	public int getHeight() {
		return this.image.getHeight();
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage( BufferedImage image ) {
		this.image = image;
	}
}

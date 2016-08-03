package painter;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class PainterPanel extends JPanel {
	// positions
	private int xmargin = 0;
	private int ymargin = 0;

	private int lastXdrag = 0;
	private int lastYdrag = 0;

	// layers, objects
	ArrayList<ArrayList<PaintableObject>> layers;
	Map<String, PaintableObject> objectMap;

	// methods
	// constructors
	public PainterPanel() {
		super();

		this.layers = new ArrayList<>();
		this.objectMap = new HashMap<>();
	}

	// custom methods
	@Override
	public void paintComponent( Graphics g ) {
		super.paintComponent( g );

		for ( int i = 0; i < this.layers.size(); i++ ) {
			for ( PaintableObject po : layers.get( i ) ) {
				g.drawImage( po.getImage(), po.getX() + this.xmargin, po.getY() + this.ymargin,
						null );
			}
		}
	}

	public void initDrag( MouseEvent event ) {
		this.lastXdrag = event.getX();
		this.lastYdrag = event.getY();
	}

	public void drag( MouseEvent event ) {
		int newX = event.getX();
		int newY = event.getY();
		this.alterXmargin( newX - lastXdrag );
		this.alterYmargin( newY - lastYdrag );
		this.repaint();
		this.lastXdrag = newX;
		this.lastYdrag = newY;
	}

	public PaintableObject[] getObjectFromPos( int x, int y ) {
		ArrayList<PaintableObject> out = new ArrayList<>();
		for ( Map.Entry<String, PaintableObject> entry : this.objectMap.entrySet() ) {
			if ( entry.getValue().inBounds( x - this.getXmargin(), y - this.getYmargin() ) ) {
				out.add( entry.getValue() );
			}
		}

		return out.toArray( new PaintableObject[out.size()] );
	}

	public void addObjectToLayer( BufferedImage image, String id, int x, int y, int layerNum )
			throws IndexOutOfBoundsException {
		PaintableObject po = new PaintableObject( image, id, x, y );
		this.layers.get( layerNum ).add( po );
		this.objectMap.put( id, po );

		this.repaint();
	}

	public PaintableObject removeObject( String id ) {
		for ( ArrayList<PaintableObject> list : layers ) {
			for ( PaintableObject po : list ) {
				if ( po.getId().equals( id ) ) {
					list.remove( po );
				}
			}
		}

		this.repaint();
		return this.objectMap.remove( id );
	}

	public void addLayer() {
		this.layers.add( new ArrayList<PaintableObject>() );
	}

	// gets, sets
	public void setXmargin( int xmargin ) {
		this.xmargin = xmargin;
	}

	public void setYmargin( int ymargin ) {
		this.ymargin = ymargin;
	}

	public int getXmargin() {
		return xmargin;
	}

	public int getYmargin() {
		return ymargin;
	}

	public void alterXmargin( int alterxmargin ) {
		this.xmargin += alterxmargin;
	}

	public void alterYmargin( int alterymargin ) {
		this.ymargin += alterymargin;
	}
}

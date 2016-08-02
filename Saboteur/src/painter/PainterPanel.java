package painter;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class PainterPanel extends JPanel {
	private int xmargin = 0;
	private int ymargin = 0;

	private int lastXdrag = 0;
	private int lastYdrag = 0;

	public PainterPanel() {
		super();
	}

	@Override
	public void paintComponent( Graphics g ) {
		super.paintComponent( g );

		g.fillRect( 0 + xmargin, 0 + ymargin, 50, 50 );
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

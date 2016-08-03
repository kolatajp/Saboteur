package painter;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class PaintTest1 {

	private JFrame frame;

	// own
	int lastX = 0, newX = 0;
	int lastY = 0, newY = 0;
	int xdiff, ydiff;

	int iter = 0;

	boolean released = false;

	/**
	 * Launch the application.
	 */
	public static void main( String[] args ) {
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch ( Throwable e ) {
			e.printStackTrace();
		}
		EventQueue.invokeLater( new Runnable() {
			@Override
			public void run() {
				try {
					PaintTest1 window = new PaintTest1();
					window.frame.setVisible( true );
				} catch ( Exception e ) {
					e.printStackTrace();
				}
			}
		} );
	}

	/**
	 * Create the application.
	 */
	public PaintTest1() {
		initialize();
		// gui
		frame.setLocationRelativeTo( null );
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds( 100, 100, 450, 300 );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		frame.getContentPane().setLayout( null );

		JPanel panel = new PainterPanel();
		panel.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseReleased( MouseEvent arg0 ) {
				released = false;
			}

			@Override
			public void mousePressed( MouseEvent e ) {
				( (PainterPanel) panel ).initDrag( e );
				if ( e.getButton() == MouseEvent.BUTTON1 ) {
					try {
						( (PainterPanel) panel ).addObjectToLayer(
								ImageIO.read( getClass().getResource( "/gold_1.png" ) ),
								"id" + iter, e.getX(), e.getY(), 0 );
					} catch ( IOException ex ) {
						System.out.println( ex );
					}
				}
			}
		} );
		panel.addMouseMotionListener( new MouseMotionAdapter() {
			@Override
			public void mouseDragged( MouseEvent arg0 ) {
				( (PainterPanel) panel ).drag( arg0 );
			}

			@Override
			public void mouseMoved( MouseEvent arg0 ) {
				PaintableObject[] ob = ( (PainterPanel) panel ).getObjectFromPos( arg0.getX(),
						arg0.getY() );
				for ( PaintableObject paintableObject : ob ) {
					System.out.println( iter + ":" + paintableObject.getId() );
					iter++;
				}
			}
		} );
		panel.setBorder( new LineBorder( new Color( 0, 0, 0 ) ) );
		panel.setBounds( 10, 11, 422, 251 );
		frame.getContentPane().add( panel );
		panel.setLayout( null );

		// own
		( (PainterPanel) panel ).addLayer();
	}
}

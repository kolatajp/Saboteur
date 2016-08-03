package other;

public class getCardString {
	public static String fromImage( String imageName ) {
		if ( imageName.charAt( 0 ) == '/' ) {
			imageName = imageName.substring( 1 );
		}

		if ( imageName.equals( "tunnel_horizontal.png" ) ) {
			return "new TunnelCard( Card.TunnelType.TUNNEL, false, true, false, true, true )";
		} else if ( imageName.equals( "tunnel_l0.png" ) ) {
			return "new TunnelCard( Card.TunnelType.TUNNEL, true, true, false, false, true )";
		} else if ( imageName.equals( "tunnel_l1.png" ) ) {
			return "new TunnelCard( Card.TunnelType.TUNNEL, true, false, false, true, true )";
		} else if ( imageName.equals( "tunnel_l2.png" ) ) {
			return "new TunnelCard( Card.TunnelType.TUNNEL, false, true, true, false, true )";
		} else if ( imageName.equals( "tunnel_l3.png" ) ) {
			return "new TunnelCard( Card.TunnelType.TUNNEL, false, false, true, true, true )";
		} else if ( imageName.equals( "tunnel_t0.png" ) ) {
			return "new TunnelCard( Card.TunnelType.TUNNEL, true, false, true, true, true )";
		} else if ( imageName.equals( "tunnel_t1.png" ) ) {
			return "new TunnelCard( Card.TunnelType.TUNNEL, true, true, true, false, true )";
		} else if ( imageName.equals( "tunnel_vertical.png" ) ) {
			return "new TunnelCard( Card.TunnelType.TUNNEL, true, false, true, false, true )";
		} else if ( imageName.equals( "tunnel_x.png" ) ) {
			return "new TunnelCard( Card.TunnelType.TUNNEL, true, true, true, true, true )";
		} else if ( imageName.equals( "tunnel_x_dead.png" ) ) {
			return "new TunnelCard( Card.TunnelType.TUNNEL, true, true, true, true, false )";
		}
		
		return null;
	}
}

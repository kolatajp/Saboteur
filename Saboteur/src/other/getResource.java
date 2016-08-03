package other;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class getResource {
	String errLog = "";

	public BufferedImage fromPath( String path ) {
		try {
			return ImageIO.read( getClass().getResource( "/" + path ) );
		} catch ( IOException e ) {
			errLog += e + "\n";
		}

		return null;
	}
	
	public String getErrLog() {
		return errLog;
	}
}

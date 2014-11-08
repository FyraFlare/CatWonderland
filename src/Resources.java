
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/*
 * Loads and stores all images at the start of the 
 * program and lets you access them by name
 */
public class Resources {

	// files to load at startup
	private static final String[] imageNames = {
		"TestBG.png"
		};
	
	/*
	 * Singleton class (only one instance in program)
	 */
	private static Resources instance = null;
	public static synchronized Resources getInstance() {
		if (instance == null) {
			instance = new Resources();
		}
		return instance;
	}
	
	
	private HashMap<String, BufferedImage> images;
	
	private Resources(){
		images = new HashMap<String, BufferedImage>();
		
		for(int i = 0; i < imageNames.length; i++){
			loadImage(imageNames[i]);
		}
	}

	public BufferedImage getImage(String filename){
		BufferedImage image = images.get(filename);
		if(image == null){
			System.out.println("Error getting image: " + filename);
			System.exit(1);
		}
		return image;
	}

	private void loadImage(String filename) {
		try {
			BufferedImage img = ImageIO.read(new File("images/" + filename));
//			BufferedImage img = ImageIO.read(ImageLibrary.class.getResource("/images/" + filename));
			images.put(filename, img);
		} catch (IOException e) {
			System.out.println("Error loading image: " + filename);
		}
	}
}

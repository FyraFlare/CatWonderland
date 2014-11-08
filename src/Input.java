
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

	static Input instance = null;

	public static Input getInstance(){
		if(instance == null){
			instance = new Input();
		}
		return instance;
	}
	
	
	boolean jumpButton;
	boolean batButton;
	
	public Input(){
		jumpButton = false;
		batButton = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_UP:
	        	jumpButton = true;
	        	break;
	        case KeyEvent.VK_SPACE:
	        	batButton = true;
	        	break;
	        case KeyEvent.VK_ESCAPE:
	        	System.exit(0);
	        	break;
	     }		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_UP:
	        	jumpButton = false;
	        	break;
	        case KeyEvent.VK_SPACE:
	        	batButton = false;
	        	break;
	     }	
	}
	
	
	
}

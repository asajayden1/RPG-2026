import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame{

	
	public Main () {
		super("Ms. Woolweaver's Awesome Project");
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

		Game play = new Game();
		((Component) play).setFocusable(true);
		
		Color LightBlue = new Color(173, 216, 230);
		
		
		setBackground(LightBlue);
		
		
		getContentPane().add(play);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	

	public static void main(String[] args) {
		Main run = new Main();
		

	}


}

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.event.*;
import java.util.Queue;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {

	private BufferedImage back;
	private ImageIcon background;
	private ArrayList<Characters> displayChars;
	private String level;
	private Characters mainChar;
	private ArrayList<Weapon> displayWeapons;
	private Queue<Enemy> enemies;
	private long lastShot;
	private long lastEnemyHit;
	private int maxHealth;


	public Game() {

		//This is the music
		Player p = new Player();
		p.playmusic("track02.wav");
		enemies = setEs();
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		displayChars = setupChars();
		level = "start";
		//only if you want to have one selected init
		mainChar = displayChars.get(0);
		displayWeapons = new ArrayList<Weapon>();
		background = new ImageIcon("background.jpg");
		//start the thread last so everything above is ready
		new Thread(this).start();

	}

	private Queue<Enemy> setEs() {
		Queue<Enemy> temp = new LinkedList<Enemy>();
		temp.add(new Brute(1000, 300));
		temp.add(new Brute(1100, 300));
		temp.add(new Brute(1200, 300));
		temp.add(new Brute(1300, 300));
		return temp;
	}

	public ArrayList<Characters> setupChars() {
		ArrayList<Characters> temp = new ArrayList<Characters>();
		temp.add(new Mage());
		temp.add(new Warrior());
		temp.add(new Archer());
		temp.add(new Monk(50, 300));

		temp.get(0).setX(144);
		temp.get(1).setX(588);
		temp.get(2).setX(1032);
		temp.get(3).setX(1476);
		return temp;

	}

	public void run() {
		try {
			while (true) {
				Thread.currentThread().sleep(10);
				repaint();
			}
		} catch (Exception e) {
		}
	}


	public void paint(Graphics g) {

		Graphics2D twoDgraph = (Graphics2D) g;



		if (back == null)
			back = (BufferedImage) (createImage(getWidth(), getHeight()));


		Graphics g2d = back.createGraphics();

		

		g2d.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		//start coding here

		switch (level) {
			case "start":
				drawStart(g2d);
				break;
			case "game":
				drawGame(g2d);
				break;
		}

		//do not delete this line of code, it draws everything above
		twoDgraph.drawImage(back, null, 0, 0);

	}

	private void drawGame(Graphics g2d) {
		long now = System.currentTimeMillis();
		Enemy e = enemies.peek();

		// enemy walks toward the player, then attacks once a second
		if (e != null && mainChar.getHealth() > 0) {
			if (e.getX() > mainChar.getX() + mainChar.getW() + 20) {
				e.setX(e.getX() - e.getSpeed());
			} else if (now - lastEnemyHit > 1000) {
				int dmg = e.getAttack() - mainChar.getDefense();
				if (dmg < 1) {
					dmg = 1;
				}
				mainChar.setHealth(mainChar.getHealth() - dmg);
				lastEnemyHit = now;
			}
		}

		// move the fired weapons and check if they hit the enemy
		for (int i = displayWeapons.size() - 1; i >= 0; i--) {
			Weapon w = displayWeapons.get(i);
			w.move();
			if (e != null) {
				Rectangle weaponBox = new Rectangle(w.getX(), w.getY(), w.getW(), w.getH());
				Rectangle enemyBox = new Rectangle(e.getX(), e.getY(), e.getW(), e.getH());
				if (weaponBox.intersects(enemyBox)) {
					hitEnemy(e, w);
					displayWeapons.remove(i);
					continue;
				}
			}
			if (w.getX() > getWidth()) {
				displayWeapons.remove(i);
			}
		}

		// if the enemy is dead take it out of the queue
		if (e != null && e.getHealth() <= 0) {
			enemies.remove();
			e = enemies.peek();
		}

		// drawing
		mainChar.drawCharAndWeap(g2d);

		// change health text to red when health is 50%
		if (mainChar.getHealth() < maxHealth * 0.5) {
    	g2d.setColor(Color.RED);
		} else {
    	g2d.setColor(Color.WHITE);
		}

		g2d.drawString("Health: " + Math.max(0, mainChar.getHealth()),
        mainChar.getX(), mainChar.getY() - 10);

		g2d.setColor(Color.WHITE);

		if (e != null) {
			e.drawCharAndWeap(g2d);
			g2d.setColor(Color.WHITE);
			g2d.drawString("Health: " + e.getHealth(), e.getX(), e.getY() - 10);
		}

		for (Weapon w : displayWeapons) {
			w.draw(g2d);
		}

		g2d.drawString("Enemies left: " + enemies.size(), 50, 50);

		if (enemies.isEmpty()) {
			g2d.drawString("You Win!", 400, 400);
		}
		if (mainChar.getHealth() <= 0) {
			g2d.drawString("You Lose!", 400, 400);
		}
	}

	// damage = weapon damage + your attack - enemy defense (at least 1)
	private void hitEnemy(Enemy e, Weapon w) {
		int dmg = w.getDamage() + mainChar.getAttack() - e.getDefense();
		if (dmg < 1) {
			dmg = 1;
		}
		e.setHealth(e.getHealth() - dmg);
	}

	private void drawStart(Graphics g2d) {
		g2d.setColor(Color.WHITE);
		g2d.drawString("Click a character to start", 600, 100);
		for (Characters c : displayChars) {
			c.drawChar(g2d);
			g2d.setColor(Color.WHITE);
			g2d.drawString(c.getName(), c.getX(), c.getY() + c.getH() + 20);
			g2d.drawString(c.getStats(), c.getX(), c.getY() + c.getH() + 40);
		}
	}


	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (level.equals("start")) {
			// find which character was clicked
			for (Characters c : displayChars) {
				if (e.getX() > c.getX() && e.getX() < c.getX() + c.getW() && e.getY() > c.getY() && e.getY() < c.getY() + c.getH()) {
					mainChar = c;
					maxHealth = mainChar.getHealth();
					mainChar.setX(100);
					mainChar.setY(300);
					enemies = setEs();
					level = "game";
					break;
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE && level.equals("game")) {
			long now = System.currentTimeMillis();
			Weapon w = mainChar.getWeapon();

			// attackSpeed
			if (now - lastShot < 200 / w.getAttackSpeed()) {
				return;
			}
			lastShot = now;

			if (w instanceof Ranged) {
				// fire a new weapon from the player
				displayWeapons.add(new Weapon(w.getDamage(), w.getAttackSpeed(), w.getPic(), mainChar.getX() + mainChar.getW(), mainChar.getY() + mainChar.getH() / 2, 120, 120));
			} else {
				//melee check for collision
				Enemy en = enemies.peek();
				if (en != null && en.getX() - (mainChar.getX() + mainChar.getW()) < 100) {
					hitEnemy(en, w);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
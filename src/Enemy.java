import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Enemy extends Characters {

    public Enemy(int x, int y, String name, ImageIcon pic, int range, int attack, int health, int defense, int speed, Weapon weapon) {
        super(x, y, name, pic, range, attack, health, defense, speed, weapon);
    }

    // enemies face left so the weapon goes on their left side
    public void drawCharAndWeap(Graphics g2d) {
        drawChar(g2d);
        getWeapon().setX(getX() - 25);
        getWeapon().setY(getY() + getH() / 2);
        getWeapon().draw(g2d);
    }
}
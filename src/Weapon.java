import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Weapon {

    private int damage, x, y, w, h;
    private int attackSpeed;
    private ImageIcon pic;

    public Weapon() {
        damage = 5;
        attackSpeed = 1;
        pic = new ImageIcon("pic.jpg");
        x = 0;
        y = 0;
        w = 50;
        h = 50;
    }

    public Weapon(int damage, int attackSpeed, ImageIcon pic, int x, int y, int w, int h) {
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.pic = pic;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    // moves a fired weapon to the right
    public void move() {
        x += 6;
    }

    public void draw(Graphics g) {
        g.drawImage(pic.getImage(), x, y, w, h, null);
    }

    public String toString() {
        return "Damage: " + damage + " Attack Speed: " + attackSpeed;
    }

    public int getDamage() {
        return damage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public ImageIcon getPic() {
        return pic;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
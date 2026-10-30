import javax.swing.ImageIcon;

public class Ranged extends Weapon {

    public Ranged() {
        super();
    }

    public Ranged(int damage, int attackSpeed, ImageIcon pic, int x, int y, int w, int h) {
        super(damage, attackSpeed, pic, x, y, w, h);
    }

    public String toString() {
        return "Ranged Weapon: " + super.toString();
    }
}
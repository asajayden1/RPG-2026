import javax.swing.ImageIcon;

public class Melee extends Weapon {

    public Melee() {
        super();
    }

    public Melee(int damage, int attackSpeed, ImageIcon pic, int x, int y, int w, int h) {
        super(damage, attackSpeed, pic, x, y, w, h);
    }

    public String toString() {
        return "Melee Weapon: " + super.toString();
    }
}
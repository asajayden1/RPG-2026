import javax.swing.ImageIcon;

public class Mage extends Characters {

    public Mage() {
        super("Mage", new ImageIcon("mage.png"), 8, 15, 30, 1, 4, new Ranged());
    }

    public Mage(int x, int y) {
        super(x, y, "Mage", new ImageIcon("mage.png"), 8, 15, 30, 1, 4, new Ranged());
        //two ways for x/y values
    }

    public String toString() {
        return "Mage";
    }

}
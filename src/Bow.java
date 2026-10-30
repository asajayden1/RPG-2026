import javax.swing.ImageIcon;

public class Bow extends Ranged {

    public Bow() {
        super(8, 3, new ImageIcon("bow.png"), 0, 0, 120, 120);
    }

    public Bow(int x, int y) {
        super(8, 3, new ImageIcon("bow.png"), x, y, 120, 120);
    }

    public String toString() {
        return "Bow";
    }
}
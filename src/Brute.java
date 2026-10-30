import javax.swing.ImageIcon;

public class Brute extends Enemy {
    public Brute(int x, int y) {
        super(x, y, "Brute", new ImageIcon("brute.png"), 5, 10, 50, 2, 3, new Weapon());
    }
}
import javax.swing.ImageIcon;

public class Assassin extends Characters {

    public Assassin() {
        super("Assassin", new ImageIcon("assassin.png"), 5, 10, 50, 2, 3, new Melee());
    }

    public Assassin(int x, int y) {
        super(x, y, "Assassin", new ImageIcon("assassin.png"), 5, 10, 50, 2, 3, new Melee());
        //two ways for x/y values
    }

    public String toString() {
        return "Assassin";
    }

}
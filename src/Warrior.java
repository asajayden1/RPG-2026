import javax.swing.ImageIcon;

public class Warrior extends Characters {

    public Warrior() {
        super("Warrior", new ImageIcon("warrior.png"), 1, 12, 60, 6, 2, new Melee());
    }

    public Warrior(int x, int y) {
        super(x, y, "Warrior", new ImageIcon("warrior.png"), 1, 12, 60, 6, 2, new Melee());
        //two ways for x/y values
    }

    public String toString() {
        return "Warrior";
    }

}
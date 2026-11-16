import javax.swing.ImageIcon;

public class Archer extends Characters {

    public Archer() {
        super("Archer", new ImageIcon("archer.png"), 10, 8, 35, 2, 50, new Bow());
    }

    public Archer(int x, int y) {
        super(x, y, "Archer", new ImageIcon("archer.png"), 10, 8, 35, 2, 50, new Bow(x+50,y));
        //two ways for x/y values
    }

    public String toString() {
        return "Archer + "+super.getSpeed();
    }

}
import javax.swing.ImageIcon;

public class Monk extends Characters {

    public Monk() {
        super("Monk", new ImageIcon("monk.png"), 1, 10, 45, 3, 8, new Melee());
    }

    public Monk(int x, int y) {
        super(x, y, "Monk", new ImageIcon("monk.png"), 1, 10, 45, 3, 8, new Melee());
        for (int i = 0; i < 3; i++) {
            super.setArrayList(new Bow(x + 50, y));
            super.setArrayList(new Weapon());
        }
        //two ways for x/y values
    }

    public String toString() {
        return "Monk + " + super.getSpeed();
    }

    public void setWeaps(Weapon w) {
        super.setArrayList(w);
    }
}
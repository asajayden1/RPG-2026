import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Characters {

    private int x, y, w, h;
    private ImageIcon pic;
    private int range, attack, health, defense, speed;
    private Weapon weapon;
    private String name;
    private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    //can also do an array list of weapons

    public Characters() {
        x = 0;
        y = 0;
        w = 0;
        h = 0;
        pic = new ImageIcon("pic.jpg");
        range = 10;
        attack = 12;
        health = 20;
        defense = 75;
        speed = 100;
        weapon = new Weapon();
    }

    public Characters(String name, ImageIcon pic, int range, int attack, int health, int defense, int speed, Weapon weapon) {
        this.name = name;
        this.pic = pic;
        this.range = range;
        this.attack = attack;
        this.health = health;
        this.defense = defense;
        this.speed = speed;
        this.weapon = weapon;
        x = 0;
        y = 300;
        w = 300;
        h = 300;
    }

    public Characters(int x, int y, String name, ImageIcon pic, int range, int attack, int health, int defense, int speed, Weapon weapon) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.pic = pic;
        this.range = range;
        this.attack = attack;
        this.health = health;
        this.defense = defense;
        this.speed = speed;
        this.weapon = weapon;
        w = 300;
        h = 300;
    }

    // draws just the character
    public void drawChar(Graphics g2d) {
        g2d.drawImage(pic.getImage(), x, y, w, h, null);

        g2d.setColor(Color.RED);
        g2d.fillRect(x, y-50, health *5, 10);
    
    }

    // draws the character and puts the weapon in front of them
    public void drawCharAndWeap(Graphics g2d) {

        drawChar(g2d);
        weapon.setX(x + w - 25);
        weapon.setY(y + h / 2);
        weapon.draw(g2d);

    }

    public void setArrayList(Weapon w) {
        weapons.add(w);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStats() {
        return "Attack:" + attack + " Defense:" + defense + " Speed:" + speed;
    }

    public Weapon getWeapon() {
        return weapon;
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

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public ImageIcon getPic() {
        return pic;
    }

    public void setPic(ImageIcon pic) {
        this.pic = pic;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}








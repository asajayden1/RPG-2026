
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Mage extends Characters {

    private BufferedImage[][] frames = new BufferedImage[4][4];
    private int currentFrame = 0;
    private long lastFrameTime = 0;
    private boolean walking = false;
    private boolean attacking = false;

    public Mage() {
        super("Mage", new ImageIcon("mage.png"), 8, 15, 30, 1, 4, new Ranged());
        loadSprites();
    }

    public Mage(int x, int y) {
        super(x, y, "Mage", new ImageIcon("mage.png"), 8, 15, 30, 1, 4, new Ranged());
        loadSprites();
    }

    private void loadSprites() {
        try {
            BufferedImage sheet = ImageIO.read(new File("mage_spritesheet.png"));

            int frameWidth = sheet.getWidth() / 4;
            int frameHeight = sheet.getHeight() / 4;

            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    frames[row][col] = sheet.getSubimage(
                        col * frameWidth,
                        row * frameHeight,
                        frameWidth,
                        frameHeight
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setWalking(boolean walking) {
        if (!attacking && this.walking != walking) {
            this.walking = walking;
            currentFrame = 0;
            lastFrameTime = System.currentTimeMillis();
        }
    }

    public void attack() {
        attacking = true;
        walking = false;
        currentFrame = 0;
        lastFrameTime = System.currentTimeMillis();
    }

    @Override
    public void drawChar(Graphics g2d) {
        if (frames[0][0] == null) {
            super.drawChar(g2d);
            return;
        }

        long now = System.currentTimeMillis();

        if (attacking) {
            if (now - lastFrameTime >= 120) {
                if (currentFrame < 3) {
                    currentFrame++;
                } else {
                    attacking = false;
                    currentFrame = 0;
                }
                lastFrameTime = now;
            }

            if (attacking) {
                g2d.drawImage(frames[3][currentFrame],
                    getX(), getY(), getW(), getH(), null);
            } else {
                g2d.drawImage(frames[0][0],
                    getX(), getY(), getW(), getH(), null);
            }

        } else if (walking) {
            if (now - lastFrameTime >= 120) {
                currentFrame = (currentFrame + 1) % 4;
                lastFrameTime = now;
            }

            g2d.drawImage(frames[2][currentFrame],
                getX(), getY(), getW(), getH(), null);

        } else {
            g2d.drawImage(frames[0][0],
                getX(), getY(), getW(), getH(), null);
        }

        g2d.setColor(Color.RED);
        g2d.fillRect(getX(), getY() - 50, getHealth() * 5, 10);
    }

    public String toString() {
        return "Mage";
    }
}

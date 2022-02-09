package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ObiektGlowny {
    static
    {

        ImageIcon ii = new ImageIcon("wiewzorzeszkiem2bezorzeszka.png");
        mainimg= ii.getImage();
    }
        public static Image mainimg;
        private int taken;
        private int lost;
        private int level=1;
        public int x;
        public int y;
        public int w;
        public int h;
        public int dx;
        public int dy;
        ObiektGlowny(int x, int y,int h,int w)
        {
            this.x=x;
            this.y=y;
            this.w=w;
            this.h=h;
            this.taken = 0;
            this.lost = 0;
        }
        public void move() {

            x += dx;
            y += dy;
        }
        public void addLevel()
        {
            this.level++;
        }
        public void addTaken()
        {
            this.taken++;
        }
        public void addLost()
        {
            this.lost++;
        }
        public int getLost()
        {
            return this.lost;
        }
        public int getTaken()
        {
            return this.taken;
        }
        public int getLevel()
        {
            return this.level;
        }
        public void setTaken(int x)
        {
            this.taken=x;
        }
        public void setLost(int x)
        {
            this.lost=x;
        }
        public void setLevel(int x)
        {
            this.level = x;
        }
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {

                    dx = -10;

            }

            if (key == KeyEvent.VK_RIGHT) {

                    dx = 10;

            }

            if (key == KeyEvent.VK_UP) {
                dy = 0;
            }

            if (key == KeyEvent.VK_DOWN) {
                dy = 0;
            }
        }

        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                dx = 0;
            }

            if (key == KeyEvent.VK_RIGHT) {
                dx = 0;
            }

            if (key == KeyEvent.VK_UP) {
                dy = 0;
            }

            if (key == KeyEvent.VK_DOWN) {
                dy = 0;
            }
        }
    public Rectangle getBounds() {
        return new Rectangle(x+35, y+20, w, h);
    }
    public boolean checkPosition()
    {
        if(this.x<=30&&this.x>=1570)
        return false;
        else
            return true;
    }
}

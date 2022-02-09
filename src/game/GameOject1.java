package game;

import java.awt.event.KeyEvent;

public class GameOject1 {
    public int x;
    public int y;
    public int w;
    public int h;
    public int dx;
    public int dy;
    GameOject1(int x, int y,int h,int w)
    {
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    }
    public void move() {

        x += dx;
        y += dy;
    }
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
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



}

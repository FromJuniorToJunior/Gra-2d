package game;

import javax.swing.*;
import java.awt.*;

public class Orzeszek {
    static
    {

        ImageIcon ii = new ImageIcon("orzeszekmini.png");
        orzeszek= ii.getImage();
    }

    public int x;
    public int y;
    public int w;
    public int h;
    public static Image orzeszek;
    public boolean visable = true;
    Orzeszek(int x, int y,int h,int w)
    {
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }
    public void setVisable(boolean visable)
    {
        this.visable=visable;
    }
    public boolean checkVisaility()
    {
        return this.visable;
    }
}

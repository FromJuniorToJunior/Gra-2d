package game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;

public class Board extends JPanel implements ActionListener, KeyListener, Levels {
    public boolean gameOver = false;
    private Image backGround;
    public ObiektGlowny glowny;
    //public Orzeszek pierwszy;
    public ArrayList<Orzeszek> listaOrzecha;
    Random rand;
    Timer timer;


    public Board() {

        initBoard();
        setFocusable(true);
        addKeyListener(this);


       glowny=new ObiektGlowny(400,730,30,30);
       listaOrzecha= new ArrayList<>();
       rand = new Random();

        timer = new Timer(10,this);
        timer.start();







    }

    private void initBoard() {

        loadImage();

        int w = backGround.getWidth(this);
        int h =  backGround.getHeight(this);
        setPreferredSize(new Dimension(w, h));
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon("bez.png");
        backGround = ii.getImage();
    }
    public void addOrzeszek()
    {
        listaOrzecha.add(new Orzeszek(0+ rand.nextInt(1500),0-rand.nextInt(800),30,30));
    }
    public void addOrzeszek2()
    {
        listaOrzecha.add(new Orzeszek(400,500,30,30));
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(backGround, 0, 0, null);
        paintone2(g,this.glowny);
        for(Orzeszek orzech : listaOrzecha)
        {
            if(orzech.visable)
            g.drawImage(Orzeszek.orzeszek, orzech.x, orzech.y, null);
        }
        paintScore(g);



        if(glowny.getLost()>3)
            paintGameOver(g);
        if(czyZaliczony()&&glowny.getLevel()!=4&&glowny.getLevel()!=5) {
            paintLevelPasse(g);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }

    public void paintone2(Graphics g, ObiektGlowny sec)
    {

        g.drawImage(ObiektGlowny.mainimg, glowny.x, glowny.y-100, null);
       // g.fillRect(sec.x,sec.y,sec.w,sec.h);
    }
    public void paintScore(Graphics g)
    {
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
     g.drawString(glowny.getTaken()+"-"+glowny.getLost(),10,45);
    }
    public void paintGameOver(Graphics g)
    {
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 150));
        g.drawString("GAME OVER",250,400);
    }

    public void paintLevelPasse(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 150));
        g.drawString("Level Passed",250,400);
    }
    public void paintgaz(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 150));
        g.drawString("Gz",250,400);
    }
    public void checkGameStatus()
    {
        if(glowny.getLost()>3)
        {
            gameOver=true;
         timer.stop();
        }
    }
    public void checkVis()
    {
        for(Iterator<Orzeszek> orzech = listaOrzecha.iterator(); orzech.hasNext();)
        {
            Orzeszek next =orzech.next();

            if(!next.checkVisaility())
            {
                orzech.remove();
            }
        }
    }
    public void checkPosition()
    {
        for(Orzeszek orzech: listaOrzecha)
        {
            if(orzech.y>800)
            {
                orzech.setVisable(false);
                glowny.addLost();
            }
        }
    }
    public void gameReset()
    {
        glowny.setLost(0);
        glowny.setTaken(0);
        gameOver=false;
        timer.start();
    }
    public void gameResetComplete()
    {
        listaOrzecha.clear();
        glowny.setLost(0);
        glowny.setTaken(0);
        gameOver=false;
        glowny.setLevel(1);
        timer.start();
    }

    @Override
    public void level(int speed,int objectsInSameTime) {
        for (Orzeszek orzech : listaOrzecha) {
            orzech.y += speed;
        }
        checkCollisions();
        if (listaOrzecha.size() < objectsInSameTime)
            addOrzeszek();
        else
            checkVis();
        checkPosition();
        repaint();
    }
    public boolean czyZaliczony()
    {
        if(glowny.getTaken()==2) {
            listaOrzecha.clear();
            return true;
        }
        else
            return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkGameStatus();
        if(!gameOver) {
            step();

            if (glowny.x < -50) {
                glowny.x = 1580;
            } else if (glowny.x > 1590) {
                glowny.x = -50;
            }
            if(glowny.getLevel()==1) {
                if (!czyZaliczony())
                    level(glowny.getLevel(), 1);
                else {
                    timer.stop();
                    glowny.addLevel();
                    gameReset();
                }
            }else
                if (glowny.getLevel()==2)
                {

                    if (!czyZaliczony())
                        level(glowny.getLevel(), 1);
                    else {
                        timer.stop();
                        glowny.addLevel();
                        gameReset();
                    }
                }
                else
                if (glowny.getLevel()==3)
                {

                    if (!czyZaliczony())
                        level(glowny.getLevel(), 2);
                    else {
                        timer.stop();
                        glowny.addLevel();
                        gameReset();
                    }
                }
                else
                if (glowny.getLevel()==4)
                {

                    if (!czyZaliczony())
                        level(glowny.getLevel(), 1);
                    else {
                        timer.stop();
                        glowny.addLevel();
                        repaint();
                        glowny.setLevel(1);
                        //gameReset();

                    }
                }


        }

    }

    private void step()
    {
        glowny.move();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        glowny.keyPressed(e);
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_SPACE)
        {
            timer.stop();
        }
        if(key==KeyEvent.VK_ENTER)
        {
            timer.start();
        }
        if(key== KeyEvent.VK_R&&gameOver==true)
        {
            gameResetComplete();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        glowny.keyReleased(e);
        int key = e.getKeyCode();

    }
    public void checkCollisions() {

        Rectangle r3 = glowny.getBounds();

        for (Orzeszek orzech : listaOrzecha) {

            Rectangle r2 = orzech.getBounds();

            if (r3.intersects(r2)) {

                orzech.setVisable(false);
                glowny.addTaken();
            }
        }
    }

}

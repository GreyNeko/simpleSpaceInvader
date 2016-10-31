/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvaderGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author HentaiNeko
 */
public class Menu{
    private final InvadersDemo applet;
    private BufferedImageLoader imageLoader;
    public SpriteSheet sSheet;
    private BufferedImage SSheet;
    private TankEntity tank;
    private TimerTask menuTimer;
    private Timer timer;
    public int locatX, locatY, button;
    public String gameOver = "";
    private final int WIDTH = 150, HEIGHT = 50;
    public Rectangle menuStartBounds, menuQuitBounds;
//    public ArrayList<image> tankLives;
    
    public Menu(InvadersDemo d){
        applet = d;
        
        menuScreen();
    }
    
    public int getX(){
        return locatX;
    }
    public int getY(){
        return locatY;
    }
    
    public void move(int x, int y){
        locatX = x;
        locatY = y;
    }
    
    public void move(int x, int y, int b){
        locatX = x;
        locatY = y;
        button = b;
    }
    
    public void menuScreen(){
        timer = new Timer();
        
        menuStartBounds = new Rectangle(125, 500, WIDTH, HEIGHT);
        menuQuitBounds = new Rectangle(325, 500, WIDTH, HEIGHT);
        imageObjectLoader();
        
        timer.scheduleAtFixedRate(menuTimer = new TimerTask(){
            @Override
            public void run(){
                if(menuStartBounds.contains(locatX, locatY)){
                    if(button == 1){
                        applet.alienInvasion = new InvasionEntity(applet);
                        applet.tank = new TankEntity(applet);
                        timer.cancel();
                        applet.StartScreen = false;
                    }
                    applet.repaint();
                }
                else if(menuQuitBounds.contains(locatX, locatY)){
                    if(button == 1){
                        timer.cancel();
                        System.exit(0);
                    }
                    applet.repaint();
                }
                else{
                    applet.repaint();
                }
                applet.repaint();
            }
        }, 0, 10);

        
    }
    
    public void menuRender(Graphics g){        
        if(menuStartBounds.contains(locatX, locatY)){
            g.setColor(Color.black);
            g.fillRect(125, 500, 150, 50);
        }
        else if(menuQuitBounds.contains(locatX, locatY)){
            g.setColor(Color.black);
            g.fillRect(325, 500, 150, 50);
        }
        
        g.drawImage(sSheet.getSprite(1, 1, 22, 22), 150, 150, 300, 300, applet);
        
        g.setColor(Color.WHITE);
        g.drawString(gameOver, 240, 445);
        
        g.drawString("START", 168, 533);
        g.drawRect(125, 500, 150, 50);
        g.drawString("QUIT", 375, 533);
        g.drawRect(325, 500, 150, 50);
        
        
        g.drawString("00", 525, 50);
        g.drawString("00", 290, 50);
        g.drawString("LIVES: ", 30, 50);
        
        for(int i = 0; i < 3; i++){
            g.drawImage(sSheet.getSprite(9, 1, 22, 22), (50*i)+125, 28, 30, 30, applet);
        }
    }
    
    public void imageObjectLoader(){
        imageLoader = new BufferedImageLoader();
//        BufferedImage SSheet = null;
        try{
            SSheet = imageLoader.ImageLoader("/res/InvadersSS.png");
        }
        catch(IOException e){
            System.out.println(e);
        }
        sSheet = new SpriteSheet(SSheet);
    }
}

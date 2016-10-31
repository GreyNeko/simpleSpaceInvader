/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvaderGame;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author HentaiNeko
 */
public class MissileEntity{
    private InvadersDemo applet;
    private InvasionEntity alienInvasion;
    private int gameAreaHeight, gameAreaWidth;
    public static final int WIDTH = 3;
    public static final int HEIGHT = 15;
    private int topBorder = 0, leftBorder = 0;
    private int locatX, locatY;
    public TimerTask tankMissile;
    public TimerTask alienMissle;
    public Timer timer;
    public Timer tankTimer;
    public Timer alienTimer;
    private int directionX = 1, directionY = 12;
    
    public  MissileEntity(InvadersDemo d, int LocatX, int LocatY, int directY){
        applet = d;
        locatX = LocatX;
        locatY = LocatY;
        directionY = directY;
        gameAreaHeight = d.getHeight()-HEIGHT;
    }
    public int getX(){
        return locatX;
    }
    public int getY(){
        return locatY;
    }
    
    public void tankLaunchSequence(){
        tankTimer = new Timer();
        
        tankTimer.schedule(tankMissile = new TimerTask(){
            @Override
            public void run(){
                tankLaunch();
                applet.repaint();
            }  
        }, 0, 10);
    }
    
    public void alienLaunchSequence(){
        alienTimer = new Timer();
        alienTimer.schedule(alienMissle = new TimerTask(){
            @Override
            public void run(){
                alienLaunch();
                applet.repaint();
            }
        }, 0, 10);
    }
    
    public void tankLaunch(){
        locatY = locatY - directionY;
        
        if(locatY <= topBorder){
            tankTimer.cancel();
            applet.tankMissleExsists = false;
        }
    }
    
    public void alienLaunch(){
        locatY = locatY + directionY;
    }
    
    public Rectangle missleBounds(){
        return (new Rectangle(getX(), getY(), WIDTH, HEIGHT));
    }
}

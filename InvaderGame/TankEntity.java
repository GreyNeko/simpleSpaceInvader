/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvaderGame;

import java.awt.Rectangle;

/**
 *
 * @author HentaiNeko
 */
public class TankEntity {
    private final InvadersDemo applet;
    private int gameAreaWidth;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 10;
    private final int tankObj = WIDTH/2;
    private final int leftBorder = 0;
    private int locatX, locatY;
    public int tankLives = 3;
    
    public TankEntity(InvadersDemo d){
        applet = d;
        gameAreaWidth = d.getWidth();
        locatX = gameAreaWidth/2;
        locatY = d.getHeight()-WIDTH;
    }
    public int getX(){
        return locatX;
    }
    public int getY(){
        return locatY;
    }
    public void resetLocation(){
        gameAreaWidth = applet.getWidth();
        locatY = applet.getHeight()-WIDTH;
    }
//    public void moveLeft(){
//        if(locatX > leftBorder){
//            locatX -= tankObj;
//        }
//    }
//    public void moveRight(){
//        if(locatX + WIDTH < gameAreaWidth){
//            locatX += tankObj;
//        }
//    }
    public void move(int x){
        locatX = x;
    }
    
    public Rectangle tankBounds(){
        return (new Rectangle(getX(), getY(), WIDTH, HEIGHT));
    }
}

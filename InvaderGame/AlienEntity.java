/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvaderGame;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author HentaiNeko
 */
public class AlienEntity {
    public SpriteAnimator sAnimator;
    private InvadersDemo applet;
    public MissileEntity missle;
    public static final int AESqr = 30;
    public int gameAreaHeight, gameAreaWidth;
    public int topBorder = 0, leftBorder = 0;
    public int locatX, locatY;
    private Random random;
    private final int min = 1, max = 100;
    public final int directionX = 1, directionY = 1;
    public int FSPEED = 500;
    private final int FRAMEDIM = 22;
    private BufferedImageLoader imageLoader;
    private SpriteSheet ss;
    private ArrayList<BufferedImage> sFrame01;
    private ArrayList<BufferedImage> sFrame02;
    private ArrayList<BufferedImage> sFrame03;
    
    public AlienEntity(InvadersDemo d, int alienLocatX, int alienLocatY){
        applet = d;
        locatX = alienLocatX;
        locatY = alienLocatY;
    }
    
    public int getX(){
        return locatX;
    }
    
    public int getY(){
        return locatY;
    }
    
    public void alienObjectLoad(int i){
        imageLoader = new BufferedImageLoader();
        BufferedImage SSheet = null;
        try{
            SSheet = imageLoader.ImageLoader("/res/InvadersSS.png");
        }
        catch(IOException e){
            System.out.println(e);
        }
        ss = new SpriteSheet(SSheet);
        
        sFrame01 = new ArrayList();
        sFrame02 = new ArrayList();
        sFrame03 = new ArrayList();
        
        if(i == 1){
            sFrame01.add(ss.getSprite(5, 1, FRAMEDIM, FRAMEDIM));
            sFrame01.add(ss.getSprite(6, 1, FRAMEDIM, FRAMEDIM));

            sAnimator = new SpriteAnimator(sFrame01);
        }
        if(i == 2 || i == 3){
            sFrame02.add(ss.getSprite(1, 1, FRAMEDIM, FRAMEDIM));
            sFrame02.add(ss.getSprite(2, 1, FRAMEDIM, FRAMEDIM));

            sAnimator = new SpriteAnimator(sFrame02);
        }
        if(i == 4 || i == 5){
            sFrame03.add(ss.getSprite(3, 1, FRAMEDIM, FRAMEDIM));
            sFrame03.add(ss.getSprite(4, 1, FRAMEDIM, FRAMEDIM));

            sAnimator = new SpriteAnimator(sFrame03);
        }
        
        sAnimator.setSpeed(FSPEED);
        sAnimator.start();
    }
    
    public Rectangle AlienBounds(){
        return (new Rectangle(getX(), getY(), AESqr, AESqr));
    }
}

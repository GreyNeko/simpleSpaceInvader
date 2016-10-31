/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvaderGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JApplet;

/**
 *
 * @author HentaiNeko
 */
public class InvadersDemo extends JApplet implements KeyListener, MouseListener, MouseMotionListener {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    private Image dbgImage;
    private Graphics dbg;
    public Menu menu;
    public TankEntity tank;
    public MissileEntity missle;
    public Boolean tankMissleExsists = false;
    public Boolean StartScreen = true;
    public AlienEntity alienEntity;
    public InvasionEntity alienInvasion;
    public SpriteAnimator spriteAnimator;
    
    @Override
    public void init() {
        setBackground(Color.DARK_GRAY);
        this.setSize(600, 800);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        
        menu = new Menu(this);
    }
    
    public void paint(Graphics g){
        dbgImage = createImage(getWidth(), getHeight());
        dbg = dbgImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbgImage, 0, 0, this);
    }
    
    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("OCR A Std", Font.PLAIN, 18));
        
        if(StartScreen.equals(false)){
            g.fillRect(tank.getX(), tank.getY(), TankEntity.WIDTH, TankEntity.HEIGHT);
            
            g.drawString(alienInvasion.getPoints(), 525, 50);
            g.drawString(alienInvasion.getTime(), 290, 50);
            g.drawString("LIVES: ", 30, 50);
            
            for(int i = 0; i < tank.tankLives; i++){
                g.drawImage(menu.sSheet.getSprite(9, 1, 22, 22), (50*i)+125, 28, 30, 30, this);
            }

            if(tankMissleExsists.equals(true)){
                g.fillRect(missle.getX(), missle.getY(), MissileEntity.WIDTH, MissileEntity.HEIGHT);
            }

            for(int i = 0; i < alienInvasion.alienMissileArray.size(); i++){
                g.fillRect(alienInvasion.alienMissileArray.get(i).getX(), alienInvasion.alienMissileArray.get(i).getY(), MissileEntity.WIDTH, MissileEntity.HEIGHT);
            }

            for(int i = 0; i < alienInvasion.AlienTest.size(); i++){
                alienInvasion.AlienTest.get(i).sAnimator.update(System.currentTimeMillis());
                g.drawImage(alienInvasion.AlienTest.get(i).sAnimator.sprite, alienInvasion.AlienTest.get(i).getX(), alienInvasion.AlienTest.get(i).getY(), AlienEntity.AESqr, AlienEntity.AESqr, null);
            }

            tank.resetLocation();
            repaint();
        }
        else{
            menu.menuRender(g);
        }
    }
    
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){
        if(tankMissleExsists.equals(false) && StartScreen.equals(false)){
            missle = new MissileEntity(this, (tank.getX()+(TankEntity.WIDTH/2)), tank.getY(), 12);
            missle.tankLaunchSequence();
            tankMissleExsists = true;
        }
    }
    public void mouseClicked(MouseEvent e){
        if(StartScreen.equals(true)){
            menu.move(e.getX(), e.getY(), e.getButton());
        }
    }
    
    public void mouseMoved(MouseEvent e){
        if(StartScreen.equals(true)){
            menu.move(e.getX(), e.getY());
        }
        menu.move(e.getX(), e.getY());
        
        if(StartScreen.equals(false)){
            tank.move(e.getX());
        }
    }
    public void mouseDragged(MouseEvent e){
        if(StartScreen.equals(false)){
            tank.move(e.getX());
        }
        
        if(tankMissleExsists.equals(false) && StartScreen.equals(false)){
            missle = new MissileEntity(this, (tank.getX()+(TankEntity.WIDTH/2)), tank.getY(), 12);
            missle.tankLaunchSequence();
            tankMissleExsists = true;
        }
    }
    
    public void keyReleased(KeyEvent e){}
    public void keyPressed(KeyEvent e){
    int typedKey = e.getKeyCode();
        System.out.println(e.getKeyCode() + " " + typedKey);
        if(typedKey == KeyEvent.VK_SPACE && StartScreen.equals(true)){
            StartScreen = false;
        }
    }
    public void keyTyped(KeyEvent e){}
    
    

    // TODO overwrite start(), stop() and destroy() methods
}

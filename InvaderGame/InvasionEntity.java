/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvaderGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author HentaiNeko
 */
public class InvasionEntity{
    
    
    private InvadersDemo applet;
    private Menu menu;
    private AlienEntity alienEntity;
    private MissileEntity missile;
    private TankEntity tank;
    private TimerTask invasionTimedMovement;
    private TimerTask alienCollisionDetect;
    private TimerTask tankCollisionDetect;
    public Timer timer;
    private int gameAreaMinHeight = 0, gameAreaMaxHeight;
    private int  gameAreaMinWidth = 60, gameAreaMaxWidth;
    private int locatX, locatY;
    private final int min = 1, max = 100;
    private int directionX = 15, directionY = 30;
    private final int SPEED = 15;
    private int secTimer = 1000;
    public int secCounter;
    private int moveTimer = 1000;
    private int tenthSecCounter = 0;
    public int pCounterInt;
    private Boolean intersectPath = false;
    public ArrayList<AlienEntity> AlienTest = new ArrayList();
    public ArrayList<MissileEntity> alienMissileArray = new ArrayList();
    
    
    public InvasionEntity(InvadersDemo d){
        applet = d;
        gameAreaMaxHeight = applet.getHeight()-30;
//        System.out.println("gameAreaMaxHeight: "+gameAreaMaxHeight);
        gameAreaMaxWidth = applet.getWidth()-60;
        
        timer = new Timer();
        
        assembleAlienMinions();
        
        invasionTimedMovement();
        alienCollisionDetect();
        tankCollisionDetect();
    }
    
    public String getPoints(){
        return (Integer.toString(pCounterInt));
    }
    public String getTime(){
        return (Integer.toString(secCounter));
    }
    
    public void assembleAlienMinions(){
        for(int i = 1; i<=5; i++){
            for(int j = 0; j <= 5; j++){
                alienEntity = new AlienEntity(applet, (60*j)+130, (60*i)+50);
                
                
                alienEntity.alienObjectLoad(i);
                AlienTest.add(alienEntity);
            }
        }
        
        Collections.sort(AlienTest, new Comparator<AlienEntity>(){
            @Override
            public int compare(AlienEntity aE01, AlienEntity aE02){
                String locX01 = Integer.toString(aE01.getX());
                String locX02 = Integer.toString(aE02.getX());
                return locX01.compareTo(locX02);
            }
        });
        
    }
    
    public void alienRandShot(int x, int y){
        Random random = new Random();

        int randomNum = random.nextInt((max-min)+1) + min;

        if(randomNum == 50){
            alienMissileArray.add(missile = new MissileEntity(applet, x, y, 6));
            missile.alienLaunchSequence();
        }
    }
    
    public void invaisonMovement(){
        for(int i = 0; i < AlienTest.size(); i++){
            AlienTest.get(i).locatX += directionX;
            
            alienRandShot(AlienTest.get(i).getX(), AlienTest.get(i).getY());

            if(AlienTest.get(0).getX() < gameAreaMinWidth && directionX == -SPEED){
                directionX = SPEED;
                AlienTest.get(0).locatX += SPEED*2;
                if(moveTimer > 200){
                    moveTimer -= 100;
//                    System.out.println("LLIMIT reached - TimerAdjusted: " + moveTimer);
                }

                for(int j = 0; j<AlienTest.size();j++){
                    AlienTest.get(j).locatY += directionY;
                    AlienTest.get(j).locatX -= SPEED;
                }
            }
            if(AlienTest.get(AlienTest.size()-1).getX() > gameAreaMaxWidth && directionX == SPEED){
                directionX = -SPEED;
                if(moveTimer > 200){
                    moveTimer -= 100;
//                    System.out.println("RRIGHT reached - TimerAdjusted: " + moveTimer);
                }

                for(int j = 0; j<AlienTest.size(); j++){
                    AlienTest.get(j).locatY += directionY;
                    AlienTest.get(j).locatX -= SPEED;
                }

            }
            
            if(AlienTest.get(i).getY() >= 740){
                intersectPath = true;
            }
        }
    }
    
    public void invasionTimedMovement(){
        timer.scheduleAtFixedRate(invasionTimedMovement = new TimerTask(){
            @Override
            public void run(){
                try{
                    if(tenthSecCounter%moveTimer == 0){
                        invaisonMovement();
                        applet.repaint();
                    }
                    else if(moveTimer <= 0 || AlienTest.isEmpty()){
//                        System.out.println("MoveTimer is at or passed 0 threshold.");
//                        System.out.println("AlienTest Array is empty");
//                        System.out.println("Game Over.");
                        timer.cancel();
                        applet.StartScreen = true;
                        applet.menu.gameOver = "Game Over";
                        applet.menu.button = 0;
                        applet.menu.menuScreen();
                    }
                    if(tenthSecCounter%secTimer == 0){
                        secCounter++;
//                        System.out.println(moveTimer);
                    }
                    tenthSecCounter += 10;
                    }
                catch(IndexOutOfBoundsException e){
                    System.out.println("Something went wrong - invasionTimedMovement -> ");
                    System.out.print(e.getMessage());
                }
            }
        }, 0, 10);
    }
    public void alienCollisionDetect(){
        timer.scheduleAtFixedRate(alienCollisionDetect = new TimerTask(){
            @Override
            public void run(){
                for(int i = 0; i < AlienTest.size(); i++){
                    try{
                        if((applet.tankMissleExsists.equals(true)) && AlienTest.get(i).AlienBounds().intersects(applet.missle.missleBounds())){
                            AlienTest.remove(i);
                            applet.missle.tankTimer.cancel();
                            applet.missle.tankTimer.purge();
                            applet.tankMissleExsists = false;
                            pCounterInt++;
                            if(moveTimer > 50){
                                moveTimer -= 30;
                            }
//                            System.out.println("CollisionDetection - Timer Read: " + moveTimer);
                        }
                        
                        if(intersectPath.equals(true) && AlienTest.get(i).AlienBounds().intersects(applet.tank.tankBounds())){
//                                System.out.println("AlienBounds intersected tankBounds");
//                                System.out.println("Game Over.");
                                timer.cancel();
                                applet.StartScreen = true;
                                applet.menu.gameOver = "Game Over";
                                applet.menu.button = 0;
                               applet.menu.menuScreen();
                        } 
                    }
                    catch(IndexOutOfBoundsException | NullPointerException  e){
                        System.out.println("Something went wrong - alienCollisionDetect -> ");
                        System.out.print(e.getMessage());
                    }                    
                }
            }
        }, 0, 10);
    }
    public void tankCollisionDetect(){
        timer.scheduleAtFixedRate(tankCollisionDetect = new TimerTask(){
            @Override
            public void run(){
                
                try{
                    for(int i = 0; i < alienMissileArray.size(); i++){
                        if(alienMissileArray.get(i).missleBounds().intersects(applet.tank.tankBounds())){
                            if(applet.tank.tankLives > 0){
                                applet.tank.tankLives -= 1;
                                alienMissileArray.get(i).alienTimer.cancel();
                                alienMissileArray.get(i).alienTimer.purge();
                                alienMissileArray.remove(i);

//                                System.out.println("Lives remaining: "+applet.tank.tankLives);
                            }
                            else{
                                alienMissileArray.get(i).alienTimer.cancel();
                                alienMissileArray.get(i).alienTimer.purge();
                                alienMissileArray.remove(i);
//                                System.out.println("alienMissileCollisionDetect");
//                                System.out.println("Game Over.");
                                timer.cancel();
                                applet.StartScreen = true;
                                applet.menu.gameOver = "Game Over";
                                applet.menu.button = 0;
                                applet.menu.menuScreen();
                            }
                        }
                        if(alienMissileArray.get(i).getY() > gameAreaMaxHeight){
//                            System.out.println("alienMissileArray missle met the gameAreaMaxHeight limiter");
                            alienMissileArray.get(i).alienTimer.cancel();
                            alienMissileArray.get(i).alienTimer.purge();
                            alienMissileArray.remove(i);
                        }
                    }
                }
                catch(IndexOutOfBoundsException | NullPointerException  e){
                    System.out.println("Something went wrong - tankCollisionDetect -> ");
                    System.out.print(e.getMessage());
                }
            }
        }, 0, 10);
    }
}

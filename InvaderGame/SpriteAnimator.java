/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvaderGame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author HentaiNeko
 */
public class SpriteAnimator {
    ArrayList<BufferedImage> frame;
    BufferedImage sprite;
    
    private volatile boolean running = false;
    
    private long previousTime, speed;
    private int frameAtPause, currentFrame;
    
    public SpriteAnimator(ArrayList<BufferedImage> Frames){
        frame = Frames;
    }
    
    public void setSpeed(long setSpeed){
        speed = setSpeed;
    }
    
    public void update(long updateTime){
        if(running){
            if(updateTime - previousTime >= speed){
                currentFrame++;
                try{
                    sprite = frame.get(currentFrame);
                }
                catch(IndexOutOfBoundsException e){
                    currentFrame = 0;
                    sprite = frame.get(currentFrame);
                }
                previousTime = updateTime;
            }
        }
    }
    public void start(){
        running = true;
        previousTime = 0;
        frameAtPause = 0;
        currentFrame = 0;
    }
    public void stop(){
        running = false;
        previousTime = 0;
        frameAtPause = 0;
        currentFrame = 0;
    }
    public void pause(){
        frameAtPause = currentFrame;
        running = false;
    }
    public void resume(){
        currentFrame = frameAtPause;
        running = true;
    }
}

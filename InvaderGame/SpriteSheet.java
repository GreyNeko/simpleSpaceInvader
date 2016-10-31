/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvaderGame;

import java.awt.image.BufferedImage;

/**
 *
 * @author HentaiNeko
 */
public class SpriteSheet {
    public BufferedImage SSImage;
    
    public SpriteSheet(BufferedImage SSheet){
        SSImage = SSheet;
    }
    
    public BufferedImage getSprite(int x, int y, int height, int width){
        BufferedImage sprite = SSImage.getSubimage((x-1)*22, (y-1)*22, height, width);
        return sprite;
    }
}

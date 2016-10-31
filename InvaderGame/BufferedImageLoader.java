/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvaderGame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author HentaiNeko
 */
public class BufferedImageLoader {
    private BufferedImage image;
    public BufferedImage ImageLoader(String path) throws IOException{
        image = ImageIO.read(getClass().getResourceAsStream(path));
        return image;
    }
}

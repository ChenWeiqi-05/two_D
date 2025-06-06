package object;

import main.GamePanel;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_Door extends SuperObject {

    GamePanel gp;
    public OBJ_Door(GamePanel gp){
        this.gp = gp;

        name = "Door";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            uTool.scaleImage( image,gp.tileSize,gp.tileSize);

        }catch (IOException e) {

            throw new RuntimeException(e);

        }

        collision = true;
    }
}

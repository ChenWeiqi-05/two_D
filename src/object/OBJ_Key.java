package object;

import Entity.Entity;
import main.GamePanel;


public class OBJ_Key extends Entity {


    public OBJ_Key(GamePanel gp) {
        super(gp);
        name = "Key";
        down1 =  setup("/objects/key",  gp.tileSize, gp.tileSize);
        description = "[" + name + "]\ni can open a door.";

    }
}

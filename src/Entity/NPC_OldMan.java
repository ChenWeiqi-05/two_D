package Entity;

import main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);//这个代码的意思是继承父类的属性
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
    }

    public void setDialogue() {
        dialogues[0] = "hello,lad.";
        dialogues[1] = "So you've come to this island to find your father?";
        dialogues[2] = "I used to be a great wizard but now.. \nI'm a bit to for taking an adventure.";
        dialogues[3] = "Well,good luck on you,hope you father have \nany danger.";

    }
    public void setAction() {

        actionLockCounter++;
        if (actionLockCounter == 120) { //npc的ai移动
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i < 25) {
                direction = "up";

            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i >= 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void speak() {
      super.speak();

    }
}

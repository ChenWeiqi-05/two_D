package Entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    // public int hasKey = 0;
    public boolean attackCanceled = false;
    int standTime = 0;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 36;
        attackArea.height = 36;

        setDaultValues();//设置默认值
        getPlayerImage();//获取玩家图片
        getPlayerAttackImage();//获取玩家攻击图片
        setItems();//设置物品
    }
    public void setItems() {
        inventory.add(currentWeapon);//添加物品
        inventory.add(currentShield);//添加物品
        inventory.add(new OBJ_Key(gp));//添加物品
        inventory.add(new OBJ_Key(gp));

    }
    public void setDaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;

//        worldX = gp.tileSize * 10;
//
//        worldY = gp.tileSize * 13;
        speed = 4;
        direction = "down";
// PLAYER STATUS
        maxLife = 6;
        life = maxLife;
        level = 1;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);

        attack = getAttack();
        defense = getDefense();
    }

    public int getAttack() {
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.attackValue;
    }

    public void getPlayerImage() {
        up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
    }

    /*  private BufferedImage loadImage(String path) throws IOException {
          InputStream is = getClass().getResourceAsStream(path);
          if (is == null) {
              throw new IOException("无法找到资源文件: " + path);
          }
          return ImageIO.read(is);
      }*/
    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);//这段代码是用来获取图片的，并且缩放到指定的大小。

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

        if (attacking == true) {
            attacking();
        } else if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
                System.out.println("向上");
                //  worldY -= speed; // 添加向上移动的逻辑
            } else if (keyH.downPressed == true) {
                direction = "down";
                System.out.println("向下");
                // worldY += speed; // 添加向下移动的逻辑
            } else if (keyH.leftPressed == true) {
                direction = "left";
                System.out.println("向左");
                //worldX -= speed; // 添加向左移动的逻辑
            } else if (keyH.rightPressed == true) {
                direction = "right";
                System.out.println("向右");
                //worldX += speed;
            }
            //这段代码用来检测obj是否发生碰撞
            collisionOn = false;
            gp.cChecker.checkTile(this);
            //这段代码用于检测obj是否发生碰撞
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            interactMonster(monsterIndex);
            contactMonster(monsterIndex);
            //  CHECK COLLISION
            gp.eHandler.checkEvent();
            if (collisionOn == false && keyH.enterPressed == false) {
                switch (direction) {
                    case "up":
                        //  System.out.println("向上碰撞");
                        worldY -= speed;
                        break;
                    case "down":
                        //System.out.println("向下碰撞");
                        worldY += speed;
                        break;
                    case "left":
                        //System.out.println("向左碰撞");
                        worldX -= speed;
                        break;
                    case "right":
                        // System.out.println("向右碰撞");
                        worldX += speed;
                        break;
                }
            }
            if (keyH.enterPressed == true && attackCanceled == false) {
                //这段代码的意思是，如果玩家按下enter键，并且没有取消攻击，那么就执行攻击逻辑。
                gp.playSE(7);

                attacking = true;
                spriteCounter = 0;
            }
            attackCanceled = false;
            gp.keyH.enterPressed = false;//
            //  gp.cChecker.checkTile(this);
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        //this needs to be outside of key if statement
        //这段代码的玩家攻击后恢复
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            //保持当前人物实体位置，以便等一下的
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //这段代码的作用是，将当前实体的位置和碰撞区域设置为
            // 攻击区域，然后根据攻击方向将实体的位置移动到攻击区域。
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }
            //从攻击区域中恢复实体的位置和碰撞区域。
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            //这段代码用来检测是否发生攻击

            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;

            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (gp.monster[i].invincible == false) {
                gp.playSE(5);

                int damage = attack - gp.monster[i].defense;//攻击力减去防御力
                if (damage < 0) {
                    damage = 0;
                }
                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + " damage!");
                gp.player.life += 2;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();//当玩家攻击史莱姆后，史莱姆会触发退后方法
                if (gp.monster[i].life <= 0) {//当史莱姆的血量小于等于0时，会触发死亡方法
                    gp.monster[i].dying = true;//死亡

                    gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
                    gp.ui.addMessage("Exp + " + gp.monster[i].exp + "!");
                    exp += gp.monster[i].exp;
                    CheckLevelUp();
                }
            }
        }
    }

    public void CheckLevelUp() {
        if (exp >= nextLevelExp) {//当经验值大于等于下一级经验值时，触发升级
            level++;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 2;

            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(8);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level " + level + "now!\n" + "You feel stronger!";

        }
    }

    public void contactMonster(int i) {//这段代码的意思是
        if (i != 999) {
            if (invincible == false) {
                gp.playSE(6);
                int damage = gp.monster[i].attack - defense;//攻击力减去防御力
                if (damage < 0) {
                    damage = 0;
                }
                life -= damage;
                invincible = true;//
            }
        }
    }

    public void interactMonster(int monsterIndex) {

    }

    public void pickUpObject(int i) {//这个方法可改变捡起道具后的数值属性
        if (i != 999) {
            //一下文本注释为寻宝小游戏的拾起逻辑的代码
            /*//999代表没有物体，并且0代表没有物体
         //   gp.obj[i].name = null;
            String objectName = gp.obj[i].name ;
            switch (objectName){
                case "Key"://注意此处单词首字母要大写！！！
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i]=null;
                    System.out.println("key：" +hasKey);
                    gp.ui.showMessage("You got "+hasKey+" key!"+"also have "+ (3 - hasKey)+ " key,should be found");
                    break;
                case "Door"://注意此处单词首字母要大写！！！
                    gp.playSE(3);
                    if (hasKey >0){
                        gp.obj[i] = null;
                        hasKey--;
                           gp.ui.showMessage("You open a door! ");

                    }else {
                        gp.ui.showMessage("You need a key!");
                    }
                    System.out.println("key：" +hasKey);
                    break;
                case "Boots":
                    gp.playSE(1);
                    speed +=3;
                    gp.obj[i] = null;
                    gp.ui.showMessage("speed up!GoGoGo！");
                    break;
                    case "Chest":
                        gp.ui.gameFinish = true;
                        gp.stopMusic();
                        gp.playSE(4);
                        break;
            }*/
        }
    }

    public void interactNPC(int i) {//这个方法可改变与npc的交互逻辑

        if (gp.keyH.enterPressed == true) {
            if (i != 999) {
                //这段代码enter  键被按下时，会触发npc的speak方法，并进入对话状态。
                System.out.println("you are hitting an npc");
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }/* else {
                gp.playSE(7);
                attacking = true;
            }*/
        }
    }

    /*    public void pickupObject(int i) {

             if (i != 999) {
             String objectName = gp.obj[i].name;
             switch (objectName) {
                 case "key":
                     hasKey++;
                     gp.obj[i] = null;
                     System.out.println("You have " + hasKey + " key(s)");
                     break;
                     case "Chest":
                         break;
                 case "Door":
                     if (hasKey > 0) {
                         gp.obj[i] = null;
                         hasKey--;
                         System.out.println("You have " + hasKey + " key(s)");

                     }
                     break;
             }
             }
         }*/
    public void draw(Graphics2D g2) {
//            g2.setColor(Color.white);
////
//  g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                }

                break;
            case "left":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if (attacking == true) {
                    tempScreenX = screenX - gp.tileSize;//这段代码的
                    // 意思是让玩家攻击时，图片向左移动一个tileSize。
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }
                }

                break;
            case "right":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if (attacking == true) {
                    //这段代码的意思是让玩家攻击时，
                    // 图片向右移动一个tileSize。
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }
                }
                break;
        }
        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        // g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        /*    g2.setFont(new Font("x12y16pxMaruMonica", Font.PLAIN, 26));
        g2.setColor(Color.white);
        g2.drawString("Invincible :"+invincibleCounter,10,400);
  */
    }


}
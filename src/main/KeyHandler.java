package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

     /*   int code = e.getKeyCode();
        GamePanel gp = new GamePanel();*/
 /*       if (gp.gameState == gp.titleState) {
            if (gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commamdNum--;
                    if (gp.ui.commamdNum < 0) {
                        gp.ui.commamdNum = 2;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commamdNum++;
                    if (gp.ui.commamdNum > 2) {
                        gp.ui.commamdNum = 0;
                    }

                }
            }

            if (code == KeyEvent.VK_ENTER) {

                if (gp.ui.commamdNum == 0) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if (gp.ui.commamdNum == 1) {
                    //  add later
                }
                if (gp.ui.commamdNum == 2) {
                    //  add later
                    System.exit(0);
                }
            }
        }
        else if (gp.ui.titleScreenState == 1) {
            if (gp.gameState == gp.playState) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commamdNum--;
                    if (gp.ui.commamdNum < 0) {
                        gp.ui.commamdNum = 2;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commamdNum++;
                    if (gp.ui.commamdNum > 2) {
                        gp.ui.commamdNum = 0;
                    }
                }

            if (code == KeyEvent.VK_ENTER) {

                if (gp.ui.commamdNum == 0) {
                    //  gp.gameState = gp.playState;
                    System.out.println("Do Some 法师 specific stuff");
                    gp.playMusic(0);
                }
                if (gp.ui.commamdNum == 1) {
                    System.out.println("Do Some 近卫 specific stuff");
                    gp.playMusic(0);
                    //  add later
                }
                if (gp.ui.commamdNum == 2) {
                    System.out.println("Do Some 医疗 specific stuff");
                    gp.playMusic(0);
                    //  add later
                    gp.playMusic(0);
                }
                if (gp.ui.commamdNum == 3) {
                    System.out.println("Do Some 特种 specific stuff");
                    //  add later
                    gp.playMusic(0);
                }
*/

 /*           }
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            }
        }
    }*/

//    @Override
//    public void keyReleased(KeyEvent e) {
//        int code = e.getKeyCode();
//
//        if (code == KeyEvent.VK_W) {
//            upPressed = false;
//        }
//        if (code == KeyEvent.VK_S) {
//            downPressed = false;
//        }
//        if (code == KeyEvent.VK_A) {
//            leftPressed = false;
//        }
//        if (code == KeyEvent.VK_D) {
//            rightPressed = false;
//        }
//
//    }
//}
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = true;

        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;

        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;

        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;

        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed =false;

        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;

        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;

        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;

        }
    }

}
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    boolean showDebugText = false;


    public KeyHandler(GamePanel gp) {

        this.gp = gp;
    }

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
        //*************TITLE STATE**************
        // 这段代码用来处理标题界面箭头的移动的

        if (gp.gameState == gp.titleState) {
            titleState(code);
        }
        //*************PLAYSTATE*******************
        else if (gp.gameState == gp.playState) {
            playState(code);
        }
        //PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        //DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }
//CHARACTER STATE
        else if (gp.gameState == gp.characterState) {
            characterState(code);
        }
    }

    public void titleState(int code) {
        if (gp.ui.titleScreenState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {

                    gp.ui.titleScreenState = 1;

                }
                if (gp.ui.commandNum == 1) {
                    //  add later 选择人物属性
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }

        } else if (gp.ui.titleScreenState == 1) {

            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;

                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    System.out.println("Do some fighter specific stuff");
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 1) {
                    System.out.println("Do some Thief specific stuff");
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 2) {
                    System.out.println("Do some Sorcerer specific stuff");
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 3) {
                    gp.ui.titleScreenState = 0;
                }
            }
        }

    }

    public void playState(int code) {
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
        if (code == KeyEvent.VK_P) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
                gp.stopMusic();  // 进入暂停状态时停止音乐
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
                gp.playMusic(0);  // 退出暂停状态时重新播放音乐
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_C) {//
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.characterState;
            } else if (gp.gameState == gp.characterState) {
                gp.gameState = gp.playState;
            }
        }
        if (code == KeyEvent.VK_T) {
            if (showDebugText == false) {
                showDebugText = true;
            } else if (showDebugText == true) {
                showDebugText = false;
            }
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
            gp.playMusic(gp.currentMusic);
        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }

    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_W) {
            if (gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.slotCol != 0) {
                gp.ui.slotCol--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.slotRow != 3) {
                gp.ui.slotRow++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.slotCol != 4) {
                gp.ui.slotCol++;
                gp.playSE(9);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
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
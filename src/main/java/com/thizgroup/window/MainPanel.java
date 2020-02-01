package com.thizgroup.window;

import com.thizgroup.block.Block;
import com.thizgroup.block.BlockType;
import com.thizgroup.block.FieldBlock;
import com.thizgroup.block.LeftBrokenLineBlock;
import com.thizgroup.block.LeftLBlock;
import com.thizgroup.block.MetaBlock;
import com.thizgroup.block.RightBrokenLineBlock;
import com.thizgroup.block.RightLBlock;
import com.thizgroup.block.TriangleBlock;
import com.thizgroup.block.VerticalLineBlock;
import com.thizgroup.utils.UIUtils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.springframework.util.ResourceUtils;

public class MainPanel extends JPanel {

  private MainWindow mainWindow;

  private BlockFactory blockFactory = new BlockFactory();

  private static final int PANEL_WIDTH = 180;
  private static final int PANEL_HEIGHT = 300;

  private boolean gameOver =false;
  private Image gameOverImage = null;

  private Rectangle panelRectangle = new Rectangle(0,0,PANEL_WIDTH,PANEL_HEIGHT);

  BlockMoveThread blockMoveThread = null;
  Boolean blockMoveThreadAlive = false;

  //定义数组存储所有的方块
  private MetaBlock[][] metaBlocks = new MetaBlock[15][9];
  private Block currentBlock = null;

  public MainPanel(MainWindow mainWindow){

    this.mainWindow = mainWindow;
    this.setBackground(Color.BLACK);
    this.setBounds(25,44,PANEL_WIDTH,PANEL_HEIGHT);
    //生成图片
    try {
      File cfgFile = ResourceUtils.getFile("classpath:images/gameover.png");
      //要想保存这个对象的话你要把image声明为BufferedImage 类型
      BufferedImage image = ImageIO.read(cfgFile);
      this.gameOverImage = image;
    } catch (IOException e) {
      e.printStackTrace();
    }

    //启动方块移动线程
    this.startBlockMoveThread();
  }

  public void startBlockMoveThread(){
    if(blockMoveThreadAlive) return ;
    gameOver = false;
    //启动线程
    if(this.blockMoveThread == null){
      this.blockMoveThread = new BlockMoveThread();
    }
    this.blockMoveThreadAlive = true;
    new Thread(this.blockMoveThread).start();
  }

  public void stopBlockMoveThread(){
    if(!this.blockMoveThreadAlive) return;
    System.out.println("游戏结束");
    gameOver = true;
    this.blockMoveThreadAlive = false;
    this.blockMoveThread = null;
  }

  public boolean isOverPanel(int x,int y){
    return x<0 || x>PANEL_WIDTH || y<0 || y>PANEL_HEIGHT;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    //画线
    //drawLine(g);
    //画方块
    drawBlocks(g);
    if(gameOver){
      //游戏结束
      g.drawImage(gameOverImage,40,100,100,80,null);
    }
  }

  private void drawBlocks(Graphics g) {

    //画当前方块
    if(currentBlock != null){
      currentBlock.paint(g);
    }

    for(int i=0;i< metaBlocks.length;i++){
      for(int j=0;j< metaBlocks[i].length;j++){
        MetaBlock metaBlock = metaBlocks[i][j];
        if(metaBlock != null){
          metaBlock.paint(g);
        }
      }
    }
  }

  /**
   * 画线
   * @param g
   */
  private void drawLine(Graphics g) {

    Color oloColor = g.getColor();

    int x = 0;
    int y = 0;

    g.setColor(Color.LIGHT_GRAY);
    int x11  = 0;
    int y11 = 0;
    int x12 = 0;
    int y12 = 300;

    for(int i=0;i<10;i++){
      g.drawLine(x11,y11,x12,y12);
      x11 += 20;
      x12 = x11;
    }

    int x21  = 0;
    int y21 = 0;
    int x22 = 180;
    int y22 = 0;

    for(int j=0;j<16;j++){
      g.drawLine(x21,y22,x22,y22);
      y21 += 20;
      y22 = y21;
    }

    g.setColor(oloColor);
  }

  public boolean canMoveLeft(MetaBlock metaBlock, int distance) {
    //越界检测
    if(!isRectangleInPanel(new Rectangle(metaBlock.getX()-distance, metaBlock.getY(),MetaBlock.BLOCK_WIDTH,MetaBlock.BLOCK_HEIGHT))) return false;

    //碰撞检测
    if(existsCollision(new MetaBlock(this.mainWindow, metaBlock.getX()-distance, metaBlock.getY()))) return false;

    return true;
  }

  public boolean isRectangleInPanel(Rectangle rectangle){
    return rectangle.getX()>=0 && (rectangle.getX()+rectangle.getWidth())<=PANEL_WIDTH
        && (rectangle.getY()+rectangle.getHeight())<=PANEL_HEIGHT;
  }

  public boolean canMoveRight(MetaBlock metaBlock, int distance) {
    //越界检测
    if(!isRectangleInPanel(new Rectangle(metaBlock.getX()+distance, metaBlock.getY(),MetaBlock.BLOCK_WIDTH,MetaBlock.BLOCK_HEIGHT))) return false;
    //碰撞检测
    if(existsCollision(new MetaBlock(this.mainWindow, metaBlock.getX()+distance, metaBlock.getY()))) return false;

    return true;
  }

  public boolean canMoveDown(MetaBlock metaBlock, int distance) {
    //越界检测
    if(!isRectangleInPanel(new Rectangle(metaBlock.getX(), metaBlock.getY()+distance,MetaBlock.BLOCK_WIDTH,MetaBlock.BLOCK_HEIGHT))) return false;
    //碰撞检测
    if(existsCollision(new MetaBlock(this.mainWindow, metaBlock.getX(), metaBlock.getY()+distance))) return false;

    return true;
  }

  /**
   *
   * 碰撞检测
   * @param b
   */
  private boolean existsCollision(MetaBlock b) {
    for(int i=0;i< metaBlocks.length;i++){
      for(int j=0;j< metaBlocks[i].length;j++){
        MetaBlock metaBlock = metaBlocks[i][j];
        if(metaBlock != null){
         if(metaBlock.isCollision(b)) return true;
        }
      }
    }

    return false;
  }

  /**
   * 碰撞检测
   * @param x
   * @param y
   */
  public boolean existsBlockCollision(int x, int y) {
    if(!isRectangleInPanel(new Rectangle(x,y,MetaBlock.BLOCK_WIDTH,MetaBlock.BLOCK_HEIGHT))) return true;
    if(isCollisionPanelTop(new Rectangle(x,y,MetaBlock.BLOCK_WIDTH,MetaBlock.BLOCK_HEIGHT))) return true;
    //碰撞检测
    if(existsCollision(new MetaBlock(this.mainWindow, x, y))) return true;
    return false;
  }

  public void putCurrentBlockIntoBlockArray(){
    if(this.currentBlock == null) return;
    Block currentBlock = this.currentBlock;
    MetaBlock[][] metaBlocks = currentBlock.getMetaBlocks();
    if(metaBlocks == null || metaBlocks.length == 0) return;
    for(int i=0;i<metaBlocks.length;i++){
      for(int j=0;j<metaBlocks[i].length;j++){
        MetaBlock metaBlock = metaBlocks[i][j];
        if(metaBlock != null){
          int row = metaBlock.getRow();
          int col = metaBlock.getCol();
          this.metaBlocks[row][col] = metaBlock;
        }
      }
    }
  }

  public boolean isCollisionPanelTop(Rectangle rectangle){
    return rectangle.getY()<0;
  }

  private class BlockFactory {

    private Random random = new Random();

    private static final int x = 4*MetaBlock.BLOCK_WIDTH;
    private static final int y = -4*MetaBlock.BLOCK_WIDTH;

    public Block getRandomBlock(){
      //获取随机色
      Color color = UIUtils.getRandomGeneralColor();
      //获取随机的方块类型
      BlockType[] blockTypes = BlockType.values();
      BlockType blockType = blockTypes[random.nextInt(blockTypes.length)];


      /**
       * 生成方块
       */
      Block block = null;
        switch (blockType){
          case LEFT_BROKEN_LINE:
            block = new LeftBrokenLineBlock(MainPanel.this.mainWindow,this.x,this.y,color);
            break;
          case RIGHT_BROKEN_LINE:
            block = new RightBrokenLineBlock(MainPanel.this.mainWindow,this.x,this.y,color);
            break;
          case LEFT_L:
            block = new LeftLBlock(MainPanel.this.mainWindow,this.x,this.y,color);
            break;
          case RIGHT_L:
            block = new RightLBlock(MainPanel.this.mainWindow,this.x,this.y,color);
            break;
          case VERTICAL_LINE:
            block = new VerticalLineBlock(MainPanel.this.mainWindow,this.x,this.y,color);
            break;
          case TRIANGLE:
            block = new TriangleBlock(MainPanel.this.mainWindow,this.x,this.y,color);
            break;
          case FIELD:
            block = new FieldBlock(MainPanel.this.mainWindow,this.x,this.y,color);
            break;
        }

      return block;
    }

  }

  public void keyPressed(KeyEvent e){
    int keyCode = e.getKeyCode();//键盘代码
    switch (keyCode){
      case KeyEvent.VK_DOWN:
        if(currentBlock != null){
          currentBlock.moveBottom();
        }
        break;
      case KeyEvent.VK_UP:
        //旋转
        if(currentBlock != null){
          currentBlock.rotate();
        }
        break;
      case KeyEvent.VK_LEFT:
        if(currentBlock != null){
          currentBlock.moveLeftOneStep();
        }
        break;
      case KeyEvent.VK_RIGHT:
        if(currentBlock != null){
          currentBlock.moveRightOneStep();
        }
        break;
    }
  }

  private class BlockMoveThread implements Runnable {

    public void run() {
      while (MainPanel.this.blockMoveThreadAlive){
        System.out.println("移动前："+currentBlock);
        if(MainPanel.this.currentBlock == null){
          //创建一个随机的方块
          Block randomBlock = MainPanel.this.blockFactory.getRandomBlock();
          //尝试将方块移到0点位置
          boolean flag =true;
          do{
            flag = randomBlock.moveDown(1);
          }while (flag && randomBlock.getY()<0);
          MainPanel.this.currentBlock = randomBlock;
          //碰撞检测
          if(!randomBlock.canMoveDown(1)){
            //todo
            MainPanel.this.stopBlockMoveThread();
            return;
          }
          try {
            Thread.sleep(900);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          continue;
        }
        //向下移动方块
        MainPanel.this.currentBlock.moveDown(1);
        if(!MainPanel.this.currentBlock.canMoveDown(1)){
          //不能移动,等待
          if(!MainPanel.this.currentBlock.canMoveDown(1)){
            //方块不能移动，将方块移入数组
            MainPanel.this.putCurrentBlockIntoBlockArray();
            //消除方块
            MainPanel.this.eliminateBlocks();
            //重新生成新的方块
            Block randomBlock = MainPanel.this.blockFactory.getRandomBlock();
            //尝试将方块移到0点位置
            boolean flag =true;
            do{
              flag = randomBlock.moveDown(1);
            }while (flag && randomBlock.getY()<0);
            MainPanel.this.currentBlock = randomBlock;
            //碰撞检测
            if(!randomBlock.canMoveDown(1)){
              MainPanel.this.stopBlockMoveThread();
              return;
            }
          }
        }
        try {
          Thread.sleep(900);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("移动后："+currentBlock);
      }

    }
  }

  /**
   * 消除方块
   */
  private void eliminateBlocks() {
    if(this.metaBlocks == null || this.metaBlocks.length == 0) return;
    MetaBlock[][] metaBlocks = this.metaBlocks;
    for(int i=0;i<metaBlocks.length;i++){
      boolean canEliminate = true;
      for(int j=0;j<metaBlocks[i].length;j++){
        MetaBlock metaBlock = metaBlocks[i][j];
        if(metaBlock == null){
          canEliminate = false;
          break;
        }
      }
      if(canEliminate){
        //可以消除
        if(i != 0){//不是第一行
          //上部所有方块下移
          for(int k=i-1;k>0;k--){
            for(int g=0;g<metaBlocks[k].length;g++){
              MetaBlock metaBlock = metaBlocks[k][g];
              if(metaBlock != null){
                metaBlock.setY(metaBlock.getY()+MetaBlock.BLOCK_HEIGHT);
              }
              metaBlocks[k+1][g] = metaBlocks[k][g];
            }
          }
          //加分
          this.mainWindow.plusScore(10);
        }

        //继续消除消除方块
        eliminateBlocks();
      }
    }
  }

}

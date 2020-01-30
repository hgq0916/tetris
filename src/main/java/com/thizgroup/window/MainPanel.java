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
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

  private MainWindow mainWindow;

  private BlockFactory blockFactory = new BlockFactory();

  private static final int PANEL_WIDTH = 180;
  private static final int PANEL_HEIGHT = 300;

  private Rectangle panelRectangle = new Rectangle(0,0,PANEL_WIDTH,PANEL_HEIGHT);

  //定义数组存储所有的方块
  private MetaBlock[][] metaBlocks = new MetaBlock[15][9];
  private Block currentBlock = null;

  public MainPanel(MainWindow mainWindow){

    this.mainWindow = mainWindow;
    this.setBackground(Color.WHITE);
    this.setBounds(25,44,PANEL_WIDTH,PANEL_HEIGHT);
    //随机生成方块
    currentBlock = blockFactory.getRandomBlock();
  }

  public boolean isOverPanel(int x,int y){
    return x<0 || x>PANEL_WIDTH || y<0 || y>PANEL_HEIGHT;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    //画线
    drawLine(g);
    //画方块
    drawBlocks(g);
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
    //碰撞检测
    if(existsCollision(new MetaBlock(this.mainWindow, x, y))) return true;
    return false;
  }

  private class BlockFactory {

    private Random random = new Random();

    private static final int x = 4*MetaBlock.BLOCK_WIDTH;
    private static final int y = 0;//-4*MetaBlock.BLOCK_HEIGHT

    public Block getRandomBlock(){
      //获取随机色
      Color color = UIUtils.getRandomGeneralColor();
      //获取随机的方块类型
      BlockType[] blockTypes = BlockType.values();
      //BlockType blockType = blockTypes[random.nextInt(blockTypes.length)];
      BlockType blockType = BlockType.TRIANGLE;


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
    System.out.println("移动前："+currentBlock);
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
    System.out.println("移动后："+currentBlock);
  }

}

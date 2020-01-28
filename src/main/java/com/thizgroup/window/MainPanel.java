package com.thizgroup.window;

import com.thizgroup.block.Block;
import com.thizgroup.block.BlockType;
import com.thizgroup.block.MetaBlock;
import com.thizgroup.utils.UIUtils;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

  private MainWindow mainWindow;

  private BlockFactory blockFactory = new BlockFactory();

  private static final int PANEL_WIDTH = 180;
  private static final int PANEL_HEIGHT = 300;

  //定义数组存储所有的方块
  private MetaBlock[][] metaBlocks = new MetaBlock[15][9];
  private Block currentBlock = null;

  public MainPanel(MainWindow mainWindow){

    this.mainWindow = mainWindow;
    //this.setBackground(new Color(0,0,0));
    this.setBackground(new Color(255,255,255));
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
    if(this.isOverPanel(metaBlock.getX()-distance, metaBlock.getY())) return false;

    //碰撞检测
    if(existsCollision(new MetaBlock(this.mainWindow, metaBlock.getX()-distance, metaBlock.getY()))) return false;

    return true;
  }

  public boolean canMoveRight(MetaBlock metaBlock, int distance) {
    //越界检测
    if(this.isOverPanel(metaBlock.getX()+distance, metaBlock.getY())) return false;

    //碰撞检测
    if(existsCollision(new MetaBlock(this.mainWindow, metaBlock.getX()+distance, metaBlock.getY()))) return false;

    return true;
  }

  public boolean canMoveDown(MetaBlock metaBlock, int distance) {
    //越界检测
    if(this.isOverPanel(metaBlock.getX(), metaBlock.getY()+distance)) return false;

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

  private class BlockFactory {

    private Random random = new Random();

    private static final int x = 4*MetaBlock.BLOCK_WIDTH;
    private static final int y = 0;//-4*MetaBlock.BLOCK_HEIGHT

    public Block getRandomBlock(){
      //获取随机色
      Color color = UIUtils.getRandomGeneralColor();
      //获取随机的方块类型
      BlockType[] blockTypes = BlockType.values();
      BlockType blockType = blockTypes[random.nextInt(blockTypes.length)];

      Block block = new Block(MainPanel.this.mainWindow,this.x,this.y,color,blockType);

      return block;
    }

  }

}

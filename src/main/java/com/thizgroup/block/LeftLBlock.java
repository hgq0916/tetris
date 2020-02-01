package com.thizgroup.block;

import com.thizgroup.window.MainPanel;
import com.thizgroup.window.MainWindow;
import java.awt.Color;

public class LeftLBlock extends Block{

  private int  center[] = new int[2];

  public LeftLBlock(MainWindow mainWindow, int x, int y, Color color) {
    super(mainWindow, x, y, color);
    this.blockType = BlockType.LEFT_L;
    init();
  }

  /**
   * 	||
   * 	||
   * 	||||
   */
  private void init() {
    MetaBlock[][] metaBlocks = new MetaBlock[3][2];
    int tempX = this.x;
    int tempY = this.y;
    metaBlocks[0][0] = new MetaBlock(this.mainWindow,tempX,tempY,this.color);
    metaBlocks[1][0] = new MetaBlock(this.mainWindow,tempX,tempY+1*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[2][0] = new MetaBlock(this.mainWindow,tempX,tempY+2*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[2][1] = new MetaBlock(this.mainWindow,tempX+1*MetaBlock.BLOCK_WIDTH,tempY+2*MetaBlock.BLOCK_HEIGHT,this.color);
    //设置中心
    center[0] = 1;
    center[1] = 0;
    this.metaBlocks = metaBlocks;
  }

  public void rotate() {
    boolean flag = leftRotate();//左旋
    if(!flag){
      rightRotate();//右旋
    }
  }

  private boolean rightRotate() {
    MetaBlock[][] tempMetaBlocks = this.metaBlocks;
    if(tempMetaBlocks == null || tempMetaBlocks.length ==0) return true;
    MetaBlock[][] blocks = new MetaBlock[tempMetaBlocks[0].length][tempMetaBlocks.length];

    int[] tempCenter = new int[2];

    for(int i=0;i<blocks.length;i++){
      for(int j=0;j<blocks[i].length;j++){
        if((blocks[i].length-1-j) == this.center[0] && i == this.center[1]){
          tempCenter[0] = i;
          tempCenter[1] = j;
        }
        blocks[i][j] = tempMetaBlocks[blocks[i].length-1-j][i];
      }
    }

    //重新计算数组第一个方块的坐标
    MetaBlock centerBlock = blocks[tempCenter[0]][tempCenter[1]];
    int centerX = centerBlock.getX();
    int centerY = centerBlock.getY();

    MetaBlock[][] newBlocks = new MetaBlock[blocks.length][blocks[0].length];

    //矩阵第一个位置的坐标
    int firstX = centerX - tempCenter[1]*MetaBlock.BLOCK_WIDTH;
    int firstY = centerY - tempCenter[0]*MetaBlock.BLOCK_HEIGHT;

    for(int i=0;i<blocks.length;i++){
      for(int j=0;j<blocks[i].length;j++){
        MetaBlock metaBlock = blocks[i][j];
        if(metaBlock != null){
          //计算新的坐标
          int newX = firstX + j*MetaBlock.BLOCK_WIDTH;
          int newY = firstY + i * MetaBlock.BLOCK_HEIGHT;
          //碰撞检测
          MainPanel mainPanel = (MainPanel) this.mainWindow.getMainPanel();
          if(mainPanel.existsBlockCollision(newX,newY)){//检测到碰撞
            return false;
          }
          newBlocks[i][j] = new MetaBlock(metaBlock.getMainWindow(),newX,newY,metaBlock.getColor());
        }
      }
    }

    //旋转完成
    this.metaBlocks = newBlocks;
    this.center = tempCenter;

    return true;
  }

  private boolean leftRotate() {
    MetaBlock[][] tempMetaBlocks = this.metaBlocks;
    if(tempMetaBlocks == null || tempMetaBlocks.length ==0) return true;
    MetaBlock[][] blocks = new MetaBlock[tempMetaBlocks[0].length][tempMetaBlocks.length];

    int[] tempCenter = new int[2];

    for(int i=0;i<blocks.length;i++){
      for(int j=0;j<blocks[i].length;j++){
        if(j == this.center[0] && (blocks.length-1-i) == this.center[1]){
          tempCenter[0] = i;
          tempCenter[1] = j;
        }
        blocks[i][j] = tempMetaBlocks[j][blocks.length-1-i];
      }
    }

    //重新计算数组第一个方块的坐标
    MetaBlock centerBlock = blocks[tempCenter[0]][tempCenter[1]];
    int centerX = centerBlock.getX();
    int centerY = centerBlock.getY();

    MetaBlock[][] newBlocks = new MetaBlock[blocks.length][blocks[0].length];

    //矩阵第一个位置的坐标
    int firstX = centerX - tempCenter[1]*MetaBlock.BLOCK_WIDTH;
    int firstY = centerY - tempCenter[0]*MetaBlock.BLOCK_HEIGHT;

    for(int i=0;i<blocks.length;i++){
      for(int j=0;j<blocks[i].length;j++){
        MetaBlock metaBlock = blocks[i][j];
        if(metaBlock != null){
          //计算新的坐标
          int newX = firstX + j*MetaBlock.BLOCK_WIDTH;
          int newY = firstY + i * MetaBlock.BLOCK_HEIGHT;
          //碰撞检测
          MainPanel mainPanel = (MainPanel) this.mainWindow.getMainPanel();
          if(mainPanel.existsBlockCollision(newX,newY)){//检测到碰撞
            return false;
          }
          newBlocks[i][j] = new MetaBlock(metaBlock.getMainWindow(),newX,newY,metaBlock.getColor());
        }
      }
    }

    //旋转完成
    this.metaBlocks = newBlocks;
    this.center = tempCenter;

    return true;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    LeftLBlock leftLBlock = new LeftLBlock(this.mainWindow,this.x,this.y,this.color);
    return leftLBlock;
  }

}

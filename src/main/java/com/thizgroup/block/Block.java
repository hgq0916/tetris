package com.thizgroup.block;

import com.thizgroup.window.MainWindow;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

public abstract class Block {

  protected MetaBlock[][] metaBlocks = {};

  protected int x;//左上角点x坐标
  protected int y;//左上角点y坐标
  protected Color color = Color.BLACK;
  protected BlockType blockType;
  protected MainWindow mainWindow;

  public Block(MainWindow mainWindow){
    this.mainWindow = mainWindow;
  }

  public Block(MainWindow mainWindow,int x,int y,Color color){
    this(mainWindow);
    this.x = x;
    this.y = y;
    this.color = color;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public BlockType getBlockType() {
    return blockType;
  }

  public void setBlockType(BlockType blockType) {
    this.blockType = blockType;
  }

  /**
   * 绘制方块
   * @param g
   */
  public void paint(Graphics g){
    MetaBlock[][] metaBlocksTemp = metaBlocks;
    if(metaBlocksTemp != null && metaBlocksTemp.length != 0){
      for(int i=0;i<metaBlocksTemp.length;i++){
        for(int j=0;j<metaBlocksTemp[i].length;j++){
          MetaBlock metaBlock = metaBlocksTemp[i][j];
          if(metaBlock != null){
            metaBlock.paint(g);
          }
        }
      }
    }
  }

  /**
   * 左移
   * @param step 步数
   * @return 成功或者失败
   */
  public  boolean moveLeft(int step){
   boolean canMove = true;
    MetaBlock[][] metaBlocksTemp = metaBlocks;
    if(metaBlocksTemp == null && metaBlocksTemp.length == 0) return true;

      for(int i=0;i<metaBlocksTemp.length;i++){
        for(int j=0;j<metaBlocksTemp[i].length;j++){
          MetaBlock metaBlock = metaBlocksTemp[i][j];
          if(metaBlock != null && !metaBlock.canMoveLeft()){
            canMove = false;
            break;
          }
        }
      }

   if(canMove){
     //可以移动
     for(int i=0;i<metaBlocksTemp.length;i++){
       for(int j=0;j<metaBlocksTemp[i].length;j++){
         MetaBlock metaBlock = metaBlocksTemp[i][j];
         if(metaBlock != null){
           metaBlock.moveLeft();
         }
       }
     }
     return true;
   }

   return false;
  }

  /**
   * 左移一步
   * @return
   */
  public  boolean moveLeftOneStep(){
    return moveLeft(1);
  }

  /**
   * 右移
   * @param step 步数
   * @return 成功或者失败
   */
  public boolean moveRight(int step){
    boolean canMove = true;
    MetaBlock[][] metaBlocksTemp = metaBlocks;
    if(metaBlocksTemp == null && metaBlocksTemp.length == 0) return true;

    for(int i=0;i<metaBlocksTemp.length;i++){
      for(int j=0;j<metaBlocksTemp[i].length;j++){
        MetaBlock metaBlock = metaBlocksTemp[i][j];
        if(metaBlock != null && !metaBlock.canMoveRight()){
          canMove = false;
          break;
        }
      }
    }

    if(canMove){
      //可以移动
      for(int i=0;i<metaBlocksTemp.length;i++){
        for(int j=0;j<metaBlocksTemp[i].length;j++){
          MetaBlock metaBlock = metaBlocksTemp[i][j];
          if(metaBlock != null){
            metaBlock.moveRight();
          }
        }
      }
      return true;
    }

    return false;
  }

  /**
   * 右移一步
   * @return
   */
  public  boolean moveRightOneStep(){
    return moveRight(1);
  }

  /**
   * 下移
   * @param step 步数
   * @return 成功或者失败
   */
  public boolean moveDown(int step){
    boolean canMove = true;
    MetaBlock[][] metaBlocksTemp = metaBlocks;
    if(metaBlocksTemp == null && metaBlocksTemp.length == 0) return true;

    for(int i=0;i<metaBlocksTemp.length;i++){
      for(int j=0;j<metaBlocksTemp[i].length;j++){
        MetaBlock metaBlock = metaBlocksTemp[i][j];
        if(metaBlock != null && !metaBlock.canMoveDown()){
          canMove = false;
          break;
        }
      }
    }

    if(canMove){
      //可以移动
      for(int i=0;i<metaBlocksTemp.length;i++){
        for(int j=0;j<metaBlocksTemp[i].length;j++){
          MetaBlock metaBlock = metaBlocksTemp[i][j];
          if(metaBlock != null){
            metaBlock.moveDown();
          }
        }
      }
      return true;
    }

    return false;
  }

  /**
   * 判断是否可以向下移动
   */
  public boolean canMoveDown(int step) {
    boolean canMove = true;
    MetaBlock[][] metaBlocksTemp = metaBlocks;
    if (metaBlocksTemp == null && metaBlocksTemp.length == 0)
      return true;

    for (int i = 0; i < metaBlocksTemp.length; i++) {
      for (int j = 0; j < metaBlocksTemp[i].length; j++) {
        MetaBlock metaBlock = metaBlocksTemp[i][j];
        if (metaBlock != null && !metaBlock.canMoveDown()) {
          canMove = false;
          break;
        }
      }
    }
    return canMove;
  }

  /**
   * 下移一步
   * @return
   */
  public  boolean moveDownOneStep(){
    return moveDown(1);
  }

  public void moveBottom(){
    boolean flag = false;
    do {
      flag = moveDown(1);
    }while (flag);
  }

  /**
   * 获取当前方块的高度
   * @return
   */
  public int getHeight(){

    if(metaBlocks == null || metaBlocks.length == 0) return 0;

    return metaBlocks.length;
  }

  @Override
  public String toString() {
    return "Block{" +
        "metaBlocks=" + Arrays.toString(metaBlocks) +
        ", x=" + x +
        ", y=" + y +
        ", color=" + color +
        ", blockType=" + blockType +
        '}';
  }

  /**
   *
   * 旋转
   */
  public abstract  void rotate();

  public MetaBlock[][] getMetaBlocks() {
    return metaBlocks;
  }

}

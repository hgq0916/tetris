package com.thizgroup.block;

import java.awt.Graphics;

public abstract class AbstractBlock {

  protected MetaBlock[] metaBlocks = new MetaBlock[4];

  private int x;//左上角点x坐标
  private int y;//左上角点y坐标

  public AbstractBlock(){
  }

  /**
   * 绘制方块
   * @param g
   */
  public abstract void paint(Graphics g);

  /**
   * 左移
   * @param step 步数
   * @return 成功或者失败
   */
  public  boolean moveLeft(int step){
   boolean canMove = false;
   for(int i=0;i<metaBlocks.length;i++){
     MetaBlock metaBlock = metaBlocks[i];
     if(metaBlock == null || !metaBlock.canMoveLeft()){
       canMove = false;
       break;
     }
   }

   if(canMove){
     //可以移动
     for(int i=0;i<metaBlocks.length;i++){
       MetaBlock metaBlock = metaBlocks[i];
       metaBlock.moveLeft();
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
    for(int i=0;i<metaBlocks.length;i++){
      MetaBlock metaBlock = metaBlocks[i];
      if(metaBlock == null || !metaBlock.canMoveRight()){
        canMove = false;
        break;
      }
    }

    if(canMove){
      //可以移动
      for(int i=0;i<metaBlocks.length;i++){
        MetaBlock metaBlock = metaBlocks[i];
        metaBlock.canMoveRight();
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
    for(int i=0;i<metaBlocks.length;i++){
      MetaBlock metaBlock = metaBlocks[i];
      if(metaBlock == null || !metaBlock.canMoveDown()){
        canMove = false;
        break;
      }
    }

    if(canMove){
      //可以移动
      for(int i=0;i<metaBlocks.length;i++){
        MetaBlock metaBlock = metaBlocks[i];
        metaBlock.moveDown();
      }
      return true;
    }

    return false;
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

}

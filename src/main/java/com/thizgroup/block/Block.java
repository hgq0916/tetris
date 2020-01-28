package com.thizgroup.block;

import com.thizgroup.window.MainWindow;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

public class Block {

  protected MetaBlock[] metaBlocks = new MetaBlock[4];

  private int x;//左上角点x坐标
  private int y;//左上角点y坐标
  private Color color = Color.BLACK;
  private BlockType blockType = BlockType.LEFT_BROKEN_LINE;
  private MainWindow mainWindow;

  public Block(MainWindow mainWindow){
    this.mainWindow = mainWindow;
  }

  public Block(MainWindow mainWindow,int x,int y,Color color,BlockType blockType){
    this(mainWindow);
    this.x = x;
    this.y = y;
    this.color = color;
    this.blockType = blockType;

    generateMetaBlocks();
  }

  /**
   * 生成方块
   */
  private void generateMetaBlocks() {
    switch (this.blockType){
      case LEFT_BROKEN_LINE:
        generateLeftBrokenLineMetaBlocks();
        break;
      case RIGHT_BROKEN_LINE:
        generateRightBrokenLineMetaBlocks();
        break;
      case LEFT_L:
        generateLeftLMetaBlocks();
        break;
      case RIGHT_L:
        generateRightLMetaBlocks();
        break;
      case VERTICAL_LINE:
        generateVerticalLineMetaBlocks();
        break;
      case TRIANGLE:
        generateTriangleMetaBlocks();
        break;
      case FIELD:
        generateFleidMetaBlocks();
        break;
    }
  }

  /**
   *  ||||
   *  ||||
   */
  private void generateFleidMetaBlocks() {
    int tempX = this.x;
    int tempY = this.y;
    metaBlocks[0] = new MetaBlock(this.mainWindow,tempX,tempY,this.color);
    metaBlocks[1] = new MetaBlock(this.mainWindow,tempX+1*MetaBlock.BLOCK_WIDTH,tempY,this.color);
    metaBlocks[2] = new MetaBlock(this.mainWindow,tempX,tempY+1*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[3] = new MetaBlock(this.mainWindow,tempX+1*MetaBlock.BLOCK_WIDTH,tempY+1*MetaBlock.BLOCK_HEIGHT,this.color);
  }

  /**
   * 	||
   * 	||
   * 	||
   * 	||
   */
  private void generateVerticalLineMetaBlocks() {
    int tempX = this.x;
    int tempY = this.y;
    metaBlocks[0] = new MetaBlock(this.mainWindow,tempX,tempY,this.color);
    metaBlocks[1] = new MetaBlock(this.mainWindow,tempX,tempY+1*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[2] = new MetaBlock(this.mainWindow,tempX,tempY+2*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[3] = new MetaBlock(this.mainWindow,tempX,tempY+3*MetaBlock.BLOCK_HEIGHT,this.color);
  }

  /**
   *      ||
   * 		  ||
   * 	  ||||
   */
  private void generateRightLMetaBlocks() {
    int tempX = this.x;
    int tempY = this.y;
    metaBlocks[0] = new MetaBlock(this.mainWindow,tempX+1*MetaBlock.BLOCK_WIDTH,tempY,this.color);
    metaBlocks[1] = new MetaBlock(this.mainWindow,tempX+1*MetaBlock.BLOCK_WIDTH,tempY+1*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[2] = new MetaBlock(this.mainWindow,tempX,tempY+2*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[3] = new MetaBlock(this.mainWindow,tempX+1*MetaBlock.BLOCK_WIDTH,tempY+2*MetaBlock.BLOCK_HEIGHT,this.color);
  }

  /**
   * 	||
   * 	||
   * 	||||
   */
  private void generateLeftLMetaBlocks() {
    int tempX = this.x;
    int tempY = this.y;
    metaBlocks[0] = new MetaBlock(this.mainWindow,tempX,tempY,this.color);
    metaBlocks[1] = new MetaBlock(this.mainWindow,tempX,tempY+1*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[2] = new MetaBlock(this.mainWindow,tempX,tempY+2*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[3] = new MetaBlock(this.mainWindow,tempX+1*MetaBlock.BLOCK_WIDTH,tempY+2*MetaBlock.BLOCK_HEIGHT,this.color);
  }

  /**
   * 	  ||
   * 	  ||||
   * 		  ||
   */
  private void generateRightBrokenLineMetaBlocks() {
    int tempX = this.x;
    int tempY = this.y;
    metaBlocks[0] = new MetaBlock(this.mainWindow,tempX,tempY,this.color);
    metaBlocks[1] = new MetaBlock(this.mainWindow,tempX,tempY+1*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[2] = new MetaBlock(this.mainWindow,tempX+1*MetaBlock.BLOCK_WIDTH,tempY+1*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[3] = new MetaBlock(this.mainWindow,tempX+1*MetaBlock.BLOCK_WIDTH,tempY+2*MetaBlock.BLOCK_HEIGHT,this.color);
  }

  /**
   * 		  ||
   * 	  ||||
   * 	  ||
   */
  private void generateLeftBrokenLineMetaBlocks() {
    int tempX = this.x;
    int tempY = this.y;
    metaBlocks[0] = new MetaBlock(this.mainWindow,tempX,tempY,this.color);
    metaBlocks[1] = new MetaBlock(this.mainWindow,tempX,tempY+1*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[2] = new MetaBlock(this.mainWindow,tempX+1*MetaBlock.BLOCK_WIDTH,tempY+1*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[3] = new MetaBlock(this.mainWindow,tempX+1*MetaBlock.BLOCK_WIDTH,tempY+2*MetaBlock.BLOCK_HEIGHT,this.color);
  }

  /**
   * 	   ||
   * 	||||||
   */
  private void generateTriangleMetaBlocks() {
    int tempX = this.x;
    int tempY = this.y;
    metaBlocks[0] = new MetaBlock(this.mainWindow,tempX+1*MetaBlock.BLOCK_WIDTH,tempY,this.color);
    metaBlocks[1] = new MetaBlock(this.mainWindow,tempX,tempY+1*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[2] = new MetaBlock(this.mainWindow,tempX+1*MetaBlock.BLOCK_WIDTH,tempY+1*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[3] = new MetaBlock(this.mainWindow,tempX+2*MetaBlock.BLOCK_WIDTH,tempY+1*MetaBlock.BLOCK_HEIGHT,this.color);
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
    for(int i=0;i<metaBlocks.length;i++){
      MetaBlock metaBlock = metaBlocks[i];
      if(metaBlock != null){
        metaBlock.paint(g);
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
        metaBlock.moveRight();
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

  /**
   * 获取当前方块的高度
   * @return
   */
  public int getHeight(){
    return blockType == null?0:blockType.getHeight();
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
  public void rotate() {

  }

}

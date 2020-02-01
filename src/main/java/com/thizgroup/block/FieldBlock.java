package com.thizgroup.block;

import com.thizgroup.window.MainWindow;
import java.awt.Color;

public class FieldBlock extends Block{

  public FieldBlock(MainWindow mainWindow, int x, int y, Color color) {
    super(mainWindow, x, y, color);
    this.blockType = BlockType.FIELD;
    init();
  }

  private void init() {
    MetaBlock[][] metaBlocks = new MetaBlock[2][2];
    int tempX = this.x;
    int tempY = this.y;
    metaBlocks[0][0] = new MetaBlock(this.mainWindow,tempX,tempY,this.color);
    metaBlocks[0][1] = new MetaBlock(this.mainWindow,tempX+1*MetaBlock.BLOCK_WIDTH,tempY,this.color);
    metaBlocks[1][0] = new MetaBlock(this.mainWindow,tempX,tempY+1*MetaBlock.BLOCK_HEIGHT,this.color);
    metaBlocks[1][1] = new MetaBlock(this.mainWindow,tempX+1*MetaBlock.BLOCK_WIDTH,tempY+1*MetaBlock.BLOCK_HEIGHT,this.color);
    this.metaBlocks = metaBlocks;
  }

  public void rotate() {
    return ;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    FieldBlock fieldBlock = new FieldBlock(this.mainWindow,this.x,this.y,this.color);
    return fieldBlock;
  }

}

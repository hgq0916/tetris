package com.thizgroup.block;

import com.thizgroup.window.MainWindow;
import java.awt.Color;

public class LeftLBlock extends Block{

  public LeftLBlock(MainWindow mainWindow, int x, int y, Color color) {
    super(mainWindow, x, y, color);
    this.blockType = BlockType.VERTICAL_LINE;
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
    this.metaBlocks = metaBlocks;
  }

  public void rotate() {
    //todo
  }




}

package com.thizgroup.block;

/**
 * 方块类型
 */
public enum BlockType {
  VERTICAL_LINE("竖线",4),
  LEFT_BROKEN_LINE("左折线",3),
  RIGHT_BROKEN_LINE("右折线",3),
  LEFT_L("左L形",3),
  RIGHT_L("右L形",3),
  TRIANGLE("三角形",2),
  FIELD("田字形",2)
  ;


  private String name;
  private int height;

  public String getName() {
    return name;
  }

  public int getHeight() {
    return height;
  }

  private BlockType(String name,int height){
    this.name = name;
  }

}

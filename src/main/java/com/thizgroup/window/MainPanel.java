package com.thizgroup.window;

import com.thizgroup.block.Block;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

  private MainWindow mainWindow;

  private static final int PANEL_WIDTH = 180;
  private static final int PANEL_HEIGHT = 300;

  //定义数组存储所有的方块
  private Block[][] blocks = new Block[15][9];

  public MainPanel(MainWindow mainWindow){

    this.mainWindow = mainWindow;
    //this.setBackground(new Color(0,0,0));
    this.setBackground(new Color(255,255,255));
    this.setBounds(25,44,PANEL_WIDTH,PANEL_HEIGHT);

    blocks[0][0] = new Block(this.mainWindow,20,20,Color.BLUE);
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
    for(int i=0;i<blocks.length;i++){
      for(int j=0;j<blocks[i].length;j++){
        Block block = blocks[i][j];
        if(block != null){
          block.paint(g);
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

  public boolean canMoveLeft(Block block, int distance) {
    //越界检测
    if(this.isOverPanel(block.getX()-distance,block.getY())) return false;

    //todo 碰撞检测

    return false;
  }


  public boolean canMoveRight(Block block, int distance) {
    //越界检测
    if(this.isOverPanel(block.getX()+distance,block.getY())) return false;

    //todo 碰撞检测

    return false;
  }

  public boolean canMoveBottom(Block block, int distance) {
    //越界检测
    if(this.isOverPanel(block.getX(),block.getY()+distance)) return false;

    //todo 碰撞检测

    return false;
  }
}

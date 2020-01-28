package com.thizgroup.block;

import com.thizgroup.window.MainPanel;
import com.thizgroup.window.MainWindow;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * 方块
 */
public class MetaBlock {

  public static final int BLOCK_WIDTH = 20;
  public static final int BLOCK_HEIGHT = 20;

  private int x = 0;
  private int y = -BLOCK_HEIGHT;//不可见
  private MainWindow mainWindow;

  private Color color = new Color(0,0,0);

  public MetaBlock(MainWindow mainWindow){
    this.mainWindow = mainWindow;
  }

  public MetaBlock(MainWindow mainWindow,int x,int y){
    this(mainWindow);
    this.x = x;
    this.y = y;
  }

  public MetaBlock(MainWindow mainWindow,int x,int y,Color color){
    this(mainWindow,x,y);
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

  public int getWidth() {
    return BLOCK_WIDTH;
  }

  public int getHeight() {
    return BLOCK_HEIGHT;
  }

  public boolean canMoveLeft(){
    MainPanel mainPanel = (MainPanel) this.mainWindow.getMainPanel();
    if(mainPanel.canMoveLeft(this,BLOCK_WIDTH)){
      return true;
    }
    return false;
  }

  public boolean canMoveRight(){
    MainPanel mainPanel = (MainPanel) this.mainWindow.getMainPanel();
    if(mainPanel.canMoveRight(this,BLOCK_WIDTH)){
      return true;
    }
    return false;
  }

  public boolean canMoveDown(){
    MainPanel mainPanel = (MainPanel) this.mainWindow.getMainPanel();
    if(mainPanel.canMoveDown(this,BLOCK_HEIGHT)){
      return true;
    }
    return false;
  }

  public boolean moveLeft(){
    if(canMoveLeft()){
      this.x = this.x - BLOCK_WIDTH;
      return true;
    }

    return false;
  }

  public boolean moveRight(){

    if(canMoveRight()){
      this.x = this.x + BLOCK_WIDTH;
      return true;
    }

    return false;
  }

  public boolean moveDown(){

    if(canMoveDown()){
      this.y = this.y + BLOCK_HEIGHT;
      return true;
    }

    return false;
  }

  public void paint(Graphics g){
    Color oldColor = g.getColor();
    g.setColor(this.color);
    g.fillRect(this.x,this.y,BLOCK_WIDTH,BLOCK_HEIGHT);
    g.setColor(Color.RED);
    g.drawRect(this.x,this.y,BLOCK_WIDTH,BLOCK_HEIGHT);
    g.setColor(oldColor);
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Rectangle getRect(){
    return new Rectangle((int)x,(int) y, BLOCK_WIDTH, BLOCK_HEIGHT);
  }

  public boolean isCollision(MetaBlock metaBlock) {
    if(this.getRect().intersects(metaBlock.getRect())) return true;

    return false;
  }

}

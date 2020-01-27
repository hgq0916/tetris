package com.thizgroup.window;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

  private MainWindow mainWindow;

  public MainPanel(MainWindow mainWindow){

    this.mainWindow = mainWindow;

  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    drawLine(g);
  }

  /**
   * 画线
   * @param g
   */
  private void drawLine(Graphics g) {

    Color oloColor = g.getColor();

    int x = 0;
    int y = 0;

    g.setColor(Color.BLUE);
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

}

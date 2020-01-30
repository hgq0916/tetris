//package com.thizgroup.window;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import javax.swing.JPanel;
//
//public class BottomPanel extends JPanel {
//
//  private MainWindow mainWindow;
//
//  public static final String COPYRIGHT1 = "made by gangquan.hu";
//  public static final String COPYRIGHT2 = "xuemei.hu®版权所有";
//
//  public BottomPanel(MainWindow mainWindow){
//    this.mainWindow = mainWindow;
//    this.setBackground(Color.WHITE);
//    this.setBounds(40,MainWindow.TITLE_BAR_HEIGHT+340,170,100);
//  }
//
//  @Override
//  public void paint(Graphics src) {
//    //画字符串
//    Graphics2D graphics2D = (Graphics2D) src;
//    Color oldColor = graphics2D.getColor();
//    graphics2D.setColor(Color.pink);
//    graphics2D.drawString(COPYRIGHT1,5,5);
//    graphics2D.drawString(COPYRIGHT2,5,20);
//  }
//}

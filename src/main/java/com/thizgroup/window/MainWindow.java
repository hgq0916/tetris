package com.thizgroup.window;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

//  界面大小：348*408像素
  public static final int WIN_WIDTH = 348;
  public static final int WIN_HEIGHT = 406;
  public static final int TITLE_BAR_HEIGHT = 26;

  //窗口标题
  public static final String WIN_TITLE = "俄罗斯方块-户学敏专用版";
  public static final String COPYRIGHT1 = "made by gangquan.hu";
  public static final String COPYRIGHT2 = "xuemei.hu®版权所有";
  //窗口背景色
  public static final Color WIN_BGC = new Color(0,0,0);

  public static final int BGC_BLOCK_WIDTH = 10;
  public static final int BGC_BLOCK_HEIGHT = 10;


  private Image iBuffer;
  private Graphics gBuffer;

  //窗口组件
  private JPanel mainPanel = null;

  /**
   * 窗口初始化
   */
  public void init(){

    //设置窗口大小
    this.setSize(new Dimension(WIN_WIDTH,WIN_HEIGHT+TITLE_BAR_HEIGHT));
    //设置窗口布局
    this.setLayout(null);
    this.setBackground(WIN_BGC);
    //设置窗口标题
    this.setTitle(WIN_TITLE);
    //设置是否可以调整窗口大小
    this.setResizable(false);
    //设置窗口的位置
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Point location = new Point((int)(screenSize.getWidth() - WIN_WIDTH)/2,(int)(screenSize.getHeight() - WIN_HEIGHT)/2);
    this.setLocation(location);
    //处理窗口关闭事件
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //组件初始化
    mainPanel = new MainPanel(this);
    mainPanel.setBounds(25,44,180,300);

    //往窗口中添加组件
    Container contentPane = this.getContentPane();
    contentPane.setLayout(null);
    //设置内容面板的大小和位置
    contentPane.setBounds(0,0,WIN_WIDTH,(WIN_HEIGHT+TITLE_BAR_HEIGHT));
    //设置窗口背景颜色
    contentPane.setBackground(WIN_BGC);

    contentPane.add(mainPanel);
  }

  public MainWindow(){
    //窗口初始化
    init();
    //设置窗口是否可见
    this.setVisible(true);
    new RepaintThread().run();
  }

  @Override
  public void update(Graphics g) {
    if(iBuffer==null)
    {
      iBuffer=createImage(this.getSize().width,this.getSize().height);
      gBuffer=iBuffer.getGraphics();
    }
    gBuffer.setColor(getBackground());
    gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
    paint(g);
    g.drawImage(iBuffer,0,0,this);
  }

  /**
   * 重写paint方法
   */
  @Override
  public void paint(Graphics src) {
    super.paint(src);

    Graphics2D graphics2D = (Graphics2D) src;
    Color oldColor = graphics2D.getColor();
    //画背景方块
    drawBackgroundBlock(graphics2D);

    //画字符串
    graphics2D.setColor(Color.pink);
    graphics2D.drawString(COPYRIGHT1,41,TITLE_BAR_HEIGHT+375);
    graphics2D.drawString(COPYRIGHT2,52,TITLE_BAR_HEIGHT+389);

    graphics2D.setColor(oldColor);
  }

  private void drawBackgroundBlock(Graphics2D graphics2D) {

    Color oldColor = graphics2D.getColor();
    graphics2D.setColor(new Color(108,201,218));

    final int bgcXStart1 = 18;
    final int bgcYStart1 = 14;
    int bgcX = bgcXStart1;
    int bgcY = bgcYStart1;

    for(int i=0;i<23;i++){
      graphics2D.fillRect(bgcX,bgcY+TITLE_BAR_HEIGHT,BGC_BLOCK_WIDTH,BGC_BLOCK_HEIGHT);
      bgcY = bgcYStart1 + (i+1)*(BGC_BLOCK_HEIGHT + 5);
    }

    final int bgcXStart2 = 208;
    final int bgcYStart2 = 14;
    int bgcX1 = bgcXStart2;
    int bgcY1 = bgcYStart2;

    for(int i=0;i<23;i++){
      graphics2D.fillRect(bgcX1,bgcY1+TITLE_BAR_HEIGHT,BGC_BLOCK_WIDTH,BGC_BLOCK_HEIGHT);
      bgcY1 = bgcYStart2 + (i+1)*(BGC_BLOCK_HEIGHT + 5);
    }

    final int bgcXStart3 = 33;
    final int bgcYStart3 = 344;
    int bgcX3 = bgcXStart3;
    int bgcY3 = bgcYStart3;

    for(int i=0;i<11;i++){
      graphics2D.fillRect(bgcX3,bgcY3+TITLE_BAR_HEIGHT,BGC_BLOCK_WIDTH,BGC_BLOCK_HEIGHT);
      bgcX3 = bgcXStart3 + (i+1)*(BGC_BLOCK_HEIGHT + 6);
    }

    graphics2D.setColor(oldColor);
  }

  private class  RepaintThread implements Runnable {

    public void run() {
      while(true){
        try{
          Thread.sleep(300);
          MainWindow.this.repaint();
        }catch (Exception e){
          e.printStackTrace();
        }
      }
    }
  }
}

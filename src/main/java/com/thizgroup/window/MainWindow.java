package com.thizgroup.window;

import com.thizgroup.block.Block;
import com.thizgroup.block.BlockType;
import com.thizgroup.block.FieldBlock;
import com.thizgroup.block.LeftBrokenLineBlock;
import com.thizgroup.block.LeftLBlock;
import com.thizgroup.block.MetaBlock;
import com.thizgroup.block.RightBrokenLineBlock;
import com.thizgroup.block.RightLBlock;
import com.thizgroup.block.TriangleBlock;
import com.thizgroup.block.VerticalLineBlock;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.springframework.util.ResourceUtils;

public class MainWindow extends JFrame {

//  界面大小：348*408像素
  public static final int WIN_WIDTH = 348;
  public static final int WIN_HEIGHT = 406;
  public static final int TITLE_BAR_HEIGHT = 26;

  //窗口标题
  public static final String WIN_TITLE = "俄罗斯方块-户雪敏专用版";
  public static final String COPYRIGHT1 = "made by gangquan.hu";
  public static final String COPYRIGHT2 = "xuemin.hu®版权所有";
  //窗口背景色
  public static final Color WIN_BGC = new Color(0,0,0);

  public static final int BGC_BLOCK_WIDTH = 10;
  public static final int BGC_BLOCK_HEIGHT = 10;

  private Block nextBlock = null;
  private int score = 0;
  private int level = 0;
  private Image nextImage = null;
  private Image scoreImage = null;
  private Image levelImage = null;


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
    //处理键盘事件
    this.addKeyListener(new KeyMonitor());

    //组件初始化
    mainPanel = new MainPanel(this);

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
    //初始化图片
    try {
      File nextFile = ResourceUtils.getFile("classpath:images/next.png");
      this.nextImage = ImageIO.read(nextFile);
      File scoreFile = ResourceUtils.getFile("classpath:images/score.png");
      this.scoreImage = ImageIO.read(scoreFile);
      File levelFile = ResourceUtils.getFile("classpath:images/level.png");
      this.levelImage = ImageIO.read(levelFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    //设置窗口是否可见
    this.setVisible(true);
    Timer timer = new Timer(); // 1. 创建Timer实例，关联线程不能是daemon(守护/后台)线程
    RepaintTimerTask repaintTimerTask = new RepaintTimerTask();
    timer.schedule(repaintTimerTask, 0L, 100L); // 3. 通过Timer定时定频率调用fooTimerTask的业务代码
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
    paint(gBuffer);
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

    //画右侧图片
    graphics2D.drawImage(nextImage,250,20+TITLE_BAR_HEIGHT,70,14,null);
    showNextBlock(graphics2D);
    graphics2D.drawImage(scoreImage,250,140+TITLE_BAR_HEIGHT,70,14,null);
    graphics2D.setColor(new Color(7,255,255));
    graphics2D.drawString(score+"",280,180+TITLE_BAR_HEIGHT);
    graphics2D.drawImage(levelImage,250,220+TITLE_BAR_HEIGHT,70,14,null);
    graphics2D.drawString(level+"",280,270+TITLE_BAR_HEIGHT);

    graphics2D.setColor(oldColor);
  }

  private void showNextBlock(Graphics2D graphics2D) {
    if(nextBlock != null){
      nextBlock.paint(graphics2D);
    }
  }

  public void plusScore(int score){
    this.score = this.score + score;
    //计算等级,满一百分升一级
    this.level = this.score/100;
  }

  public void updateNextBlock(Block block){
    if(block == null) {
      this.nextBlock = null;
      return;
    }
    Color color = block.getColor();
    BlockType blockType = block.getBlockType();

    /**
     * 生成方块
     */
    Block nextBlock = null;
    int x = 260;
    int y = 40+TITLE_BAR_HEIGHT;
    switch (blockType){
      case LEFT_BROKEN_LINE:
        nextBlock = new LeftBrokenLineBlock(this,x,y,color);
        break;
      case RIGHT_BROKEN_LINE:
        nextBlock = new RightBrokenLineBlock(this,x,y,color);
        break;
      case LEFT_L:
        nextBlock = new LeftLBlock(this,x,y,color);
        break;
      case RIGHT_L:
        nextBlock = new RightLBlock(this,x,y,color);
        break;
      case VERTICAL_LINE:
        nextBlock = new VerticalLineBlock(this,x,y,color);
        break;
      case TRIANGLE:
        nextBlock = new TriangleBlock(this,x,y,color);
        break;
      case FIELD:
        nextBlock = new FieldBlock(this,x,y,color);
        break;
    }

    this.nextBlock = nextBlock;
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

  public void resetScore() {
    this.score = 0;
    this.level = this.score/10;
  }

  private class RepaintTimerTask extends TimerTask {

    public void run() {
        MainWindow.this.repaint();
    }
  }

  public JPanel getMainPanel() {
    return mainPanel;
  }


  private class KeyMonitor extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
      //键盘按下
      ((MainPanel)MainWindow.this.mainPanel).keyPressed(e);
    }
  }

}

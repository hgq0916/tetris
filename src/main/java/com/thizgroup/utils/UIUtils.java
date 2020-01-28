package com.thizgroup.utils;

import java.awt.Color;
import java.util.Random;

/**
 * ui工具类
 */
public class UIUtils {

  private static final Random random = new Random();

  private static final Color[] generalColors = {
      Color.PINK,Color.CYAN,Color.MAGENTA,Color.orange,Color.GREEN
  };

  /**
   * 获取随机色
   * @return
   */
  public static Color getRandomColor(){
    return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
  }

  /**
   * 获取通用随机色
   * @return
   */
  public static Color getRandomGeneralColor(){
    return generalColors[random.nextInt(generalColors.length)];
  }

}

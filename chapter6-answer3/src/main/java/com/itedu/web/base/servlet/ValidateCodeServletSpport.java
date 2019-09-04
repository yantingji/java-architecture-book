/*
 *  Copyright (C) 2013 安泰尔科技有限公司 Corporation
 * All Rights Reserved.
 * 公司网址: www.antair.cn
 */
package com.itedu.web.base.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * 登录页面验证码Servlet具体处理逻辑类。
 * 
 * <pre>
 * 修改履历
 * -----------------------------------------------------------------------------
 *         VERSION       DATE            BY       CHANGE/COMMENT
 * -----------------------------------------------------------------------------
 *          1.0         2013/10/11      颜廷吉            create
 * -----------------------------------------------------------------------------
 * </pre>
 */
public class ValidateCodeServletSpport {
    /**
     * 输出流
     */
    private OutputStream outputStream;

    /**
     * 图像宽度
     */
    private static final int WIDTH = 160;

    /**
     * 图像高度
     */
    private static final int HEIGHT = 60;

    /**
     * 图像字符个数
     */
    private static final int CHAR_NUM = 4;

    /**
     * 图像内干扰线个数
     */
    private static final int DISTURB_LINE_NUM = 8;

    /**
     * 验证码范围
     */
    private final char[] chars = "2345678ABCDEFGHJKLMPRSTUVWXYabcdefhkmnqrstuvwx".toCharArray();

    /**
     * 字体名字
     */
    private static String[] fontName = new String[] { "Arial", "Courier", "Georgia", "Verdana", "Tahoma", "Times" };

    /**
     * 字体式样
     */
    private static int[] fontStyle = new int[] { Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC };

    /**
     * 原高度60%变换率
     */
    private static final double HEIGHT_RATE_60 = 0.6;

    /**
     * 原高度98%变换率
     */
    private static final double HEIGHT_RATE_98 = 0.98;

    /**
     * 原高度80%变换率
     */
    private static final double HEIGHT_RATE_80 = 0.80;

    /**
     * 干扰线颜色最大颜色数值
     */
    private static final int COLOR_200 = 200;

    /**
     * 图片底版最大颜色数值
     */
    private static final int COLOR_245 = 245;

    /**
     * 图片最大颜色数值
     */
    private static final int COLOR_255 = 255;

    /**
     * 随机数扩大倍数
     */
    private static final int RANDOM_TIMES_10000 = 10000;

    /**
     * 构造函数。
     * @param outputStream OutputStream
     */
    public ValidateCodeServletSpport(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * 画图像字符处理。
     * @throws IOException IOException
     * @return 图像字符
     */
    public String drawCode() throws IOException {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2d = bufferedImage.createGraphics();

        // 画底板
        drawBaseboard(graphics2d);
        // 画干扰线
        drawDisturbLine(graphics2d);
        // 画字母
        char[] codes = drawChars(graphics2d);
        // 释放资源
        graphics2d.dispose();
        // 输出
        ImageIO.write(bufferedImage, "gif", outputStream);

        return new String(codes);
    }

    /**
     * 画图底版处理。
     * @param graphics2d Graphics2D
     */
    private void drawBaseboard(Graphics2D graphics2d) {
        graphics2d.setColor(new Color(COLOR_245, COLOR_245, COLOR_245));
        graphics2d.fillRect(0, 0, WIDTH, HEIGHT);
    }

    /**
     * 画图图像验证码字符处理。
     * @param graphics2d Graphics2D
     * @return 图像验证码字符数组
     */
    private char[] drawChars(Graphics2D graphics2d) {
        BufferedImage[] bufImg = new BufferedImage[CHAR_NUM];
        char[] codes = generateRandomCode();
        for (int i = 0; i < CHAR_NUM; i++) {
            bufImg[i] = generateBuffImg(codes[i]);
            // +1不要出了左边图片底板位置
            graphics2d.drawImage(bufImg[i], null, (int) (HEIGHT * HEIGHT_RATE_60) * i + 1, 0);
        }
        return codes;
    }

    /**
     * 画图图像上干扰线处理。
     * @param graphics Graphics2D
     */
    private void drawDisturbLine(Graphics2D graphics) {
        for (int i = 0; i < DISTURB_LINE_NUM; i++) {
            graphics.setColor(generateRandomColor());
            int x = (int) (Math.random() * RANDOM_TIMES_10000) % (WIDTH + 1);
            int x1 = (int) (Math.random() * RANDOM_TIMES_10000) % (WIDTH + 1);
            int y = (int) (Math.random() * RANDOM_TIMES_10000) % (HEIGHT + 1);
            int y1 = (int) (Math.random() * RANDOM_TIMES_10000) % (HEIGHT + 1);
            graphics.drawLine(x, y, x1, y1);
        }

    }

    /**
     * 画图每个字符验证码处理。
     * @param c char
     * @return 字符验证码内存图像
     */
    private BufferedImage generateBuffImg(char c) {
        String imgStr = Character.toString(c);
        Color forecolor = generateRandomColor();
        String fontName = generateRandomFontName();
        int fontStyle = generateRandomStyle();
        int fontSize = generateRandomSize();
        int strX = (HEIGHT - fontSize) / 2;
        int strY = (HEIGHT - fontSize) / 2 + fontSize;

        double theta = generateRandomTheta();

        BufferedImage bufferedImage = new BufferedImage(HEIGHT, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setColor(new Color(COLOR_255, COLOR_255, COLOR_255, 0));
        graphics2D.fillRect(0, 0, HEIGHT, HEIGHT);

        graphics2D.setColor(forecolor);
        graphics2D.setFont(new Font(fontName, fontStyle, fontSize));
        graphics2D.rotate(theta, HEIGHT / 2, HEIGHT / 2);
        graphics2D.drawString(imgStr, strX, strY);

        graphics2D.dispose();
        return bufferedImage;
    }

    /**
     * 随机生成画图每个字符验证码旋转角度处理。
     * @return 字符验证码旋转角度
     */
    private double generateRandomTheta() {
        double randomTheta;
        int intRandom = (int) (Math.random() * RANDOM_TIMES_10000) % 2;
        if (intRandom == 0) {
            randomTheta = -1 * Math.random();
        } else {
            randomTheta = Math.random();
        }
        return randomTheta;
    }

    /**
     * 随机生成颜色处理。
     * @return 颜色
     */
    private Color generateRandomColor() {
        // 200颜色不要与底色245重合
        int r = (int) (Math.random() * RANDOM_TIMES_10000) % COLOR_200;
        int g = (int) (Math.random() * RANDOM_TIMES_10000) % COLOR_200;
        int b = (int) (Math.random() * RANDOM_TIMES_10000) % COLOR_200;
        return new Color(r, g, b);
    }

    /**
     * 随机生成字体名称处理。
     * @return 字体名称
     */
    private String generateRandomFontName() {
        int pos = (int) (Math.random() * RANDOM_TIMES_10000) % (fontName.length);
        return fontName[pos];
    }

    /**
     * 随机生成字体式样处理。
     * @return 字体式样
     */
    private int generateRandomStyle() {
        int pos = (int) (Math.random() * RANDOM_TIMES_10000) % (fontStyle.length);
        return fontStyle[pos];
    }

    /**
     * 随机生成图像大小处理。
     * @return 图像大小
     */
    private int generateRandomSize() {
        int max = (int) (HEIGHT * HEIGHT_RATE_98);
        int min = (int) (HEIGHT * HEIGHT_RATE_80);
        return (int) (Math.random() * RANDOM_TIMES_10000) % (max - min + 1) + min;
    }

    /**
     * 随机生成验证码字符处理。
     * @return 验证码字符数组
     */
    private char[] generateRandomCode() {
        char[] ret = new char[CHAR_NUM];
        for (int i = 0; i < CHAR_NUM; i++) {
            int letterPosition = (int) (Math.random() * RANDOM_TIMES_10000) % (chars.length);
            ret[i] = chars[letterPosition];
        }
        return ret;
    }
}

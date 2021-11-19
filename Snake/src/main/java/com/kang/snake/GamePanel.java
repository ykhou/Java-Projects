package com.kang.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import static java.awt.font.TextAttribute.FONT;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    // 蛇
    int length; // 蛇的长度
    int[] snakeX = new int[500]; // 蛇的坐标X
    int[] snakeY = new int[500]; // 蛇的坐标Y
    String fx; // R:右 , L:左 , U , D
    boolean isStart = false; // 游戏是否开始

    Timer timer = new Timer(100,this);

    // 食物
    int foodX;
    int foodY;
    Random random = new Random();

    // 死亡判断
    boolean isFail = false;

    // 积分
    int sorce = 0;

    // 构造器
    public GamePanel(){
        init();

        // 获取键盘的监听事件
        this.setFocusable(true);
        this.addKeyListener(this);

        // 定时器
        timer.start();
    }

    // 初始化
    public void init(){
        length = 3;
        snakeX[0] = 100;snakeY[0] = 100; // 头部坐标
        snakeX[1] = 75;snakeY[1] = 100; // 第一个身体坐标
        snakeX[2] = 50;snakeY[2] = 100; // 第二个身体坐标
        fx = "R";

        foodX = 25 + 25 * random.nextInt(18);
        foodY = 25 + 25 * random.nextInt(18);

        sorce = 0;
    }

    // 画板：界面、蛇
    // Graphics: 画笔
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 清屏
        this.setBackground(Color.WHITE); // 背景颜色

        // Data.header.paintIcon(this, g,20, 11); // 绘制头部的广告栏
        g.fillRect(0, 0, 500, 500); // 绘制游戏区域

        // 画一条静态小蛇
        if (fx.equals("R")) {
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        } else if (fx.equals("U")) {
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }

        for (int i=1; i<length; i++) {
            // 小蛇的身体长度通过length来控制
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        // 画一个食物
        Data.food.paintIcon(this,g,foodX,foodY);

        // 显示积分
        g.setColor(Color.white);
        g.setFont(new Font("宋体",Font.BOLD,15));
        g.drawString("长度:" + length,400,35);
        g.drawString("分数:" + sorce,400,55);
        // 游戏提示：是否开始
        if (isStart==false) {
            // 画一个文字
            g.setColor(Color.white);
            g.setFont(new Font("宋体",Font.BOLD,25));
            g.drawString("按下空格开始游戏",150,200);
        }
        // 游戏失败
        if (isFail) {
            g.setColor(Color.white);
            g.setFont(new Font("宋体",Font.BOLD,25));
            g.drawString("游戏失败，按下空格重新游戏",150,200);
        }
    }

    // 接收键盘输入:监听
    @Override
    public void keyPressed(KeyEvent e) {
        // 键盘按下，未释放
        // 获取按下的键盘是哪个键
        int keyCode = e.getKeyCode();

        if (keyCode==KeyEvent.VK_SPACE) {
            if (isFail) {
                isFail = false;
                init();
            } else {
                isStart = !isStart;
                repaint(); // 刷新界面

            }
        }

        // 键盘控制走向
        if (keyCode==KeyEvent.VK_LEFT && fx!="R") {
            fx = "L";
        } else if (keyCode==KeyEvent.VK_RIGHT && fx!="L") {
            fx = "R";
        } else if (keyCode==KeyEvent.VK_UP && fx!="D") {
            fx = "U";
        } else if (keyCode==KeyEvent.VK_DOWN && fx!="U") {
            fx = "D";
        }
    }

    // 定时器，监听时间，帧：执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        // 执行定时操作
        if (isStart && isFail==false) {
            // 移动
            for (int i=length-1; i>0; i--) {
                snakeY[i] = snakeY[i-1];
                snakeX[i] = snakeX[i-1];
            }
            // 通过控制方向让头部移动
            if (fx.equals("R")) {
                snakeX[0] += 25;
                // 边界判断
                if (snakeX[0] >= 500) {
                    snakeX[0] = 0;
                }
            }else if (fx.equals("L")) {
                snakeX[0] -= 25;
                if(snakeX[0] < 0) {
                    snakeX[0] = 475;
                }
            } else if (fx.equals("U")) {
                snakeY[0] -= 25;
                if (snakeY[0] < 0) {
                    snakeY[0] = 475;
                }
            } else if (fx.equals("D")) {
                snakeY[0] += 25;
                if (snakeY[0] >= 500) {
                    snakeY[0] = 0;
                }
            }

            // 如果小蛇的头和食物坐标重合
            if (snakeX[0]==foodX && snakeY[0]==foodY) {
                // 小蛇长度加1
                length ++;
                sorce ++;
                // 重新刷新食物
                foodX = 25 + 25 * random.nextInt(18);
                foodY = 25 + 25 * random.nextInt(18);
            }

            // 结束判断
            for (int i=1; i<length; i++) {
                if (snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]) {
                    isFail = true;
                }
            }

            // 刷新界面 必须
            repaint();
        }
        timer.start(); // 让时间动起来
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // 键盘按下，弹起
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // 按下某个键
    }
}

package com.kang.snake;

import javax.swing.*;

public class StartGames {
    public static void main(String[] args) {
        // 1.绘制一个静态窗口
        JFrame jFrame = new JFrame("贪吃蛇");
        jFrame.setBounds(0, 0, 512, 535); // 设置界面大小
        //jFrame.setBounds(0, 0, 600, 600);
        jFrame.setResizable(false); // 窗口不可改变
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭事件

        // 2.面板 JPanel 可以加入到Frame中
        jFrame.add(new GamePanel());


        jFrame.setVisible(true); // 窗口可见

    }
}

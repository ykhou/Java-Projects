package com.kang.calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 简易计算器
public class TestCalc {
    public static void main(String[] args) {
        new Calculator();
    }
}

// 计算器类
class Calculator extends Frame {
    public Calculator() {
        // 3个文本框
        TextField num1 = new TextField(10);
        TextField num2 = new TextField(10);
        TextField num3 = new TextField(20);
        // 1个按钮
        Button button = new Button("=");
        button.addActionListener(new MyCalculatorListener(num1, num2, num3)); // 加一个监听事件
        // 1个标签
        Label label = new Label("+");

        // 布局
        setLayout(new FlowLayout()); // 流式布局

        add(num1);
        add(label);
        add(num2);
        add(button);
        add(num3);

        pack(); // 自适应大小
        setVisible(true); // 设置可见性

    }
}

// 监听器类
class MyCalculatorListener implements ActionListener {
    // 获取三个变量
    private TextField num1, num2 , num3;

    public MyCalculatorListener(TextField num1, TextField num2, TextField num3) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 1.获得加数和被加数
        int n1 = Integer.parseInt(num1.getText()); // 传进来为string类型，要转为int类型
        int n2 = Integer.parseInt(num2.getText());

        // 2.将两个值相加后的值，放到第三个框
        num3.setText("" + (n1+n2));

        // 3.清除前两个框
        num1.setText("");
        num2.setText("");
    }
}

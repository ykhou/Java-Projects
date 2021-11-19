package com.kang.calculator2;
/*
    将计算器类和监听器类进行组合
*/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 简易计算器
public class TestCalc {
    public static void main(String[] args) {
        new Calculator().loadFrame();
    }
}

// 计算器类
class Calculator extends Frame {
    // 属性
    TextField num1, num2, num3;

    // 方法
    public void loadFrame() {
        // 3个文本框
        num1 = new TextField(10);
        num2 = new TextField(10);
        num3 = new TextField(20);
        // 1个按钮
        Button button = new Button("=");
        // 1个标签
        Label label = new Label("+");

        button.addActionListener(new MyCalculatorListener(this)); // 加一个监听事件

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
    Calculator calculator = null;

    public MyCalculatorListener(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 1.获得加数和被加数
        int n1 = Integer.parseInt(calculator.num1.getText());
        int n2 = Integer.parseInt(calculator.num2.getText());

        // 2.将两个值相加后的值，放到第三个框
        calculator.num3.setText("" + (n1+n2));

        // 3.清除前两个框
        calculator.num1.setText("");
        calculator.num2.setText("");
    }
}

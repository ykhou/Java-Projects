package com.kang.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    // 创建一个菜单
    static List<Dish> dishList = new ArrayList<>();
    // 静态代码块进行菜单初始化
    static{
        dishList.add(new Dish("1", "菜1", 30));
        dishList.add(new Dish("2", "菜2", 20));
        dishList.add(new Dish("3", "菜3", 10));
    }

    public static void main(String[] args) {
        // 创建客人
        Customer customer = new Customer(2,3,0);
        // 展示主菜单
        showMainMenu();
        // 开始点菜
        Scanner s = new Scanner(System.in);

        while(true) {
            String onnLevelComm = s.nextLine();

            Scanner s1 = new Scanner(System.in);

            if ("#".equals(onnLevelComm)) {
                // 返回一级菜单
                showMainMenu();
            }

            if ("1".equals(onnLevelComm)) {
                // 展示本店所有菜
                showMenu();
                while (true) {
                    String secondLevelComm = s1.nextLine();
                    if("*".equals(secondLevelComm)) {
                        System.out.println("点菜结束，输入#返回上一级菜单");
                        break;
                    }
                    Dish dish = getDish(secondLevelComm);
                    customer.getDishs().add(dish);
                    System.out.println("点菜"+dish.getDishName()+"成功");

                }
            }
            if ("2".equals(onnLevelComm)) {
                // 查看已点的菜
                System.out.println("查看已点,输入-加上菜号移除已点菜单");
                showCustomerMenu(customer);
                while (true) {
                    String secondLevelComm = s1.nextLine();
                    if("*".equals(secondLevelComm)) {
                        System.out.println("点菜结束，输入#返回上一级菜单");
                        break;
                    }
                    remove(customer,secondLevelComm);
                }



            }
            if ("3".equals(onnLevelComm)) {
                // 结账
                showAllPrice(customer);
            }
            if ("4".equals(onnLevelComm)) {
                // 退出
                System.out.println("退出");
                break;
            }
            if ("5".equals(onnLevelComm)) {
                // 帮助
                showHelp();
            }
            s1.close();
        }
        System.out.println("谢谢,再见");
        s.close();
        
    }

    /*
     * 1
     * 展示本店所有菜
     * */
    public static  void showMenu() {
        System.out.println("=======本店菜品=======");
        for (Dish dish : dishList) {
            System.out.println(dish.getDishNum()+"  "+dish.getDishName()+"  "+ dish.getPrice());
        }
        System.out.println("=====================");
    }
    /**
     * 
     * @param secondLevelComm
     * @return
     */
    public static Dish getDish(String secondLevelComm) {
        Dish targetDish = null;
        if (secondLevelComm != null && !"".equals(secondLevelComm)) {
            for (Dish dish : dishList) {
                if (secondLevelComm.equals(dish.getDishNum())) {
                    // 克隆一个对象
                    try {
                        targetDish = (Dish)dish.clone();
                        break;
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return targetDish;
    }

    /*
    * 2
    * 展示所点的菜
    * */
    public static void showCustomerMenu(Customer customer) {
        System.out.println("======="+customer.getDeskNum()+"=======");
        for (Dish dish : customer.getDishs()) {
            System.out.println(dish.getDishNum()+" "+dish.getDishName()+" "+dish.getPrice());
        }
        System.out.println("=========================");
    }

    /*
    * 3
    * 移除已点的菜
    * */
    public static void remove(Customer customer, String secondLevelComm) {
        String dishNum = secondLevelComm.substring(1);
        List<Dish> dishes = customer.getDishs();
        for (int i = 0; i < dishes.size(); i++) {
            Dish dish = dishes.get(i);
            if (dish.getDishNum().equals(dishNum)) {
                dishes.remove(i);
                System.out.println(dish.getDishName()+"已从菜单中移除");
            }
        }
        System.out.println("使用*号结束删除");
    }

    /*
    * 4
    * 计算总的消费金额
    * */
    public static void showAllPrice(Customer customer) {
        int AllPrice = 0;
        for (Dish dish : customer.getDishs()) {
            AllPrice += dish.getPrice();
        }
        System.out.println(AllPrice);
        System.out.println("使用#返回上一级菜单");
    }

    /*
    * 5
    * 显示帮助信息
    * */
    public static void showHelp() {
        System.out.println("=====帮助信息如下=====");
    }

    /*
    * #
    * 显示主菜单
    * */
    public static void showMainMenu() {
        System.out.println("+------主菜单------+");
        System.out.println("|  点菜       1    |");
        System.out.println("|  查看已点    2    |");
        System.out.println("|  结账       3    |");
        System.out.println("|  退出       4    |");
        System.out.println("|  帮助       5    |");
        System.out.println("+-----------------+");
    }
}

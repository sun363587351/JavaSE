package gui;

import java.awt.*;

import java.awt.event.*;

/**
 * Created with GUI.
 * User: IFT8
 * Date: 2014/10/5 21:06
 */
public class FrameDemo {

    public static void main(String[] args) {
        Frame frame = new Frame();
        //设置位置以及大小
        frame.setBounds(500, 300, 200, 200);
        //添加关闭事件
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //设置布局
        frame.setLayout(new FlowLayout());
        //添加按钮
        Button btn = new Button("Button");
        frame.add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button down");
            }
        });
        //双击事件
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (2 == e.getClickCount())
                    System.out.println("double");
            }
        });

        //菜单栏
        MenuBar menuBar = new MenuBar();
        //父菜单
        Menu fileMenu = new Menu("File");
        //子菜单
        Menu subMenu = new Menu("subMenu");
        //菜单项
        MenuItem item = new MenuItem("item");
        MenuItem subItem = new MenuItem("subItem");
        menuBar.add(fileMenu);
        fileMenu.add(subMenu);
        fileMenu.add(item);
        subMenu.add(subItem);
        //设置菜单栏
        frame.setMenuBar(menuBar);
        //显示
        frame.setVisible(true);
    }
}


package demoTest.demo.timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class TimerTest {

    public static void main(String[] args) {
        var listener = new TimerPrinter();
        var timer = new Timer(1000, listener);
        timer.start();
        JOptionPane.showConfirmDialog(null, "退出？");
        System.exit(0);
    }

}

class TimerPrinter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("时间：" + Instant.ofEpochMilli(actionEvent.getWhen()));
        Toolkit.getDefaultToolkit().beep();
    }
}
package SimpleSwingProgram;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HARU {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Frame");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ×ボタン無効化
        frame.setLayout(null);
        
        JButton button2 = new JButton("×");
        button2.setBounds(380, 380, 380, 380);

        JButton button1 = new JButton(" ");
        button1.setBounds(0, 0, 700, 700);
        
       
        
        button1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "登録されました");
            }
        });
        
        button2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "クリア");
                e.consume(); // イベントの伝播を停止
            }
        });
        
        frame.add(button1);
        frame.add(button2);
        frame.setVisible(true);
    }
}

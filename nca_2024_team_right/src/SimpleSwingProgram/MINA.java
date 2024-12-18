package SimpleSwingProgram;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MINA extends JFrame{
  public static void main(){
    MINA frame = new MINA("MyTitle");
    frame.setVisible(true);
  }

  MINA(String title){
    setTitle(title);
    setBounds(100, 100, 728, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ImageIcon icon1 = new ImageIcon("チーム制作作品1.png");
    ImageIcon icon2 = new ImageIcon("広告.png");
    ImageIcon icon3 = new ImageIcon("広告　モンゴル.png");
    ImageIcon icon4 = new ImageIcon("A.png");

    JLabel label1 = new JLabel(icon1);
    JLabel label2 = new JLabel(icon2);
    JLabel label3 = new JLabel(icon3);
    JLabel label4 = new JLabel(icon4);
    
    JPanel p = new JPanel();
    p.add(label1);
    
    JPanel q = new JPanel();
    q.add(label2);
   
    JPanel a = new JPanel();
    a.add(label2);
    
    JPanel r = new JPanel();
    r.add(label2);
    
    
    add(p, BorderLayout.CENTER);
  }
}
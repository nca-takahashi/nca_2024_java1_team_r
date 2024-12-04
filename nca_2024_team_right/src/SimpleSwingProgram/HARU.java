package SimpleSwingProgram;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class HARU {
	public void no11() {
		JFrame frame = new JFrame("問題11");

		JButton button = new JButton("Click me");
		
		button.addActionListener(e -> {
			
		});

		JLabel label = new JLabel("Unchecked", SwingConstants.CENTER);

		frame.setLayout(new BorderLayout());
		
		frame.add(button, BorderLayout.CENTER);
		
		frame.add(label, BorderLayout.SOUTH);

		frame.setSize(300, 200);
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		frame.setVisible(true);
	}
}

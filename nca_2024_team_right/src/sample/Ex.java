package sample;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * ExクラスはGUIコンポーネントを使用して様々なレイアウトを実装するクラスです。
 */
public class Ex {

	/**
	 * no00メソッドはアプリケーションを終了するためのウィンドウを表示します。
	 */
	public void no00() {
		// 新しいJFrameを作成
		JFrame frame = new JFrame("問題00: アプリ終了");
		// フレームのサイズを設定 (幅: 300, 高さ: 100)
		frame.setSize(300, 100);
		// フレームの閉じる操作を設定 (JFrame.EXIT_ON_CLOSE)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ラベルを作成
		JLabel label = new JLabel("このウィンドウの[X]で全てを終了します");
		// ラベルをフレームに追加
		frame.add(label);
		// フレームを表示
		frame.setVisible(true);
	}

	// 問題1: ボタンをクリックすると「Hello, World!」と表示する。
	public void no01() {
		// 新しいJFrameを作成し、タイトルを設定
		JFrame frame = new JFrame("問題1");
		// "Click me"というテキストを持つボタンを作成
		JButton button = new JButton("Click me");
		// 空のラベルを作成し、中央揃えに設定
		JLabel label = new JLabel(" ", SwingConstants.CENTER);

		// ボタンがクリックされたときにラベルに"Hello, World!"を表示するアクションを追加
		button.addActionListener(e -> label.setText("Hello, World!"));

		// フレームのレイアウトをBorderLayoutに設定
		frame.setLayout(new BorderLayout());
		// ボタンをフレームの中央に追加
		frame.add(button, BorderLayout.CENTER);
		// ラベルをフレームの下部に追加
		frame.add(label, BorderLayout.SOUTH);

		// フレームのサイズを設定
		frame.setSize(300, 200);
		// フレームの閉じる操作を設定
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// フレームを表示
		frame.setVisible(true);
	}

	// moveImageメソッドは画像を表示し、マウスでドラッグして動かすことができます。
	public void moveImage() {
		// 新しいJFrameを作成し、タイトルを設定
		JFrame frame = new JFrame("画像を動かす");
		// フレームのサイズを設定
		frame.setSize(500, 500);
		// フレームの閉じる操作を設定
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 画像を読み込む
		ImageIcon imageIcon = new ImageIcon("C://temp/test.png");
		JLabel imageLabel = new JLabel(imageIcon);

		// 画像の初期位置を設定
		imageLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());

		// フレームにレイアウトマネージャを設定しない（絶対位置を使用）
		frame.setLayout(null);
		frame.add(imageLabel);

		// マウスリスナーを追加してドラッグ操作を処理
		final Point[] initialClick = { null };

		imageLabel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				initialClick[0] = e.getPoint();
				imageLabel.getComponentAt(initialClick[0]);
			}
		});

		imageLabel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				// 現在のマウスの位置を取得
				int thisX = imageLabel.getLocation().x;
				int thisY = imageLabel.getLocation().y;

				// マウスの移動距離を計算
				if (initialClick[0] != null) {
					int xMoved = e.getX() - initialClick[0].x;
					int yMoved = e.getY() - initialClick[0].y;

					// 新しい位置を設定
					int X = thisX + xMoved;
					int Y = thisY + yMoved;
					imageLabel.setLocation(X, Y);
				}
			}
		});

		// フレームを表示
		frame.setVisible(true);
	}

}

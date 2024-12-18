package SimpleSwingProgram;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TAISEI extends JFrame {
    // スコア表示用ラベル
    private JLabel scoreLabel;
    // 残り時間表示用ラベル
    private JLabel timerLabel;
    // ゲーム内のスコア
    private int score = 0;
    // 目標（的）の位置を管理するためのPoint
    private Point targetPosition;
    // 目標の最初の円の直径
    private int targetDiameter = 100;
    // ゲームオーバー状態を管理するフラグ
    private boolean gameOver = false;
    // ゲームの残り時間
    private int remainingTime = 10;
    // タイマー
    private Timer gameTimer;

    // ゲームパネル（ゲーム画面の描画）
    private JPanel gamePanel;

    // スタートボタン
    private JButton startButton;

    // コンストラクタ（ゲームの初期設定）
    public TAISEI() {
        // ゲームウィンドウの設定
        setTitle("Target game"); // ゲームのタイトル
        setSize(900, 800); // ゲーム画面のサイズ（幅900px, 高さ800px）
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ウィンドウを閉じたときにこのゲームを終了
        setLayout(null); // レイアウトを無効にして、手動で配置

        // スコア表示用ラベルの作成
        scoreLabel = new JLabel("score 0", JLabel.LEFT);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // フォント設定
        scoreLabel.setBounds(10, 10, 200, 30); // ラベルの位置とサイズを設定（左上から10px, 200px幅, 30px高さ）
        add(scoreLabel); // ラベルをフレームに追加

        // 残り時間表示用ラベルの作成
        timerLabel = new JLabel("time limit 10", JLabel.RIGHT);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // フォント設定
        timerLabel.setBounds(getWidth() - 230, 10, 200, 30); // 右上に表示（画面幅から少し余裕を持たせる）
        add(timerLabel); // ラベルをフレームに追加

        // ゲーム画面のパネル
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // 基本的な描画処理を呼び出し

                if (gameOver) {
                    g.setColor(Color.BLACK); // 文字の色を黒に設定
                    g.setFont(new Font("Arial", Font.PLAIN, 30)); // フォント設定
                    g.drawString("game over", 350, 280); // ゲームオーバーのメッセージ
                    g.drawString("your score " + score, 335, 330); // スコア表示
                    return; // ゲームオーバーの場合は描画を終了
                }

                // 背景を白で塗りつぶす
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight()); // 背景を塗りつぶす

                // ターゲットの描画
                if (targetPosition != null) {
                    g.setColor(Color.RED);
                    g.fillOval(targetPosition.x, targetPosition.y, targetDiameter, targetDiameter); // 円を描画
                }
            }
        };
        gamePanel.setBounds(0, 0, getWidth(), getHeight()); // パネルのサイズを設定
        add(gamePanel); // パネルをフレームに追加

        // スタートボタンの作成
        startButton = new JButton("game start");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24)); // フォント設定
        startButton.setBounds(getWidth() / 2 - 100, getHeight() / 2 - 50, 200, 50); // 画面中央に配置
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(); // ゲーム開始
            }
        });
        add(startButton); // ボタンをフレームに追加

        // ゲームタイマーの設定（1秒ごとに残り時間を減らす）
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    remainingTime--; // 残り時間を1秒減らす
                    timerLabel.setText("time limit " + remainingTime); // タイマーラベルを更新

                    if (remainingTime <= 0) {
                        gameOver = true; // 残り時間が0になったらゲームオーバー
                        repaint(); // 画面を再描画
                    }
                }
            }
        });

        // クリックイベントのリスナーを追加
        addMouseListener(new MouseAdapter() {
            // マウスがクリックされたときに呼ばれる
            public void mousePressed(MouseEvent e) {
                // ゲームが終了していないかつ、クリック位置がターゲット内の場合
                if (!gameOver && targetPosition != null && isClickInsideTarget(e.getPoint())) {
                    score++; // スコアを1増加
                    // 円のサイズを10px小さくする（最小20pxまで）
                    targetDiameter = Math.max(20, targetDiameter - 5);
                    spawnTarget(); // 新しいターゲットを表示
                    scoreLabel.setText("score " + score); // スコアラベルを更新
                    repaint(); // 画面を再描画（ターゲットを更新）
                }
            }
        });

        // 画面を中央に表示
        setLocationRelativeTo(null);
        setVisible(true); // ゲーム画面を表示
    }

    // 目標をランダムな位置に表示するメソッド
    private void spawnTarget() {
        // 画面内でターゲットの位置をランダムに設定
        int x = (int) (Math.random() * (getWidth() - targetDiameter)); // 画面幅内でターゲットのx位置
        int y = (int) (Math.random() * (getHeight() - targetDiameter)); // 画面高さ内でターゲットのy位置
        targetPosition = new Point(x, y); // 目標の位置を設定
        repaint(); // 目標の位置を更新し、再描画
    }

    private boolean isClickInsideTarget(Point clickPoint) {
        // 目標の中心座標
        int centerX = targetPosition.x + targetDiameter / 2;  // ターゲットの中心X座標
        int centerY = targetPosition.y + targetDiameter / 2;  // ターゲットの中心Y座標
        
        // 目標の半径（直径の半分）
        int radius = targetDiameter / 2;
        
        // クリック位置とターゲット中心との距離を計算
        double distance = Math.sqrt(Math.pow(clickPoint.x - centerX, 2) + Math.pow(clickPoint.y - centerY, 2));
        
        // 距離が半径以下ならターゲット内
        return distance <= radius; 
    }

    // ゲーム開始時の処理
    private void startGame() {
        score = 0; // ゲーム開始時にスコアを0にリセット
        remainingTime = 10; // ゲーム開始時に残り時間を10秒に設定
        gameOver = false; // ゲームオーバーフラグをfalseに設定
        targetDiameter = 60; // ターゲットの最初の直径を100に設定
        spawnTarget(); // 最初のターゲットを生成
        gameTimer.start(); // ゲームタイマーをスタート
        startButton.setVisible(false); // スタートボタンを非表示にする
    }

    // メインメソッド（アプリケーションのエントリーポイント）
    public static void main(String[] args) {
        // ゲームを開始
        new TAISEI(); // TAISEIクラスのインスタンスを作成してゲームを開始
    }
}


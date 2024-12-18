package SimpleSwingProgram;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;



public class TAISEI extends JFrame {
    // スコア表示用ラベル
    private JLabel scoreLabel; 
    // ゲーム内のスコア
    private int score = 0; 
    // 目標（的）の位置を管理するためのPoint
    private Point targetPosition; 
    // 目標の最初の円の直径
    private int targetDiameter = 100; 
    // ゲームタイマー（10秒）を管理するためのタイマー
    private Timer gameTimer; 
    // ゲームオーバー状態を管理するフラグ
    private boolean gameOver = false; 

    // コンストラクタ（ゲームの初期設定）
    public TAISEI() {
        // ゲームウィンドウの設定
        setTitle("的をクリックゲーム"); // ゲームのタイトル
        setSize(900, 800); // ゲーム画面のサイズ（幅900px, 高さ800px）
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ウィンドウを閉じたときにこのゲームを終了
        setLayout(null); // レイアウトを無効にして、手動で配置

        // スコア表示用ラベルの作成
        scoreLabel = new JLabel("スコア: 0", JLabel.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // フォント設定
        scoreLabel.setBounds(10, 10, 200, 30); // ラベルの位置とサイズを設定（左上から10px, 200px幅, 30px高さ）
        add(scoreLabel); // ラベルをフレームに追加

        // 最初のターゲット（的）を生成
        spawnTarget();

        // クリックイベントのリスナーを追加
        addMouseListener(new java.awt.event.MouseAdapter() {
            // マウスがクリックされたときに呼ばれる
            public void mousePressed(java.awt.event.MouseEvent e) {
                // ゲームが終了していないかつ、クリック位置がターゲット内の場合
                if (!gameOver && isClickInsideTarget(e.getPoint())) {
                    score++; // スコアを1増加
                    // 円のサイズを10px小さくする（最小20pxまで）
                    targetDiameter = Math.max(5, targetDiameter - 10); 
                    spawnTarget(); // 新しいターゲットを表示
                    repaint(); // 画面を再描画（ターゲットを更新）
                }
            }
        });

        // ゲーム開始処理
        startGame();

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

    // ゲームの再描画処理
    @Override
    public void paint(Graphics g) {
        super.paint(g); // JFrameのpaintメソッドを呼び出して、基本的な描画処理を行う

        if (gameOver) {
            return; // ゲームオーバーの場合は再描画をスキップ
        }

        // ターゲットが設定されている場合（ターゲットの位置がnullでない場合）
        if (targetPosition != null) {
            // 赤い色でターゲットの円を描画
            g.setColor(Color.RED);
            g.fillOval(targetPosition.x, targetPosition.y, targetDiameter, targetDiameter); // 円を描画
        }

        // スコアの表示
        g.setColor(Color.BLACK); // スコア表示の色を黒に設定
        g.setFont(new Font("Arial", Font.PLAIN, 24)); // スコア表示のフォント設定
        g.drawString("スコア: " + score, 10, 50); // スコアを画面に描画
    }

    // クリックされた位置がターゲット内かどうかを判定するメソッド
    private boolean isClickInsideTarget(Point clickPoint) {
        // 目標の中心座標
        int centerX = targetPosition.x + targetDiameter / 2;
        int centerY = targetPosition.y + targetDiameter / 2;
        // 目標の半径（直径の半分）
        int radius = targetDiameter / 2;

        // クリック位置とターゲット中心との距離が半径以下なら、ターゲット内
        return Math.pow(clickPoint.x - centerX, 2) + Math.pow(clickPoint.y - centerY, 2) <= Math.pow(radius, 2);
    }

    // ゲーム開始時の処理
    private void startGame() {
        score = 0; // ゲーム開始時にスコアを0にリセット
        gameOver = false; // ゲームオーバーフラグをfalseに設定
        targetDiameter = 100; // ターゲットの最初の直径を100に設定
    }

    // メインメソッド（アプリケーションのエントリーポイント）
    public static void main(String[] args) {
        // ゲームを開始
        new TAISEI(); // TAISEIクラスのインスタンスを作成してゲームを開始
    }
}

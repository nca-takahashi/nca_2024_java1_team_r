package SimpleSwingProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class YUGA {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleMazeGame game = new SimpleMazeGame();
            game.setVisible(true);
        });
    }

    public static class SimpleMazeGame extends JFrame {
        private static final int CELL_SIZE = 40; // セルのサイズ
        private final int[][][] stages = { // ステージごとの迷路データ
                {
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                        {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
                        {1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 1, 0, 0, 1, 1, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1, 0, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 1, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                },
                {
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
                        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                        {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                },
                {
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
                        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                        {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                        {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                },
                {
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1},
                        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                        {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1},
                        {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                        {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                }
        };
        private final boolean reversedControls = false; // 操作反転フラグ
        private final ArrayList<Point> traps = new ArrayList<>(); // トラップ位置
        private final ArrayList<Point> items = new ArrayList<>(); // アイテム位置
        private int ROWS; // 行数（ステージごとに変化）
        private int COLS; // 列数（ステージごとに変化）
        private int playerRow = 1; // プレイヤーの初期行位置
        private int playerCol = 1; // プレイヤーの初期列位置
        private int itemsCollected = 0; // 収集済みアイテム数
        private int[][] maze; // 現在の迷路
        private int currentStage = 0; // 現在のステージ

        public SimpleMazeGame() {
            setTitle("Simple Maze Game");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(false);
            loadStage(currentStage); // 最初のステージを読み込む

            addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();
                    int moveRow = 0, moveCol = 0;

                    if (key == KeyEvent.VK_UP) {
                        moveRow = reversedControls ? 1 : -1;
                    } else if (key == KeyEvent.VK_DOWN) {
                        moveRow = reversedControls ? -1 : 1;
                    } else if (key == KeyEvent.VK_LEFT) {
                        moveCol = reversedControls ? 1 : -1;
                    } else if (key == KeyEvent.VK_RIGHT) {
                        moveCol = reversedControls ? -1 : 1;
                    }

                    // 新しい位置を計算
                    int newRow = playerRow + moveRow;
                    int newCol = playerCol + moveCol;

                    // 新しい位置が壁でないか確認
                    if (maze[newRow][newCol] == 0) {
                        playerRow = newRow;
                        playerCol = newCol;
                        checkTrapCollision(); // トラップとの衝突判定
                        checkItemCollection(); // アイテム収集判定
                    }
                    repaint();
                    checkWinCondition(); // ステージクリア判定
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }

        private void loadStage(int stageIndex) {
            maze = stages[stageIndex];
            ROWS = maze.length;
            COLS = maze[0].length;
            playerRow = 1;
            playerCol = 1;
            itemsCollected = 0;
            traps.clear();
            items.clear();

            // ステージごとのトラップとアイテムを設定
            if (stageIndex == 0) {
                traps.add(new Point(4, 4));
                traps.add(new Point(2, 7)); // 追加したトラップ
                addItemToMaze(); // アイテムを追加
            } else if (stageIndex == 1) {
                traps.add(new Point(2, 5));
                traps.add(new Point(5, 3)); // 追加したトラップ
                addItemToMaze(); // アイテムを追加
            } else if (stageIndex == 2) {
                traps.add(new Point(4, 6));
                traps.add(new Point(1, 9)); // 追加したトラップ
                addItemToMaze(); // アイテムを追加
            } else if (stageIndex == 3) {
                traps.add(new Point(5, 4));
                traps.add(new Point(8, 2)); // 追加したトラップ
                addItemToMaze(); // アイテムを追加
            }

            // ゴール位置を設定（ステージごとに異なる位置）
            if (stageIndex == 0) {
                items.add(new Point(ROWS - 2, COLS - 2)); // ゴールをアイテムとして設定
            } else if (stageIndex == 1) {
                items.add(new Point(ROWS - 2, COLS - 3)); // ゴールをアイテムとして設定
            } else if (stageIndex == 2) {
                items.add(new Point(ROWS - 2, COLS - 4)); // ゴールをアイテムとして設定
            } else if (stageIndex == 3) {
                items.add(new Point(ROWS - 2, COLS - 5)); // ゴールをアイテムとして設定
            }

            // ウィンドウサイズを迷路に合わせる
            setSize(COLS * CELL_SIZE + getInsets().left + getInsets().right,
                    ROWS * CELL_SIZE + getInsets().top + getInsets().bottom);
        }

        private void addItemToMaze() {
            Random random = new Random();
            int itemRow, itemCol;

            // アイテムをランダムに配置
            do {
                itemRow = random.nextInt(ROWS);
                itemCol = random.nextInt(COLS);
            } while (maze[itemRow][itemCol] == 1 || (itemRow == playerRow && itemCol == playerCol)); // アイテムが壁かプレイヤーと重ならないようにする

            items.add(new Point(itemRow, itemCol));
        }

        private void checkTrapCollision() {
            for (Point trap : traps) {
                if (playerRow == trap.x && playerCol == trap.y) {
                    JOptionPane.showMessageDialog(this, "トラップに当たりました！最初からやり直しです。");
                    loadStage(currentStage); // トラップに当たったら現在のステージの最初からやり直し
                    repaint();
                    return; // それ以降の処理をしない
                }
            }
        }

        private void checkItemCollection() {
            items.removeIf(item -> {
                if (playerRow == item.x && playerCol == item.y) {
                    itemsCollected++;
                    return true;
                }
                return false;
            });
        }

        private void checkWinCondition() {
            if (playerRow == ROWS - 2 && playerCol == COLS - 2) { // ゴール地点に到達
                if (!items.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "アイテムを全て集めてからゴールしてください！");
                } else {
                    currentStage++; // 次のステージへ進む
                    if (currentStage >= stages.length) {
                        JOptionPane.showMessageDialog(this, "全ステージクリアおめでとうございます！");
                        dispose(); // ゲーム終了
                    } else {
                        loadStage(currentStage); // 次のステージを読み込む
                        JOptionPane.showMessageDialog(this, "次のステージへ！");
                    }
                }
            }
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            // 迷路の描画
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (maze[i][j] == 1) { // 壁
                        g.setColor(Color.BLACK);
                    } else {
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    g.setColor(Color.GRAY);
                    g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }

            // プレイヤーの描画
            g.setColor(Color.BLUE);
            g.fillRect(playerCol * CELL_SIZE, playerRow * CELL_SIZE, CELL_SIZE, CELL_SIZE);

            // トラップの描画
            g.setColor(Color.RED);
            for (Point trap : traps) {
                g.fillRect(trap.y * CELL_SIZE, trap.x * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }

            // アイテムの描画
            g.setColor(Color.GREEN);
            for (Point item : items) {
                g.fillOval(item.y * CELL_SIZE, item.x * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }

            // ゴールの描画
            g.setColor(Color.YELLOW);
            g.fillRect((COLS - 2) * CELL_SIZE, (ROWS - 2) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }
}


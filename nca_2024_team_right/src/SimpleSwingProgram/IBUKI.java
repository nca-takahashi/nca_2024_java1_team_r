package SimpleSwingProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class IBUKI extends JFrame {
    private int currentQuestionIndex = 0;
    private int score = 0;

    private final String[] questions = {
            "1. 関ヶ原の戦いが起きたのはいつ？",
            "2. 明治維新はいつ起きた？",
            "3. この中で同じ種類じゃない動物は？",
            "4. 億、兆、京、垓の次の単位は？",
            "5. ブラジルの首都は？",
            "6. 宮城県は何の収穫量が日本一？",
            "7. コンピューターで人間の脳を再現したものは何？"
    };

    private final String[][] options = {
            {"a) 1603年", "b) 1600年", "c) 1590年","d)1560年"},
            {"a) 1850年", "b) 1859年", "c) 1868年", "d) 1889年"},
            {"a) 牛", "b) ヒツジ", "c) 熊", "d) 鯨"},
            {"a) 正", "b) 溝", "c) 不可思議", "d) 秭"},
            {"a) ブラジリア", "b) サンパウロ", "c) リオデジャネイロ","d)ブエノスアイレス"},
            {"a) 米", "b) リンゴ", "c) パプリカ", "d) キャベツ"},
            {"a) AI", "b) 機械学習", "c) ニューラルネットワーク","d)コンピューティング"}
    };

    private final int[] correctAnswers = {1, 2, 2, 3, 0, 2, 2}; // 各質問の正解インデックス

    private JLabel questionLabel;
    private JButton[] optionButtons;

    public IBUKI() {
        // ウィンドウ設定
        setTitle("クイズゲーム");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // ウィンドウを画面中央に配置
        setLayout(new BorderLayout());

        // 質問表示ラベル
        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("MS Gothic", Font.BOLD, 18)); // 日本語に対応したフォント
        add(questionLabel, BorderLayout.NORTH);

        // 選択肢のボタン
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        optionButtons = new JButton[4];
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JButton();
            buttonPanel.add(optionButtons[i]);
            int index = i; // ボタンのインデックスを保持
            optionButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkAnswer(index);
                }
            });
        }
        add(buttonPanel, BorderLayout.CENTER);

        // 初期化
        loadQuestion();

        setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText(questions[currentQuestionIndex]);
            for (int i = 0; i < optionButtons.length; i++) {
                if (i < options[currentQuestionIndex].length) {
                    optionButtons[i].setText(options[currentQuestionIndex][i]);
                    optionButtons[i].setEnabled(true); // ボタンを有効化
                } else {
                    optionButtons[i].setText("");
                    optionButtons[i].setEnabled(false); // 不要なボタンを無効化
                }
            }
        } else {
            endQuiz();
        }
    }

    private void checkAnswer(int selectedIndex) {
        if (selectedIndex == correctAnswers[currentQuestionIndex]) {
            score++;
            JOptionPane.showMessageDialog(this, "正解です！");
        } else {
            JOptionPane.showMessageDialog(this, "不正解です！正解は " +
                    options[currentQuestionIndex][correctAnswers[currentQuestionIndex]]);
        }
        currentQuestionIndex++;
        loadQuestion();
    }

    private void endQuiz() {
        // 終了メッセージを表示
        JOptionPane.showMessageDialog(this, "クイズ終了！スコア: " + questions.length + "点中" + score +"点");
        System.exit(0);
    }

    public static void main(String[] args) {
        // GUIを安全に初期化
        SwingUtilities.invokeLater(() -> new IBUKI());
    }
}

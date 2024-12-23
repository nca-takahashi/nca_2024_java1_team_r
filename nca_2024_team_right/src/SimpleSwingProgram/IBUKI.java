package SimpleSwingProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class IBUKI extends JFrame {
    private final String[] questions = {
            "1. 関ヶ原の戦いが起きたのはいつ？",
            "2. 明治維新はいつ起きた？",
            "3. この中で同じ種類じゃない動物は？",
            "4. 億、兆、京、垓の次の単位は？",
            "5. ブラジルの首都は？",
            "6. 宮城県は何の収穫量が日本一？",
            "7. コンピューターで人間の脳を再現したものは何？",
            "8. Javaの創始者は誰？",
            "9. Pythonの開発者は誰？",
            "10. AIのフルフォームは何？",
            "11. 機械学習の一種であるニューラルネットワークの基本単位は？",
            "12. Gitの開発者は誰？",
            "13. HTMLのフルフォームは何？",
            "14. CSSのフルフォームは何？",
            "15. JavaScriptの発明者は誰？",
            "16. 日本の首都は？",
            "17. 世界で最も高い山は？",
            "18. 最も長い川は？",
            "19. 最も大きい砂漠は？",
            "20. 最も深い海溝は？"
    };
    private final String[][] options = {
            {"a) 1603年", "b) 1600年", "c) 1590年", "d)1560年"},
            {"a) 1850年", "b) 1859年", "c) 1868年", "d) 1889年"},
            {"a) 牛", "b) ヒツジ", "c) 熊", "d) 鯨"},
            {"a) 正", "b) 溝", "c) 不可思議", "d) 秭"},
            {"a) ブラジリア", "b) サンパウロ", "c) リオデジャネイロ", "d)ブエノスアイレス"},
            {"a) 米", "b) リンゴ", "c) パプリカ", "d) キャベツ"},
            {"a) AI", "b) 機械学習", "c) ニューラルネットワーク", "d)コンピューティング"},
            {"a) ジェームズ・ゴスリン", "b) ビル・ゲイツ", "c) グイド・ヴァンロッサム", "d) ラリー・ペイジ"},
            {"a) ジェームズ・ゴスリン", "b) ビル・ゲイツ", "c) グイド・ヴァンロッサム", "d) ラリー・ペイジ"},
            {"a) Artificial Intelligence", "b) Automated Intelligence", "c) Advanced Intelligence", "d) Autonomous Intelligence"},
            {"a) ニューロン", "b) シナプス", "c) ノード", "d) レイヤー"},
            {"a) リーナス・トーバルズ", "b) スティーブ・ジョブズ", "c) マーク・ザッカーバーグ", "d) ラリー・ペイジ"},
            {"a) HyperText Markup Language", "b) HyperText Markdown Language", "c) HyperTool Markup Language", "d) HyperText Machine Language"},
            {"a) Cascading Style Sheets", "b) Creative Style Sheets", "c) Computer Style Sheets", "d) Colorful Style Sheets"},
            {"a) Brendan Eich", "b) Tim Berners-Lee", "c) James Gosling", "d) Guido van Rossum"},
            {"a) 大阪", "b) 京都", "c) 東京", "d) 名古屋"},
            {"a) エベレスト", "b) キリマンジャロ", "c) アコンカグア", "d) デナリ"},
            {"a) ナイル川", "b) アマゾン川", "c) 長江", "d) ミシシッピ川"},
            {"a) サハラ砂漠", "b) ゴビ砂漠", "c) カラハリ砂漠", "d) アラビア砂漠"},
            {"a) マリアナ海溝", "b) トンガ海溝", "c) フィリピン海溝", "d) 日本海溝"}
    };
    private final int[] correctAnswers = {1, 2, 2, 3, 0, 2, 2, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0}; // 各質問の正解インデックス
    private final JLabel questionLabel;
    private final JButton[] optionButtons;
    private int currentQuestionIndex = 0;
    private int score = 0;

    public IBUKI() {
        // ウィンドウ設定
        setTitle("クイズゲーム");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400); // Adjusted width to be wider
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

    public static void main(String[] args) {
        // GUIを安全に初期化
        SwingUtilities.invokeLater(IBUKI::new);
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
        JOptionPane.showMessageDialog(this, "クイズ終了！スコア: " + questions.length + "点中" + score + "点");
        dispose();
    }
}
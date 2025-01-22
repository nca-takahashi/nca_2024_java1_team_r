package SimpleSwingProgram;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

class IBUKI extends JFrame {
    // 質問と正解インデックスを保持するクラス
    static class Question {
        String questionText;
        String[] options;
        int correctAnswerIndex;

        public Question(String questionText, String[] options, int correctAnswerIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }
    }

    private final ArrayList<Question> questionList = new ArrayList<>();
    private final JLabel questionLabel;
    private final JButton[] optionButtons;
    private int currentQuestionIndex = 0;
    private int score = 0;

    public IBUKI() {
        // 質問を作成してリストに追加
        questionList.add(new Question("関ヶ原の戦いが起きたのはいつ？", new String[]{"a) 1603年", "b) 1600年", "c) 1590年", "d)1560年"}, 1));
        questionList.add(new Question("明治維新はいつ起きた？", new String[]{"a) 1850年", "b) 1859年", "c) 1868年", "d) 1889年"}, 2));
        questionList.add(new Question("この中で同じ種類じゃない動物は？", new String[]{"a) 牛", "b) ヒツジ", "c) 熊", "d) 鯨"}, 2));
        questionList.add(new Question("億、兆、京、垓の次の単位は？", new String[]{"a) 正", "b) 溝", "c) 不可思議", "d) 秭"}, 3));
        questionList.add(new Question("ブラジルの首都は？", new String[]{"a) ブラジリア", "b) サンパウロ", "c) リオデジャネイロ", "d)ブエノスアイレス"}, 0));
        questionList.add(new Question("宮城県は何の収穫量が日本一？", new String[]{"a) 米", "b) リンゴ", "c) パプリカ", "d) キャベツ"}, 2));
        questionList.add(new Question("コンピューターで人間の脳を再現したものは何？", new String[]{"a) AI", "b) 機械学習", "c) ニューラルネットワーク", "d)コンピューティング"}, 2));
        questionList.add(new Question("Javaの創始者は誰？", new String[]{"a) ジェームズ・ゴスリン", "b) ビル・ゲイツ", "c) グイド・ヴァンロッサム", "d) ラリー・ペイジ"}, 0));
        questionList.add(new Question("ロシア革命はいつ起きた？", new String[]{"a) 1917年", "b) 1918年", "c) 1919年", "d) 1921年"}, 0));
        questionList.add(new Question("指輪物語を著書したのは誰？", new String[]{"a) J・Kローリング", "b)アーサー・コナン・ドイル" , "c) J・R・R・トールキン", "d) C・S・ルイス"}, 2));
        questionList.add(new Question("メキシコ革命はいつ起きた？", new String[]{"a) 1920年", "b) 1914年", "c) 1919年", "d) 1910年"}, 3));
        questionList.add(new Question("世界で一番広い砂漠は？", new String[]{"a) サハラ砂漠", "b) ゴビ砂漠", "c) 南極", "d) ネバダ砂漠"}, 2));
        questionList.add(new Question("金閣寺を著書したのは誰？", new String[]{"a) 三島由紀夫", "b) 夏目漱石", "c) 山崎豊子", "d) 茶川龍之介"}, 0));
        questionList.add(new Question("世界で一番大きい湖は？", new String[]{"a) アラル海", "b) カスピ海", "c) 五大湖", "d) 琵琶湖"}, 1));
        questionList.add(new Question("アメリカの首都は？", new String[]{"a) ニューヨーク", "b) ワシントンDC", "c) フィラデルフィア", "d) ロサンゼルス"}, 0));
        // 必要に応じて他の質問も追加

        // 質問をシャッフル
        Collections.shuffle(questionList);

        // ウィンドウ設定
        setTitle("クイズゲーム");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 質問表示ラベル
        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("MS Gothic", Font.BOLD, 18));
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
        if (currentQuestionIndex < questionList.size()) {
            Question currentQuestion = questionList.get(currentQuestionIndex);
            questionLabel.setText(currentQuestion.questionText);
            for (int i = 0; i < optionButtons.length; i++) {
                if (i < currentQuestion.options.length) {
                    optionButtons[i].setText(currentQuestion.options[i]);
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
        Question currentQuestion = questionList.get(currentQuestionIndex);
        if (selectedIndex == currentQuestion.correctAnswerIndex) {
            score++;
            JOptionPane.showMessageDialog(this, "正解です！");
        } else {
            JOptionPane.showMessageDialog(this, "不正解です！正解は " +
                    currentQuestion.options[currentQuestion.correctAnswerIndex]);
        }
        currentQuestionIndex++;
        loadQuestion();
    }

    private void endQuiz() {
        // 終了メッセージを表示
        JOptionPane.showMessageDialog(this, "クイズ終了！スコア: " + questionList.size() + "点中" + score + "点");
        dispose();
    }
}
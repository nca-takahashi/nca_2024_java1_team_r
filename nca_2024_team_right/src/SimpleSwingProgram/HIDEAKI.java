package SimpleSwingProgram;

import java.util.Random;

public class HIDEAKI {

    public class DiceGame {

        public static void main(String[] args) {
            // 2つのさいころを振る
            int diceRoll1 = rollDice();
            int diceRoll2 = rollDice();

            // プレイヤーの現在位置を0に設定
            int position = 0;

            // 2つのさいころの目の合計だけ進む
            position += diceRoll1 + diceRoll2;

            // 結果を表示
            System.out.println("1つ目のさいころの目は: " + diceRoll1);
            System.out.println("2つ目のさいころの目は: " + diceRoll2);
            System.out.println("さいころの合計の目は: " + (diceRoll1 + diceRoll2));
            System.out.println("現在位置は: " + position);
        }

        // さいころを振るメソッド (1から6までのランダムな数を返す)
        public static int rollDice() {
            Random random = new Random();
            return random.nextInt(6) + 1;  // 1から6までのランダムな数を生成
        }
    }


}

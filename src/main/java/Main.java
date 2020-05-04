import domain.hand.PokerHand;
import domain.hand.PokerHandJudge;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // 5枚のカード入力して役名を返す
        //Input : poker S-3 S-4 H-3 H-4 D-5

        System.out.println(
                PokerHandJudge.judge(Arrays.asList("S-3 S-4 H-3 H-4 D-5".split(" ")))
                //output: Two Pair
        );
    }
}

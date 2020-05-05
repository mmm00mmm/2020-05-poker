import domain.card.CardList;
import domain.hand.PokerHandJudge;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // 5枚のカード入力して役名を返す
        System.out.println(
                PokerHandJudge.judge(
                        CardList.create(Arrays.asList("S-3 S-4 H-3 H-4 D-5".split(" ")))
                )
        );
    }
}

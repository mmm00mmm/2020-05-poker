import domain.card.Cards;
import domain.hand.PokerHandJudge;

public class Main {
    public static void main(String[] args) {
        // 5枚のカード入力して役名を返す
        System.out.println(
                PokerHandJudge.judge(
                        Cards.create("S-3 S-6 H-3 H-4 D-5")
                )
        );
    }
}

package domain.card;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class TrumpNumber {
    private final int number;

    static TrumpNumber create(String s) {
        switch (s) {
            case "J":
                return new TrumpNumber(11);
            case "Q":
                return new TrumpNumber(12);
            case "K":
                return new TrumpNumber(13);
            case "A":
                return new TrumpNumber(14);
            default:
                return new TrumpNumber(Integer.parseInt(s)); //TODO:数値範囲チェック
        }
    }
}

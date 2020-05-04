package domain.card;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TrumpNumber {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    private final String name;
    private final int number;

    public static TrumpNumber getTrumpNumber(final String name) {
        TrumpNumber[] trumpNumbers = TrumpNumber.values();
        for (TrumpNumber trumpNumber : trumpNumbers) {
            if (trumpNumber.name.equals(name)) {
                return trumpNumber;
            }
        }
        throw new RuntimeException("カードの数字が不正");
    }
}

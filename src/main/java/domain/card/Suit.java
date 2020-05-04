package domain.card;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Suit {
    Spade("S", 1),
    Heart("H", 2),
    Diamond("D", 3),
    Club("C", 4);

    private final String name;
    private final int number;

    public static Suit getSuit(final String name) {
        Suit[] suits = Suit.values();
        for (Suit suit : suits) {
            if (suit.name.equals(name)) {
                return suit;
            }
        }
        throw new RuntimeException("スートが不正");
    }
}

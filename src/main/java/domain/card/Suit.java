package domain.card;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Suit {
    Spade("S"),
    Heart("H"),
    Diamond("D"),
    Club("C");

    private final String name;

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

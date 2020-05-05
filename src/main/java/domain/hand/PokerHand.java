package domain.hand;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PokerHand {
    HIGH_CARDS("High CardList"),
    ONE_PAIR("One Pair"),
    TWO_PAIR("Two Pair"),
    THREE_OF_A_KIND("Three Of A Kind"),
    STRAIGHT("Straight"),
    FLUSH("Flush"),
    FULL_HOUSE("Full House"),
    FOUR_OF_A_KIND("Four Of A Kind"),
    STRAIGHT_FLUSH("Straight Flush"),
    ROYAL_STRAIGHT_FLUSH("Royal Straight Flush");

    @Getter
    private final String name;
}

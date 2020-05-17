package domain.card;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Getter
public class Card {
    private final Suit suit;
    private final TrumpNumber trumpNumber;

    static Card create(String s) {
        List<String> splitCard = Arrays.asList(s.split("-"));
        return new Card(
                Suit.getSuit(splitCard.get(0)),
                TrumpNumber.create(splitCard.get(1))
        );
    }
}

import domain.card.Card;
import domain.card.Suit;
import domain.card.TrumpNumber;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Convert {
    static List<Card> convert(String strings) {
        if (Arrays.stream(strings.split(" ")).count() == 5) {
            return Arrays.stream(strings.split(" "))
                    .map(Convert::createCard)
                    .sorted(Comparator.comparing(card -> card.getTrumpNumber().getNumber()))
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("カードが５枚ではありません");
        }
    }

    private static Card createCard(String s) {
        List<String> strings = Arrays.asList(s.split("-"));
        return new Card(
                Suit.getSuit(strings.get(0)),
                TrumpNumber.getTrumpNumber(strings.get(1))
        );
    }
}

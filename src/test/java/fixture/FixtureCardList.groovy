package fixture

import domain.card.Card
import domain.card.CardList

class FixtureCardList {
    List<Card> onePair = create("S-3 S-4 H-3 H-6 D-5")
    List<Card> twoPair = create("S-3 S-4 H-3 H-4 D-5")
    List<Card> threeOfAKind = create("S-3 S-4 H-3 H-4 D-3")
    List<Card> straight = create("S-4 S-2 H-3 H-5 D-6")

    private static List<Card> create(String s) {
        return CardList.create(Arrays.asList(s.split(" ")))
    }
}
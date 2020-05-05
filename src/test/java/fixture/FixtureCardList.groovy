package fixture

import domain.card.Card
import domain.card.CardList

class FixtureCardList {
    List<Card> onePair = create("S-3 S-4 H-3 H-6 D-5")
    List<Card> twoPair = create("S-3 S-4 H-3 H-4 D-5")
    List<Card> threeOfAKind = create("S-3 S-2 H-3 H-4 D-3")
    List<Card> straight = create("S-4 S-2 H-3 H-5 D-6")
    List<Card> flush = create("S-A S-2 S-3 S-5 S-Q")
    List<Card> fullHouse = create("S-A D-A C-A S-5 H-5")

    private static List<Card> create(String s) {
        return CardList.create(Arrays.asList(s.split(" ")))
    }
}
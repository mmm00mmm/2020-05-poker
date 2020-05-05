package domain.card

import spock.lang.Specification

class CardListTest extends Specification {
    def create() {
        expect:
        CardList.create(["S-3", "C-4"]) == [new Card(Suit.Spade, TrumpNumber.THREE), new Card(Suit.Club, TrumpNumber.FOUR)]
    }
}

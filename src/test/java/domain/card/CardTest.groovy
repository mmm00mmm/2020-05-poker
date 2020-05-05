package domain.card

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class CardTest extends Specification {
    def create() {
        expect:
        Card.create("S-3") == new Card(Suit.Spade, TrumpNumber.THREE)
    }

    def failure() {
        when:
        Card.create(input)

        then:
        def e = thrown(RuntimeException)
        e.message == expected

        where:
        input  || expected
        "S,13" || "スートが不正"
        "A-5"  || "スートが不正"
        "S-13" || "カードの数字が不正"
    }
}

package domain.card

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class SuitTest extends Specification {
    def getSuit() {
        expect:
        Suit.getSuit(name) == expected

        where:
        name || expected
        "S"  || Suit.Spade
        "H"  || Suit.Heart
        "D"  || Suit.Diamond
        "C"  || Suit.Club
    }

    def failure() {
        when:
        Suit.getSuit("A")

        then:
        def e = thrown(RuntimeException)
        e.message == "スートが不正"
    }
}

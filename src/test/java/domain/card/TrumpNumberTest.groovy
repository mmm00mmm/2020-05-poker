package domain.card

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class TrumpNumberTest extends Specification {
    def getTrumpNumber() {
        expect:
        TrumpNumber.getTrumpNumber(name) == expected

        where:
        name || expected
        "2"  || TrumpNumber.TWO
        "3"  || TrumpNumber.THREE
        "4"  || TrumpNumber.FOUR
        "5"  || TrumpNumber.FIVE
        "6"  || TrumpNumber.SIX
        "7"  || TrumpNumber.SEVEN
        "8"  || TrumpNumber.EIGHT
        "9"  || TrumpNumber.NINE
        "10" || TrumpNumber.TEN
        "J"  || TrumpNumber.JACK
        "Q"  || TrumpNumber.QUEEN
        "K"  || TrumpNumber.KING
        "A"  || TrumpNumber.ACE
    }

    def failure() {
        when:
        TrumpNumber.getTrumpNumber("11")

        then:
        def e = thrown(RuntimeException)
        e.message == "カードの数字が不正"
    }
}

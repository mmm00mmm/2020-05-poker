package domain.card

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class TrumpNumberTest extends Specification {
    def getTrumpNumber() {
        expect:
        TrumpNumber.create(name) == new TrumpNumber(expected)

        where:
        name || expected
        "2"  || 2
        "3"  || 3
        "4"  || 4
        "5"  || 5
        "6"  || 6
        "7"  || 7
        "8"  || 8
        "9"  || 9
        "10" || 10
        "J"  || 11
        "Q"  || 12
        "K"  || 13
        "A"  || 14
    }
}

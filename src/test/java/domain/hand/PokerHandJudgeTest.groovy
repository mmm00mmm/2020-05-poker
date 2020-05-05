package domain.hand

import fixture.FixtureCardList
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class PokerHandJudgeTest extends Specification {
    def "judge() #testName"() {
        expect:
        PokerHandJudge.judge(cards) == expected

        where:
        testName             | cards                                    || expected
        "onePair"            | new FixtureCardList().onePair            || PokerHand.ONE_PAIR.name
        "twoPair"            | new FixtureCardList().twoPair            || PokerHand.TWO_PAIR.name
        "threeOfAKind"       | new FixtureCardList().threeOfAKind       || PokerHand.THREE_OF_A_KIND.name
        "straight"           | new FixtureCardList().straight           || PokerHand.STRAIGHT.name
        "flush"              | new FixtureCardList().flush              || PokerHand.FLUSH.name
        "fullHouse"          | new FixtureCardList().fullHouse          || PokerHand.FULL_HOUSE.name
        "fourOfAKind"        | new FixtureCardList().fourOfAKind        || PokerHand.FOUR_OF_A_KIND.name
        "straightFlush"      | new FixtureCardList().straightFlush      || PokerHand.STRAIGHT_FLUSH.name
        "royalStraightFlush" | new FixtureCardList().royalStraightFlush || PokerHand.ROYAL_STRAIGHT_FLUSH.name
    }
}

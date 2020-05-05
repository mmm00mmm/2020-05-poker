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
        testName  | cards                         || expected
        "onePair" | new FixtureCardList().onePair || PokerHand.ONE_PAIR.name
        "twoPair" | new FixtureCardList().twoPair || PokerHand.TWO_PAIR.name
    }
}

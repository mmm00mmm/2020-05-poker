package domain.hand

import domain.card.CardList
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class PokerHandJudgeTest extends Specification {
    def judge() {
        expect:
        PokerHandJudge.judge(
                CardList.create(Arrays.asList(cards.split(" ")))
        ) == expected

        where:
        cards                         || expected
        "H-Q D-A S-2 D-3 D-K"         || PokerHand.HIGH_CARDS.name
        "D-2 C-Q D-J S-A D-10"        || PokerHand.HIGH_CARDS.name
        "S-3 S-4 H-3 H-6 D-5"         || PokerHand.ONE_PAIR.name
        "S-3 S-4 H-3 H-4 D-5"         || PokerHand.TWO_PAIR.name
        "S-3 S-2 H-3 H-4 D-3"         || PokerHand.THREE_OF_A_KIND.name
        "S-4 S-2 H-3 H-5 D-6"         || PokerHand.STRAIGHT.name
        "S-A S-2 S-3 S-5 S-Q"         || PokerHand.FLUSH.name
        "S-A D-A C-A S-5 H-5"         || PokerHand.FULL_HOUSE.name
        "H-2 S-2 D-2 C-2 S-3"         || PokerHand.FOUR_OF_A_KIND.name
        "S-6 S-7 S-8 S-9 S-10"        || PokerHand.STRAIGHT_FLUSH.name
        "D-A D-K D-Q D-J D-10"        || PokerHand.ROYAL_STRAIGHT_FLUSH.name
    }

    def isOnePair() {
        expect:
        PokerHandJudge.isOnePair(
                CardList.create(Arrays.asList(cards.split(" ")))
        ) == expected

        where:
        cards                 || expected
        "S-3 S-4 H-3 H-6 D-5" || true
        "S-3 S-4 H-3 H-4 D-5" || false
        "S-3 D-3 H-3 H-4 D-5" || false
    }

    def isTwoPair() {
        expect:
        PokerHandJudge.isTwoPair(
                CardList.create(Arrays.asList(cards.split(" ")))
        ) == expected

        where:
        cards                 || expected
        "S-3 S-4 H-3 H-4 D-5" || true
        "S-3 S-4 H-3 H-4 D-4" || false
    }

    def isThreeOfAKind() {
        expect:
        PokerHandJudge.isThreeOfAKind(
                CardList.create(Arrays.asList(cards.split(" ")))
        ) == expected

        where:
        cards                 || expected
        "S-3 S-4 H-3 H-4 D-4" || true
        "S-3 S-4 H-4 C-4 D-4" || false
    }

    def isStraight() {
        expect:
        PokerHandJudge.isStraight(
                CardList.create(Arrays.asList(cards.split(" ")))
        ) == expected

        where:
        cards                 || expected
        "S-2 S-3 H-4 C-5 D-6" || true
        "S-2 S-3 H-4 C-5 D-A" || false //Aは今回14として扱うため
        "S-2 S-3 H-4 C-5 D-5" || false
    }

    def isFlush() {
        expect:
        PokerHandJudge.isFlush(
                CardList.create(Arrays.asList(cards.split(" ")))
        ) == expected

        where:
        cards                 || expected
        "S-2 S-3 S-4 S-5 S-6" || true
        "S-2 S-3 D-4 S-5 S-6" || false
    }

    def isFullHouse() {
        expect:
        PokerHandJudge.isFullHouse(
                CardList.create(Arrays.asList(cards.split(" ")))
        ) == expected

        where:
        cards                 || expected
        "S-A D-A C-A S-5 H-5" || true
        "S-A D-A C-A H-A H-5" || false
    }

    def isFourOfAKind() {
        expect:
        PokerHandJudge.isFourOfAKind(
                CardList.create(Arrays.asList(cards.split(" ")))
        ) == expected

        where:
        cards                 || expected
        "S-A D-A C-A H-A H-5" || true
        "S-A D-5 C-A H-A H-5" || false
    }

    def isStraightFlush() {
        expect:
        PokerHandJudge.isStraightFlush(
                CardList.create(Arrays.asList(cards.split(" ")))
        ) == expected

        where:
        cards                  || expected
        "S-7 S-8 S-9 S-10 S-J" || true
        "D-7 S-8 S-9 S-10 S-J" || false
    }

    def isRoyalStraightFlush() {
        expect:
        PokerHandJudge.isRoyalStraightFlush(
                CardList.create(Arrays.asList(cards.split(" ")))
        ) == expected

        where:
        cards                  || expected
        "S-K S-Q S-10 S-J S-A" || true
        "S-K S-Q S-10 D-J S-A" || false
    }
}

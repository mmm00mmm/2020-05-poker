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
        "C-8 C-Q C-Q C-6 C-7"         || PokerHand.FLUSH.name              //FLUSHでONE_PAIR（トランプとしてはありえない）
        "C-7 C-Q C-Q C-6 C-7"         || PokerHand.FLUSH.name              //FLUSHでTWO_PAIR（トランプとしてはありえない）
        "C-Q C-Q C-Q C-6 C-7"         || PokerHand.FLUSH.name              //FLUSHでTHREE_OF_A_KIND（トランプとしてはありえない）
        "S-A D-A C-A S-5 H-5"         || PokerHand.FULL_HOUSE.name
        "S-A S-A S-A S-5 S-5"         || PokerHand.FULL_HOUSE.name         //FLUSHでFULL_HOUSE（トランプとしてはありえない）
        "H-2 S-2 D-2 C-2 S-3"         || PokerHand.FOUR_OF_A_KIND.name
        "S-4 S-4 S-4 S-4 S-8"         || PokerHand.FOUR_OF_A_KIND.name     //FLUSHでFOUR_OF_A_KIND（トランプとしてはありえない）
        "S-4 S-4 S-4 S-4 D-10 D-10"   || PokerHand.FOUR_OF_A_KIND.name     //入力が多いパタン
        "D-2 H-2 S-K C-A D-3 S-3 C-3" || PokerHand.FULL_HOUSE.name         //入力が多いパタン
        "D-2 H-2 S-A C-A D-3 S-3 C-3" || PokerHand.THREE_OF_A_KIND.name    //入力が多いパタン
        "S-6 S-7 S-8 S-9 S-10 S-J"    || PokerHand.STRAIGHT.name           //入力が多いパタン（同じスートが６枚なのでフラッシュにはならない）
        "S-6 S-7 S-8 S-9 S-10"        || PokerHand.STRAIGHT_FLUSH.name
        "D-A D-K D-Q D-J D-10"        || PokerHand.ROYAL_STRAIGHT_FLUSH.name
    }
}

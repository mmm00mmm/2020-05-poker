package domain.hand;

import domain.card.Card;
import domain.card.Cards;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PokerHandJudge {
    public static PokerHand judge(Cards cards) {
        if (isRoyalStraightFlush(cards)) {
            return PokerHand.ROYAL_STRAIGHT_FLUSH;
        } else if (isStraightFlush(cards)) {
            return PokerHand.STRAIGHT_FLUSH;
        } else if (isFourOfAKind(cards)) {
            return PokerHand.FOUR_OF_A_KIND;
        } else if (isFullHouse(cards)) {
            return PokerHand.FULL_HOUSE;
        } else if (isFlush(cards)) {
            return PokerHand.FLUSH;
        } else if (isStraight(cards)) {
            return PokerHand.STRAIGHT;
        } else if (isThreeOfAKind(cards)) {
            return PokerHand.THREE_OF_A_KIND;
        } else if (isTwoPair(cards)) {
            return PokerHand.TWO_PAIR;
        } else if (isOnePair(cards)) {
            return PokerHand.ONE_PAIR;
        } else {
            return PokerHand.HIGH_CARDS;
        }
    }

    static boolean isOnePair(Cards cards) {
        //同じ番号が２枚１組
        return groupingByTrumpNumber(cards, 2) == 1;
    }

    static boolean isTwoPair(Cards cards) {
        //同じ番号２枚２組
        return groupingByTrumpNumber(cards, 2) == 2;
    }

    static boolean isThreeOfAKind(Cards cards) {
        //同じ番号３枚
        return groupingByTrumpNumber(cards, 3) == 1;
    }

    static boolean isStraight(Cards cards) {
        //連続した数字の５枚
        int minNum = cards.stream().findFirst().get().getTrumpNumber().getNumber();
        return IntStream.range(minNum, minNum + 5)
                .boxed()
                .collect(Collectors.toList())
                .equals(
                        cards.stream()
                                .map(card -> card.getTrumpNumber().getNumber())
                                .collect(Collectors.toList())
                );
    }

    static boolean isFlush(Cards cards) {
        //同じスートの５枚
        return cards.stream()
                .map(Card::getSuit)
                .distinct()
                .count() == 1;
    }

    static boolean isFullHouse(Cards cards) {
        //同じ番号３枚と同じ番号２枚
        return isOnePair(cards) && isThreeOfAKind(cards);
    }

    static boolean isFourOfAKind(Cards cards) {
        //同じ番号４枚とその他１枚
        return groupingByTrumpNumber(cards, 4) == 1;
    }

    static boolean isStraightFlush(Cards cards) {
        //連続した数字で同じスート
        return isStraight(cards) && isFlush(cards);
    }

    static boolean isRoyalStraightFlush(Cards cards) {
        //同じスートのA-K-Q-J-10
        //フラッシュかつストレートかつ最初が１０
        return isFlush(cards) && isStraight(cards) && cards.stream().findFirst().get().getTrumpNumber().getNumber() == 10;
    }

    private static long groupingByTrumpNumber(Cards cards, int n) {
        return cards.stream()
                .collect(
                        Collectors.groupingBy(Card::getTrumpNumber, Collectors.counting())
                )
                .entrySet().stream()
                .filter(c -> c.getValue() == n)
                .count();
    }
}

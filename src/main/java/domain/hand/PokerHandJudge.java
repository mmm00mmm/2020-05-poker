package domain.hand;

import domain.card.Card;
import domain.card.TrumpNumber;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerHandJudge {
    public static String judge(List<Card> cards) {
        if (isRoyalStraightFlush(cards)) {
            return PokerHand.ROYAL_STRAIGHT_FLUSH.getName();
        } else if (isStraightFlush(cards)) {
            return PokerHand.STRAIGHT_FLUSH.getName();
        } else if (isFourOfAKind(cards)) {
            return PokerHand.FOUR_OF_A_KIND.getName();
        } else if (isFullHouse(cards)) {
            return PokerHand.FULL_HOUSE.getName();
        } else if (isFlush(cards)) {
            return PokerHand.FLUSH.getName();
        } else if (isStraight(cards)) {
            return PokerHand.STRAIGHT.getName();
        } else if (isThreeOfAKind(cards)) {
            return PokerHand.THREE_OF_A_KIND.getName();
        } else if (isTwoPair(cards)) {
            return PokerHand.TWO_PAIR.getName();
        } else if (isOnePair(cards)) {
            return PokerHand.ONE_PAIR.getName();
        } else {
            return PokerHand.HIGH_CARDS.getName();
        }
    }

    private static Boolean isOnePair(List<Card> cards) {
        //同じ番号が２枚１組
        return groupByTrumpNumber(cards)
                .entrySet().stream()
                .filter(card -> card.getValue() == 2)
                .count() == 1;
    }

    private static Boolean isTwoPair(List<Card> cards) {
        //同じ番号２枚２組
        return groupByTrumpNumber(cards)
                .entrySet().stream()
                .filter(c -> c.getValue() == 2)
                .count() == 2;
    }

    private static Boolean isThreeOfAKind(List<Card> cards) {
        //同じ番号３枚
        return groupByTrumpNumber(cards)
                .entrySet().stream()
                .filter(c -> c.getValue() == 3)
                .count() == 1;
    }

    private static Boolean isStraight(List<Card> cards) {
        //連続した数字の５枚
        List<Card> sortCards = sortByTrumpNumber(cards);

        for (int i = 0; i < sortCards.size(); i++) {
            if (sortCards.get(i).getTrumpNumber().getNumber() != (sortCards.get(0).getTrumpNumber().getNumber() + i)) {
                return false;
            }
        }
        return true;
    }

    private static Boolean isFlush(List<Card> cards) {
        //同じスートの５枚
        return cards.stream()
                .collect(
                        Collectors.groupingBy(Card::getSuit, Collectors.counting())
                )
                .entrySet().stream()
                .filter(c -> c.getValue() == 5)
                .count() == 1;
    }

    private static Boolean isFullHouse(List<Card> cards) {
        //同じ番号３枚と同じ番号２枚
        return isOnePair(cards) && isThreeOfAKind(cards);
    }

    private static Boolean isFourOfAKind(List<Card> cards) {
        //同じ番号４枚とその他１枚
        return groupByTrumpNumber(cards)
                .entrySet().stream()
                .filter(c -> c.getValue() == 4)
                .count() == 1;
    }

    private static Boolean isStraightFlush(List<Card> cards) {
        //連続した数字で同じスート
        return isStraight(cards) && isFlush(cards);
    }

    private static Boolean isRoyalStraightFlush(List<Card> cards) {
        //同じスートのA-K-Q-J-10
        if (isFlush(cards)) {
            List<Card> sortCards = sortByTrumpNumber(cards);

            List<Integer> royalStraight = Arrays.asList(10, 11, 12, 13, 14);

            for (int i = 0; i < sortCards.size(); i++) {
                if (sortCards.get(i).getTrumpNumber().getNumber() != royalStraight.get(i)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private static List<Card> sortByTrumpNumber(List<Card> cards) {
        return cards.stream()
                .sorted(Comparator.comparing(card -> card.getTrumpNumber().getNumber()))
                .collect(Collectors.toList());
    }

    private static Map<TrumpNumber, Long> groupByTrumpNumber(List<Card> cards) {
        return cards.stream()
                .collect(
                        Collectors.groupingBy(Card::getTrumpNumber, Collectors.counting())
                );
    }
}

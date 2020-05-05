package domain.hand;

import domain.card.Card;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PokerHandJudge {
    public static String judge(List<Card> cards) {
        if (isFullHouse(cards)) {
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
            return null; //TODO
        }
    }

    private static Boolean isOnePair(List<Card> cards) {
        //同じ番号が２枚１組
        return cards.stream()
                .collect(
                        Collectors.groupingBy(Card::getTrumpNumber, Collectors.counting())
                )
                .entrySet().stream()
                .filter(c -> c.getValue() == 2)
                .count()
                == 1;
    }

    private static Boolean isTwoPair(List<Card> cards) {
        //同じ番号２枚２組
        return cards.stream()
                .collect(
                        Collectors.groupingBy(Card::getTrumpNumber, Collectors.counting())
                )
                .entrySet().stream()
                .filter(c -> c.getValue() == 2)
                .count()
                == 2;
    }

    private static Boolean isThreeOfAKind(List<Card> cards) {
        //同じ番号３枚
        return cards.stream()
                .collect(
                        Collectors.groupingBy(Card::getTrumpNumber, Collectors.counting())
                )
                .entrySet().stream()
                .filter(c -> c.getValue() == 3)
                .count()
                == 1;
    }

    private static Boolean isStraight(List<Card> cards) {
        //連続した数字の５枚
        List<Card> sortCards = cards.stream()
                .sorted(Comparator.comparing(card -> card.getTrumpNumber().getNumber()))
                .collect(Collectors.toList());

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
        return null; //TODO
    }

    private static Boolean isStraightFlush(List<Card> cards) {
        //連続した数字で同じスート
        return null; //TODO
    }

    private static Boolean isRoyalStraightFlush(List<Card> cards) {
        //同じスートのA-K-Q-J-10
        return null; //TODO
    }
}

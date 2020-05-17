package domain.card;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//↓newをprivateにできるので、create()以外でnewできない
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Cards {
    private final List<Card> list;

    public static Cards create(String string) {
        if (Arrays.stream(string.split(" ")).count() == 5) {
            return new Cards(
                    Arrays.stream(string.split(" "))
                            .map(Card::create)
                            .sorted(Comparator.comparing(card -> card.getTrumpNumber().getNumber()))
                            .collect(Collectors.toList())
            );
        } else {
            throw new RuntimeException("カードが５枚ではありません");
        }
    }

    public Stream<Card> stream() {
        return list.stream();
    }
}

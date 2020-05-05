package domain.card;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CardList {
    public static List<Card> create(List<String> strings) {
        return strings.stream()
                .map(Card::create)
                .collect(Collectors.toList());
    }
}

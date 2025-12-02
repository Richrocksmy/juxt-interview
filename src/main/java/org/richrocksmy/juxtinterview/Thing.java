package org.richrocksmy.juxtinterview;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class Thing {

    public enum Currency {
        USD,
        GBP,
        EUR
    }

    public record Trade(List<Leg> legs) {}

    public record Leg(double value, Currency currency) {}

    public Map<Currency, Double> applyTrades(final List<Trade> trades) {
        Map<Currency, List<Leg>> legs = trades.stream()
                .flatMap(it -> it.legs().stream())
                .collect(Collectors.groupingBy(Leg::currency));

        return legs.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        it -> it.getValue().stream().map(Leg::value).mapToDouble(Double::doubleValue).sum()));
    }
}

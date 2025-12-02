package org.richrocksmy.juxtinterview;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.richrocksmy.juxtinterview.Thing.Currency.EUR;
import static org.richrocksmy.juxtinterview.Thing.Currency.GBP;
import static org.richrocksmy.juxtinterview.Thing.Currency.USD;

class ThingTest {

    @Test
    @DisplayName("Test that trades are applied correctly")
    void thingShouldDoTheThing() {
        // Given
        Thing thing = new Thing();
        Map<Thing.Currency, Double> expectedBalances = Map.of(
                USD, 1300.0D,
                GBP, 230.0D,
                EUR, -1500.0D
        );

        List<Thing.Trade> trades = List.of(
                createTrade(1000, USD, -770, GBP),
                createTrade(1000, GBP, -500, EUR),
                createTrade(-1000, EUR, 300, USD)
        );

        //  When
        Map<Thing.Currency, Double> balanceChanges = thing.applyTrades(trades);

        // Then
        assertThat(balanceChanges.get(USD)).isEqualTo(expectedBalances.get(USD));
    }

    private Thing.Trade createTrade(final double leg1Value, Thing.Currency leg1Currency,
                                    final double leg2Value, Thing.Currency leg2Currency) {
        Thing.Leg leg1 = new Thing.Leg(leg1Value, leg1Currency);
        Thing.Leg leg2 = new Thing.Leg(leg2Value, leg2Currency);

        return new Thing.Trade(List.of(leg1, leg2));
    }
}
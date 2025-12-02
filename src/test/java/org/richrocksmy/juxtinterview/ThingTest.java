package org.richrocksmy.juxtinterview;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ThingTest {

    @DisplayName("Test that thing does the thing")
    @MethodSource("getTestData")
    @ParameterizedTest(name = "{0}")
    void thingShouldDoTheThing(final String inputData, final int mockResult, final int result) {
        // Given
        SomeOtherThing someOtherThing = mock(SomeOtherThing.class);
        when(someOtherThing.doSomeOtherThing(inputData)).thenReturn(mockResult);
        Thing thing = new Thing(someOtherThing);

        //  When / Then
        assertThat(thing.doTheThing(inputData)).isEqualTo(result);
    }

    private static Stream<Arguments> getTestData() {
        return Stream.of(
                arguments(Named.of("Should return 3 when input is 1", "1"), 2, 3),
                arguments(Named.of("Should return 4 when input is 2", "2"), 3, 4),
                arguments(Named.of("Should return 5 when input is 3", "3"), 4, 5),
                arguments(Named.of("Should return 6 when input is 4", "4"), 5, 6)
        );
    }
}
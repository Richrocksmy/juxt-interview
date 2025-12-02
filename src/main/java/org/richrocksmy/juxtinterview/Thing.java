package org.richrocksmy.juxtinterview;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class Thing {

    private final SomeOtherThing someOtherThing;

    public int doTheThing(final String someData) {
        return someOtherThing.doSomeOtherThing(someData) + 1;
    }

}

package com.ead.authuser.comuns.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestUtils {

    public static final EasyRandom EASY_RANDOM =
            new EasyRandom(new EasyRandomParameters().stringLengthRange(1, 5).collectionSizeRange(1, 5));
}

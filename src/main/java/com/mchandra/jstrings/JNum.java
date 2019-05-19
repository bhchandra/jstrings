package com.mchandra.jstrings;

import lombok.Value;

@Value(staticConstructor = "of")
public class JNum implements Json {
    private final Double num;

    @Override
    public String string() {
        return num.toString();
    }
}

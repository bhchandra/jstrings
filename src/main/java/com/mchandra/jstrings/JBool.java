package com.mchandra.jstrings;


import lombok.Value;

@Value(staticConstructor = "of")
public class JBool implements Json {
    private final boolean b;

    @Override
    public String string() {
        return Boolean.toString(b);
    }
}
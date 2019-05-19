package com.mchandra.jstrings;


import lombok.Value;

@Value(staticConstructor = "of")
public class JStr implements Json {
    private final String str;

    @Override
    public String string() {
        return '\"' + str + '\"';
    }
}
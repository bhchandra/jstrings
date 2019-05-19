package com.mchandra.jstrings;

import lombok.Value;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Value(staticConstructor = "of")
public class JList implements Json {
    private final List<Json> elems;

    public static JList of(Json... jsons) {
        return JList.of(Arrays.asList(jsons));
    }

    public static JList of(String... strs) {
        return JList.of(
                Arrays.stream(strs).map(JStr::of).collect(toList())
        );
    }

    @Override
    public String string() {
        return elems.stream()
                .map(Json::string)
                .collect(joining(", ", "[", "]"));
    }
}